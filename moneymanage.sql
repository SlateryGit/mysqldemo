/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-18 22:21:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for moneymanage
-- ----------------------------
DROP TABLE IF EXISTS `moneymanage`;
CREATE TABLE `moneymanage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `gotmoney` int(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moneymanage
-- ----------------------------
INSERT INTO `moneymanage` VALUES ('1', '1', '2018-12-18 14:13:41', '900', '90.00', null);
