CREATE TABLE public.branches (
	id serial4 NOT NULL,
	branch_name varchar(150) NOT NULL,
	branch_code varchar(10) NULL,
	is_active int2 NULL DEFAULT 1,
	created_at timestamp NULL,
	created_by varchar(50) NULL,
	updated_at timestamp NULL,
	updated_by varchar(50) NULL,
	is_deleted int2 NOT NULL DEFAULT 0,
	deleted_at timestamp NULL,
	deleted_by varchar(50) NULL,
	CONSTRAINT branches_pkey PRIMARY KEY (id)
);

CREATE TABLE public.menus (
	id serial4 NOT NULL,
	menu_name varchar(100) NOT NULL,
	order_by numeric(20, 6) NULL DEFAULT 0.000000,
	url varchar(255) NULL,
	redirect varchar(255) NULL,
	component varchar(255) NULL,
	icon varchar(255) NULL,
	parent_id int8 NULL,
	is_active int2 NOT NULL DEFAULT 1,
	is_deleted int2 NOT NULL DEFAULT 0,
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
    is_active int2 NOT NULL DEFAULT 1,
	is_deleted int2 NOT NULL DEFAULT 0,
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
     is_active int2 NOT NULL DEFAULT 1,
	is_deleted int2 NOT NULL DEFAULT 0,
	CONSTRAINT oauth_refresh_token_pkey PRIMARY KEY (id)
);

