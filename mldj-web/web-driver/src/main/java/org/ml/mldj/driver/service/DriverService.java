package org.ml.mldj.driver.service;

import org.ml.mldj.driver.client.DriverFeignClient;
import org.ml.mldj.driver.config.WxConfig;
import org.ml.mldj.model.dto.DriverLoginForm;
import org.ml.mldj.model.entity.Driver;
import org.ml.mldj.model.vo.LoginVO;
import org.ml.mldj.model.vo.WxLoginInfoVO;
import org.mldj.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DriverService {

    @Autowired
    WxConfig wxConfig;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DriverFeignClient driverFeignClient;


    public LoginVO login(DriverLoginForm form) {
        // 获取openid
        WxLoginInfoVO openid = getOpenid(form.getCode());
        if (openid != null) {
            // 查询数据库获取用户信息
            Result<Driver> driver = driverFeignClient.getDriverByOpenId(openid.getOpenid());
            // 用户不存在就注册
            if (driver != null && driver.isOk() && driver.getData() != null) {
                Result<String> userId = driverFeignClient.registerNewDriver(form);
            }

            // 用户存在就走登录
            // 根据用户id生成token
            //
            return new LoginVO();
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
}
