package org.ml.mldj.driver.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ml.mldj.common.constant.RedisConst;
import org.ml.mldj.driver.mapper.DriverMapper;
import org.ml.mldj.driver.mapper.DriverSettingsMapper;
import org.ml.mldj.driver.mapper.WalletMapper;
import org.ml.mldj.driver.service.IDriverService;
import org.ml.mldj.model.dto.DriverLoginForm;
import org.ml.mldj.model.dto.DriverPageForm;
import org.ml.mldj.model.entity.Driver;
import org.ml.mldj.model.entity.DriverSettings;
import org.ml.mldj.model.entity.Wallet;
import org.ml.mldj.model.vo.DriverSettingVO;
import org.ml.mldj.model.vo.DriverVO;
import org.ml.mldj.model.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 代驾司机表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements IDriverService {
    @Autowired
    DriverMapper driverMapper;
    @Autowired
    DriverSettingsMapper driverSettingsMapper;
    @Autowired
    WalletMapper walletMapper;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public Driver getDriverByOpenId(String openid) {
        return null;
    }

    @Override
    public String registerNewDriver(DriverLoginForm form) {
        Driver driver = new Driver();
        BeanUtils.copyProperties(form, driver);
        driverMapper.insert(driver);
        Driver driverByOpenId = getDriverByOpenId(driver.getOpenId());
        // 设置司机的默认设置
        JSONObject json = new JSONObject();

        json.put("orientation", "");
        json.put("listenService", true);
        json.put("orderDistance", 0);
        json.put("rangeDistance", 5);
        json.put("autoAccept", false);
        DriverSettings driverSettings = new DriverSettings();
        driverSettings.setDriverId(driverByOpenId.getId());
        driverSettings.setSettings(json.toString());
        driverSettingsMapper.insert(driverSettings);

        Wallet wallet = new Wallet();
        wallet.setDriverId(driverByOpenId.getId());
        wallet.setBalance(new BigDecimal(0));
        wallet.setPassword(null);
        walletMapper.insert(wallet);

        return driverByOpenId.getOpenId();
    }

    @Override
    public DriverVO query(String driverId) {
        Driver driver = driverMapper.selectById(driverId);
        DriverVO driverVO = new DriverVO();
        BeanUtils.copyProperties(driver, driverVO);
        return driverVO;
    }

    @Override
    public DriverSettingVO queryDriverSetting(String driverId) {
        DriverSettings driverSettings = driverSettingsMapper.selectById(driverId);
        DriverSettingVO driverSettingVO = new DriverSettingVO();
        BeanUtils.copyProperties(driverSettings, driverSettingVO);
        return driverSettingVO;
    }

    @Override
    public PageVO<DriverVO> queryDriverPage(DriverPageForm form) {
        // 创建分页对象，指定当前页和每页大小
        Page<DriverVO> page = new Page<>(form.getPageNum(), form.getPageSize());
        Page<DriverVO> res = driverMapper.queryDriverPage(page, form);
        return PageVO.buildPageVO(res);
    }

    @Override
    public void offline(String driverId) {
        // 清理Redis记录
        String key =  RedisConst.DRIVER_INFO_KEY_PREFIX + driverId;
        redisTemplate.delete(key);
        // todo 更新司机状态为离线

    }
}
