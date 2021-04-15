/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : ncut_drain1

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 15/04/2021 14:13:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user_lb
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_lb`;
CREATE TABLE `tb_user_lb`  (
  `UClassID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `UClassName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NoteMeme` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`UClassID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user_lb
-- ----------------------------
INSERT INTO `tb_user_lb` VALUES ('1', 'laoban', NULL);
INSERT INTO `tb_user_lb` VALUES ('2', 'dagongzai', NULL);

-- ----------------------------
-- Table structure for td_areas
-- ----------------------------
DROP TABLE IF EXISTS `td_areas`;
CREATE TABLE `td_areas`  (
  `AreaID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AreaName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `AreaLeader` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `AreaCreateDate` datetime(0) DEFAULT NULL,
  `AreaLeadPhone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`AreaID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_areas
-- ----------------------------
INSERT INTO `td_areas` VALUES ('1', 'shijingshan', 'laoq', NULL, 'test');
INSERT INTO `td_areas` VALUES ('10', 'miyun', 'laotp', NULL, 'test');
INSERT INTO `td_areas` VALUES ('2', 'chaoyang', 'laow', NULL, 'test');
INSERT INTO `td_areas` VALUES ('3', 'dongcheng', 'laoe', NULL, 'test');
INSERT INTO `td_areas` VALUES ('4', 'xicheng', 'laor', NULL, 'test');
INSERT INTO `td_areas` VALUES ('5', 'haidian', 'laot', NULL, 'test');
INSERT INTO `td_areas` VALUES ('6', 'fengtai', 'laoty', NULL, 'test');
INSERT INTO `td_areas` VALUES ('7', 'daxing', 'laotu', NULL, 'test');
INSERT INTO `td_areas` VALUES ('8', 'changping', 'laoti', NULL, 'test');
INSERT INTO `td_areas` VALUES ('9', 'fangshan', 'laoto', NULL, 'test');

