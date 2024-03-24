
CREATE TABLE public.menus (
	id serial4 NOT NULL,
	menu_name varchar(100) NOT NULL,
	order_by numeric(20, 6) NULL DEFAULT 0.000000,
	url varchar(255) NULL,
	redirect varchar(255) NULL,
	component varchar(255) NULL,
	icon varchar(255) NULL,
	parent_id int8 NULL,
	is_active boolean NOT NULL DEFAULT true,
	is_deleted boolean NOT NULL DEFAULT false,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
	created_by varchar(100) NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	updated_by varchar(100) NULL,
	updated_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT menus_pkey PRIMARY KEY (id)
);

CREATE TABLE public.oauth_access_token (
	id serial4 NOT NULL,
	token_id varchar(255) NULL,
	"token" text NULL,
	authentication_id varchar(255) NOT NULL,
	user_id int8 NOT NULL,
	user_name varchar(255) NULL,
	client_id varchar(255) NULL,
	authentication text NULL,
	refresh_token varchar(255) NULL,
	expired_at timestamp NOT NULL,
	created_at timestamp NOT NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NOT NULL,
	updated_by varchar(100) NULL,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
    is_active boolean NOT NULL DEFAULT true,
	is_deleted boolean NOT NULL DEFAULT false,
	CONSTRAINT oauth_access_token_pkey PRIMARY KEY (id)
);


CREATE TABLE public.oauth_approvals (
	id serial4 NOT NULL,
	userid varchar(256) NULL,
	clientid varchar(256) NULL,
	"scope" varchar(256) NULL,
	status varchar(10) NULL,
	expiresat timestamp NULL,
	lastmodifiedat timestamp NULL,
	CONSTRAINT oauth_approvals_pkey PRIMARY KEY (id)
);

CREATE TABLE public.oauth_client_details (
	client_id varchar(256) NOT NULL,
	resource_ids varchar(256) NULL,
	client_secret varchar(256) NULL,
	"scope" varchar(256) NULL,
	authorized_grant_types varchar(256) NULL,
	web_server_redirect_uri varchar(256) NULL,
	authorities varchar(256) NULL,
	access_token_validity int4 NULL,
	refresh_token_validity int4 NULL,
	additional_information varchar(4096) NULL,
	autoapprove varchar(256) NULL,
	CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
);


CREATE TABLE public.oauth_refresh_token (
	id serial4 NOT NULL,
	token_id varchar(255) NULL,
	"token" text NULL,
	authentication text NULL,
	expired_at timestamp NOT NULL,
	created_at timestamp NOT NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NOT NULL,
	updated_by varchar(100) NULL,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
     is_active boolean NOT NULL DEFAULT true,
	is_deleted boolean NOT NULL DEFAULT false,
	CONSTRAINT oauth_refresh_token_pkey PRIMARY KEY (id)
);

CREATE TABLE public.permissions (
	id serial4 NOT NULL,
	permission_name varchar(100) NOT NULL,
	"type" varchar(50) NULL,
	menu_id int8 NULL,
	is_active boolean NOT NULL DEFAULT true,
	is_deleted boolean NOT NULL DEFAULT false,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
	created_at timestamp NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NULL,
	updated_by varchar(100) NULL,
	CONSTRAINT permissions_pkey PRIMARY KEY (id)
);


CREATE TABLE public.role_permissions (
	id serial4 NOT NULL,
	role_id int8 NOT NULL,
	permission_id int8 NOT NULL,
	is_active boolean NOT NULL DEFAULT true,
	is_deleted boolean NOT NULL DEFAULT false,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
	created_at timestamp NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NULL,
	updated_by varchar(100) NULL,
	CONSTRAINT role_permissions_pkey PRIMARY KEY (id)
);

