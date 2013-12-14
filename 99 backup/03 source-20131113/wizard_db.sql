/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : wizard_db

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-12-14 22:31:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `wizard_authority`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_authority`;
CREATE TABLE `wizard_authority` (
  `PK_ID` varchar(40) NOT NULL,
  `FK_ROLE_ID` varchar(20) NOT NULL,
  `FK_MENU_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`PK_ID`),
  KEY `FK_AUTH_ROLE_ID` (`FK_ROLE_ID`),
  KEY `FK_AUTH_MENU_ID` (`FK_MENU_ID`),
  CONSTRAINT `FK_AUTH_MENU_ID` FOREIGN KEY (`FK_MENU_ID`) REFERENCES `wizard_menu` (`PK_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_AUTH_ROLE_ID` FOREIGN KEY (`FK_ROLE_ID`) REFERENCES `wizard_role_info` (`PK_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_authority
-- ----------------------------
INSERT INTO `wizard_authority` VALUES ('225c422f-27b5-4939-86dd-15fe5d255ef9', '1', '1010');
INSERT INTO `wizard_authority` VALUES ('2cd6305f-2c48-4ef3-a827-713ad3238bc5', '1', '2020');
INSERT INTO `wizard_authority` VALUES ('82936115-9cc3-4fab-8ee7-eefd6ce348c4', '2', '1010');
INSERT INTO `wizard_authority` VALUES ('8ee7fb28-4224-41aa-a095-a79157dddf8f', '1', '1020');
INSERT INTO `wizard_authority` VALUES ('b3d6f14d-8325-49b5-86a4-e9dc21e31ede', '1', '1030');
INSERT INTO `wizard_authority` VALUES ('ca0a7acc-e4f3-4b9a-a3ef-19a177a0209f', '2', '9010');
INSERT INTO `wizard_authority` VALUES ('d8ffa387-e70f-4f37-b34c-aba8759b2228', '1', '1040');
INSERT INTO `wizard_authority` VALUES ('ef9d3b02-2fea-47cd-858e-2d893b241ed2', '1', '9010');
INSERT INTO `wizard_authority` VALUES ('f1114e58-b459-46ae-9c9b-f3d6efcd1ba2', '1', '2010');

-- ----------------------------
-- Table structure for `wizard_code`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_code`;
CREATE TABLE `wizard_code` (
  `PK_ID` varchar(20) NOT NULL,
  `TYPE_ID` varchar(20) NOT NULL,
  `CONTENT` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_code
-- ----------------------------
INSERT INTO `wizard_code` VALUES ('10', '0', '日志级别');
INSERT INTO `wizard_code` VALUES ('1010', '10', 'debug');
INSERT INTO `wizard_code` VALUES ('1020', '10', 'info');
INSERT INTO `wizard_code` VALUES ('1030', '10', 'warn');
INSERT INTO `wizard_code` VALUES ('1040', '10', 'error');
INSERT INTO `wizard_code` VALUES ('1050', '10', 'fatal');

-- ----------------------------
-- Table structure for `wizard_log_info`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_log_info`;
CREATE TABLE `wizard_log_info` (
  `PK_ID` varchar(20) NOT NULL,
  `LOG_LEVEL` varchar(10) NOT NULL,
  `LOG_TITLE` varchar(100) NOT NULL,
  `LOG_DETAIL` varchar(1000) DEFAULT NULL,
  `LOG_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_log_info
-- ----------------------------
INSERT INTO `wizard_log_info` VALUES ('1', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:44:42', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('10', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:45:51', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('11', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:46:00', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('12', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:46:00', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('13', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:47:15', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('14', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:47:16', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('15', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 19:10:09', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('16', '1020', '登录成功', 'zhanglizhi', '2013-01-04 19:10:10', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('17', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 19:11:11', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('18', '1020', '登录成功', 'zhanglizhi', '2013-01-04 19:11:11', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('19', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-12 16:33:44', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('2', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:45:14', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('20', '1020', '登录成功', 'zhanglizhi', '2013-08-12 16:33:44', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('21', '1020', '用户登录', '{\"pwd\":[\"\"],\"user\":[\"\"]}', '2013-08-13 09:12:22', '');
INSERT INTO `wizard_log_info` VALUES ('22', '1030', '登录失败', '', '2013-08-13 09:12:22', '');
INSERT INTO `wizard_log_info` VALUES ('23', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-13 09:13:02', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('24', '1020', '登录成功', 'zhanglizhi', '2013-08-13 09:13:04', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('25', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-21 09:59:42', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('26', '1020', '登录成功', 'zhanglizhi', '2013-08-21 09:59:43', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('27', '1020', '用户登录', '{\"pwd\":[\"123\"],\"user\":[\"admin\"]}', '2013-08-21 10:14:47', 'admin');
INSERT INTO `wizard_log_info` VALUES ('28', '1030', '登录失败', 'admin', '2013-08-21 10:14:47', 'admin');
INSERT INTO `wizard_log_info` VALUES ('29', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-21 10:15:04', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('3', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:45:22', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('30', '1020', '登录成功', 'zhanglizhi', '2013-08-21 10:15:04', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('31', '1020', '用户登录', '{\"pwd\":[\"\"],\"user\":[\"zhanglizhi\"]}', '2013-08-21 10:15:09', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('32', '1030', '登录失败', 'zhanglizhi', '2013-08-21 10:15:09', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('33', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-21 10:15:18', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('34', '1020', '登录成功', 'zhanglizhi', '2013-08-21 10:15:18', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('35', '1020', '用户登录', '{\"pwd\":[\"rgfre\"],\"user\":[\"jfhkjw\"]}', '2013-08-21 10:18:18', 'jfhkjw');
INSERT INTO `wizard_log_info` VALUES ('36', '1030', '登录失败', 'jfhkjw', '2013-08-21 10:18:18', 'jfhkjw');
INSERT INTO `wizard_log_info` VALUES ('37', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-21 10:18:31', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('38', '1020', '登录成功', 'zhanglizhi', '2013-08-21 10:18:31', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('39', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-08-21 10:19:43', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('4', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:45:25', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('40', '1020', '登录成功', 'zhanglizhi', '2013-08-21 10:19:43', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('41', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 13:54:23', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('42', '1020', '登录成功', 'zhanglizhi', '2013-10-08 13:54:24', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('43', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:09:13', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('44', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:09:13', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('45', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:11:08', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('46', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:11:08', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('47', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:15:05', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('48', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:15:05', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('49', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:25:21', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('5', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:45:30', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('50', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:25:22', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('51', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:53:05', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('52', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:53:05', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('53', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:54:14', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('54', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:54:14', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('55', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 14:56:56', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('56', '1020', '登录成功', 'zhanglizhi', '2013-10-08 14:56:56', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('57', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:21:58', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('58', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:21:58', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('59', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:25:25', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('6', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:45:30', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('60', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:25:25', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('61', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:42:45', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('62', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:42:45', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('63', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:46:27', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('64', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:46:28', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('65', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:50:57', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('66', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:50:57', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('67', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:51:34', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('68', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:51:34', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('69', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:53:44', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('7', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:45:37', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('70', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:53:44', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('71', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-08 15:58:12', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('72', '1020', '登录成功', 'zhanglizhi', '2013-10-08 15:58:12', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('73', '1020', '用户登录', '{\"pwd\":[\"1231\"],\"user\":[\"admin\"]}', '2013-10-16 14:35:26', 'admin');
INSERT INTO `wizard_log_info` VALUES ('74', '1030', '登录失败', 'admin', '2013-10-16 14:35:27', 'admin');
INSERT INTO `wizard_log_info` VALUES ('75', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-10-16 14:35:33', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('76', '1020', '登录成功', 'zhanglizhi', '2013-10-16 14:35:33', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('77', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-11-08 21:28:41', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('78', '1020', '登录成功', 'zhanglizhi', '2013-11-08 21:28:42', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('79', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-12-10 11:11:04', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('8', '1020', '登录成功', 'zhanglizhi', '2013-01-04 18:45:37', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('80', '1020', '登录成功', 'zhanglizhi', '2013-12-10 11:11:05', 'zhanglizhi');
INSERT INTO `wizard_log_info` VALUES ('9', '1020', '用户登录', '{\"pwd\":[\"042888\"],\"user\":[\"zhanglizhi\"]}', '2013-01-04 18:45:51', 'zhanglizhi');

-- ----------------------------
-- Table structure for `wizard_menu`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_menu`;
CREATE TABLE `wizard_menu` (
  `PK_ID` varchar(20) NOT NULL,
  `P_PK_ID` varchar(20) DEFAULT NULL,
  `MENU_NAME` varchar(60) NOT NULL,
  `MENU_PATH` varchar(100) DEFAULT '',
  `DISPLAY` varchar(1) NOT NULL,
  PRIMARY KEY (`PK_ID`),
  KEY `PK_ID` (`PK_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_menu
-- ----------------------------
INSERT INTO `wizard_menu` VALUES ('00', '0', '首页', 'wizard/system/welcome', '1');
INSERT INTO `wizard_menu` VALUES ('10', '0', '系统管理', '', '1');
INSERT INTO `wizard_menu` VALUES ('1010', '10', '修改密码', 'wizard/manage/modifyPwd', '1');
INSERT INTO `wizard_menu` VALUES ('1020', '10', '成员管理', 'wizard/manage/memberManage', '1');
INSERT INTO `wizard_menu` VALUES ('1030', '10', '菜单管理', 'wizard/manage/menuManage', '1');
INSERT INTO `wizard_menu` VALUES ('1040', '10', '权限管理', 'wizard/manage/permissionManage', '1');
INSERT INTO `wizard_menu` VALUES ('20', '0', '系统维护', '', '1');
INSERT INTO `wizard_menu` VALUES ('2010', '20', '日志维护', 'wizard/maintain/logMaintain', '1');
INSERT INTO `wizard_menu` VALUES ('2020', '20', '编码维护', 'wizard/maintain/codeMaintain', '1');
INSERT INTO `wizard_menu` VALUES ('90', '0', '小程序', '', '1');
INSERT INTO `wizard_menu` VALUES ('9010', '90', '画布', 'wizard-applet/canvas', '1');

-- ----------------------------
-- Table structure for `wizard_org_info`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_org_info`;
CREATE TABLE `wizard_org_info` (
  `PK_ID` varchar(20) NOT NULL,
  `ORG_NAME` varchar(60) NOT NULL,
  `ORG_DETAIL` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_org_info
-- ----------------------------
INSERT INTO `wizard_org_info` VALUES ('1', 'admin', '管理员');
INSERT INTO `wizard_org_info` VALUES ('2', 'guest', '访问者');
INSERT INTO `wizard_org_info` VALUES ('3', 'das', 'dfdfdfdf');

-- ----------------------------
-- Table structure for `wizard_role_info`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_role_info`;
CREATE TABLE `wizard_role_info` (
  `PK_ID` varchar(20) NOT NULL,
  `ROLE_NAME` varchar(60) NOT NULL,
  `ROLE_DETAIL` varchar(300) DEFAULT NULL,
  `FK_ORG_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`PK_ID`),
  KEY `FK_ORG_ID` (`FK_ORG_ID`),
  CONSTRAINT `FK_ORG_ID` FOREIGN KEY (`FK_ORG_ID`) REFERENCES `wizard_org_info` (`PK_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_role_info
-- ----------------------------
INSERT INTO `wizard_role_info` VALUES ('1', 'administrator', '管理员', '1');
INSERT INTO `wizard_role_info` VALUES ('2', 'human', '顾客', '2');

-- ----------------------------
-- Table structure for `wizard_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `wizard_user_info`;
CREATE TABLE `wizard_user_info` (
  `PK_ID` varchar(20) NOT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(80) NOT NULL,
  `USER_DETAIL` varchar(300) DEFAULT NULL,
  `FK_ROLE_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`PK_ID`),
  UNIQUE KEY `UNIQUE_INDEX_USER_NAME` (`USER_NAME`),
  KEY `FK_ROLE_ID` (`FK_ROLE_ID`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`FK_ROLE_ID`) REFERENCES `wizard_role_info` (`PK_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wizard_user_info
-- ----------------------------
INSERT INTO `wizard_user_info` VALUES ('1', 'zhanglizhi', '51F9AD0E2873048E37A47991D60F9C20', null, '1');
INSERT INTO `wizard_user_info` VALUES ('2', 'joaquinaimar', 'AA814A1F0CE59B4F1F3A31DD5296EBD7', null, '2');
