package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

/**
 * 司机人脸识别表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_driver_recognition", schema = "hxds_dr")
public class TbDriverRecognition {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @NotNull
    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    /**
     * 检测日期
     */
    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /**
     * 创建时间
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}