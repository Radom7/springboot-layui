/*
 Navicat Premium Data Transfer

 Source Server         : 线下服务器
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 172.16.1.60:3306
 Source Schema         : tiantue

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 05/12/2018 10:56:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_admin_permission`;
CREATE TABLE `base_admin_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `pid` int(11) DEFAULT NULL COMMENT '父菜单id',
  `descpt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单url',
  `create_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '添加时间',
  `update_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标志（0:删除 1：存在）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_admin_permission
-- ----------------------------
INSERT INTO `base_admin_permission` VALUES (1, '系统管理', 0, '系统管理', '', '2018-11-30 10:27:34', '2018-11-30 10:27:34', 1);
INSERT INTO `base_admin_permission` VALUES (2, '账号管理', 1, '账号管理', '/user/userManage', '2018-11-30 11:44:41', '2018-11-30 11:56:34', 1);
INSERT INTO `base_admin_permission` VALUES (3, '角色管理', 1, '角色管理', '/role/roleManage', '2018-11-30 11:45:27', '2018-11-30 11:45:27', 1);
INSERT INTO `base_admin_permission` VALUES (7, '权限管理', 1, '权限管理', '/permission/permissionManage', '2018-11-30 11:48:35', '2018-11-30 15:13:38', 1);
INSERT INTO `base_admin_permission` VALUES (9, '基本设置', 0, '基本设置', '', '2018-11-30 12:10:32', '2018-11-30 12:10:32', 1);
INSERT INTO `base_admin_permission` VALUES (10, '服务类目管理', 9, '服务类目管理', '/goodsCategory/goodsCategoryManage', '2018-12-04 11:47:07', '2018-12-04 11:47:07', 1);
INSERT INTO `base_admin_permission` VALUES (11, '服务类型管理', 9, '服务类型管理', '/serviceType/serviceTypeManage', '2018-12-04 11:47:59', '2018-12-04 11:47:59', 1);
INSERT INTO `base_admin_permission` VALUES (12, '支付方式', 9, '支付方式', '/payplatform/payplatManage', '2018-12-04 11:48:44', '2018-12-04 11:48:44', 1);
INSERT INTO `base_admin_permission` VALUES (13, '银行管理', 9, '银行管理', '/bank/bankManage', '2018-12-04 11:49:13', '2018-12-04 11:49:13', 1);
INSERT INTO `base_admin_permission` VALUES (14, '省市区管理', 9, '省市区管理', '/position/positionManage', '2018-12-04 11:49:36', '2018-12-04 11:50:02', 1);

SET FOREIGN_KEY_CHECKS = 1;
