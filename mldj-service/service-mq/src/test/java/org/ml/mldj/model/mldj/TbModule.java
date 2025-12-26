package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 模块资源表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_module", schema = "hxds_mis")
public class TbModule {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    /**
     * 模块编号
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "module_code", nullable = false, length = 200)
    private String moduleCode;

    /**
     * 模块名称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "module_name", nullable = false, length = 200)
    private String moduleName;

}