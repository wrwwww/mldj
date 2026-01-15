package org.ml.mldj.model.driver.dto;

public class ThirdPartyKycClient {
    public IdCardOcrResult ocrIdCard(IdCardUploadReq req) {
        return null;
    }

    public DriverLicenseResult verifyDriverLicense(String imageUrl, String idName, String idNumber) {
        return null;
    }

    public FaceLivenessToken startLiveness() {
        return null;
    }
}
