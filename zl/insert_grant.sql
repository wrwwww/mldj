insert into mailang.sys_role(role_name, role_code, description)
    VALUE ('超级管理员', 'ADMIN',
           '超管');
;
insert into mailang.sys_user_role(role_id, user_id) value (
                                                               (select id from mailang.sys_role where role_code = 'ADMIN'),
                                                               (select id from mailang.sys_user where username = 'admin'));
select *
from mailang.sys_user_role;
SELECT DISTINCT m.*
FROM mailang.sys_menu m
         INNER JOIN mailang.sys_role_menu rm ON m.id = rm.menu_id
WHERE rm.role_id IN (1)                  AND m.status = 1         AND m.is_deleted = 0
ORDER BY m.parent_id, m.sort, m.id;

-- 数据源表
CREATE TABLE IF NOT EXISTS `gen_datasource` (
                                                `id` varchar(32) NOT NULL COMMENT 'ID',
                                                `name` varchar(100) NOT NULL COMMENT '数据源名称',
                                                `db_type` varchar(20) NOT NULL COMMENT '数据库类型',
                                                `host` varchar(100) NOT NULL COMMENT '主机地址',
                                                `port` int NOT NULL COMMENT '端口',
                                                `database` varchar(100) NOT NULL COMMENT '数据库名',
                                                `username` varchar(100) NOT NULL COMMENT '用户名',
                                                `password` varchar(255) NOT NULL COMMENT '密码',
                                                `params` varchar(500) COMMENT '连接参数',
                                                `remark` varchar(500) COMMENT '备注',
                                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据源配置';

-- 生成配置表
CREATE TABLE IF NOT EXISTS `gen_config` (
                                            `id` varchar(32) NOT NULL COMMENT 'ID',
                                            `datasource_id` varchar(32) NOT NULL COMMENT '数据源ID',
                                            `table_name` varchar(100) NOT NULL COMMENT '表名',
                                            `module_name` varchar(50) NOT NULL COMMENT '模块名',
                                            `entity_name` varchar(100) NOT NULL COMMENT '实体名',
                                            `title` varchar(100) NOT NULL COMMENT '功能标题',
                                            `route_path` varchar(100) NOT NULL COMMENT '路由路径',
                                            `columns` json NOT NULL COMMENT '列配置',
                                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                            PRIMARY KEY (`id`),
                                            KEY `idx_datasource_id` (`datasource_id`),
                                            KEY `idx_table_name` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成配置';