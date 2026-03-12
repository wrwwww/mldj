package org.ml.mldj.model.system.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 码表类型表
 *
 * @TableName sys_code_type
 */
@Getter
@NoArgsConstructor
@Setter
public class SysCodeTypeVO implements Serializable {


    private String id;
    /**
     * 码表类型唯一标识，如 role/status/category
     */

    private String codeType;
    /**
     * 码表类型名称，如 用户角色/文章状态/分类类型
     */

    private String name;
    /**
     * 说明
     */

    private String description;


}
