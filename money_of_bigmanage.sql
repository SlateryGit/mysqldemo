/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-21 13:49:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for money_of_bigmanage
-- ----------------------------
DROP TABLE IF EXISTS `money_of_bigmanage`;
CREATE TABLE `money_of_bigmanage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `gotrealmoney` int(255) DEFAULT NULL,
  `leftBigmoney` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of money_of_bigmanage
-- ----------------------------
INSERT INTO `money_of_bigmanage` VALUES ('1', '1', '2018-12-18 14:13:12', '5', '5');
