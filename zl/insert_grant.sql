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
ORDER BY m.parent_id, m.sort, m.id