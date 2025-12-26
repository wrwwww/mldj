package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 司机人脸识别记录表
 */
@Getter
@Setter
@Entity
@Table(name = "driver_face_recognition", schema = "mailang")
public class DriverFaceRecognition {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @Column(name = "driver_id")
    private Long driverId;

    /**
     * 人脸识别时间
     */
    @Column(name = "face_date")
    private Instant faceDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 逻辑删除标志 0=未删除 1=已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}