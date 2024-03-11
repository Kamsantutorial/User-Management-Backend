-- Creating table branches
CREATE TABLE IF NOT EXISTS branches (
  id SERIAL PRIMARY KEY,
  branch_name varchar(150) NOT NULL,
  branch_code varchar(10),
  is_active smallint DEFAULT 1,
  created_at timestamp DEFAULT NULL,
  created_by varchar(50),
  updated_at timestamp DEFAULT NULL,
  updated_by varchar(50),
  is_deleted smallint NOT NULL DEFAULT 0,
  deleted_at timestamp DEFAULT NULL,
  deleted_by varchar(50)
);

-- Inserting data into branches
INSERT INTO branches (branch_name, branch_code, is_active)
VALUES
  ('Pochentong', '0010', 1),
  ('SMC', '0003', 1),
  ('Siem Reap', '0002', 1),
  ('Sihanouk Ville', '0009', 1),
  ('Aeon Mall Sen Sok', '0011', 1),
  ('Banteaymeanchey', '0008', 1),
  ('Battambang', '0006', 1),
  ('Chbar Ampov', '0012', 1),
  ('Kompongcham', '0004', 1),
  ('Phnom Penh', '0001', 1),
  ('Takeo', '0005', 1),
  ('Head Quarter', '1311', 1),
  ('Aeon Mall Pnhom Penh', '0007', 1, '2023-03-21 03:46:04', NULL, '2023-03-21 04:08:44', NULL, 0, NULL, NULL),
  ('Aeon Mall Mean Chey', '0013', 1, '2023-03-22 07:28:57', NULL, NULL, NULL, 0, NULL, NULL);

-- Creating table menus
CREATE TABLE IF NOT EXISTS menus (
  id SERIAL PRIMARY KEY,
  menu_name varchar(100) NOT NULL,
  order_by numeric(20,6) DEFAULT 0.000000,
  url varchar(255),
  redirect varchar(255),
  component varchar(255),
  icon varchar(255),
  parent_id bigint,
  is_active smallint NOT NULL DEFAULT 1,
  is_deleted smallint NOT NULL DEFAULT 0,
  deleted_at timestamp,
  deleted_by varchar(100),
  created_by varchar(100),
  created_at timestamp DEFAULT current_timestamp,
  updated_by varchar(100),
  updated_at timestamp DEFAULT current_timestamp
);

-- Inserting data into menus
INSERT INTO menus (menu_name, order_by, url, redirect, component, icon, parent_id, is_active, is_deleted)
VALUES
  ('Dashboard', 1.000000, '/dashboard', NULL, 'Dashboard', 'HomeOutlined', NULL, 1, 0),
  ('KHQR Management', 1.000000, '/generate', NULL, 'KHQRGeneration', 'QrcodeOutlined', NULL, 1, 0),
  ('Settings', 1.000000, '/settings', NULL, '', 'SettingOutlined', NULL, 1, 0),
  ('Users', 1.000000, '/user', NULL, 'User', 'UserOutlined', 3, 1, 0),
  ('Permissions', 1.000000, '/permission', NULL, 'Permission', 'CheckOutlined', 3, 1, 0),
  ('Roles', 1.000000, '/role', NULL, 'Role', 'UserOutlined', 3, 1, 0),
  ('Menus', 1.000000, '/menu', NULL, 'Menu', 'MenuOutlined', 3, 1, 0),
  ('Branches', 1.000000, '/branch', NULL, 'Branch', 'CheckOutlined', 3, 1, 0),
  ('Reporting', 1.000000, '/report', NULL, NULL, 'HistoryOutlined', NULL, 1, 0),
  ('Email History', 1.000000, '/email-history', NULL, 'Email', 'HistoryOutlined', 9, 1, 0),
  ('Payment History', 1.000000, '/payment-history', NULL, 'Transaction', 'HistoryOutlined', 9, 1, 0);

-- Creating table permissions
CREATE TABLE IF NOT EXISTS permissions (
  id SERIAL PRIMARY KEY,
  permission_name varchar(100) NOT NULL,
  type varchar(50),
  menu_id bigint,
  is_active smallint NOT NULL DEFAULT 1,
  is_deleted smallint NOT NULL DEFAULT 0,
  deleted_at timestamp,
  deleted_by varchar(100),
  created_at timestamp,
  created_by varchar(100),
  updated_at timestamp,
  updated_by varchar(100)
);

