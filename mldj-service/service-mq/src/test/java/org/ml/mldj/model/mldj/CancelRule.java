package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cancel_rule", schema = "mailang")
public class CancelRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Lob
    @Column(name = "rule")
    private String rule;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}