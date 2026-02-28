package org.ml.mldj.model.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "排序项")
public class SortItem {

    @Schema(description = "排序字段", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "排序字段名称非法")
    private String column;

    @Schema(description = "排序方向 ASC/DESC", defaultValue = "ASC")
    private SortDirection direction = SortDirection.ASC;

    /**
     * 是否升序
     */
    public boolean isAscending() {
        return SortDirection.ASC.equals(direction);
    }

    /**
     * 获取下划线格式的字段名（驼峰转下划线）
     */
    public String getUnderlineColumn() {
        if (StringUtils.isBlank(column)) {
            return column;
        }
        return StringUtils.camelToUnderline(column);
    }

    public enum SortDirection {
        ASC, DESC
    }
}
