/*
Navicat MySQL Data Transfer

Source Server         : D7
Source Server Version : 50126
Source Host           : localhost:3306
Source Database       : forex

Target Server Type    : MYSQL
Target Server Version : 50126
File Encoding         : 65001

Date: 2012-11-27 17:48:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accountbaseinfo`
-- ----------------------------
DROP TABLE IF EXISTS `accountbaseinfo`;
CREATE TABLE `accountbaseinfo` (
  `ID_` varchar(40) NOT NULL COMMENT '主键',
  `ACCOUNT_` varchar(40) DEFAULT NULL COMMENT '账户名',
  `NAME_` varchar(40) DEFAULT NULL,
  `CURRENCY_` varchar(40) DEFAULT NULL,
  `CURRENTDATE_` date DEFAULT NULL,
  `DEPOSIT_` double DEFAULT NULL,
  `CLOSETRADE_` double DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accountbaseinfo
-- ----------------------------
INSERT INTO `accountbaseinfo` VALUES ('46cf725c-36ae-4e71-ad3d-95f49477d08a', '823023', 'EURUSD', 'USD', null, '154.970001220703', '-154.919998168945');
INSERT INTO `accountbaseinfo` VALUES ('df7c45de-ac0a-4255-ad6f-229c4d26f5ff', '159129', 'Xiaole Li', 'USD', null, '-71375', '82813.7109375');

-- ----------------------------
-- Table structure for `accounttransactionsinfo`
-- ----------------------------
DROP TABLE IF EXISTS `accounttransactionsinfo`;
CREATE TABLE `accounttransactionsinfo` (
  `ID_` varchar(40) NOT NULL,
  `ACCOUNT_ID_` varchar(40) DEFAULT NULL,
  `TICKET_` varchar(40) DEFAULT NULL,
  `OPENTIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TYPE_` varchar(40) DEFAULT NULL,
  `SIZE_` varchar(40) DEFAULT NULL,
  `ITEM_` varchar(40) DEFAULT NULL,
  `OPENPRICE_` double DEFAULT NULL,
  `SL_` double DEFAULT NULL,
  `TP_` double DEFAULT NULL,
  `CLOASTIME_` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CLOSEPRICE_` double DEFAULT NULL,
  `COMMISSION_` double DEFAULT NULL,
  `TAXES_` double DEFAULT NULL,
  `SWAP_` double DEFAULT NULL,
  `PROFIT_` double DEFAULT NULL COMMENT '利润',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `forex_contactus`
-- ----------------------------
DROP TABLE IF EXISTS `forex_contactus`;
CREATE TABLE `forex_contactus` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `comments` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forex_contactus
-- ----------------------------
INSERT INTO `forex_contactus` VALUES ('1cd61cc7-b70d-4109-b390-430f77cfb191', '', '', '', '');
INSERT INTO `forex_contactus` VALUES ('728760db-dbc8-4a7e-ba2a-f7b7fc9cd336', '1', '3', '2', '4');
INSERT INTO `forex_contactus` VALUES ('82e79309-00bc-44d1-9530-a5448e679841', 'Andy', '15000000005', 'comeonchou@163.com', 'No comments.');

-- ----------------------------
-- Table structure for `forex_system`
-- ----------------------------
DROP TABLE IF EXISTS `forex_system`;
CREATE TABLE `forex_system` (
  `systemid` varchar(36) NOT NULL DEFAULT '',
  `systemkey` varchar(20) DEFAULT NULL,
  `systemcode` varchar(100) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`systemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forex_system
-- ----------------------------
INSERT INTO `forex_system` VALUES ('0004d760-9d03-426a-9b8f-1ad6e5b525f1', 'skin', 'pink', '皮肤颜色');
