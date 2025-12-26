package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

/**
 * 司机设置表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_driver_settings", schema = "hxds_dr")
public class TbDriverSetting {
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
     * 个人设置
     */
    @NotNull
    @Column(name = "settings", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> settings;

}