package org.ml.mldj.driver.service;

import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.client.DriverFeignClient;
import org.ml.mldj.driver.config.WxConfig;
import org.ml.mldj.model.common.LoginVO;
import org.ml.mldj.model.common.WxLoginInfoVO;
import org.ml.mldj.model.driver.dto.DriverBasicInfoUpdateForm;
import org.ml.mldj.model.driver.dto.WxLoginDTO;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.entity.DriverSet;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.ml.mldj.security.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class DriverService {

    @Autowired
    WxConfig wxConfig;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DriverFeignClient driverFeignClient;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public LoginVO login(WxLoginDTO form) {
        // 获取openid
        WxLoginInfoVO openid = getOpenid(form.getCode());
        if (openid != null) {
            // 查询数据库获取用户信息
            DriverInfo driver = driverFeignClient.getDriverByOpenId(openid.getOpenid()).unwrap();
            Long userId = null;
            // 用户存在就走登录
            if (driver == null) {
                // 用户不存在就注册
                driver = driverFeignClient.registerNewDriver(form).unwrap();
            }
            userId = driver.getId();
            // 根据用户id生成token
            String token = jwtTokenUtil.generateAccessToken(userId,"x");
            String refreshToken = jwtTokenUtil.generateRefreshToken(userId);
            LoginVO loginVO = new LoginVO();
            BeanUtils.copyProperties(driver, loginVO);
            loginVO.setToken(token);
            loginVO.setRefreshToken(refreshToken);
            return loginVO;
        }
        throw new RuntimeException();
    }

    public WxLoginInfoVO getOpenid(String code) {

        String url = String.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                wxConfig.getAppid(), wxConfig.getAppsecret(), code
        );

        // 发送请求到微信 API
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            String openid = (String) responseBody.get("openid");
            String sessionKey = (String) responseBody.get("session_key");
            WxLoginInfoVO wxLoginInfoVO = new WxLoginInfoVO();
            wxLoginInfoVO.setOpenid(openid);
            wxLoginInfoVO.setSessionKey(sessionKey);
            return wxLoginInfoVO;
            // 返回 openid 和 session_key
//            return ResponseEntity.ok(Map.of("openid", openid, "session_key", sessionKey));
        } else {
//            return ResponseEntity.status(500).body("获取openid失败");
            return null;
        }
    }

    public Result<DriverVO> getDriverById(String driverId) {
        Result<DriverInfo> driverResult = driverFeignClient.queryDriverByDriverId(driverId);
        DriverInfo unwrap = driverResult.unwrap();
        return Result.success(convertDriverToVO(unwrap));
    }

    private DriverVO convertDriverToVO(DriverInfo driver) {
        DriverVO driverVO = new DriverVO();
        driverVO.setId(driver.getId());
        driverVO.setName(driver.getName());
        driverVO.setPhone(driver.getPhone());
        driverVO.setDriverLicenseNo(driver.getDriverLicenseNo());
        driverVO.setWorkStatus(driver.getStatus());
        return driverVO;
    }


    public void updateBasicInfo(Long driverId, DriverBasicInfoUpdateForm form) {
        Result<Void> updateResult = driverFeignClient.updateDriverBasicInfo(driverId, form);
        if (updateResult == null || !updateResult.isOk()) {
            throw new RuntimeException("Driver basic info update failed");
        }
    }

    public Result<?> offline(String driverId) {
        return driverFeignClient.Offline(driverId);
    }

    public Result<?> online(String driverId) {
        return driverFeignClient.Online(driverId);
    }

    public DriverSettingVO queryDriverSetting(String driverId) {
        Result<DriverSet> driverSettings = driverFeignClient.queryDriverSettings(driverId);
        DriverSettingVO driverSettingVO = new DriverSettingVO();
        BeanUtils.copyProperties(driverSettings, driverSettingVO);
        return driverSettingVO;
    }
}
