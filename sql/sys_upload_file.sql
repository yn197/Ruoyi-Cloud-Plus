/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : ry-cloud

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 19/08/2023 13:54:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_file`;
CREATE TABLE `sys_upload_file`  (
  `id` bigint(20) NOT NULL DEFAULT 0 COMMENT '主键id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名字',
  `file_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件id',
  `bucket_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桶名',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除标志默认0未删除，1已删除）',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态（默认0启用，1禁用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_upload_file
-- ----------------------------
INSERT INTO `sys_upload_file` VALUES (1690966475785457665, '蝴蝶.png', 'fc77d4f687e44d6ab738c401d3cf08bc', 'test', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `sys_upload_file` VALUES (1690966847379861505, '蝴蝶.png', '954cba6ca3ae44d28503ca641f56e99c', 'test', NULL, NULL, NULL, NULL, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
