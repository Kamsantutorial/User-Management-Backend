-- Dumping database structure for user_management
CREATE DATABASE IF NOT EXISTS `user_management` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `user_management`;

-- Dumping structure for table branches
DROP TABLE IF EXISTS `branches`;
CREATE TABLE IF NOT EXISTS `branches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(150) NOT NULL,
  `branch_code` varchar(10) DEFAULT NULL,
  `is_active` tinyint(4) DEFAULT 1,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
);

-- Dumping data for table branches
INSERT INTO `branches` (`branch_name`, `branch_code`, `is_active`, `created_at`, `created_by`, `updated_at`, `updated_by`, `is_deleted`, `deleted_at`, `deleted_by`) 
VALUES
	('Pochentong', '0010', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('SMC', '0003', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('Siem Reap', '0002', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('Sihanouk Ville', '0009', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('Aeon Mall Sen Sok', '0011', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('Banteaymeanchey', '0008', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('Battambang', '0006', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	('Chbar Ampov', '0012', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	( 'Kompongcham', '0004', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	( 'Phnom Penh', '0001', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	( 'Takeo', '0005', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	( 'Head Quarter', '1311', 1, NULL, NULL, NULL, NULL, 0, NULL, NULL),
	( 'Aeon Mall Pnhom Penh', '0007', 1, '2023-03-21 03:46:04', NULL, '2023-03-21 04:08:44', NULL, 0, NULL, NULL),
	( 'Aeon Mall Mean Chey', '0013', 1, '2023-03-22 07:28:57', NULL, NULL, NULL, 0, NULL, NULL);

-- Dumping structure for table menus
DROP TABLE IF EXISTS `menus`;
CREATE TABLE IF NOT EXISTS `menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL,
  `order_by` decimal(20,6) DEFAULT 0.000000,
  `url` varchar(255) DEFAULT NULL,
  `redirect` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `is_active` binary(1) NOT NULL DEFAULT '1',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(100) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
);

-- Dumping data for table menus
INSERT INTO menus (menu_name,order_by,url,redirect,component,icon,parent_id,is_active,is_deleted,deleted_at,deleted_by,created_by,created_at,updated_by,updated_at) 
VALUES
	 ('Dashboard',1.000000,'/dashboard',NULL,'Dashboard','HomeOutlined',NULL,0x31,0,NULL,NULL,NULL,'2023-09-20 13:15:05',NULL,'2023-09-20 13:15:05'),
	 ('KHQR Management',1.000000,'/generate',NULL,'KHQRGeneration','QrcodeOutlined',NULL,0x31,0,NULL,NULL,NULL,'2023-09-20 13:01:35',NULL,'2023-09-20 13:01:35'),
	 ('Settings',1.000000,'/settings',NULL,'','SettingOutlined',NULL,0x31,0,NULL,NULL,NULL,'2023-09-20 13:55:24',NULL,'2023-09-20 13:55:24'),
	 ('Users',1.000000,'/user',NULL,'User','UserOutlined',3,0x31,0,NULL,NULL,NULL,'2023-09-20 13:56:26',NULL,'2023-09-20 13:56:26'),
	 ('Permissions',1.000000,'/permission',NULL,'Permission','CheckOutlined',3,0x31,0,NULL,NULL,NULL,'2023-09-20 14:54:06',NULL,'2023-09-20 14:54:06'),
	 ('Roles',1.000000,'/role',NULL,'Role','UserOutlined',3,0x31,0,NULL,NULL,NULL,'2023-09-20 14:54:43',NULL,'2023-09-20 14:54:43'),
	 ('Menus',1.000000,'/menu',NULL,'Menu','MenuOutlined',3,0x31,0,NULL,NULL,NULL,'2023-09-20 14:55:27',NULL,'2023-09-20 14:55:27'),
	 ('Branches',1.000000,'/branch',NULL,'Branch','CheckOutlined',3,0x31,0,NULL,NULL,NULL,'2023-09-20 15:25:47',NULL,'2023-09-20 15:25:47'),
	 ('Reporting',1.000000,'/report',NULL,NULL,'HistoryOutlined',NULL,0x31,0,NULL,NULL,'1','2023-09-22 10:31:01','1','2023-09-22 10:31:01'),
	 ('Email History',1.000000,'/email-history',NULL,'Email','HistoryOutlined',9,0x31,0,NULL,NULL,'1','2023-09-22 10:32:15','1','2023-09-22 10:32:15'),
   ('Payment History',1.000000,'/payment-history',NULL,'Transaction','HistoryOutlined',9,0x31,0,NULL,NULL,'1','2023-09-22 10:32:15','1','2023-09-22 10:32:15');

