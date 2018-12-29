/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-21 13:48:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ordermanage
-- ----------------------------
DROP TABLE IF EXISTS `ordermanage`;
CREATE TABLE `ordermanage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(255) NOT NULL,
  `receiver` int(255) NOT NULL,
  `flowerid` int(255) NOT NULL,
  `value` double(255,0) DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `quantity` int(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `accept` int(11) DEFAULT NULL,
  `sent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordermanage
-- ----------------------------
INSERT INTO `ordermanage` VALUES ('1', '1', '2', '3', '6675', null, '2018-10-30 16:26:54', '2', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('24', '1', '2', '3', '1280', null, '2018-12-18 09:31:05', '1', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('25', '1', '2', '10', '600', null, '2018-12-18 09:44:11', '20', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('26', '1', '2', '10', '600', null, '2018-12-18 09:45:27', '20', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('27', '1', '2', '12', '2500', null, '2018-12-18 10:24:16', '50', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('28', '1', '2', '12', '1000', null, '2018-12-18 10:25:53', '20', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('30', '1', '2', '10', '30', '2018-12-18 10:32:19', '2018-12-18 10:32:04', '1', '可添加留言', '-1', '1');
INSERT INTO `ordermanage` VALUES ('31', '2', '1', '3', '2560', null, '2018-12-18 12:17:35', '2', null, '-1', '-1');
INSERT INTO `ordermanage` VALUES ('32', '2', '1', '7', '496', '2018-12-18 12:18:01', '2018-12-18 12:17:53', '2', '可添加留言', '0', '0');
INSERT INTO `ordermanage` VALUES ('33', '3', '1', '10', '30', '2018-12-18 21:08:04', null, '1', null, '1', '1');
INSERT INTO `ordermanage` VALUES ('34', '4', '1', '5', '990', '2018-12-18 21:08:34', null, '1', null, '1', '0');
INSERT INTO `ordermanage` VALUES ('35', '4', '2', '5', '1980', '2018-12-18 21:10:08', null, '2', null, '-1', '1');
INSERT INTO `ordermanage` VALUES ('36', '2', '1', '10', '30', '2018-12-19 13:44:24', null, '1', null, '-1', '0');
INSERT INTO `ordermanage` VALUES ('39', '5', '1', '10', '3000', '2018-12-19 16:30:59', null, '300', null, '1', '1');
INSERT INTO `ordermanage` VALUES ('40', '5', '3', '3', '2560', '2018-12-19 16:31:43', null, '2', null, '-1', '0');
INSERT INTO `ordermanage` VALUES ('41', '1', '2', '11', '40', '2018-12-19 14:02:43', '2018-12-19 14:02:40', '2', '这位大佬很忙，没有留言呢 ', '-1', '1');
INSERT INTO `ordermanage` VALUES ('44', '1', '5', '8', '348', '2018-12-19 14:41:19', '2018-12-19 14:41:11', '3', '这位大佬很忙，没有留言呢 ', '-1', '0');
INSERT INTO `ordermanage` VALUES ('52', '1', '2', '3', '5120', '2018-12-20 06:18:25', '2018-12-20 06:18:15', '4', '这位大佬很忙，没有留言呢 ', '-1', '0');
INSERT INTO `ordermanage` VALUES ('54', '5', '2', '4', '4400', '2018-12-20 06:26:07', '2018-12-20 06:26:03', '5', '这位大佬很忙，没有留言呢 ', '-1', '0');
INSERT INTO `ordermanage` VALUES ('56', '55', '1', '5', '2970', '2018-12-20 08:32:59', '2018-12-20 08:32:46', '3', 'hhh', '0', '0');
INSERT INTO `ordermanage` VALUES ('59', '1', '2', '3', '1280', '2018-12-20 13:21:03', '2018-12-20 13:16:05', '1', '这位大佬很忙，没有留言呢 ', '-1', '0');
INSERT INTO `ordermanage` VALUES ('61', '1', '2', '6', '2360', '2018-12-20 14:47:41', '2018-12-20 14:47:39', '2', '这位大佬很忙，没有留言呢 ', '-1', '0');
