insert into
menu(id, module, name, parent_id, classname)
values
(0, 'ROOT', 'root', NULL, NULL),
(1, 'SECURITY', 'menu_security', 0, NULL),
(2, 'USER', 'menu_user', 1, 'userlist'),
(3, 'ROLE', 'menu_role', 1, NULL)
;

insert into
permission(id, name)
values
(0, 'ROOT_R'),
(1, 'SECURITY_R'),
(2, 'USER_R'),
(3, 'USER_W'),
(4, 'ROLE_R'),
(5, 'ROLE_W')
;

insert into
role(id, name)
values
(0, 'SECURITY_R'),
(1, 'SECURITY_W')
;

insert into
role_permission(role_id, permission_id)
values
(0, 0),
(0, 1),
(0, 2),
(0, 3)
;

insert into
role_permission(role_id, permission_id)
values
(0, 4),
(0, 5)
;

insert into
user(id, username, password)
values
(0, 'watson', 'wapwap12')
;

insert into
user_role(user_id, role_id)
values
(0, 0)
;


