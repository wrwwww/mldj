package org.ml.mldj.model.vo;

import lombok.Data;

@Data
public class OrderChargeVO {
    String amount;
    String chargeRuleId;
    String baseMileagePrice;
    String baseMileage;
    String exceedMileagePrice;
    String baseMinute;
    String exceedMinutePrice;
    String baseReturnMileage;
    String exceedReturnMileage;
}
