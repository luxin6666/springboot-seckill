/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : hzit

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 06/01/2020 15:04:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_ms
-- ----------------------------
DROP TABLE IF EXISTS `t_ms`;
CREATE TABLE `t_ms`  (
  `ms_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '抢购活动id',
  `good_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `count` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `price` int(11) NULL DEFAULT NULL COMMENT '商品价格',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态：0活动开始1活动进行中2活动结束',
  PRIMARY KEY (`ms_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ms
-- ----------------------------
INSERT INTO `t_ms` VALUES (1, 1, 10, 1, '2019-12-18 08:51:54', '2019-12-27 08:51:27', 0);

SET FOREIGN_KEY_CHECKS = 1;
