package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 司机设置表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_driver_settings")
@Schema(name = "DriverSettings", description = "司机设置表")
public class DriverSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 个人设置
     */
    @Schema(description = "个人设置")
    private String settings;
}
