package org.ml.mldj.driver.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.driver.mapper.DriverAuthMapper;
import org.ml.mldj.model.driver.DriverAuthStatus;
import org.ml.mldj.model.driver.dto.*;
import org.ml.mldj.model.driver.entity.DriverAuth;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DriverAuthService {

    private final DriverAuthMapper repository;
    private final ThirdPartyKycClient kycClient = new ThirdPartyKycClient();

    @Transactional
    public Long startAuth(Long driverId) {
        DriverAuth auth = new DriverAuth();
        auth.setDriverId(driverId);
        auth.setStatus(DriverAuthStatus.INIT);
        repository.insert(auth);
        return auth.getId();
    }

    @Transactional
    public void uploadIdCard(Long authId, IdCardUploadReq req) {
        DriverAuth auth = getAuth(authId, DriverAuthStatus.INIT);

        IdCardOcrResult ocr = kycClient.ocrIdCard(req);

        auth.setIdName(ocr.getName());
        auth.setIdNumber(ocr.getIdNumber());
        auth.setStatus(DriverAuthStatus.ID_UPLOADED);

        LambdaUpdateWrapper<DriverAuth> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(DriverAuth::getId, auth.getId()).eq(DriverAuth::getStatus, DriverAuthStatus.ID_UPLOADED);
        updateWrapper.set(DriverAuth::getIdNumber, auth.getIdNumber()).set(DriverAuth::getStatus, DriverAuthStatus.ID_UPLOADED).set(DriverAuth::getIdName, auth.getIdName());
        repository.update(updateWrapper);
    }

    @Transactional
    public void uploadDriverLicense(Long authId, DriverLicenseUploadReq req) {
        DriverAuth auth = getAuth(authId, DriverAuthStatus.ID_UPLOADED);

        DriverLicenseResult result = kycClient.verifyDriverLicense(
                req.getImageUrl(),
                auth.getIdName(),
                auth.getIdNumber()
        );

        if (!result.isValid()) {
            auth.setStatus(DriverAuthStatus.REJECTED);
            auth.setLicenseVerified(false);
            return;
        }

        LambdaUpdateWrapper<DriverAuth> driverAuthLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        driverAuthLambdaUpdateWrapper.eq(DriverAuth::getDriverId, auth.getId()).eq(DriverAuth::getStatus, DriverAuthStatus.LICENSE_UPLOADED);
        driverAuthLambdaUpdateWrapper.set(DriverAuth::getStatus, DriverAuthStatus.LICENSE_UPLOADED).set(DriverAuth::getLicenseNumber, auth.getLicenseNumber()).set(DriverAuth::getLicenseType, auth.getLicenseType()).set(DriverAuth::getLicenseExpireAt, auth.getLicenseExpireAt());
    }

    public FaceLivenessStartResp startFaceLiveness(Long authId) {
        DriverAuth auth = getAuth(authId, DriverAuthStatus.LICENSE_UPLOADED);

        FaceLivenessToken token = kycClient.startLiveness();

        FaceLivenessStartResp resp = new FaceLivenessStartResp();
        resp.setBizToken(token.getBizToken());
        resp.setActions(token.getActions());

        LambdaUpdateWrapper<DriverAuth> driverAuthLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        driverAuthLambdaUpdateWrapper.eq(DriverAuth::getStatus, DriverAuthStatus.FACE_UPLOADED).eq(DriverAuth::getDriverId,auth.getId()).set(DriverAuth::getStatus, DriverAuthStatus.FACE_UPLOADED).set(DriverAuth::getFaceSimilarity,auth.getFaceSimilarity());

        return resp;
    }

    @Transactional
    public void handleFaceResult(Long authId, FaceLivenessResultReq req) {
        DriverAuth auth = repository.selectById(authId);
        if (auth == null) {
            throw new BizException("<UNK>");
        }


        auth.setFaceSimilarity(req.getFaceSimilarity());
        auth.setLivenessScore(req.getLivenessScore());
        auth.setVerifyTraceId(req.getTraceId());

        if (req.getLivenessScore().compareTo(BigDecimal.valueOf(0.8)) >= 0
                && req.getFaceSimilarity().compareTo(BigDecimal.valueOf(85)) >= 0
                && Boolean.TRUE.equals(auth.getLicenseVerified())) {

            auth.setStatus(DriverAuthStatus.VERIFIED);
        } else {
            auth.setStatus(DriverAuthStatus.REJECTED);
        }
        repository.updateById(auth);

    }

    private DriverAuth getAuth(Long authId, DriverAuthStatus expect) {
        LambdaQueryWrapper<DriverAuth> driverAuthLambdaQueryWrapper = new LambdaQueryWrapper<>();
        driverAuthLambdaQueryWrapper.eq(DriverAuth::getId, authId).eq(DriverAuth::getStatus, expect);
        DriverAuth auth = repository.selectOne(driverAuthLambdaQueryWrapper);
        if (auth == null) {
            throw new BizException("认证状态异常");
        }
        return auth;
    }

}