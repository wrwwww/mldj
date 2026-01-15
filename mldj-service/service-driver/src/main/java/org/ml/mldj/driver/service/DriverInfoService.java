package org.ml.mldj.driver.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.common.constant.RedisConst;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.mapper.DriverAccountMapper;
import org.ml.mldj.driver.mapper.DriverInfoMapper;
import org.ml.mldj.driver.mapper.DriverSetMapper;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.driver.DriverWorkStatus;
import org.ml.mldj.model.driver.dto.DriverLicenseInfoDTO;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.driver.dto.RealnameSubmitDTO;
import org.ml.mldj.model.driver.dto.WxLoginDTO;
import org.ml.mldj.model.driver.entity.DriverAccount;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.entity.DriverSet;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private DriverAccountMapper driverAccountMapper;

    //    @Autowired
    public DriverInfo getDriverByOpenId(String openid) {
        return null;
    }

    public String registerNewDriver(WxLoginDTO form) {
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
        driverAccountMapper.insert(driverAccount);

//        Wallet wallet = new Wallet();
//        wallet.setDriverId(driverByOpenId.getId());
//        wallet.setBalance(new BigDecimal(0));
//        wallet.setPassword(null);
//        walletMapper.insert(wallet);

        return driverByOpenId.getWxOpenId();
    }

    public DriverInfo query(String driverId) {
        return driverInfoMapper.selectById(driverId);
    }

    public DriverSet queryDriverSetting(String driverId) {
        return driverSettingsMapper.selectById(driverId);
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
        // 更新司机状态为离线
        LambdaUpdateWrapper<DriverInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DriverInfo::getId, driverId).eq(DriverInfo::getStatus, DriverWorkStatus.ONLINE.getCode());
        updateWrapper.set(DriverInfo::getStatus, DriverWorkStatus.OFFLINE.getCode());

    }

    public Result<?> snatchingOrder(String driverId, String orderId) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateDriverLicense(DriverLicenseInfoDTO driverLicenseInfoDTO, String driverId) {
        LambdaUpdateWrapper<DriverInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DriverInfo::getId, driverId)
                .set(DriverInfo::getDriverLicenseNo, driverLicenseInfoDTO.getDriverLicenseNo())
                .set(DriverInfo::getDriverLicenseClass, driverLicenseInfoDTO.getDriverLicenseClass())
                .set(DriverInfo::getDriverLicenseExpire, driverLicenseInfoDTO.getDriverLicenseExpire())
                .set(DriverInfo::getDriverLicenseIssueDate, driverLicenseInfoDTO.getDriverLicenseIssueDate())
        ;
        int update = driverInfoMapper.update(updateWrapper);
        if (update == 1) {
            return Result.success();
        }
        throw new BizException("更新失败");
    }

    @Transactional
    public Result<?> updateDriverRealName(RealnameSubmitDTO submitDTO, String userId) {
        LambdaUpdateWrapper<DriverInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DriverInfo::getId, userId).set(DriverInfo::getIdcardNo, submitDTO.getIdcardNo())
                .set(DriverInfo::getIdcardAddress, submitDTO.getIdcardAddress())
                .set(DriverInfo::getIdcardAddress, submitDTO.getIdcardAddress())
                .set(DriverInfo::getIdcardExpire, submitDTO.getIdcardExpire())
                .set(DriverInfo::getIdcardFrontUrl, submitDTO.getIdcardFrontUrl())
        ;
        int update = driverInfoMapper.update(updateWrapper);
        if (update == 1) {
            return Result.success();
        }
        throw new BizException("更新失败");
    }

    public void online(String driverId) {
        LambdaQueryWrapper<DriverInfo> queryWrapper = new LambdaQueryWrapper<DriverInfo>().eq(DriverInfo::getId, driverId);
        DriverInfo driverInfo = driverInfoMapper.selectOne(queryWrapper);

        String key = RedisConst.DRIVER_INFO_KEY_PREFIX + driverId;

        // 切换到在线
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(driverInfo));
        LambdaUpdateWrapper<DriverInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DriverInfo::getId, driverId).eq(DriverInfo::getStatus, DriverWorkStatus.OFFLINE.getCode());
        updateWrapper.set(DriverInfo::getStatus, DriverWorkStatus.ONLINE.getCode());


    }
}
