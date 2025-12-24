package org.ml.mldj.model.dto.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 司机位置实体
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverLocation {
    private String driverId;           // 司机ID
    private String driverName;         // 司机姓名
    private double latitude;          // 纬度
    private double longitude;         // 经度
    private LocationType locationType; // 位置类型
    private DriverStatus status;       // 司机状态
    private double rating;            // 评分
    private Integer currentOrderId;   // 当前订单ID
    private LocalDateTime updateTime; // 最后更新时间
    private VehicleInfo vehicle;      // 车辆信息
    // 枚举定义
    public enum DriverStatus {
        ONLINE,         // 在线待命
        OFFLINE,        // 离线
        BUSY,           // 忙碌（接单中）
        RESTING,        // 休息中
        BREAK           // 短暂休息
    }

    public enum LocationType {
        GPS,            // GPS定位
        NETWORK,        // 网络定位
        MANUAL          // 手动设置
    }

    @Data
    public static class VehicleInfo {
        private String plateNumber;    // 车牌号
        private String brand;          // 品牌
        private String model;          // 型号
        private String color;          // 颜色
        private Integer seatCapacity;  // 座位数
    }
}