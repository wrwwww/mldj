package org.ml.mldj.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SnowflakeOrderIdGenerator implements OrderIdGenerator {

    // ==============================Fields===========================================
    /** 开始时间截 (2024-01-01) */
    private final long twepoch = 1704038400000L; // 2024-01-01 00:00:00

    /** 机器id所占的位数 */
    private final long machineIdBits = 5L;

    /** 数据标识id所占的位数 */
    private final long dataCenterIdBits = 5L;

    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxMachineId = -1L ^ (-1L << machineIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /** 机器ID向左移12位 */
    private final long machineIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private final long dataCenterIdShift = sequenceBits + machineIdBits;

    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + machineIdBits + dataCenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private long machineId;

    /** 数据中心ID(0~31) */
    private long dataCenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    // 城市代码映射
    private final Map<String, String> cityCodeMap = new ConcurrentHashMap<>();

    // 随机数生成器
    private final Random random = new Random();

    // ==============================Constructors=====================================
    public SnowflakeOrderIdGenerator(OrderIdConfig config) {
        if (config.getDataCenterId() > maxDataCenterId || config.getDataCenterId() < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        if (config.getMachineId() > maxMachineId || config.getMachineId() < 0) {
            throw new IllegalArgumentException(
                    String.format("machineId can't be greater than %d or less than 0", maxMachineId));
        }
        this.dataCenterId = config.getDataCenterId();
        this.machineId = config.getMachineId();

        // 初始化城市代码映射
        initCityCodes();
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) // 时间戳部分
                | (dataCenterId << dataCenterIdShift)       // 数据中心部分
                | (machineId << machineIdShift)             // 机器标识部分
                | sequence;                                 // 序列号部分
    }

    /**
     * 生成代驾订单ID
     */
    @Override
    public String generate(String cityCode, Long userId) {
        // 1. 生成雪花ID
        long snowflakeId = nextId();

        // 2. 获取城市代码（3位）
        String formattedCityCode = formatCityCode(cityCode);

        // 3. 获取当前时间（12位：yyyyMMddHHmm）
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

        // 4. 机器ID（3位）
        String machine = String.format("%03d", machineId);

        // 5. 序列号（从雪花ID中提取，4位）
        String sequence = String.format("%04d", (snowflakeId & sequenceMask) % 10000);

        // 6. 随机数（3位）
        String randomStr = String.format("%03d", random.nextInt(1000));

        // 7. 用户ID后缀（可选，4位）
        String userSuffix = "";
        if (userId != null) {
            userSuffix = String.format("%04d", userId % 10000);
        }

        // 拼接：业务前缀(2) + 城市(3) + 时间(12) + 机器(3) + 序列(4) + 随机(3) + 用户(4)
        return "DD" + formattedCityCode + timestamp + machine + sequence + randomStr + userSuffix;
    }

    /**
     * 批量生成
     */
    @Override
    public List<String> batchGenerate(String cityCode, int count) {
        List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(generate(cityCode, null));
        }
        return result;
    }

    /**
     * 解析订单ID
     */
//    @Override
//    public OrderIdInfo parse(String orderId) {
//        if (orderId == null || orderId.length() < 31) {
//            throw new IllegalArgumentException("无效的订单ID格式");
//        }
//
//        try {
//            OrderIdInfo info = new OrderIdInfo();
//            info.setOrderId(orderId);
//
//            // 解析各部分
//            info.setBizPrefix(orderId.substring(0, 2));                      // 业务前缀
//            info.setCityCode(orderId.substring(2, 5));                       // 城市代码
//            info.setTimestamp(orderId.substring(5, 17));                     // 时间戳
//            info.setMachineId(Integer.parseInt(orderId.substring(17, 20)));  // 机器ID
//            info.setSequence(Integer.parseInt(orderId.substring(20, 24)));   // 序列号
//            info.setRandomNum(Integer.parseInt(orderId.substring(24, 27)));  // 随机数
//
//            if (orderId.length() > 27) {
//                info.setUserIdSuffix(orderId.substring(27));                 // 用户ID后缀
//            }
//
//            // 解析时间
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//            LocalDateTime orderTime = LocalDateTime.parse(info.getTimestamp(), formatter);
//            info.setOrderTime(orderTime);
//
//            return info;
//
//        } catch (Exception e) {
//            throw new IllegalArgumentException("订单ID解析失败: " + orderId, e);
//        }
//    }

    /**
     * 格式化城市代码
     */
    private String formatCityCode(String cityCode) {
        if (cityCode == null || cityCode.length() != 3) {
            // 默认使用000
            return "000";
        }
        return cityCode;
    }

    /**
     * 初始化城市代码
     */
    private void initCityCodes() {
        // 这里可以从数据库或配置文件中加载
        cityCodeMap.put("010", "北京");
        cityCodeMap.put("021", "上海");
        cityCodeMap.put("020", "广州");
        cityCodeMap.put("0755", "深圳");
        // ... 更多城市
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}