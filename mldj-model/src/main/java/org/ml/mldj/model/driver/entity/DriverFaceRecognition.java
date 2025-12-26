package org.ml.mldj.model.driver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 司机人脸识别记录表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("driver_face_recognition")
@Schema(name = "DriverFaceRecognition", description = "司机人脸识别记录表")
public class DriverFaceRecognition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 人脸识别时间
     */
    @Schema(description = "人脸识别时间")
    private LocalDateTime faceDate;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志 0=未删除 1=已删除
     */
    @Schema(description = "逻辑删除标志 0=未删除 1=已删除")
    private Boolean isDeleted;
}
