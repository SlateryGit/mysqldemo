/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-20 17:41:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flower
-- ----------------------------
DROP TABLE IF EXISTS `flower`;
CREATE TABLE `flower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `quantity` int(255) DEFAULT NULL,
  `means` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flower
-- ----------------------------
INSERT INTO `flower` VALUES ('1', '向阳花', '0.00', '999999', '接受好友', '/flower/sun.jpg', '3');
INSERT INTO `flower` VALUES ('2', '含羞草', '0.00', '999999', '拒绝好友', '/flower/shy.jpg', '3');
INSERT INTO `flower` VALUES ('3', '华为礼盒', '1280.00', '500', '123', '/flower/hwsuit.jpg', '1');
INSERT INTO `flower` VALUES ('4', '水晶球', '880.00', '300', null, '/flower/crystalball.jpg', '1');
INSERT INTO `flower` VALUES ('5', '书签套装', '990.00', '300', null, '/flower/pen.jpg', '1');
INSERT INTO `flower` VALUES ('6', '遇见杯', '1180.00', '300', null, '/flower/meet.jpg', '1');
INSERT INTO `flower` VALUES ('7', '玫瑰', '248.00', '200', null, '/flower/rose.jpg', '2');
INSERT INTO `flower` VALUES ('8', '百合', '116.00', '999', '543', '/flower/f1.jpg', '2');
INSERT INTO `flower` VALUES ('9', '发财树', '249.00', '999', null, '/flower/f2.jpg', '2');
INSERT INTO `flower` VALUES ('10', '黄金花', '30.00', '99999', null, '/flower/v1.jpg', '3');
INSERT INTO `flower` VALUES ('11', '白金花', '20.00', '99999', '和', '/flower/v2.jpg', '3');
INSERT INTO `flower` VALUES ('12', '兰花', '50.00', '999999', null, '/flower/v3.jpg', '3');

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
INSERT INTO `hibernate_sequence` VALUES ('57');

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of moneymanage
-- ----------------------------
INSERT INTO `moneymanage` VALUES ('1', '1', '2018-12-18 14:13:41', '900', '90.00', null);
INSERT INTO `moneymanage` VALUES ('45', '1', '2018-12-20 06:09:20', '6480', '648.00', null);
INSERT INTO `moneymanage` VALUES ('46', '1', '2018-12-20 06:09:27', '6480', '648.00', null);
INSERT INTO `moneymanage` VALUES ('47', '1', '2018-12-20 06:09:48', '2000', '200.00', null);
INSERT INTO `moneymanage` VALUES ('48', '2', '2018-12-20 06:11:48', '6480', '648.00', null);
INSERT INTO `moneymanage` VALUES ('50', '2', '2018-12-20 06:17:06', '6480', '648.00', null);
INSERT INTO `moneymanage` VALUES ('51', '2', '2018-12-20 06:17:20', '648000', '64800.00', null);
INSERT INTO `moneymanage` VALUES ('53', '5', '2018-12-20 06:25:38', '6480', '648.00', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

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
INSERT INTO `ordermanage` VALUES ('56', '55', '1', '5', '2970', '2018-12-20 08:32:59', '2018-12-20 08:32:46', '3', 'hhh', '-1', '0');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `ismale` int(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `Signature` varchar(255) DEFAULT NULL,
  `Icon` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `money` int(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `bigmoney` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '张0', '18', '0', '1100', 'shax', '0011', 'beijin0', 'singj', '/Icon/13000000009_225210.jpg', '/photo/13000000009_225210.jpg', '4392', '2018-09-11', '59');
INSERT INTO `userinfo` VALUES ('2', '张1', '18', '0', '1122', '2b', '2211', 'beijin1', 'Sing', '/Icon/13000000009_225210.jpg', '/defaultIcon.jpg', '3058', '2000-02-29', '99');
INSERT INTO `userinfo` VALUES ('3', '张2', '18', '0', '1133', '3b', '3311', 'beijin2', 't2', null, null, '1000', '2018-10-03', '33');
INSERT INTO `userinfo` VALUES ('4', '张3', '18', '0', '1144', '4b', '4411', 'beijin3', 't3', null, null, '1000', '2018-10-30', '44');
INSERT INTO `userinfo` VALUES ('5', '张4', '18', '1', '1155', '5b', '5511', 'beijin4', 't4', null, null, '3080', '2018-11-28', '11');
INSERT INTO `userinfo` VALUES ('6', 'Three', '1938', '0', '3333', 'sansam', '333333', 'Mars', 'sign', '', null, '0', '1981-07-16', '0');
INSERT INTO `userinfo` VALUES ('7', 'Four', '3920', '0', '4444', 'sansam', '444444', 'Mars', 'find me', '', null, '0', '1998-12-31', '0');
INSERT INTO `userinfo` VALUES ('8', 'Five', '-79', '0', '5555', 'Fivvr', '555555', 'Mars', 'find me', '', null, '0', '1997-12-31', '0');
INSERT INTO `userinfo` VALUES ('9', 'Six', '21', '0', '6666', '666', '666666', 'Mars', 'sign', '', null, '0', '1997-12-31', '0');
INSERT INTO `userinfo` VALUES ('10', 'Seven', '19', '0', '7777', 'Week', '777777', 'Mars', 'find me', '', null, '0', '2000-01-01', '0');
INSERT INTO `userinfo` VALUES ('11', 'cs', '1', '0', '2216', 'fasf', '123456', 'Mars', '', '', null, '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('12', 'Nine', '1', '0', '9999', 'phone', '123456', 'Mars', '', '', null, '0', '2018-11-29', '0');
INSERT INTO `userinfo` VALUES ('13', 'Fourp', '1', '0', '44456', 'sansam', '123456', '', '', '', null, '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('14', 'privphone', '1', '1', '3569', 'sansam', '123456', 'sadffd', '', '', null, '0', '2018-11-28', '0');
INSERT INTO `userinfo` VALUES ('15', 'allphone', '1', '1', '13000009220', 'fasf', '123456', 'jkb', '', '', null, '0', '2018-11-28', '0');
INSERT INTO `userinfo` VALUES ('16', 'bub', '1', '0', '13000000897', 'ytv', '999999', 'Mars', 'sign', '', null, '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('17', 'bub', '1', '0', '9548', 'ytv', '999999', 'Mars', 'sign', '', null, '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('20', '123$4IIcon', '1', '0', '13000001234', 'sfadlj', '7777777', 'Mars', 'sign', '\\/img\\/Icon\\/13000001234223555.jpg', null, '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('21', 'Fxxc', '1', '0', '13000000009', 'faww', '987654', 'Mars', 'sign', '\\img\\Icon\\13000000009_225210.jpg', '\\img\\photo\\13000000009_225210.jpg', '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('33', 'sq', '4', '1', '13000009876', 'sq', '000000', 'Mars', 'sf', '\\Icon\\13000009876_221949.jpg', '\\photo\\13000009876_221949.jpg', '0', '2015-03-05', '0');
INSERT INTO `userinfo` VALUES ('39', '张88', '0', '0', '1561', null, null, '按时回来', null, null, null, '30', null, '0');
INSERT INTO `userinfo` VALUES ('55', 'md5t', '6', '1', '13033224455', 'MD5', 'e12a3689458885dbb0a04cee0939ab9b', 'Mars 33445566', 'find me', '\\Icon\\13033224455_162952.jpg', '\\photo\\13033224455_162952.jpg', '10380', '2013-01-30', '30');
