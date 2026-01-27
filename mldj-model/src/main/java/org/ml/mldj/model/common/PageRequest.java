package org.ml.mldj.model.common;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PageRequest {

    @Schema(description = "每页记录数")
    @Range(min = 10, max = 50, message = "length必须在10~50之间")
    private int pageSize = 20;
    @Min(1)
    @Schema(description = "页数")
    private int pageNum = 1;
}