-- Inserting data into permissions
INSERT INTO permissions (permission_name, type, menu_id, is_active, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES
  ('VIEW_DASHBOARD', NULL, 1, 1, 0, '2023-09-20 13:15:05', NULL, '2023-09-20 13:15:05', NULL),
  ('VIEW_KHQR', NULL, 2, 1, 0, '2023-09-20 13:01:35', NULL, '2023-09-20 13:01:35', NULL),
  ('UPDATE_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL),
  ('CREATE_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL),
  ('DOWNLOAD_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL),
  ('SEND_MAIL_KHQR', NULL, 2, 1, 0, NULL, NULL, NULL, NULL),
  ('VIEW_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL),
  ('CREATE_USER', '', 4, 1, 0, '2023-09-22 15:16:41', '1', '2023-09-22 15:16:41', '1'),
  ('UPDATE_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL),
  ('DELETE_USER', NULL, 4, 1, 0, NULL, NULL, NULL, NULL),
  ('VIEW_PERMISSION', '', 5, 1, 0, '2023-09-22 15:24:06', '1', '2023-09-22 15:24:06', '1'),
  ('CREATE_PERMISSION', '', 5, 1, 0, '2023-09-22 15:24:41', '1', '2023-09-22 15:24:41', '1'),
  ('UPDATE_PERMISSION', '', 5, 1, 0, '2023-09-22 15:25:00', '1', '2023-09-22 15:25:00', '1'),
  ('VIEW_ROLE', '', 6, 1, 0, '2023-09-22 15:25:24', '1', '2023-09-22 15:25:34', '1'),
  ('CREATE_ROLE', '', 6, 1, 0, '2023-09-22 15:26:04', '1', '2023-09-22 15:26:04', '1'),
  ('UPDATE_ROLE', '', 6, 1, 0, '2023-09-22 15:26:19', '1', '2023-09-22 15:26:19', '1'),
  ('VIEW_MENU', '', 7, 1, 0, '2023-09-22 15:26:42', '1', '2023-09-22 15:26:42', '1'),
  ('CREATE_MENU', '', 7, 1, 0, '2023-09-22 15:26:59', '1', '2023-09-22 15:26:59', '1'),
  ('UPDATE_MENU', '', 7, 1, 0, '2023-09-22 15:27:16', '1', '2023-09-22 15:27:16', '1'),
  ('VIEW_BRANCH', '', 8, 1, 0, '2023-09-22 15:27:32', '1', '2023-09-22 15:27:32', '1'),
  ('CREATE_BRANCH', '', 8, 1, 0, '2023-09-22 15:27:42', '1', '2023-09-22 15:27:53', '1'),
  ('UPDATE_BRANCH', '', 8, 1, 0, '2023-09-22 15:28:12', '1', '2023-09-22 15:28:12', '1'),
  ('VIEW_EMAIL_HISTORY', '', 10, 1, 0, '2023-09-22 10:33:37', '1', '2023-09-22 10:33:37', '1'),
  ('VIEW_PAYMENT_HISTORY', '', 10, 1, 0, '2023-09-22 10:33:37', '1', '2023-09-22 10:33:37', '1');

-- Creating table roles
CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY,
  role_name varchar(100) NOT NULL,
  is_active smallint NOT NULL DEFAULT 1,
  is_deleted smallint NOT NULL DEFAULT 0,
  deleted_at timestamp,
  deleted_by varchar(100),
  created_at timestamp,
  created_by varchar(100),
  updated_at timestamp,
  updated_by varchar(100)
);

-- Inserting data into roles
INSERT INTO roles (role_name, is_active, is_deleted, created_at, created_by, updated_at, updated_by)
VALUES ('SUPER-ADMIN', 1, 0, '2023-09-04 08:40:00', 'anonymousUser', NULL, NULL);

-- Creating table role_permissions
CREATE TABLE IF NOT EXISTS role_permissions (
  id SERIAL PRIMARY KEY,
  role_id bigint NOT NULL,
  permission_id bigint NOT NULL,
  is_active smallint NOT NULL DEFAULT 1,
  is_deleted smallint NOT NULL DEFAULT 1,
  deleted_at timestamp,
  deleted_by varchar(100),
  created_at timestamp,
  created_by varchar(100),
  updated_at timestamp,
  updated_by varchar(100)
);

-- Inserting data into role_permissions
INSERT INTO role_permissions (role_id, permission_id, is_active, is_deleted, deleted_at, deleted_by, created_at, created_by, updated_at, updated_by)
VALUES
  (1, 1, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 2, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 3, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 4, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 5, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 6, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 7, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 8, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 9, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 10, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 11, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 12, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 13, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 14, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 15, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 16, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 17, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 18, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 19, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1'),
  (1, 20, 1, 0, '2023-09-26 14:11:39', NULL, '2023-09-26 14:11:39', '1', '2023-09-26 14:11:39', '1');

-- Creating table users
CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  branch_id varchar(10),
  username varchar(100) NOT NULL,
  fullname varchar(100),
  staff_id varchar(50),
  phone_number varchar(50),
  password text,
  email varchar(100),
  failed_attempt int DEFAULT 0,
  locked smallint DEFAULT 0,
  last_login timestamp,
  is_active smallint DEFAULT 1,
  is_deleted smallint DEFAULT 0,
  created_at timestamp,
  created_by varchar(100),
  updated_at timestamp,
  updated_by varchar(100),
  deleted_at timestamp,
  deleted_by varchar(100)
);

-- Inserting data into users
INSERT INTO users (branch_id, username, password, fullname, staff_id, phone_number, email, failed_attempt, locked, created_at, created_by, updated_at, updated_by)
VALUES ('1', 'super-admin', '$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a', 'Administrator', '23155', '092793059', 'super-admin@aeon.com.kh', 0, 0, '2023-09-04 08:40:00', 'anonymousUser', '2023-09-26 14:13:48', '1');

-- Creating table user_roles
CREATE TABLE IF NOT EXISTS user_roles (
  id SERIAL PRIMARY KEY,
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  is_active smallint DEFAULT 1,
  is_deleted smallint DEFAULT 0,
  deleted_at timestamp,
  deleted_by varchar(100),
  created_at timestamp,
  created_by varchar(100),
  updated_at timestamp,
  updated_by varchar(100)
);

-- Inserting data into user_roles
INSERT INTO user_roles (user_id, role_id, created_at, created_by, updated_at, updated_by)
VALUES (1, 1, '2023-09-04 08:40:00', 'anonymousUser', '2023-09-26 14:13:48', '1');

-- Creating table oauth_client_details
CREATE TABLE oauth_client_details (
  client_id varchar(256) NOT NULL PRIMARY KEY,
  resource_ids varchar(256),
  client_secret varchar(256),
  scope varchar(256),
  authorized_grant_types varchar(256),
  web_server_redirect_uri varchar(256),
  authorities varchar(256),
  access_token_validity int,
  refresh_token_validity int,
  additional_information varchar(4096),
  autoapprove varchar(256)
);

-- Inserting data into oauth_client_details
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('admin', '$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a', 'read, write', 'client_credentials,password,refresh_token', 3600, 3600, '{}', 'true');

-- Creating table oauth_access_token
CREATE TABLE oauth_access_token (
  id SERIAL PRIMARY KEY,
  token_id varchar(255),
  token text,
  authentication_id varchar(255) NOT NULL,
  user_id bigint NOT NULL,
  user_name varchar(255),
  client_id varchar(255),
  authentication text,
  refresh_token varchar(255),
  expired_at timestamp NOT NULL,
  created_at timestamp NOT NULL,
  created_by varchar(100),
  updated_at timestamp NOT NULL,
  updated_by varchar(100),
  deleted_at timestamp,
  deleted_by varchar(100)
);

-- Creating table oauth_refresh_token
CREATE TABLE oauth_refresh_token (
  id SERIAL PRIMARY KEY,
  token_id varchar(255),
  token text,
  authentication text,
  expired_at timestamp NOT NULL,
  created_at timestamp NOT NULL,
  created_by varchar(100),
  updated_at timestamp NOT NULL,
  updated_by varchar(100),
  deleted_at timestamp,
  deleted_by varchar(100)
);

-- Creating table oauth_approvals
CREATE TABLE oauth_approvals (
  id SERIAL PRIMARY KEY,
  userId varchar(256),
  clientId varchar(256),
  scope varchar(256),
  status varchar(10),
  expiresAt timestamp,
  lastModifiedAt timestamp
);
