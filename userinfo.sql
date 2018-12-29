/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : flowershop

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2018-12-21 13:48:43
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `userinfo` VALUES ('1', '张0', '18', '0', '1100', 'woafsdlj', '9174e5b543aa4e8fdc07cc9dae7b5c80', 'sdfafasdf', 'new one', '/photo/1100_201111.jpg', '/photo/13000000009_225210.jpg', '752', '2018-09-11', '59');
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
INSERT INTO `userinfo` VALUES ('20', '123$4IIcon', '1', '0', '13000001234', 'sfadlj', '7777777', 'Mars', 'sign', '/Icon/13000001234223555.jpg', null, '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('21', 'Fxxc', '1', '0', '13000000009', 'faww', '987654', 'Mars', 'sign', '/Icon/13000000009_225210.jpg', '/photo/13000000009_225210.jpg', '0', '2018-11-27', '0');
INSERT INTO `userinfo` VALUES ('33', 'sq', '4', '1', '13000009876', 'sq', '000000', 'Mars', 'sf', '/Icon/13000009876_221949.jpg', '/photo/13000009876_221949.jpg', '0', '2015-03-05', '0');
INSERT INTO `userinfo` VALUES ('39', '张88', '1', '0', '15512345678', null, 'e10adc3949ba59abbe56e057f20f883e', '按时回来', null, '/Icon/15512345678_13348.jpg', null, '30', null, '0');
INSERT INTO `userinfo` VALUES ('55', 'md5t', '6', '1', '13033224455', 'MD5', 'e12a3689458885dbb0a04cee0939ab9b', 'Mars 33445566', 'find me', '\\Icon\\13033224455_162952.jpg', '\\photo\\13033224455_162952.jpg', '10380', '2013-01-30', '30');
INSERT INTO `userinfo` VALUES ('60', 'Four', '1', '0', '13012345678', 'fasf', 'e10adc3949ba59abbe56e057f20f883e', 'Mars', 'sign', '\\Icon\\13012345678_2215.jpg', '\\photo\\13012345678_2215.jpg', '0', '2018-11-28', '0');
