package org.ml.mldj.driver.service;

import lombok.RequiredArgsConstructor;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.driver.mapper.DriverAuthMapper;
import org.ml.mldj.driver.mapper.DriverInfoMapper;
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
    private final ThirdPartyKycClient kycClient =new ThirdPartyKycClient();

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
//
        auth.setIdName(ocr.getName());
        auth.setIdNumber(ocr.getIdNumber());
        auth.setStatus(DriverAuthStatus.ID_UPLOADED);
        repository.updateById(auth);
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

        auth.setLicenseNumber(result.getLicenseNumber());
        auth.setLicenseType(result.getLicenseType());
        auth.setLicenseExpireAt(result.getExpireDate());
        auth.setLicenseVerified(true);
        auth.setStatus(DriverAuthStatus.LICENSE_UPLOADED);
    }

    public FaceLivenessStartResp startFaceLiveness(Long authId) {
        DriverAuth auth = getAuth(authId, DriverAuthStatus.LICENSE_UPLOADED);

        FaceLivenessToken token = kycClient.startLiveness();

        FaceLivenessStartResp resp = new FaceLivenessStartResp();
        resp.setBizToken(token.getBizToken());
        resp.setActions(token.getActions());

        auth.setStatus(DriverAuthStatus.FACE_UPLOADED);
        repository.updateById(auth);
        return resp;
    }

    @Transactional
    public void handleFaceResult(Long authId, FaceLivenessResultReq req) {
        DriverAuth auth = repository.selectById(authId);
        if (auth==null){
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
        DriverAuth auth = repository.selectById(authId);
        if (auth == null) {
            throw new BizException("认证不存在");
        }

        if (auth.getStatus() != expect) {
            throw new BizException("认证状态非法");
        }
        return auth;
    }

}