package org.ml.mldj.driver.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.common.constant.RedisConst;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.mapper.DriverSetMapper;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.driver.dto.DriverLoginForm;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.driver.entity.DriverAccount;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.driver.mapper.DriverInfoMapper;
import org.ml.mldj.model.driver.entity.DriverSet;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class DriverInfoService {

    @Autowired
    DriverInfoMapper driverInfoMapper;
    @Autowired
    DriverSetMapper driverSettingsMapper;
//    @Autowired
//    WalletMapper walletMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    //    @Autowired
    public DriverInfo getDriverByOpenId(String openid) {
        return null;
    }

    public String registerNewDriver(DriverLoginForm form) {
        DriverInfo driver = new DriverInfo();
        BeanUtils.copyProperties(form, driver);
        driverInfoMapper.insert(driver);
        DriverInfo driverByOpenId = getDriverByOpenId(driver.getWxOpenId());
        // 设置司机的默认设置
        JSONObject json = new JSONObject();

        json.put("orientation", "");
        json.put("listenService", true);
        json.put("orderDistance", 0);
        json.put("rangeDistance", 5);
        json.put("autoAccept", false);
        DriverSet driverSettings = new DriverSet();
        driverSettings.setDriverId(driverByOpenId.getId());
//        driverSettings.(json.toString());
        driverSettingsMapper.insert(driverSettings);
        DriverAccount driverAccount = new DriverAccount();
        driverAccount.setDriverId(driverByOpenId.getId());

//        Wallet wallet = new Wallet();
//        wallet.setDriverId(driverByOpenId.getId());
//        wallet.setBalance(new BigDecimal(0));
//        wallet.setPassword(null);
//        walletMapper.insert(wallet);

        return driverByOpenId.getWxOpenId();
    }

    public DriverVO query(String driverId) {
        DriverInfo driver = driverInfoMapper.selectById(driverId);
        DriverVO driverVO = new DriverVO();
        BeanUtils.copyProperties(driver, driverVO);
        return driverVO;
    }

    public DriverSettingVO queryDriverSetting(String driverId) {
        DriverSet driverSettings = driverSettingsMapper.selectById(driverId);
        DriverSettingVO driverSettingVO = new DriverSettingVO();
        BeanUtils.copyProperties(driverSettings, driverSettingVO);
        return driverSettingVO;
    }

    public PageVO<DriverVO> queryDriverPage(DriverPageForm form) {
        // 创建分页对象，指定当前页和每页大小
        Page<DriverVO> page = new Page<>(form.getPageNum(), form.getPageSize());
        Page<DriverVO> res = driverInfoMapper.queryDriverPage(page, form);
        return PageVO.buildPageVO(res);
    }

    public void offline(String driverId) {
        // 清理Redis记录
        String key = RedisConst.DRIVER_INFO_KEY_PREFIX + driverId;
        redisTemplate.delete(key);
        // todo 更新司机状态为离线

    }

    public Result<?> snatchingOrder(String driverId, String orderId) {
        return null;
    }
}
