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
 * 行为表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_action", schema = "hxds_mis")
public class TbAction {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    /**
     * 行为编号
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "action_code", nullable = false, length = 200)
    private String actionCode;

    /**
     * 行为名称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "action_name", nullable = false, length = 200)
    private String actionName;

}