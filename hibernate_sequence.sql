/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-18 22:21:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('34');