CREATE TABLE public.roles (
	id serial4 NOT NULL,
	role_name varchar(100) NOT NULL,
	is_active boolean NOT NULL DEFAULT true,
	is_deleted boolean NOT NULL DEFAULT false,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
	created_at timestamp NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NULL,
	updated_by varchar(100) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


CREATE TABLE public.user_roles (
	id serial4 NOT NULL,
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	is_active boolean NULL DEFAULT true,
	is_deleted boolean NULL DEFAULT false,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
	created_at timestamp NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NULL,
	updated_by varchar(100) NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users (
	id serial4 NOT NULL,
	branch_id varchar(10) NULL,
	username varchar(100) NOT NULL,
	fullname varchar(100) NULL,
	staff_id varchar(50) NULL,
	phone_number varchar(50) NULL,
	"password" text NULL,
	email varchar(100) NULL,
	failed_attempt int4 NULL DEFAULT 0,
	"locked" boolean NULL DEFAULT false,
	last_login timestamp NULL,
	is_active boolean NULL DEFAULT true,
	is_deleted boolean NULL DEFAULT true,
	created_at timestamp NULL,
	created_by varchar(100) NULL,
	updated_at timestamp NULL,
	updated_by varchar(100) NULL,
	deleted_at timestamp NULL,
	deleted_by varchar(100) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

INSERT INTO public.oauth_client_details
(client_id, resource_ids, client_secret, "scope", authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES('admin', NULL, '$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a', 'read, write', 'client_credentials,password,refresh_token', NULL, NULL, 3600, 3600, '{}', 'true');


INSERT INTO public.menus (id, menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted, deleted_at, deleted_by, created_by, created_at, updated_by, updated_at) VALUES(1, 'Dashboard', 1.000000, '/dashboard', NULL, 'Dashboard', 'HomeOutlined', NULL, true, false, NULL, NULL, NULL, '2024-03-11 22:35:08.399', NULL, '2024-03-11 22:35:08.399');
INSERT INTO public.menus (id, menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted, deleted_at, deleted_by, created_by, created_at, updated_by, updated_at) VALUES(3, 'Settings', 1.000000, '/settings', NULL, '', 'SettingOutlined', NULL, true, false, NULL, NULL, NULL, '2024-03-11 22:35:08.399', NULL, '2024-03-11 22:35:08.399');
INSERT INTO public.menus (id, menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted, deleted_at, deleted_by, created_by, created_at, updated_by, updated_at) VALUES(4, 'Users', 1.000000, '/user', NULL, 'User', 'UserOutlined', 3, true, false, NULL, NULL, NULL, '2024-03-11 22:35:08.399', NULL, '2024-03-11 22:35:08.399');
INSERT INTO public.menus (id, menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted, deleted_at, deleted_by, created_by, created_at, updated_by, updated_at) VALUES(5, 'Permissions', 1.000000, '/permission', NULL, 'Permission', 'CheckOutlined', 3, true, false, NULL, NULL, NULL, '2024-03-11 22:35:08.399', NULL, '2024-03-11 22:35:08.399');
INSERT INTO public.menus (id, menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted, deleted_at, deleted_by, created_by, created_at, updated_by, updated_at) VALUES(6, 'Roles', 1.000000, '/role', NULL, 'Role', 'UserOutlined', 3, true, false, NULL, NULL, NULL, '2024-03-11 22:35:08.399', NULL, '2024-03-11 22:35:08.399');
INSERT INTO public.menus (id, menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted, deleted_at, deleted_by, created_by, created_at, updated_by, updated_at) VALUES(7, 'Menus', 1.000000, '/menu', NULL, 'Menu', 'MenuOutlined', 3, true, false, NULL, NULL, NULL, '2024-03-11 22:35:08.399', NULL, '2024-03-11 22:35:08.399');



INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 'VIEW_DASHBOARD', NULL, 1, true, false, NULL, NULL, '2023-09-20 13:15:05.000', NULL, '2023-09-20 13:15:05.000', NULL);
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(2, 'VIEW_USER', NULL, 4, true, false, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(3, 'CREATE_USER', '', 4, true, false, NULL, NULL, '2023-09-22 15:16:41.000', '1', '2023-09-22 15:16:41.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(4, 'UPDATE_USER', NULL, 4, true, false, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(5, 'DELETE_USER', NULL, 4, true, false, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(6, 'VIEW_PERMISSION', '', 5, true, false, NULL, NULL, '2023-09-22 15:24:06.000', '1', '2023-09-22 15:24:06.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(7, 'CREATE_PERMISSION', '', 5, true, false, NULL, NULL, '2023-09-22 15:24:41.000', '1', '2023-09-22 15:24:41.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(8, 'UPDATE_PERMISSION', '', 5, true, false, NULL, NULL, '2023-09-22 15:25:00.000', '1', '2023-09-22 15:25:00.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(9, 'VIEW_ROLE', '', 6, true, false, NULL, NULL, '2023-09-22 15:25:24.000', '1', '2023-09-22 15:25:34.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(10, 'CREATE_ROLE', '', 6, true, false, NULL, NULL, '2023-09-22 15:26:04.000', '1', '2023-09-22 15:26:04.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(11, 'UPDATE_ROLE', '', 6, true, false, NULL, NULL, '2023-09-22 15:26:19.000', '1', '2023-09-22 15:26:19.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(12, 'VIEW_MENU', '', 7, true, false, NULL, NULL, '2023-09-22 15:26:42.000', '1', '2023-09-22 15:26:42.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(13, 'CREATE_MENU', '', 7, true, false, NULL, NULL, '2023-09-22 15:26:59.000', '1', '2023-09-22 15:26:59.000', '1');
INSERT INTO public.permissions (id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(14, 'UPDATE_MENU', '', 7, true, false, NULL, NULL, '2023-09-22 15:27:16.000', '1', '2023-09-22 15:27:16.000', '1');


INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 1, true, false, NULL, NULL, '2024-03-14 07:06:29.415', '1', '2024-03-14 07:06:29.415', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 2, true, false, NULL, NULL, '2024-03-14 07:06:29.435', '1', '2024-03-14 07:06:29.435', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 3, true, false, NULL, NULL, '2024-03-14 07:06:29.431', '1', '2024-03-14 07:06:29.431', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 4, true, false, NULL, NULL, '2024-03-14 07:06:29.432', '1', '2024-03-14 07:06:29.432', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 5, true, false, NULL, NULL, '2024-03-14 07:06:29.432', '1', '2024-03-14 07:06:29.432', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 6, true, false, NULL, NULL, '2024-03-14 07:06:29.433', '1', '2024-03-14 07:06:29.433', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 7, true, false, NULL, NULL, '2024-03-14 07:06:29.433', '1', '2024-03-14 07:06:29.433', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 8, true, false, NULL, NULL, '2024-03-14 07:06:29.434', '1', '2024-03-14 07:06:29.434', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 9, true, false, NULL, NULL, '2024-03-14 07:06:29.434', '1', '2024-03-14 07:06:29.434', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 10, true, false, NULL, NULL, '2024-03-14 07:06:29.435', '1', '2024-03-14 07:06:29.435', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 11, true, false, NULL, NULL, '2024-03-14 07:06:29.435', '1', '2024-03-14 07:06:29.435', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 12, true, false, NULL, NULL, '2024-03-14 07:06:29.434', '1', '2024-03-14 07:06:29.434', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 13, true, false, NULL, NULL, '2024-03-14 07:06:29.435', '1', '2024-03-14 07:06:29.435', '1');
INSERT INTO public.role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by) VALUES(1, 14, true, false, NULL, NULL, '2024-03-14 07:06:29.436', '1', '2024-03-14 07:06:29.436', '1');


INSERT INTO public.roles
(id, role_name, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(1, 'SUPER-ADMIN', true, false, NULL, NULL, '2023-09-04 08:40:00.000', 'anonymousUser', NULL, NULL);



INSERT INTO public.user_roles
(id, user_id, role_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(1, 1, 1, true, false, NULL, NULL, '2023-09-04 08:40:00.000', 'anonymousUser', '2023-09-26 14:13:48.000', '1');


INSERT INTO public.users
(id, branch_id, username, fullname, staff_id, phone_number, "password", email, failed_attempt, "locked", last_login, is_active, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(1, '1', 'super-admin', 'Administrator', '23155', '092793059', '$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a', 'super-admin@aeon.com.kh', 0, false, NULL, true, false, '2023-09-04 08:40:00.000', 'anonymousUser', '2023-09-26 14:13:48.000', '1', NULL, NULL);