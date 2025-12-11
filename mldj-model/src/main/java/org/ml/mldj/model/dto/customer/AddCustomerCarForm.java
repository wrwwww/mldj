package org.ml.mldj.model.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCustomerCarForm {
    String CustomerId;
    /**
     * 车牌号
     */
    @Schema(description = "车牌号")
    private String carPlate;

    /**
     * 车型
     */
    @Schema(description = "车型")
    private String carType;
}