-- ----------------------------
-- Table structure for td_comm_rec
-- ----------------------------
DROP TABLE IF EXISTS `td_comm_rec`;
CREATE TABLE `td_comm_rec`  (
  `PackageID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PeerAddress` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `D_ID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `W_work` smallint(6) DEFAULT NULL,
  `V_pre` smallint(6) DEFAULT NULL,
  `OV_period` smallint(6) DEFAULT NULL,
  `OV_waterline` smallint(6) DEFAULT NULL,
  `OV_keeptime` smallint(6) DEFAULT NULL,
  `CV_waterline` smallint(6) DEFAULT NULL,
  `V_actiontime` smallint(6) DEFAULT NULL,
  `CmdStatus` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`PackageID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10036 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_comm_rec
-- ----------------------------
INSERT INTO `td_comm_rec` VALUES (1, '1', '117.136.0.127', '1', 11, 1, 5, 10, 1, 5, 5, 0);
INSERT INTO `td_comm_rec` VALUES (2, '2', '130.192.33.45', '2', 21, 41, 3, 30, 2, 20, 6, 1);
INSERT INTO `td_comm_rec` VALUES (3, '3', '165.181.7.32', '3', 22, 10, 10, 80, 3, 40, 7, 0);
INSERT INTO `td_comm_rec` VALUES (4, '4', '10.0.10.32', '4', 23, 80, 15, 120, 4, 80, 8, 1);
INSERT INTO `td_comm_rec` VALUES (5, '5', '202.102.168.8', '5', 11, 100, 1, 80, 5, 60, 9, 0);
INSERT INTO `td_comm_rec` VALUES (6, '6', '192.168.1.4', '6', 21, 50, 6, 25, 6, 10, 1, 1);
INSERT INTO `td_comm_rec` VALUES (7, '7', '150.1.1.5', '7', 21, 64, 1, 40, 2, 30, 4, 0);
INSERT INTO `td_comm_rec` VALUES (8, '8', '162.3.117.21', '8', 11, 16, 2, 30, 3, 10, 2, 1);
INSERT INTO `td_comm_rec` VALUES (9, '9', '101.11.1.3', '9', 21, 30, 3, 50, 1, 30, 4, 0);
INSERT INTO `td_comm_rec` VALUES (10, '10', '111.111.11.5', '10', 23, 22, 4, 70, 2, 40, 3, 1);

-- ----------------------------
-- Table structure for td_error_rec
-- ----------------------------
DROP TABLE IF EXISTS `td_error_rec`;
CREATE TABLE `td_error_rec`  (
  `ERId` smallint(6) NOT NULL AUTO_INCREMENT,
  `Error_Position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `If_deal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Time` datetime(0) NOT NULL,
  PRIMARY KEY (`ERId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for td_errordeal_rec
-- ----------------------------
DROP TABLE IF EXISTS `td_errordeal_rec`;
CREATE TABLE `td_errordeal_rec`  (
  `ERDId` smallint(6) NOT NULL AUTO_INCREMENT,
  `ERId` smallint(6) NOT NULL,
  `Exception` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `C_t` datetime(0) DEFAULT NULL,
  `User` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PackageId` bigint(20) NOT NULL,
  PRIMARY KEY (`ERDId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_errordeal_rec
-- ----------------------------
INSERT INTO `td_errordeal_rec` VALUES (1, 1, 'famendabukai', 'dakaifamen', '2021-04-07 21:15:14', 'laowang', 1);
INSERT INTO `td_errordeal_rec` VALUES (2, 2, 'famenguanbushang', 'guanshangfamen', '2021-04-06 21:15:25', 'laoli', 2);
INSERT INTO `td_errordeal_rec` VALUES (3, 3, 'shuiyajiancebudao', 'jianceshuiya', '2021-04-01 14:41:11', 'laoliu', 3);
INSERT INTO `td_errordeal_rec` VALUES (4, 4, 'shuiyaguoda', 'jianshaoshuiya', '2021-04-14 14:41:16', 'laozhao', 4);
INSERT INTO `td_errordeal_rec` VALUES (5, 5, 'boom', 'protect', '2021-04-28 14:41:20', 'laoha', 5);
INSERT INTO `td_errordeal_rec` VALUES (6, 6, 'longtousongle', 'loose', NULL, 'xiaowang', 6);
INSERT INTO `td_errordeal_rec` VALUES (7, 7, 'longtouguojin', 'tight', NULL, 'xiaoli', 7);
INSERT INTO `td_errordeal_rec` VALUES (8, 8, 'xiaomaobig', 'mistake', NULL, 'xiaoliu', 8);
INSERT INTO `td_errordeal_rec` VALUES (9, 9, 'damaobing', 'lalala', NULL, 'xiaozhao', 9);
INSERT INTO `td_errordeal_rec` VALUES (10, 10, 'houhou', 'liuliuliu', NULL, 'xiaoha', 10);

-- ----------------------------
-- Table structure for td_pack_list
-- ----------------------------
DROP TABLE IF EXISTS `td_pack_list`;
CREATE TABLE `td_pack_list`  (
  `pId` bigint(20) NOT NULL AUTO_INCREMENT,
  `PackageID` bigint(20) NOT NULL,
  `PackagePP` int(11) NOT NULL,
  `RecDate` datetime(0) NOT NULL,
  `TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PeerAddress` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PeerMacAdd` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`pId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_pack_list
-- ----------------------------
INSERT INTO `td_pack_list` VALUES (1, 1, 0, '2020-12-01 19:08:20', '1', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (2, 2, 1, '2020-12-02 19:08:54', '2', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (3, 3, 2, '2020-12-03 19:09:27', '3', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (4, 4, 0, '2020-12-05 19:09:50', '4', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (5, 5, 1, '2020-12-04 19:10:02', '5', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (6, 6, 2, '2020-11-01 21:14:46', '6', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (7, 7, 0, '2020-11-02 21:15:00', '7', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (8, 8, 1, '2020-11-06 21:15:13', '8', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (9, 9, 2, '2020-11-12 21:15:25', '9', NULL, NULL);
INSERT INTO `td_pack_list` VALUES (10, 10, 0, '2020-11-20 21:15:50', '10', NULL, NULL);

-- ----------------------------
-- Table structure for td_pips
-- ----------------------------
DROP TABLE IF EXISTS `td_pips`;
CREATE TABLE `td_pips`  (
  `PipID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PipName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PipDesc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PipCreateDate` datetime(0) DEFAULT NULL,
  `AreaID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PipLeadPhone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`PipID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_pips
-- ----------------------------
INSERT INTO `td_pips` VALUES ('1', 'shichao', NULL, NULL, '1', NULL);
INSERT INTO `td_pips` VALUES ('10', 'shanshi', NULL, NULL, '10', NULL);
INSERT INTO `td_pips` VALUES ('2', 'chaodong', NULL, NULL, '2', NULL);
INSERT INTO `td_pips` VALUES ('3', 'dongxi', NULL, NULL, '3', NULL);
INSERT INTO `td_pips` VALUES ('4', 'xihai', NULL, NULL, '4', NULL);
INSERT INTO `td_pips` VALUES ('5', 'haishi', NULL, NULL, '5', NULL);
INSERT INTO `td_pips` VALUES ('6', 'dianfeng', NULL, NULL, '6', NULL);
INSERT INTO `td_pips` VALUES ('7', 'taida', NULL, NULL, '7', NULL);
INSERT INTO `td_pips` VALUES ('8', 'xingchang', NULL, NULL, '8', NULL);
INSERT INTO `td_pips` VALUES ('9', 'pingfang', NULL, NULL, '9', NULL);

-- ----------------------------
-- Table structure for td_rec_detail
-- ----------------------------
DROP TABLE IF EXISTS `td_rec_detail`;
CREATE TABLE `td_rec_detail`  (
  `Id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PackageID` bigint(20) UNSIGNED NOT NULL,
  `TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `D_ID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `V_status` smallint(6) DEFAULT NULL,
  `V_per` smallint(6) DEFAULT NULL,
  `W_line` smallint(6) NOT NULL COMMENT '水位',
  `B_status` smallint(6) DEFAULT NULL,
  `O_temp` smallint(6) DEFAULT NULL,
  `E_temp` smallint(6) DEFAULT NULL,
  `D_doorsta` smallint(6) DEFAULT NULL,
  `W_work` smallint(6) DEFAULT NULL,
  `F_Volume` smallint(6) DEFAULT NULL,
  `OV_period` smallint(6) DEFAULT NULL,
  `OV_waterline` smallint(6) DEFAULT NULL,
  `OV_keeptime` smallint(6) DEFAULT NULL,
  `CV_waterline` smallint(6) DEFAULT NULL,
  `V_actiontime` smallint(6) DEFAULT NULL,
  `UD_period` smallint(6) DEFAULT NULL,
  `C_times` smallint(6) DEFAULT NULL,
  `Send_error` smallint(6) DEFAULT NULL,
  `Rece_error` smallint(6) DEFAULT NULL,
  `Create_time` datetime(0) DEFAULT NULL,
  `Update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_rec_detail
-- ----------------------------
INSERT INTO `td_rec_detail` VALUES (1, 1, '1', '1', 10, 1, 0, 10, 10, 129, 10, 11, 80, 5, 10, 1, 5, 5, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (2, 2, '2', '2', 20, 41, 15, 20, 20, 10, 10, 21, 50, 3, 30, 2, 20, 6, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (3, 3, '3', '3', 10, 10, 60, 10, 10, 25, 10, 22, 10, 10, 80, 3, 40, 7, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (4, 4, '4', '4', 10, 80, 90, 20, 10, 30, 20, 23, 30, 15, 120, 4, 80, 8, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (5, 5, '5', '5', 20, 100, 0, 10, 20, 40, 20, 11, 20, 1, 80, 5, 60, 9, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (6, 6, '6', '6', 10, 50, 80, 20, 10, 8, 10, 21, 22, 6, 25, 6, 10, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (7, 7, '7', '7', 20, 64, 30, 10, 10, 35, 20, 21, 70, 1, 40, 2, 30, 4, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (8, 8, '8', '8', 10, 16, 60, 10, 10, 20, 10, 11, 100, 2, 30, 3, 10, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (9, 9, '9', '9', 20, 30, 40, 10, 10, 31, 10, 21, 60, 3, 50, 1, 30, 4, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (10, 10, '10', '10', 10, 22, 78, 20, 20, 66, 20, 23, 40, 4, 70, 2, 40, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `td_rec_detail` VALUES (11, 11, '11', '11', 11, 11, 11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for td_terminals
-- ----------------------------
DROP TABLE IF EXISTS `td_terminals`;
CREATE TABLE `td_terminals`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TmnName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `U1TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `U2TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `D1TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `D2TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PipID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ConPont1` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Conpont2` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Conpont3` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `TmnDesc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `UserName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_terminals
-- ----------------------------
INSERT INTO `td_terminals` VALUES (1, '1', '石景山1号控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xiaoha');
INSERT INTO `td_terminals` VALUES (2, '2', '朝阳控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xiaoha');
INSERT INTO `td_terminals` VALUES (3, '3', '东城控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xiaoha');
INSERT INTO `td_terminals` VALUES (4, '4', '西城控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xiaoha');
INSERT INTO `td_terminals` VALUES (5, '5', '海淀控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xiaoha');
INSERT INTO `td_terminals` VALUES (6, '6', '丰台控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'liyijie');
INSERT INTO `td_terminals` VALUES (7, '7', '大兴控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'liyijie');
INSERT INTO `td_terminals` VALUES (8, '8', '昌平控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'liyijie');
INSERT INTO `td_terminals` VALUES (9, '9', '房山控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'liyijie');
INSERT INTO `td_terminals` VALUES (10, '10', '密云控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'liyijie');
INSERT INTO `td_terminals` VALUES (11, '11', '我得控制柜', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'liyijie');
INSERT INTO `td_terminals` VALUES (12, '12', '十二号控制柜', '', '', '', '', '', '', '', '', '', NULL);

-- ----------------------------
-- Table structure for td_tp
-- ----------------------------
DROP TABLE IF EXISTS `td_tp`;
CREATE TABLE `td_tp`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PipID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `PTid` int(11) NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_tp
-- ----------------------------
INSERT INTO `td_tp` VALUES (1, '1', '1', 1);
INSERT INTO `td_tp` VALUES (2, '2', '1', 2);
INSERT INTO `td_tp` VALUES (3, '3', '1', 3);
INSERT INTO `td_tp` VALUES (4, '4', '1', 4);
INSERT INTO `td_tp` VALUES (5, '5', '1', 5);
INSERT INTO `td_tp` VALUES (6, '6', '1', 6);
INSERT INTO `td_tp` VALUES (7, '7', '1', 7);
INSERT INTO `td_tp` VALUES (8, '8', '1', 8);
INSERT INTO `td_tp` VALUES (9, '9', '1', 9);
INSERT INTO `td_tp` VALUES (10, '10', '1', 10);
INSERT INTO `td_tp` VALUES (11, '11', '2', 11);

-- ----------------------------
-- Table structure for td_user
-- ----------------------------
DROP TABLE IF EXISTS `td_user`;
CREATE TABLE `td_user`  (
  `UserID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `UserName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `UserPswd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `UClassID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Ustate` int(11) NOT NULL,
  `RegTime` datetime(0) DEFAULT NULL,
  `MoPhone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `RealName` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `DPTName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_user
-- ----------------------------
INSERT INTO `td_user` VALUES ('10', 'xiaoha', '123456', '2', 0, NULL, '161816133', NULL, 'xiaolajiao');
INSERT INTO `td_user` VALUES ('2', 'laoli', '123456', '2', 1, NULL, '123789654', NULL, 'alibaba');
INSERT INTO `td_user` VALUES ('3', 'laoliu', '123456', '2', 1, NULL, '1234566666', NULL, 'huawei');
INSERT INTO `td_user` VALUES ('4', 'laozhao', '123456', '3', 1, NULL, '11111111', NULL, 'tencent');
INSERT INTO `td_user` VALUES ('5', 'laoha', '123456', '2', 0, NULL, '1111122222', NULL, 'xiaomi');
INSERT INTO `td_user` VALUES ('6', 'xiaowang', '123456', '2', 1, NULL, '13151166', NULL, 'oppo');
INSERT INTO `td_user` VALUES ('7', 'xiaoli', '123456', '3', 0, NULL, '1616161611', NULL, 'vivo');
INSERT INTO `td_user` VALUES ('8', 'liyijie', '123456', '1', 1, NULL, '21981156', '', 'meizu');
INSERT INTO `td_user` VALUES ('9', 'xiaozhao', '123456', '1', 1, NULL, '1686158', NULL, 'jinli');

-- ----------------------------
-- Table structure for td_user_right
-- ----------------------------
DROP TABLE IF EXISTS `td_user_right`;
CREATE TABLE `td_user_right`  (
  `RId` smallint(6) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Right_PP` int(11) NOT NULL,
  `TmnID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PipID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `AreaID` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`RId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of td_user_right
-- ----------------------------
INSERT INTO `td_user_right` VALUES (1, '1', 0, '1', '1', '1');
INSERT INTO `td_user_right` VALUES (2, '2', 1, '2', '2', '2');
INSERT INTO `td_user_right` VALUES (3, '3', 2, '3', '3', '3');
INSERT INTO `td_user_right` VALUES (4, '4', 3, '4', '4', '4');
INSERT INTO `td_user_right` VALUES (5, '5', 4, '5', '5', '5');
INSERT INTO `td_user_right` VALUES (6, '6', 0, '6', '6', '6');
INSERT INTO `td_user_right` VALUES (7, '7', 1, '7', '7', '7');
INSERT INTO `td_user_right` VALUES (8, '8', 2, '8', '8', '8');
INSERT INTO `td_user_right` VALUES (9, '9', 3, '9', '9', '9');
INSERT INTO `td_user_right` VALUES (10, '10', 4, '10', '10', '10');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1003, 'admin', 'admin', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
