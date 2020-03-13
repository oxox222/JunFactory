/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : sale

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/03/2020 09:49:19
*/

CREATE DATABASE gj_factory DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE gj_factory;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_consignee
-- ----------------------------
DROP TABLE IF EXISTS `t_consignee`;
CREATE TABLE `t_consignee` (
    `consignee` varchar(255) DEFAULT NULL COMMENT '收货方'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货方表';

-- ----------------------------
-- Table structure for t_party
-- ----------------------------
DROP TABLE IF EXISTS `t_party`;
CREATE TABLE `t_party` (
    `party` varchar(255) DEFAULT NULL COMMENT '甲方'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='甲方表';

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
    `party` varchar(255) DEFAULT NULL COMMENT '甲方',
    `product` varchar(255) DEFAULT NULL COMMENT '产品'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='甲方需求表';

-- ----------------------------
-- Table structure for t_sales
-- ----------------------------
DROP TABLE IF EXISTS `t_sales`;
CREATE TABLE `t_sales` (
    `id` varchar(255) DEFAULT NULL COMMENT 'uuid',
    `party` varchar(255) DEFAULT NULL COMMENT '甲方',
    `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
    `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
    `phone` varchar(255) DEFAULT NULL COMMENT '手机',
    `address` varchar(255) DEFAULT NULL COMMENT '地址',
    `time` timestamp NULL DEFAULT NULL COMMENT '开单时间',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `odd` varchar(255) DEFAULT NULL COMMENT '单号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for t_sales_product
-- ----------------------------
DROP TABLE IF EXISTS `t_sales_product`;
CREATE TABLE `t_sales_product` (
    `id` varchar(255) DEFAULT NULL COMMENT 'uuid',
    `product` varchar(25) DEFAULT NULL COMMENT '产品',
    `size` varchar(255) DEFAULT NULL COMMENT '规格',
    `number` int(255) DEFAULT NULL COMMENT '数量',
    `price` double(255,2) DEFAULT NULL COMMENT '单价'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单细节表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
    `user` varchar(255) NOT NULL COMMENT '用户名',
    `password` varchar(255) DEFAULT NULL COMMENT '密码',
    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
    `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
    PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
