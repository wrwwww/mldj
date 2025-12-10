package org.ml.mldj.model.entity;

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
 * 
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_feedback")
@Schema(name = "Feedback", description = "")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 反馈者姓名
     */
    @Schema(description = "反馈者姓名")
    private String customerName;

    /**
     * 反馈者电话
     */
    @Schema(description = "反馈者电话")
    private String customerTel;

    /**
     * 接待员ID
     */
    @Schema(description = "接待员ID")
    private Long userId;

    /**
     * 反馈类型，1系统故障，2服务质量，3支付异常，4其他
     */
    @Schema(description = "反馈类型，1系统故障，2服务质量，3支付异常，4其他")
    private Byte type;

    /**
     * 反馈内容
     */
    @Schema(description = "反馈内容")
    private String content;

    /**
     * 反馈状态，1未处理，2已处理
     */
    @Schema(description = "反馈状态，1未处理，2已处理")
    private Byte status;

    /**
     * 处理结果
     */
    @Schema(description = "处理结果")
    private String result;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
