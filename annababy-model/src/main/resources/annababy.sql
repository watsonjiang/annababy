insert into
menu(id, module, name, parent_id, classname)
values
(1, 'ROOT', 'root', NULL, NULL),
(2, 'SECURITY', 'menu_security', 1, NULL),
(3, 'USER', 'menu_user', 2, 'user'),
(4, 'ROLE', 'menu_role', 2, NULL)
;

insert into
permission(id, name)
values
(1, 'PERM_USER_R'),
(2, 'PERM_USER_W'),
(3, 'PERM_ROLE_R'),
(4, 'PERM_ROLE_W')
;

insert into
role(id, name)
values
(1, 'ROLE_SECURITY_R'),
(2, 'ROLE_SECURITY_W')
;

insert into
role_permission(role_id, permission_id)
values
(1, 1),
(1, 3),
(2, 2),
(2, 4)
;

insert into
user(id, username, password)
values
(1, 'watson', '$2a$10$Mhk9WSFUR1Zv9/mKibLjkuX/J1/qVrReWWQAryxhCQqzsVaZMa4Eu'),
(2, 'anna', '$2a$10$Mhk9WSFUR1Zv9/mKibLjkuX/J1/qVrReWWQAryxhCQqzsVaZMa4Eu')
;

insert into
user_role(user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1)
;