CREATE TABLE public.permissions (
	id serial4 NOT NULL,
	permission_name varchar(100) NOT NULL,
	"type" varchar(50) NULL,
	menu_id int8 NULL,
	is_active int2 NOT NULL DEFAULT 1,
	is_deleted int2 NOT NULL DEFAULT 0,
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
	is_active int2 NOT NULL DEFAULT 1,
	is_deleted int2 NOT NULL DEFAULT 1,
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
	is_active int2 NOT NULL DEFAULT 1,
	is_deleted int2 NOT NULL DEFAULT 0,
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
	is_active int2 NULL DEFAULT 1,
	is_deleted int2 NULL DEFAULT 0,
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
	"locked" int2 NULL DEFAULT 0,
	last_login timestamp NULL,
	is_active int2 NULL DEFAULT 1,
	is_deleted int2 NULL DEFAULT 0,
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



INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(1, 'VIEW_DASHBOARD', NULL, 1, 1, 0, NULL, NULL, '2023-09-20 13:15:05.000', NULL, '2023-09-20 13:15:05.000', NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(2, 'VIEW_KHQR', NULL, 2, 1, 0, NULL, NULL, '2023-09-20 13:01:35.000', NULL, '2023-09-20 13:01:35.000', NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(3, 'UPDATE_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(4, 'CREATE_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(5, 'DOWNLOAD_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(6, 'SEND_MAIL_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(7, 'VIEW_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(8, 'CREATE_USER', '', 4, 1, 0, NULL, NULL, '2023-09-22 15:16:41.000', '1', '2023-09-22 15:16:41.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(9, 'UPDATE_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(10, 'DELETE_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(11, 'VIEW_PERMISSION', '', 5, 1, 0, NULL, NULL, '2023-09-22 15:24:06.000', '1', '2023-09-22 15:24:06.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(12, 'CREATE_PERMISSION', '', 5, 1, 0, NULL, NULL, '2023-09-22 15:24:41.000', '1', '2023-09-22 15:24:41.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(13, 'UPDATE_PERMISSION', '', 5, 1, 0, NULL, NULL, '2023-09-22 15:25:00.000', '1', '2023-09-22 15:25:00.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(14, 'VIEW_ROLE', '', 6, 1, 0, NULL, NULL, '2023-09-22 15:25:24.000', '1', '2023-09-22 15:25:34.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(15, 'CREATE_ROLE', '', 6, 1, 0, NULL, NULL, '2023-09-22 15:26:04.000', '1', '2023-09-22 15:26:04.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(16, 'UPDATE_ROLE', '', 6, 1, 0, NULL, NULL, '2023-09-22 15:26:19.000', '1', '2023-09-22 15:26:19.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(17, 'VIEW_MENU', '', 7, 1, 0, NULL, NULL, '2023-09-22 15:26:42.000', '1', '2023-09-22 15:26:42.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(18, 'CREATE_MENU', '', 7, 1, 0, NULL, NULL, '2023-09-22 15:26:59.000', '1', '2023-09-22 15:26:59.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(19, 'UPDATE_MENU', '', 7, 1, 0, NULL, NULL, '2023-09-22 15:27:16.000', '1', '2023-09-22 15:27:16.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(20, 'VIEW_BRANCH', '', 8, 1, 0, NULL, NULL, '2023-09-22 15:27:32.000', '1', '2023-09-22 15:27:32.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(21, 'CREATE_BRANCH', '', 8, 1, 0, NULL, NULL, '2023-09-22 15:27:42.000', '1', '2023-09-22 15:27:53.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(22, 'UPDATE_BRANCH', '', 8, 1, 0, NULL, NULL, '2023-09-22 15:28:12.000', '1', '2023-09-22 15:28:12.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(23, 'VIEW_EMAIL_HISTORY', '', 10, 1, 0, NULL, NULL, '2023-09-22 10:33:37.000', '1', '2023-09-22 10:33:37.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(24, 'VIEW_PAYMENT_HISTORY', '', 10, 1, 0, NULL, NULL, '2023-09-22 10:33:37.000', '1', '2023-09-22 10:33:37.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(25, 'VIEW_DASHBOARD', NULL, 1, 1, 0, NULL, NULL, '2023-09-20 13:15:05.000', NULL, '2023-09-20 13:15:05.000', NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(26, 'VIEW_KHQR', NULL, 2, 1, 0, NULL, NULL, '2023-09-20 13:01:35.000', NULL, '2023-09-20 13:01:35.000', NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(27, 'UPDATE_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(28, 'CREATE_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(29, 'DOWNLOAD_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(30, 'SEND_MAIL_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(31, 'VIEW_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(32, 'CREATE_USER', '', 4, 1, 0, NULL, NULL, '2023-09-22 15:16:41.000', '1', '2023-09-22 15:16:41.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(33, 'UPDATE_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(34, 'DELETE_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(35, 'VIEW_PERMISSION', '', 5, 1, 0, NULL, NULL, '2023-09-22 15:24:06.000', '1', '2023-09-22 15:24:06.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(36, 'CREATE_PERMISSION', '', 5, 1, 0, NULL, NULL, '2023-09-22 15:24:41.000', '1', '2023-09-22 15:24:41.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(37, 'UPDATE_PERMISSION', '', 5, 1, 0, NULL, NULL, '2023-09-22 15:25:00.000', '1', '2023-09-22 15:25:00.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(38, 'VIEW_ROLE', '', 6, 1, 0, NULL, NULL, '2023-09-22 15:25:24.000', '1', '2023-09-22 15:25:34.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(39, 'CREATE_ROLE', '', 6, 1, 0, NULL, NULL, '2023-09-22 15:26:04.000', '1', '2023-09-22 15:26:04.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(40, 'UPDATE_ROLE', '', 6, 1, 0, NULL, NULL, '2023-09-22 15:26:19.000', '1', '2023-09-22 15:26:19.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(41, 'VIEW_MENU', '', 7, 1, 0, NULL, NULL, '2023-09-22 15:26:42.000', '1', '2023-09-22 15:26:42.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(42, 'CREATE_MENU', '', 7, 1, 0, NULL, NULL, '2023-09-22 15:26:59.000', '1', '2023-09-22 15:26:59.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(43, 'UPDATE_MENU', '', 7, 1, 0, NULL, NULL, '2023-09-22 15:27:16.000', '1', '2023-09-22 15:27:16.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(44, 'VIEW_BRANCH', '', 8, 1, 0, NULL, NULL, '2023-09-22 15:27:32.000', '1', '2023-09-22 15:27:32.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(45, 'CREATE_BRANCH', '', 8, 1, 0, NULL, NULL, '2023-09-22 15:27:42.000', '1', '2023-09-22 15:27:53.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(46, 'UPDATE_BRANCH', '', 8, 1, 0, NULL, NULL, '2023-09-22 15:28:12.000', '1', '2023-09-22 15:28:12.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(47, 'VIEW_EMAIL_HISTORY', '', 10, 1, 0, NULL, NULL, '2023-09-22 10:33:37.000', '1', '2023-09-22 10:33:37.000', '1');
INSERT INTO public.permissions
(id, permission_name, "type", menu_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(48, 'VIEW_PAYMENT_HISTORY', '', 10, 1, 0, NULL, NULL, '2023-09-22 10:33:37.000', '1', '2023-09-22 10:33:37.000', '1');



INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(1, 1, 1, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(2, 1, 2, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(3, 1, 3, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(4, 1, 4, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(5, 1, 5, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(6, 1, 6, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(7, 1, 7, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(8, 1, 8, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(9, 1, 9, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(10, 1, 10, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(11, 1, 11, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(12, 1, 12, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(13, 1, 13, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(14, 1, 14, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(15, 1, 15, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(16, 1, 16, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(17, 1, 17, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(18, 1, 18, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(19, 1, 19, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(20, 1, 20, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(21, 1, 1, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(22, 1, 2, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(23, 1, 3, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(24, 1, 4, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(25, 1, 5, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(26, 1, 6, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(27, 1, 7, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(28, 1, 8, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(29, 1, 9, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(30, 1, 10, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(31, 1, 11, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(32, 1, 12, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(33, 1, 13, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(34, 1, 14, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(35, 1, 15, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(36, 1, 16, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(37, 1, 17, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(38, 1, 18, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(39, 1, 19, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');
INSERT INTO public.role_permissions
(id, role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(40, 1, 20, 1, 0, '2023-09-26 14:11:39.000', NULL, '2023-09-26 14:11:39.000', '1', '2023-09-26 14:11:39.000', '1');



INSERT INTO public.roles
(id, role_name, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(1, 'SUPER-ADMIN', 1, 0, NULL, NULL, '2023-09-04 08:40:00.000', 'anonymousUser', NULL, NULL);



INSERT INTO public.user_roles
(id, user_id, role_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES(1, 1, 1, 1, 0, NULL, NULL, '2023-09-04 08:40:00.000', 'anonymousUser', '2023-09-26 14:13:48.000', '1');


INSERT INTO public.users
(id, branch_id, username, fullname, staff_id, phone_number, "password", email, failed_attempt, "locked", last_login, is_active, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(1, '1', 'super-admin', 'Administrator', '23155', '092793059', '$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a', 'super-admin@aeon.com.kh', 0, 0, NULL, 1, 0, '2023-09-04 08:40:00.000', 'anonymousUser', '2023-09-26 14:13:48.000', '1', NULL, NULL);