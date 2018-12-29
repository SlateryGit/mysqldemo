/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-21 13:49:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL,
  `friendid` int(11) DEFAULT NULL,
  `myid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('1', '2', '1');
INSERT INTO `friend` VALUES ('2', '3', '1');
INSERT INTO `friend` VALUES ('3', '4', '1');
INSERT INTO `friend` VALUES ('4', '4', '2');
INSERT INTO `friend` VALUES ('43', '5', '1');
