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
 * 部门表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_dept", schema = "hxds_mis")
public class TbDept {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    /**
     * 部门名称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "dept_name", nullable = false, length = 200)
    private String deptName;

    /**
     * 部门电话
     */
    @Size(max = 20)
    @Column(name = "tel", length = 20)
    private String tel;

    /**
     * 部门邮箱
     */
    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    /**
     * 备注
     */
    @Size(max = 20)
    @Column(name = "`desc`", length = 20)
    private String desc;

}