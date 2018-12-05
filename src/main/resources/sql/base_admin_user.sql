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

 Date: 05/12/2018 10:56:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `base_admin_user`;
CREATE TABLE `base_admin_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sys_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统用户名称',
  `sys_user_pwd` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系统用户密码',
  `role_id` int(255) DEFAULT NULL COMMENT '角色',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `reg_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登记时间',
  `user_status` int(1) NOT NULL DEFAULT 0 COMMENT '状态（0：无效；1：有效）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理员帐号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_admin_user
-- ----------------------------
INSERT INTO `base_admin_user` VALUES (1, 'admin', '3ef7164d1f6167cb9f2658c07d3c2f0a', 1, '13411182215', '2018-11-22 10:57:33', 1);
INSERT INTO `base_admin_user` VALUES (2, 'jackson', '6565673a6caee66a6acbd51415bddbda', 2, '19563648695', '2018-11-22 10:57:33', 1);
INSERT INTO `base_admin_user` VALUES (4, 'alice', '5e1030d25f5ca46aac4c0369b908d762', 2, '11111111111', '2018-11-22 11:01:58', 1);

SET FOREIGN_KEY_CHECKS = 1;
