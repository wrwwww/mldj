package org.ml.mldj.model.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据源配置
 */
@Data
@TableName("gen_datasource")
public class Datasource {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 数据源名称 */
    private String name;

    /** 数据库类型: mysql/postgresql/oracle */
    private String dbType;

    /** 主机地址 */
    private String host;

    /** 端口 */
    private Integer port;

    /** 数据库名 */
    private String databaseName;

    /** 用户名 */
    private String username;

    /** 密码（加密存储） */
    private String password;

    /** 连接参数 */
    private String params;

    /** 备注 */
    private String remark;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}