-- Dumping structure for table permissions
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT 1,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Dumping data for table permissions
INSERT INTO permissions (permission_name,`type`,menu_id,is_active,is_deleted,deleted_at,deleted_by,created_at,created_by,updated_at,updated_by) 
VALUES
	 ('VIEW_DASHBOARD',NULL,1,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	 ('VIEW_KHQR',NULL,2,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	 ('UPDATE_KHQR',NULL,2,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
   ('CREATE_KHQR',NULL,2,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
   ('DOWNLOAD_KHQR',NULL,2,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	 ('SEND_MAIL_KHQR',NULL,2,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	 ('VIEW_USER',NULL,4,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	 ('CREATE_USER','',4,1,0,NULL,NULL,'2023-09-22 15:16:41','1','2023-09-22 15:16:41','1'),
	 ('UPDATE_USER',NULL,4,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
     ('DELETE_USER',NULL,4,1,0,NULL,NULL,NULL,NULL,NULL,NULL),
	 ('VIEW_PERMISSION','',5,1,0,NULL,NULL,'2023-09-22 15:24:06','1','2023-09-22 15:24:06','1'),
	 ('CREATE_PERMISSION','',5,1,0,NULL,NULL,'2023-09-22 15:24:41','1','2023-09-22 15:24:41','1'),
	 ('UPDATE_PERMISSION','',5,1,0,NULL,NULL,'2023-09-22 15:25:00','1','2023-09-22 15:25:00','1'),
	 ('VIEW_ROLE','',6,1,0,NULL,NULL,'2023-09-22 15:25:24','1','2023-09-22 15:25:34','1'),
	 ('CREATE_ROLE','',6,1,0,NULL,NULL,'2023-09-22 15:26:04','1','2023-09-22 15:26:04','1'),
	 ('UPDATE_ROLE','',6,1,0,NULL,NULL,'2023-09-22 15:26:19','1','2023-09-22 15:26:19','1'),
	 ('VIEW_MENU','',7,1,0,NULL,NULL,'2023-09-22 15:26:42','1','2023-09-22 15:26:42','1'),
	 ('CREATE_MENU','',7,1,0,NULL,NULL,'2023-09-22 15:26:59','1','2023-09-22 15:26:59','1'),
	 ('UPDATE_MENU','',7,1,0,NULL,NULL,'2023-09-22 15:27:16','1','2023-09-22 15:27:16','1'),
	 ('VIEW_BRANCH','',8,1,0,NULL,NULL,'2023-09-22 15:27:32','1','2023-09-22 15:27:32','1'),
	 ('CREATE_BRANCH','',8,1,0,NULL,NULL,'2023-09-22 15:27:42','1','2023-09-22 15:27:53','1'),
	 ('UPDATE_BRANCH','',8,1,0,NULL,NULL,'2023-09-22 15:28:12','1','2023-09-22 15:28:12','1'),
	 ('VIEW_EMAIL_HISTORY','',10,1,0,NULL,NULL,'2023-09-22 10:33:37','1','2023-09-22 10:33:37','1'),
   ('VIEW_PAYMENT_HISTORY','',10,1,0,NULL,NULL,'2023-09-22 10:33:37','1','2023-09-22 10:33:37','1');

-- Dumping structure for table roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT 1,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Dumping data for table roles
INSERT INTO roles (role_name,is_active,is_deleted,deleted_at,deleted_by,created_at,created_by,updated_at,updated_by) 
VALUES
	 ('SUPER-ADMIN',1,0,NULL,'',NULL,NULL,NULL,NULL);

-- Dumping structure for table role_permissions
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE IF NOT EXISTS `role_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(50) NOT NULL,
  `permission_id` varchar(50) NOT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT 1,
  `is_deleted` tinyint(4) NOT NULL DEFAULT 1,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Dumping data for table role_permissions
INSERT INTO role_permissions (role_id,permission_id,is_active,is_deleted,deleted_at,deleted_by,created_at,created_by,updated_at,updated_by) 
VALUES
	 ('1','1',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','2',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','3',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','4',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','5',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','6',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','7',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','8',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','9',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','10',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','11',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','12',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','13',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','14',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','15',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','16',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','17',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','18',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','19',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1'),
	 ('1','20',1,0,'2023-09-26 14:11:39',NULL,NULL,NULL,'2023-09-26 14:11:39','1');

-- Dumping structure for table users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id` varchar(10) DEFAULT NULL,
    `username` varchar(100) NOT NULL,
    `fullname` varchar(100) DEFAULT NULL,
    `staff_id` varchar(50) DEFAULT NULL,
    `phone_number` varchar(50) DEFAULT NULL,
    `password` text DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    `failed_attempt` int(11) DEFAULT 0,
    `locked` tinyint(4) DEFAULT 0,
    `last_login` datetime DEFAULT NULL,
    `is_active` tinyint(4) DEFAULT 1,
    `is_deleted` tinyint(4) DEFAULT 0,
    `created_at` timestamp NULL DEFAULT NULL,
    `created_by` varchar(100) DEFAULT NULL,
    `updated_at` timestamp NULL DEFAULT NULL,
    `updated_by` varchar(100) DEFAULT NULL,
    `deleted_at` timestamp NULL DEFAULT NULL,
    `deleted_by` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- Dumping data for table users
INSERT INTO users (branch_id,username,`password`,fullname,staff_id,phone_number,email,failed_attempt,locked,last_login,is_active,is_deleted,created_at,created_by,updated_at,updated_by,deleted_at,deleted_by) VALUES
	 ('1','super-admin','$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a','Administrator','23155','092793059','super-admin@aeon.com.kh',0,0,NULL,1,0,'2023-09-04 08:40:00','anonymousUser','2023-09-26 14:13:48','1',NULL,NULL);

-- Dumping structure for table user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `is_active` tinyint(4) DEFAULT 1,
  `is_deleted` tinyint(4) DEFAULT 0,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `deleted_by` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Dumping data for table user_roles
INSERT INTO user_roles (user_id,role_id,is_active,is_deleted,deleted_at,deleted_by,created_at,created_by,updated_at,updated_by) VALUES
	 ('1','1',1,0,NULL,NULL,'2023-09-04 15:40:00','anonymousUser','2023-09-26 14:13:48','1');

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
);

INSERT INTO `oauth_client_details` VALUES ('admin',NULL,'$2a$10$9VtTIDoB0ZPFrVNBonXpJ.8TBtyUFE1o/48hczsCdQqnG9W7jkY7a','read, write','client_credentials,password,refresh_token',NULL,NULL,3600,3600,'{}','true');

drop table if exists `oauth_access_token`;
create table `oauth_access_token` (
  `id` bigint(20) not null auto_increment,
  `token_id` varchar(255) default null,
  `token`text default null,
  `authentication_id` varchar(255) not null,
    `user_id` bigint(20) NOT NULL,
  `user_name` varchar(255) default null,
  `client_id` varchar(255) default null,
  `authentication` text default null,
  `refresh_token` varchar(255) default null,
  `is_active` tinyint(4) default 1,
  `is_deleted` tinyint(4) default 0,
  `created_at` timestamp null default null,
  `created_by` varchar(100) default null,
  `updated_at` timestamp null default null,
  `updated_by` varchar(100) default null,
  `deleted_at` timestamp null default null,
  `deleted_by` varchar(100) default null,
  primary key (`id`)
);

drop table if exists `oauth_refresh_token`;
create table `oauth_refresh_token` (
  `id` bigint(20) not null auto_increment,
  `token_id` varchar(255) default null,
  `token` text default null,
  `authentication` text default null,
  `is_active` tinyint(4) default 1,
  `is_deleted` tinyint(4) default 0,
  `created_at` timestamp null default null,
  `created_by` varchar(100) default null,
  `updated_at` timestamp null default null,
  `updated_by` varchar(100) default null,
  `deleted_at` timestamp null default null,
  `deleted_by` varchar(100) default null,
   primary key (`id`)
);

