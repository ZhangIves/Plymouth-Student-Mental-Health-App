/*
 Navicat Premium Data Transfer

 Source Server         : 本机Mysql
 Source Server Type    : MySQL
 Source Server Version : 50716 (5.7.16)
 Source Host           : localhost:3306
 Source Schema         : mental-health

 Target Server Type    : MySQL
 Target Server Version : 50716 (5.7.16)
 File Encoding         : 65001

 Date: 21/04/2025 21:40:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户标识',
  `questionnaire_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `selected_options` json NULL COMMENT '选中的选项ID列表',
  `answer_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文本回答',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for evaluation_answer
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_answer`;
CREATE TABLE `evaluation_answer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `evaluation_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `friend_id` bigint(20) NOT NULL COMMENT '好友Id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'https://api.prodless.com/avatar.png' COMMENT '头像',
  `alphabetic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字母索引',
  `status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '状态 1：正常；0：删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id_status`(`user_id`) USING BTREE,
  INDEX `idx_user_id_friend_id`(`user_id`, `friend_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '好友信息表(friend)' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 123, 1223, '哈哈哈', 'https://api.prodless.com/avatar.png', NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `friend` VALUES (1790719467499945985, 1714187751569, 1713970987194, '测测', '/static/img/avatar-1.jpeg', 'C', 1, NULL, NULL, '2024-05-15', NULL, '2024-05-16 10:55:41');
INSERT INTO `friend` VALUES (1790719467499945986, 1713970987194, 1714187751569, '张希', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2024-05-15', NULL, '2024-05-16 10:08:33');
INSERT INTO `friend` VALUES (1790752347005583361, 1715780878185, 1714187751569, '张希', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2024-05-15', NULL, '2024-05-17 16:38:41');
INSERT INTO `friend` VALUES (1790752347005583362, 1714187751569, 1715780878185, '', '/static/img/avatar-1.jpeg', '', 1, NULL, NULL, '2024-05-15', NULL, '2024-05-16 10:55:33');
INSERT INTO `friend` VALUES (1790913106876456962, 1715821849672, 1714187751569, '张希', '/static/img/javadog.png', 'Z', 1, NULL, NULL, '2024-05-16', NULL, '2024-05-16 09:16:16');
INSERT INTO `friend` VALUES (1790913106876456963, 1714187751569, 1715821849672, '', '/static/img/avatar-1.jpeg', '', 1, NULL, NULL, '2024-05-16', NULL, '2025-04-07 10:08:39');
INSERT INTO `friend` VALUES (1790974890639515649, 1715836583052, 1714187751569, '张希', '/static/img/avatar-1.jpeg', 'Z', 0, NULL, NULL, '2024-05-16', NULL, '2024-05-16 13:30:42');
INSERT INTO `friend` VALUES (1790974890639515650, 1714187751569, 1715836583052, '15588881111', '/static/img/avatar-1.jpeg', '1', 1, NULL, NULL, '2024-05-16', NULL, '2024-05-16 13:18:27');
INSERT INTO `friend` VALUES (1791389858081951746, 1715823425456, 1714187751569, '张希', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2024-05-17', NULL, '2024-05-17 16:46:48');
INSERT INTO `friend` VALUES (1791389858081951747, 1714187751569, 1715823425456, '中国', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2024-05-17', NULL, '2024-05-17 16:47:05');
INSERT INTO `friend` VALUES (1791458226088669186, 1743501557796, 1715823425456, '中国', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2024-05-17', NULL, '2024-05-20 15:12:06');
INSERT INTO `friend` VALUES (1791458226122223618, 1715823425456, 1715823298893, '潇潇', '/static/img/avatar-1.jpeg', 'X', 1, NULL, NULL, '2024-05-17', NULL, '2024-05-17 21:18:27');
INSERT INTO `friend` VALUES (1792120584200998913, 1716109753638, 1715823425456, '中国', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2024-05-19', NULL, '2024-05-19 17:10:35');
INSERT INTO `friend` VALUES (1792120584268107778, 1715823425456, 1716109753638, '灰太狼', '/static/img/avatar-1.jpeg', 'H', 1, NULL, NULL, '2024-05-19', NULL, '2024-05-19 17:10:22');
INSERT INTO `friend` VALUES (1907010821724487682, 1743501557796, 1715780878185, '谢邪', '/static/img/avatar-1.jpeg', 'X', 1, NULL, NULL, '2025-04-01', NULL, '2025-04-01 18:11:53');
INSERT INTO `friend` VALUES (1907010821724487683, 1715780878185, 1743501557796, '张阿生', '/static/img/avatar-1.jpeg', 'Z', 1, NULL, NULL, '2025-04-01', NULL, '2025-04-01 18:12:04');
INSERT INTO `friend` VALUES (1909078872137646082, 1743994846661, 1713970987194, 'CECE', '/static/img/avatar-1.jpeg', 'C', 1, NULL, NULL, '2025-04-07', NULL, '2025-04-07 11:01:02');
INSERT INTO `friend` VALUES (1909078872171200514, 1713970987194, 1743994846661, 'nnda', '/static/img/avatar-1.jpeg', 'N', 1, NULL, NULL, '2025-04-07', NULL, '2025-04-07 11:01:32');
INSERT INTO `friend` VALUES (1909078872204754946, 1743994846661, 1715780878185, 'XIEXIE', '/static/img/avatar-1.jpeg', 'X', 1, NULL, NULL, '2025-04-07', NULL, '2025-04-07 11:01:20');
INSERT INTO `friend` VALUES (1909078872204754947, 1715780878185, 1743994846661, 'nnda', 'https://api.prodless.com/avatar.png', 'N', 1, NULL, NULL, '2025-04-07', NULL, '2025-04-07 11:00:47');
INSERT INTO `friend` VALUES (1911238162302550018, 1744509661548, 1713970987194, 'CECE Doctor', '/static/img/avatar-1.jpeg', 'C', 1, NULL, NULL, '2025-04-13', NULL, '2025-04-13 10:01:17');
INSERT INTO `friend` VALUES (1911238162331910145, 1713970987194, 1744509661548, 'MD', '/static/img/avatar-1.jpeg', 'M', 1, NULL, NULL, '2025-04-13', NULL, '2025-04-21 10:48:57');
INSERT INTO `friend` VALUES (1911238162331910146, 1744509661548, 1715780878185, 'XIEXIE Doctor', '/static/img/avatar-1.jpeg', 'X', 1, NULL, NULL, '2025-04-13', NULL, '2025-04-13 10:02:26');
INSERT INTO `friend` VALUES (1911238162331910147, 1715780878185, 1744509661548, 'MD', 'https://api.prodless.com/avatar.png', 'M', 1, NULL, NULL, '2025-04-13', NULL, '2025-04-13 10:01:02');

-- ----------------------------
-- Table structure for friend_msg
-- ----------------------------
DROP TABLE IF EXISTS `friend_msg`;
CREATE TABLE `friend_msg`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `msg_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `msg_type` tinyint(1) NOT NULL COMMENT '消息类型(0-系统消息;1-文字;2-图片;3-语音;4-视频)',
  `from_user_id` bigint(20) NOT NULL COMMENT '消息发送方',
  `to_user_id` bigint(20) NOT NULL COMMENT '消息接收方',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0-正常;1-撤销;)',
  `time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语音时长',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_from_user_id`(`from_user_id`) USING BTREE,
  INDEX `idx_to_user_id`(`to_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend_msg
-- ----------------------------
INSERT INTO `friend_msg` VALUES (1790719467537694722, '以上是打招呼内容', 0, 1714187751569, 1713970987194, 0, NULL, NULL, NULL, '2024-05-15 20:22:46', NULL, '2024-05-15 20:22:46');
INSERT INTO `friend_msg` VALUES (1790719467604803585, '我们已经是好友啦,开始聊天吧!', 1, 1714187751569, 1713970987194, 0, NULL, NULL, NULL, '2024-05-15 20:22:46', NULL, '2024-05-15 20:22:46');
INSERT INTO `friend_msg` VALUES (1790752347005583363, '以上是打招呼内容', 0, 1715780878185, 1714187751569, 0, NULL, NULL, NULL, '2024-05-15 22:33:25', NULL, '2024-05-15 22:33:25');
INSERT INTO `friend_msg` VALUES (1790752347005583364, '我们已经是好友啦,开始聊天吧!', 1, 1715780878185, 1714187751569, 0, NULL, NULL, NULL, '2024-05-15 22:33:25', NULL, '2024-05-15 22:33:25');
INSERT INTO `friend_msg` VALUES (1790913106943565825, '以上是打招呼内容', 0, 1715821849672, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:12:13', NULL, '2024-05-16 09:12:13');
INSERT INTO `friend_msg` VALUES (1790913106943565826, '我们已经是好友啦,开始聊天吧!', 1, 1715821849672, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:12:13', NULL, '2024-05-16 09:12:13');
INSERT INTO `friend_msg` VALUES (1790917179767939075, '在吗', 1, 1715821849672, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:28:24', NULL, '2024-05-16 09:28:24');
INSERT INTO `friend_msg` VALUES (1790921612883025922, '3312', 1, 1714187751569, 1713970987194, 0, NULL, NULL, NULL, '2024-05-16 09:46:01', NULL, '2024-05-16 09:46:01');
INSERT INTO `friend_msg` VALUES (1790924275762749442, 'fff', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:56:36', NULL, '2024-05-16 09:56:36');
INSERT INTO `friend_msg` VALUES (1790924312798453762, 'wnwnn\n', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:56:45', NULL, '2024-05-16 09:56:45');
INSERT INTO `friend_msg` VALUES (1790924327889559553, '😁', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:56:48', NULL, '2024-05-16 09:56:48');
INSERT INTO `friend_msg` VALUES (1790924377503981570, '😙', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:57:00', NULL, '2024-05-16 09:57:00');
INSERT INTO `friend_msg` VALUES (1790924407975600129, 'ad', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:57:07', NULL, '2024-05-16 09:57:07');
INSERT INTO `friend_msg` VALUES (1790924450036080642, '你好兄弟\n', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:57:17', NULL, '2024-05-16 09:57:17');
INSERT INTO `friend_msg` VALUES (1790924483733118978, '好的\n', 1, 1714187751569, 1713970987194, 0, NULL, NULL, NULL, '2024-05-16 09:57:25', NULL, '2024-05-16 09:57:25');
INSERT INTO `friend_msg` VALUES (1790924546693816321, '嗯\n', 1, 1713970987194, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 09:57:40', NULL, '2024-05-16 09:57:40');
INSERT INTO `friend_msg` VALUES (1790974890639515651, '以上是打招呼内容', 0, 1715836583052, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 13:17:43', NULL, '2024-05-16 13:17:43');
INSERT INTO `friend_msg` VALUES (1790974890639515652, '我们已经是好友啦,开始聊天吧!', 1, 1715836583052, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 13:17:43', NULL, '2024-05-16 13:17:43');
INSERT INTO `friend_msg` VALUES (1790975002895867906, '你好啊', 1, 1715836583052, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 13:18:10', NULL, '2024-05-16 13:18:10');
INSERT INTO `friend_msg` VALUES (1790975090376466433, 'nh \n', 1, 1714187751569, 1715836583052, 0, NULL, NULL, NULL, '2024-05-16 13:18:31', NULL, '2024-05-16 13:18:31');
INSERT INTO `friend_msg` VALUES (1790975200682467330, '嗯', 1, 1715836583052, 1714187751569, 0, NULL, NULL, NULL, '2024-05-16 13:18:57', NULL, '2024-05-16 13:18:57');
INSERT INTO `friend_msg` VALUES (1790975224883601410, '1', 1, 1714187751569, 1715836583052, 0, NULL, NULL, NULL, '2024-05-16 13:19:03', NULL, '2024-05-16 13:19:03');
INSERT INTO `friend_msg` VALUES (1790978307223085058, '12', 1, 1714187751569, 1715836583052, 0, NULL, NULL, NULL, '2024-05-16 13:31:18', NULL, '2024-05-16 13:31:18');
INSERT INTO `friend_msg` VALUES (1790978336159588354, '31231', 1, 1714187751569, 1715836583052, 0, NULL, NULL, NULL, '2024-05-16 13:31:25', NULL, '2024-05-16 13:31:25');
INSERT INTO `friend_msg` VALUES (1791389858081951748, '以上是打招呼内容', 0, 1715823425456, 1714187751569, 0, NULL, NULL, NULL, '2024-05-17 16:46:39', NULL, '2024-05-17 16:46:39');
INSERT INTO `friend_msg` VALUES (1791389858081951749, '我们已经是好友啦,开始聊天吧!', 1, 1715823425456, 1714187751569, 0, NULL, NULL, NULL, '2024-05-17 16:46:39', NULL, '2024-05-17 16:46:39');
INSERT INTO `friend_msg` VALUES (1791389948460814338, '你还', 1, 1715823425456, 1714187751569, 0, NULL, NULL, NULL, '2024-05-17 16:47:01', NULL, '2024-05-17 16:47:01');
INSERT INTO `friend_msg` VALUES (1791389998616301569, '在吗', 1, 1715823425456, 1714187751569, 0, NULL, NULL, NULL, '2024-05-17 16:47:13', NULL, '2024-05-17 16:47:13');
INSERT INTO `friend_msg` VALUES (1791390024298024962, 'z ', 1, 1714187751569, 1715823425456, 0, NULL, NULL, NULL, '2024-05-17 16:47:19', NULL, '2024-05-17 16:47:19');
INSERT INTO `friend_msg` VALUES (1791390074478678017, '啦啦啦', 1, 1715823425456, 1714187751569, 0, NULL, NULL, NULL, '2024-05-17 16:47:31', NULL, '2024-05-17 16:47:31');
INSERT INTO `friend_msg` VALUES (1791457671144415234, '1', 1, 1715823425456, 1714187751569, 0, NULL, NULL, NULL, '2024-05-17 21:16:07', NULL, '2024-05-17 21:16:07');
INSERT INTO `friend_msg` VALUES (1791458226155778050, '以上是打招呼内容', 0, 1715823298893, 1715823425456, 0, NULL, NULL, NULL, '2024-05-17 21:18:19', NULL, '2024-05-17 21:18:19');
INSERT INTO `friend_msg` VALUES (1791458226155778051, '我们已经是好友啦,开始聊天吧!', 1, 1715823298893, 1715823425456, 0, NULL, NULL, NULL, '2024-05-17 21:18:19', NULL, '2024-05-17 21:18:19');
INSERT INTO `friend_msg` VALUES (1791458264214892546, '1', 1, 1715823425456, 1715823298893, 0, NULL, NULL, NULL, '2024-05-17 21:18:29', NULL, '2024-05-17 21:18:29');
INSERT INTO `friend_msg` VALUES (1792120584268107779, '以上是打招呼内容', 0, 1716109753638, 1715823425456, 0, NULL, NULL, NULL, '2024-05-19 17:10:18', NULL, '2024-05-19 17:10:18');
INSERT INTO `friend_msg` VALUES (1792120584268107780, '我们已经是好友啦,开始聊天吧!', 1, 1716109753638, 1715823425456, 0, NULL, NULL, NULL, '2024-05-19 17:10:18', NULL, '2024-05-19 17:10:18');
INSERT INTO `friend_msg` VALUES (1792120620959879171, 'nihao\n', 1, 1715823425456, 1716109753638, 0, NULL, NULL, NULL, '2024-05-19 17:10:27', NULL, '2024-05-19 17:10:27');
INSERT INTO `friend_msg` VALUES (1792120674701496321, 'hhh', 1, 1715823425456, 1716109753638, 0, NULL, NULL, NULL, '2024-05-19 17:10:40', NULL, '2024-05-19 17:10:40');
INSERT INTO `friend_msg` VALUES (1907010821724487684, '以上是打招呼内容', 0, 1743501557796, 1715780878185, 0, NULL, NULL, NULL, '2025-04-01 18:03:05', NULL, '2025-04-01 18:03:05');
INSERT INTO `friend_msg` VALUES (1907010821762236417, '我们已经是好友啦,开始聊天吧!', 1, 1743501557796, 1715780878185, 0, NULL, NULL, NULL, '2025-04-01 18:03:05', NULL, '2025-04-01 18:03:05');
INSERT INTO `friend_msg` VALUES (1907013061164068865, 'nih \n', 1, 1743501557796, 1715780878185, 0, NULL, NULL, NULL, '2025-04-01 18:11:59', NULL, '2025-04-01 18:11:59');
INSERT INTO `friend_msg` VALUES (1907013132215578626, '是', 1, 1715780878185, 1743501557796, 0, NULL, NULL, NULL, '2025-04-01 18:12:16', NULL, '2025-04-01 18:12:16');
INSERT INTO `friend_msg` VALUES (1908350645303721986, '😃', 1, 1743501557796, 1715780878185, 0, NULL, NULL, NULL, '2025-04-05 10:47:04', NULL, '2025-04-05 10:47:04');
INSERT INTO `friend_msg` VALUES (1909076912739110914, '123456', 1, 1743994194201, 1713970987194, 0, NULL, NULL, NULL, '2025-04-07 10:53:00', NULL, '2025-04-07 10:53:00');
INSERT INTO `friend_msg` VALUES (1909077352507691010, '31231', 1, 1743994194201, 1713970987194, 0, NULL, NULL, NULL, '2025-04-07 10:54:44', NULL, '2025-04-07 10:54:44');
INSERT INTO `friend_msg` VALUES (1909077407662788609, '2', 1, 1743994194201, 1713970987194, 0, NULL, NULL, NULL, '2025-04-07 10:54:58', NULL, '2025-04-07 10:54:58');
INSERT INTO `friend_msg` VALUES (1909078943340150785, '2131', 1, 1743994846661, 1713970987194, 0, NULL, NULL, NULL, '2025-04-07 11:01:04', NULL, '2025-04-07 11:01:04');
INSERT INTO `friend_msg` VALUES (1909079021488422915, '3232', 1, 1743994846661, 1715780878185, 0, NULL, NULL, NULL, '2025-04-07 11:01:22', NULL, '2025-04-07 11:01:22');
INSERT INTO `friend_msg` VALUES (1911238248721989635, 'nih \n', 1, 1744509661548, 1713970987194, 0, NULL, NULL, NULL, '2025-04-13 10:01:22', NULL, '2025-04-13 10:01:22');
INSERT INTO `friend_msg` VALUES (1914149350220939266, '你好', 1, 1713970987194, 1744509661548, 0, NULL, NULL, NULL, '2025-04-21 10:49:03', NULL, '2025-04-21 10:49:03');

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群名称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群头像',
  `notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群公告',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群介绍',
  `alphabetic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字母索引',
  `admin_user_id` bigint(20) NULL DEFAULT NULL COMMENT '群主',
  `status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '状态 1：正常；0：删除',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群组信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1792043635785068545, 'nihao', 'http://localhost:8066/uploadImages/20240519120431165.jpg', 'ddd', '222', 'N', 1715823425456, 1, NULL, NULL, '2024-05-19 12:04:32', NULL, '2024-05-19 12:04:32');

-- ----------------------------
-- Table structure for group_manager
-- ----------------------------
DROP TABLE IF EXISTS `group_manager`;
CREATE TABLE `group_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_num` int(11) NULL DEFAULT NULL,
  `group_max_num` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_manager
-- ----------------------------

-- ----------------------------
-- Table structure for group_msg
-- ----------------------------
DROP TABLE IF EXISTS `group_msg`;
CREATE TABLE `group_msg`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '群组id',
  `msg_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `msg_type` tinyint(1) NOT NULL COMMENT '消息类型(0-系统消息;1-文字;2-图片;3-语音;4-视频)',
  `from_user_id` bigint(20) NOT NULL COMMENT '发送方Id',
  `from_user_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送方昵称',
  `from_user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送方头像',
  `time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语音时长',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0-正常;1-撤销;2-删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE,
  INDEX `idx_from_user_id`(`from_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群组消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `group_id` bigint(20) NULL DEFAULT NULL COMMENT '群组id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群组名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `user_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `adminable` tinyint(1) NULL DEFAULT NULL COMMENT '是否管理员(0-否；1-是)',
  `source` tinyint(1) NULL DEFAULT 0 COMMENT '来源(0-创建入群；1-扫码入群；2.拉人入群)',
  `status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '状态 1：正常；0：删除；-1退群',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES (1792043635785068546, 1792043635785068545, 'nihao', 1715823425456, '中国(群主)', 'http://localhost:8066/uploadImages/20240519104305697.jpg', 1, 0, 1, NULL, NULL, '2024-05-19', NULL, '2024-05-19 12:04:32');
INSERT INTO `group_user` VALUES (1792453399660658690, 1792043635785068545, 'nihao', 1715823298893, '潇潇', NULL, 0, 1, 1, NULL, NULL, '2024-05-20', NULL, '2024-05-20 15:12:47');

-- ----------------------------
-- Table structure for invitation
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `user_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'https://api.prodless.com/avatar.png' COMMENT '用户头像',
  `friend_id` bigint(20) NOT NULL COMMENT '好友Id',
  `friend_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好友昵称',
  `friend_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好友头像',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0-待通过;1-已通过;2-拒绝)',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拒绝理由',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_friend_id`(`friend_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mental_health_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `mental_health_evaluation`;
CREATE TABLE `mental_health_evaluation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
  `score_min` int(11) NOT NULL COMMENT 'Minimum Score',
  `score_max` int(11) NOT NULL COMMENT 'Maximum Score',
  `result_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Result Level',
  `result_description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Result Description',
  `daily_advice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Daily Recommendations',
  `professional_advice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Professional Guidance',
  `emergency_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'Mental Health Hotline: 12355' COMMENT 'Emergency Contacts',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `score_range`(`score_min`, `score_max`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Mental Health Evaluation Scoring Criteria' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mental_health_evaluation
-- ----------------------------
INSERT INTO `mental_health_evaluation` VALUES (1, 0, 10, 'Normal Range', 'Good mental state', 'Maintain a regular daily schedule, perform aerobic exercise 3-5 times weekly, keep an emotion journal', 'Recommend quarterly mental health self-assessment', 'Mental Health Hotline: 12355');
INSERT INTO `mental_health_evaluation` VALUES (2, 11, 20, 'Mild Anxiety', 'Experiencing mild psychological stress', 'Practice meditation/deep breathing, reduce caffeine intake, ensure 7-8 hours of sleep', 'Monthly consultations with a psychological counselor recommended', 'Mental Health Hotline: 12355');
INSERT INTO `mental_health_evaluation` VALUES (3, 21, 30, 'Moderate Depression', 'Persistent low mood', 'Establish a structured routine, engage in 30-minute outdoor activities daily, avoid major decisions', 'Bi-weekly professional counseling sessions advised, consider medication if necessary', 'Mental Health Hotline: 12355');
INSERT INTO `mental_health_evaluation` VALUES (4, 31, 40, 'Severe Risk', 'Exhibiting psychological crisis tendencies', 'Avoid being alone, contact trusted companions immediately, suspend high-intensity work', 'Immediate psychiatric intervention required, hospitalization observation recommended', 'Mental Health Hotline: 12355');

-- ----------------------------
-- Table structure for msg_unread_record
-- ----------------------------
DROP TABLE IF EXISTS `msg_unread_record`;
CREATE TABLE `msg_unread_record`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `target_id` bigint(20) NOT NULL COMMENT '目标方Id(对应好友/群组 id)',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户Id',
  `unread_num` int(11) NULL DEFAULT 0 COMMENT '未读数量',
  `source` tinyint(1) NULL DEFAULT NULL COMMENT '消息来源(0-好友；1-群组)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_target_id_user_id`(`target_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息未读标记' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of msg_unread_record
-- ----------------------------
INSERT INTO `msg_unread_record` VALUES (1790917179767939074, 1715821849672, 1714187751569, 0, 0, NULL, NULL, '2024-05-16 09:28:24', NULL, '2025-04-07 10:31:52');
INSERT INTO `msg_unread_record` VALUES (1790921612883025921, 1714187751569, 1713970987194, 0, 0, NULL, NULL, '2024-05-16 09:46:01', NULL, '2024-05-16 10:08:38');
INSERT INTO `msg_unread_record` VALUES (1790924275762749441, 1713970987194, 1714187751569, 0, 0, NULL, NULL, '2024-05-16 09:56:36', NULL, '2024-05-17 16:15:08');
INSERT INTO `msg_unread_record` VALUES (1790975002895867905, 1715836583052, 1714187751569, 0, 0, NULL, NULL, '2024-05-16 13:18:10', NULL, '2025-04-07 10:31:44');
INSERT INTO `msg_unread_record` VALUES (1790975090300968961, 1714187751569, 1715836583052, 2, 0, NULL, NULL, '2024-05-16 13:18:31', NULL, '2024-05-16 13:31:25');
INSERT INTO `msg_unread_record` VALUES (1791389948460814337, 1715823425456, 1714187751569, 1, 0, NULL, NULL, '2024-05-17 16:47:01', NULL, '2024-05-17 21:16:07');
INSERT INTO `msg_unread_record` VALUES (1791390024298024961, 1714187751569, 1715823425456, 0, 0, NULL, NULL, '2024-05-17 16:47:19', NULL, '2024-05-19 11:53:35');
INSERT INTO `msg_unread_record` VALUES (1791458264080674818, 1715823425456, 1715823298893, 0, 0, NULL, NULL, '2024-05-17 21:18:28', NULL, '2024-05-20 15:12:06');
INSERT INTO `msg_unread_record` VALUES (1792120620959879170, 1715823425456, 1716109753638, 1, 0, NULL, NULL, '2024-05-19 17:10:27', NULL, '2024-05-19 17:10:39');
INSERT INTO `msg_unread_record` VALUES (1907013061138903041, 1743501557796, 1715780878185, 1, 0, NULL, NULL, '2025-04-01 18:11:59', NULL, '2025-04-05 10:47:04');
INSERT INTO `msg_unread_record` VALUES (1907013132198801410, 1715780878185, 1743501557796, 0, 0, NULL, NULL, '2025-04-01 18:12:16', NULL, '2025-04-13 11:06:02');
INSERT INTO `msg_unread_record` VALUES (1909076912739110913, 1743994194201, 1713970987194, 3, 0, NULL, NULL, '2025-04-07 10:53:00', NULL, '2025-04-07 10:54:58');
INSERT INTO `msg_unread_record` VALUES (1909078943298207746, 1743994846661, 1713970987194, 0, 0, NULL, NULL, '2025-04-07 11:01:04', NULL, '2025-04-07 11:01:32');
INSERT INTO `msg_unread_record` VALUES (1909079021488422914, 1743994846661, 1715780878185, 1, 0, NULL, NULL, '2025-04-07 11:01:22', NULL, '2025-04-07 11:01:22');
INSERT INTO `msg_unread_record` VALUES (1911238248721989634, 1744509661548, 1713970987194, 0, 0, NULL, NULL, '2025-04-13 10:01:22', NULL, '2025-04-21 10:49:13');
INSERT INTO `msg_unread_record` VALUES (1914149350220939265, 1713970987194, 1744509661548, 1, 0, NULL, NULL, '2025-04-21 10:49:03', NULL, '2025-04-21 10:49:03');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `questionnaire_id` bigint(20) NOT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('SINGLE','MULTIPLE','TEXT') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `level` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 320 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (101, 1, '您最常使用的设备类型是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (102, 1, '您每天使用本应用的时长大约是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (103, 1, '您最常使用的功能有哪些？', 'MULTIPLE', 0);
INSERT INTO `question` VALUES (104, 1, '您对当前界面设计的满意度如何？', 'SINGLE', 0);
INSERT INTO `question` VALUES (105, 1, '您更倾向于哪种通知方式？', 'MULTIPLE', 0);
INSERT INTO `question` VALUES (106, 1, '您认为当前版本最需要改进的方面是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (107, 1, '您是否愿意推荐本应用给朋友？', 'SINGLE', 0);
INSERT INTO `question` VALUES (108, 1, '您常用的支付方式有哪些？', 'MULTIPLE', 0);
INSERT INTO `question` VALUES (109, 1, '您希望新增哪些功能？', 'MULTIPLE', 0);
INSERT INTO `question` VALUES (110, 1, '您对我们的产品还有什么其他建议？', 'TEXT', 0);
INSERT INTO `question` VALUES (201, 2, '过去两周内您的整体情绪状态如何？', 'SINGLE', 0);
INSERT INTO `question` VALUES (202, 2, '您最近遇到的主要压力来源是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (203, 2, '您每天的睡眠质量如何？', 'SINGLE', 0);
INSERT INTO `question` VALUES (204, 2, '当感到焦虑时，您通常如何应对？', 'SINGLE', 0);
INSERT INTO `question` VALUES (205, 2, '您进行体育锻炼的频率是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (206, 2, '您与亲友的交流频率是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (207, 2, '您是否出现过以下症状？', 'SINGLE', 0);
INSERT INTO `question` VALUES (208, 2, '您对心理咨询服务的接受程度是？', 'SINGLE', 0);
INSERT INTO `question` VALUES (209, 2, '您希望获得哪些方面的心理支持？', 'SINGLE', 0);
INSERT INTO `question` VALUES (210, 3, '请描述最近一次让您感到压力的事件', 'TEXT', 0);
INSERT INTO `question` VALUES (311, 4, 'How has your overall mood been over the past two weeks?', 'SINGLE', 0);
INSERT INTO `question` VALUES (312, 4, 'What has been your main source of stress recently?', 'SINGLE', 0);
INSERT INTO `question` VALUES (313, 4, 'How would you rate your daily sleep quality?', 'SINGLE', 0);
INSERT INTO `question` VALUES (314, 4, 'How do you typically cope when feeling anxious?', 'SINGLE', 0);
INSERT INTO `question` VALUES (315, 4, 'How often do you engage in physical exercise?', 'SINGLE', 0);
INSERT INTO `question` VALUES (316, 4, 'How frequently do you communicate with friends/family?', 'SINGLE', 0);
INSERT INTO `question` VALUES (317, 4, 'Have you experienced any of these symptoms?', 'SINGLE', 0);
INSERT INTO `question` VALUES (318, 4, 'How open are you to psychological counseling services?', 'SINGLE', 0);
INSERT INTO `question` VALUES (319, 4, 'Do you crave to make new friends?', 'SINGLE', 0);

-- ----------------------------
-- Table structure for question_answer
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer`  (
  `id` int(11) NOT NULL,
  `evaluation_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_answer
-- ----------------------------

-- ----------------------------
-- Table structure for question_option
-- ----------------------------
DROP TABLE IF EXISTS `question_option`;
CREATE TABLE `question_option`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) NOT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `level` int(11) NULL DEFAULT 0,
  `score` int(5) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_option
-- ----------------------------
INSERT INTO `question_option` VALUES (1001, 101, '智能手机', 0, NULL);
INSERT INTO `question_option` VALUES (1002, 101, '平板电脑', 0, NULL);
INSERT INTO `question_option` VALUES (1003, 101, '笔记本电脑', 0, NULL);
INSERT INTO `question_option` VALUES (1004, 102, '少于30分钟', 0, NULL);
INSERT INTO `question_option` VALUES (1005, 102, '30分钟-1小时', 0, NULL);
INSERT INTO `question_option` VALUES (1006, 102, '1-2小时', 0, NULL);
INSERT INTO `question_option` VALUES (1007, 102, '2小时以上', 0, NULL);
INSERT INTO `question_option` VALUES (1008, 103, '即时通讯', 0, NULL);
INSERT INTO `question_option` VALUES (1009, 103, '文件传输', 0, NULL);
INSERT INTO `question_option` VALUES (1010, 103, '视频会议', 0, NULL);
INSERT INTO `question_option` VALUES (1011, 103, '日程管理', 0, NULL);
INSERT INTO `question_option` VALUES (1012, 104, '非常满意', 0, NULL);
INSERT INTO `question_option` VALUES (1013, 104, '比较满意', 0, NULL);
INSERT INTO `question_option` VALUES (1014, 104, '一般', 0, NULL);
INSERT INTO `question_option` VALUES (1015, 104, '不满意', 0, NULL);
INSERT INTO `question_option` VALUES (1016, 105, '推送通知', 0, NULL);
INSERT INTO `question_option` VALUES (1017, 105, '邮件提醒', 0, NULL);
INSERT INTO `question_option` VALUES (1018, 105, '短信通知', 0, NULL);
INSERT INTO `question_option` VALUES (1019, 106, '运行速度', 0, NULL);
INSERT INTO `question_option` VALUES (1020, 106, '界面设计', 0, NULL);
INSERT INTO `question_option` VALUES (1021, 106, '功能丰富性', 0, NULL);
INSERT INTO `question_option` VALUES (1022, 106, '系统稳定性', 0, NULL);
INSERT INTO `question_option` VALUES (1023, 107, '非常愿意', 0, NULL);
INSERT INTO `question_option` VALUES (1024, 107, '可能愿意', 0, NULL);
INSERT INTO `question_option` VALUES (1025, 107, '不确定', 0, NULL);
INSERT INTO `question_option` VALUES (1026, 107, '不愿意', 0, NULL);
INSERT INTO `question_option` VALUES (1027, 108, '支付宝', 0, NULL);
INSERT INTO `question_option` VALUES (1028, 108, '微信支付', 0, NULL);
INSERT INTO `question_option` VALUES (1029, 108, '银联云闪付', 0, NULL);
INSERT INTO `question_option` VALUES (1030, 108, 'Apple Pay', 0, NULL);
INSERT INTO `question_option` VALUES (1031, 109, '多设备同步', 0, NULL);
INSERT INTO `question_option` VALUES (1032, 109, '语音助手', 0, NULL);
INSERT INTO `question_option` VALUES (1033, 109, '夜间模式', 0, NULL);
INSERT INTO `question_option` VALUES (1034, 109, '数据统计', 0, NULL);
INSERT INTO `question_option` VALUES (2001, 201, '非常积极', 0, 1);
INSERT INTO `question_option` VALUES (2002, 201, '比较积极', 0, 2);
INSERT INTO `question_option` VALUES (2003, 201, '一般', 0, 3);
INSERT INTO `question_option` VALUES (2004, 201, '比较消极', 0, 4);
INSERT INTO `question_option` VALUES (2005, 201, '非常消极', 0, 5);
INSERT INTO `question_option` VALUES (2006, 202, '工作/学业压力', 0, 1);
INSERT INTO `question_option` VALUES (2007, 202, '经济压力', 0, 2);
INSERT INTO `question_option` VALUES (2008, 202, '人际关系', 0, 3);
INSERT INTO `question_option` VALUES (2009, 202, '健康问题', 0, 4);
INSERT INTO `question_option` VALUES (2010, 202, '家庭矛盾', 0, 5);
INSERT INTO `question_option` VALUES (2011, 203, '非常好（7小时以上）', 0, 1);
INSERT INTO `question_option` VALUES (2012, 203, '较好（6-7小时）', 0, 2);
INSERT INTO `question_option` VALUES (2013, 203, '一般（5-6小时）', 0, 3);
INSERT INTO `question_option` VALUES (2014, 203, '较差（5小时以下）', 0, 4);
INSERT INTO `question_option` VALUES (2015, 204, '找人倾诉', 0, 1);
INSERT INTO `question_option` VALUES (2016, 204, '运动锻炼', 0, 2);
INSERT INTO `question_option` VALUES (2017, 204, '冥想/深呼吸', 0, 3);
INSERT INTO `question_option` VALUES (2018, 204, '转移注意力', 0, 4);
INSERT INTO `question_option` VALUES (2019, 204, '压抑情绪', 0, 5);
INSERT INTO `question_option` VALUES (2020, 205, '每天坚持', 0, 1);
INSERT INTO `question_option` VALUES (2021, 205, '每周3-5次', 0, 2);
INSERT INTO `question_option` VALUES (2022, 205, '每周1-2次', 0, 3);
INSERT INTO `question_option` VALUES (2023, 205, '几乎不运动', 0, 4);
INSERT INTO `question_option` VALUES (2024, 206, '每天交流', 0, 1);
INSERT INTO `question_option` VALUES (2025, 206, '每周3-5次', 0, 2);
INSERT INTO `question_option` VALUES (2026, 206, '每周1-2次', 0, 3);
INSERT INTO `question_option` VALUES (2027, 206, '几乎不交流', 0, 4);
INSERT INTO `question_option` VALUES (2028, 207, '持续情绪低落', 0, 1);
INSERT INTO `question_option` VALUES (2029, 207, '食欲明显变化', 0, 2);
INSERT INTO `question_option` VALUES (2030, 207, '注意力难以集中', 0, 3);
INSERT INTO `question_option` VALUES (2031, 207, '过度紧张焦虑', 0, 4);
INSERT INTO `question_option` VALUES (2032, 207, '睡眠障碍', 0, 5);
INSERT INTO `question_option` VALUES (2033, 208, '非常愿意尝试', 0, 1);
INSERT INTO `question_option` VALUES (2034, 208, '在必要时会考虑', 0, 2);
INSERT INTO `question_option` VALUES (2035, 208, '不太确定', 0, 3);
INSERT INTO `question_option` VALUES (2036, 208, '完全不愿意', 0, 4);
INSERT INTO `question_option` VALUES (2037, 209, '情绪管理技巧', 0, 1);
INSERT INTO `question_option` VALUES (2038, 209, '压力缓解方法', 0, 2);
INSERT INTO `question_option` VALUES (2039, 209, '人际关系指导', 0, 3);
INSERT INTO `question_option` VALUES (2040, 209, '职业规划建议', 0, 4);
INSERT INTO `question_option` VALUES (2041, 209, '自我认知提升', 0, 5);
INSERT INTO `question_option` VALUES (3062, 311, 'Increased significantly', 0, 1);
INSERT INTO `question_option` VALUES (3063, 311, 'Slightly increased', 0, 2);
INSERT INTO `question_option` VALUES (3064, 311, 'No change', 0, 3);
INSERT INTO `question_option` VALUES (3065, 311, 'Slightly decreased', 0, 4);
INSERT INTO `question_option` VALUES (3066, 311, 'Lost interest', 0, 5);
INSERT INTO `question_option` VALUES (3067, 312, 'Never', 0, 1);
INSERT INTO `question_option` VALUES (3068, 312, 'Rarely', 0, 2);
INSERT INTO `question_option` VALUES (3069, 312, 'Occasionally', 0, 3);
INSERT INTO `question_option` VALUES (3070, 312, 'Frequently', 0, 4);
INSERT INTO `question_option` VALUES (3071, 312, 'Constantly', 0, 5);
INSERT INTO `question_option` VALUES (3072, 313, 'Eating much more', 0, 1);
INSERT INTO `question_option` VALUES (3073, 313, 'Slightly increased', 0, 2);
INSERT INTO `question_option` VALUES (3074, 313, 'No change', 0, 3);
INSERT INTO `question_option` VALUES (3075, 313, 'Slightly decreased', 0, 4);
INSERT INTO `question_option` VALUES (3076, 313, 'Loss of appetite', 0, 5);
INSERT INTO `question_option` VALUES (3077, 314, 'No difficulty', 0, 1);
INSERT INTO `question_option` VALUES (3078, 314, 'Mild difficulty', 0, 2);
INSERT INTO `question_option` VALUES (3079, 314, 'Moderate difficulty', 0, 3);
INSERT INTO `question_option` VALUES (3080, 314, 'Severe difficulty', 0, 4);
INSERT INTO `question_option` VALUES (3081, 314, 'Cannot focus at all', 0, 5);
INSERT INTO `question_option` VALUES (3082, 315, 'Serious accident', 0, 1);
INSERT INTO `question_option` VALUES (3083, 315, 'Natural disaster', 0, 2);
INSERT INTO `question_option` VALUES (3084, 315, 'Physical abuse', 0, 3);
INSERT INTO `question_option` VALUES (3085, 315, 'Emotional abuse', 0, 4);
INSERT INTO `question_option` VALUES (3086, 315, 'Major loss', 0, 5);
INSERT INTO `question_option` VALUES (3087, 316, 'Very positive', 0, 1);
INSERT INTO `question_option` VALUES (3088, 316, 'Mostly positive', 0, 2);
INSERT INTO `question_option` VALUES (3089, 316, 'Neutral', 0, 3);
INSERT INTO `question_option` VALUES (3090, 316, 'Mostly negative', 0, 4);
INSERT INTO `question_option` VALUES (3091, 316, 'Very negative', 0, 5);
INSERT INTO `question_option` VALUES (3092, 317, 'Extremely optimistic', 0, 1);
INSERT INTO `question_option` VALUES (3093, 317, 'Cautiously optimistic', 0, 2);
INSERT INTO `question_option` VALUES (3094, 317, 'Neutral', 0, 3);
INSERT INTO `question_option` VALUES (3095, 317, 'Pessimistic', 0, 4);
INSERT INTO `question_option` VALUES (3096, 317, 'Hopeless', 0, 5);
INSERT INTO `question_option` VALUES (3097, 318, 'Open to it but have not explored it much yet', 0, 5);
INSERT INTO `question_option` VALUES (3098, 318, 'Willing to give it a try if it aligns with my needs.', 0, 4);
INSERT INTO `question_option` VALUES (3099, 318, 'Actively considering it as a potential solution for my challenges.', 0, 3);
INSERT INTO `question_option` VALUES (3100, 318, 'Eager to start counseling and believe it could significantly help me.', 0, 2);
INSERT INTO `question_option` VALUES (3101, 318, 'Fully committed to counseling as a cornerstone of my mental health journey.', 0, 1);
INSERT INTO `question_option` VALUES (3102, 319, 'Absolutely', 0, 5);
INSERT INTO `question_option` VALUES (3103, 319, 'Moderate', 0, 4);
INSERT INTO `question_option` VALUES (3104, 319, 'Neutral', 0, 3);
INSERT INTO `question_option` VALUES (3105, 319, 'Mild', 0, 2);
INSERT INTO `question_option` VALUES (3106, 319, 'Weakest', 0, 1);

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questionnaire
-- ----------------------------
INSERT INTO `questionnaire` VALUES (1, '移动端用户体验调研', '请根据真实使用体验填写问卷', '2025-04-02 18:03:03');
INSERT INTO `questionnaire` VALUES (2, '心理健康状况评估', '本问卷用于了解近期的心理状态，所有信息严格保密', '2025-04-05 11:27:20');
INSERT INTO `questionnaire` VALUES (4, 'Psychological Health Status Assessment', 'This questionnaire is used to understand recent psychological status, and all information is strictly confidential', '2025-04-12 09:20:50');

-- ----------------------------
-- Table structure for sys_banner
-- ----------------------------
DROP TABLE IF EXISTS `sys_banner`;
CREATE TABLE `sys_banner`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `sort_num` int(10) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1776946016830275592 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_banner
-- ----------------------------
INSERT INTO `sys_banner` VALUES (1776946016830275589, 'http://localhost:8066/uploadImages/20240513084136696.jpg', '热门活动', 1, 100, '2024-05-11 10:58:05', '2024-05-13 20:41:38');
INSERT INTO `sys_banner` VALUES (1776946016830275590, 'http://localhost:8066/uploadImages/20240513084130405.jpg', '新游戏来袭！！！', 1, 123, '2024-05-11 13:26:40', '2024-05-13 20:41:52');
INSERT INTO `sys_banner` VALUES (1776946016830275591, 'http://localhost:8066/uploadImages/20240513084123647.jpg', '测试', 1, 21, '2024-05-11 13:41:54', '2024-05-13 20:41:25');

-- ----------------------------
-- Table structure for sys_news
-- ----------------------------
DROP TABLE IF EXISTS `sys_news`;
CREATE TABLE `sys_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序（序号越大越靠前）',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '状态（0：禁止；1：显示）',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '新闻类型',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户id',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `news_image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_news
-- ----------------------------
INSERT INTO `sys_news` VALUES (1, '开发者笔记 - 新固定史诗套装调整方案', '<p>&nbsp;&nbsp;&nbsp;大家好，我是国服制作人洪镇赫。</p><p>&nbsp;&nbsp;&nbsp;近期于体验服爆料的新固定史诗套装相关内容引起了一些讨论，我想通过这篇开发者笔记向大家传达一些信息。</p><p>我们注意到勇士们很关注自定义装备是否被淘汰的问题，我想在这里阐明我们的意图和应对策略。</p><p>&nbsp;</p><p>&nbsp;&nbsp;&nbsp;首先，虽然勇士们对于固定史诗套装属性各持观点，但我们在最初策划新史诗套装时，首要目的是保证不辜负已经努力获取到自定义的勇士的努力，同时为勇士们提供更多样的选择。</p><p>&nbsp;&nbsp;&nbsp;为此，我们考虑了自定义搭配的平衡，以最常用的搭配——即以深潜手镯为基础的“劳力士”的全身自定义搭配为标准设置了平衡。</p><p>然而当体验服上线后，出现了我们未能预料的情况 : 新史诗套装与攻速鞋搭配产生的效率要高于自定义搭配，导致一些通过大量努力获取的自定义的强度不尽人意。我们在发现问题后立即进行了内部商讨，并与腾讯的负责人讨论了应对策略，我们计划通过以下方式解决这个问题 :&nbsp;</p><p>&nbsp;</p><p>1.“未知命魂之痕”、“巡礼命魂之痕”、“沉沙命魂之痕”套装效果的技能伤害属性将被下调，预计平均下调约4%。</p><p><br></p><p><br></p><ol><li>删除“未知命魂之痕”、“巡礼命魂之痕”套装中的速度属性，删除项链部位“未知之瞳项链”、“巡礼者的困局”、“总司令官的壮烈一步”的速度属性。</li></ol><p>3.“未知征战之录”、“巡礼征战之录”、“沉沙征战之录”套装效果中添加速度属性，分别为所有速度属性增加25%、20%、15%。</p><p><br></p><p><br></p><ol><li>未知套、巡礼套、沉沙套都将添加11件套装效果。</li></ol><p>混合搭配录/痕套装时，也可以获得11件套装效果。 (例如 : 沉沙征战之录套装 + 未知征战之录套装)</p><p>套装效果为技能伤害 +3%，技能范围 +10%，移动速度+15%。</p><p><br></p><p><br></p><ol><li>耳环部位“未知之力战镰”、“巡礼者的前进之意志”、“总司令官的战友遗物”的四维 +125属性将改为辅助职业专属属性。</li></ol><p>以这次新史诗套装问题为例，我们正密切关注体验服出现的问题和勇士的意见，并计划尽快响应和商讨来解决问题。我们将继续努力发挥体验服的作用，并尽我们最大的努力让勇士们能够享受到更多的DNF的更新。</p><p>&nbsp;</p><p>谢谢大家。</p><p>洪镇赫。&nbsp;</p>', NULL, 1, 2, NULL, NULL, '2024-05-11 14:21:33', '2024-05-11 22:15:29', NULL, NULL, NULL);
INSERT INTO `sys_news` VALUES (2, '测试', '<h2 class=\"ql-align-center\"><span style=\"color: rgb(230, 0, 0);\">开发者笔记 - 新固定史诗套装调整方案</span></h2>', NULL, 1, 1, NULL, NULL, '2024-05-11 14:26:11', '2024-05-11 22:15:36', NULL, NULL, NULL);
INSERT INTO `sys_news` VALUES (3, '本地图片测试', '<p><img src=\"http://localhost:8066/uploadImages/20240511023554526.png\"></p>', NULL, 1, 2, NULL, NULL, '2024-05-11 14:35:58', '2024-05-11 22:15:16', NULL, NULL, NULL);
INSERT INTO `sys_news` VALUES (4, '《DNF全职业百科》12.21版本特别节目直播', '<p>	《地下城与勇士：创新世纪》（以下简称“DNF”或“地下城与勇士”）12月21日神界版本来啦！你是否还在为新版本的职业成长犯愁？还在担心版本理解跟不上大部队？不用担心！DNF神界版本特别节目《全职业百科》将于12月21日-12月24日 每天14:00直播开讲，干货满满，精彩不断！</p><p>	&nbsp;</p><p class=\"ql-align-center\"><img src=\"https://game.gtimg.cn/images/dnf/a20230424comic/121901.png\"></p><p>&nbsp;</p><p><strong>【直播时间：12月21日-12月24日 每天14:00-22:00，邀你共同畅游神界】</strong></p><p>斗鱼直播：<a href=\"https://www.douyu.com/470321?dyshid=3ae3aa7-31507a9d88e6d91948e8b54600061601&amp;dyshci=40\" rel=\"noopener noreferrer\" target=\"_blank\">立即前往》》</a></p><p>虎牙直播：<a href=\"https://www.huya.com/660143\" rel=\"noopener noreferrer\" target=\"_blank\">立即前往》》</a></p><p>AcFun直播：<a href=\"https://live.acfun.cn/live/34748392\" rel=\"noopener noreferrer\" target=\"_blank\">立即前往》》</a></p><p>Bilibili直播：<a href=\"https://live.bilibili.com/4089850\" rel=\"noopener noreferrer\" target=\"_blank\">立即前往》》</a></p><p>快手直播：<a href=\"https://live.kuaishou.com/u/3xmd2iwxjjxi28s\" rel=\"noopener noreferrer\" target=\"_blank\">立即前往》》</a></p><p>勇士们还可在微信视频号搜索“地下城与勇士”进入官方赛事直播间，观看《全职业百科》DNF神界版本特别节目。</p><p>&nbsp;</p><p><strong>【14:00-22:00职业大咖现身说法，金牌讲师正式开课】</strong></p><p>	经过层层筛选的65位各职业大咖将组成豪华的金牌讲师团，为各位勇士带来上手神界版本的各项“课程”！各个职业大咖们依次上阵，轮番带来神界版本下各个职业的改版技能介绍、搬砖性能分析及强度分析。</p><p>	同时针对大家关注的职业打造问题，各个职业大咖们也将为各位勇士带来技能加点选择、护石选择和徽章及时装选择的建议，在流派抉择上也会重点为大家根据职业适配度推荐合适的流派并在装备天赋选择、自定义词条选择等方面给到“最优解”。</p><p>	现场还将通过实机演示的方式为各位勇士直观呈现各个职业各个流派的打桩演示和实战副本演示。最后的直播Q&amp;A环节也将为各位勇士答疑解惑，各位勇士一定不要错过啦！</p>', NULL, 1, 1, NULL, NULL, '2024-05-11 21:34:47', '2024-05-11 22:09:29', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_news_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_news_type`;
CREATE TABLE `sys_news_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_news_type
-- ----------------------------
INSERT INTO `sys_news_type` VALUES (1, '普通通告', 1, 1, '2024-05-10 23:23:36', '2024-05-10 23:28:57');
INSERT INTO `sys_news_type` VALUES (2, '紧急通告', 2, 0, '2024-05-10 23:28:50', '2024-05-10 23:54:00');

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for sys_question
-- ----------------------------
DROP TABLE IF EXISTS `sys_question`;
CREATE TABLE `sys_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zone_id` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_question
-- ----------------------------

-- ----------------------------
-- Table structure for sys_question_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_question_log`;
CREATE TABLE `sys_question_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_question_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(10) NULL DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT 1,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15851 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 100, 1, '2023-06-07 01:02:16', '2024-04-27 10:10:00', '最高权限，仅后台登录，不要删除');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'common', 50, 1, '2023-05-07 10:02:27', '2024-05-07 14:57:43', '普通用户，禁止删除');
INSERT INTO `sys_role` VALUES (15850, '测试', 'technician', 30, 0, '2024-04-24 21:47:57', '2024-05-13 20:54:15', '技术，仅后台');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `full_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `status` int(1) NULL DEFAULT 0 COMMENT '帐号状态（0正常 1停用）',
  `sex` int(1) NULL DEFAULT 1 COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标注身份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1713970987194', '15200002222', 'e10adc3949ba59abbe56e057f20f883e', 'CECE', '15200002222', 0, 1, NULL, NULL, NULL, '2024-04-24 23:03:07', '2024-05-10 21:43:43', 'doctor');
INSERT INTO `sys_user` VALUES ('1714187751569', '15622080099', 'e10adc3949ba59abbe56e057f20f883e', 'ZHANGXIE', '15200001111', 0, 1, 'http://localhost:8066/uploadImages/20240514091304885.png', NULL, NULL, '2024-04-27 11:15:52', '2024-05-14 09:13:06', '用户账户');
INSERT INTO `sys_user` VALUES ('1715780878185', '18800001111', 'e10adc3949ba59abbe56e057f20f883e', 'XIEXIE', '18800001111', 0, 1, NULL, NULL, NULL, '2024-05-15 21:47:58', '2024-05-15 23:22:58', 'doctor');
INSERT INTO `sys_user` VALUES ('1715821849672', '16600001111', 'e10adc3949ba59abbe56e057f20f883e', '哈喽', '16600001111', 0, 1, NULL, NULL, NULL, '2024-05-16 09:10:50', '2024-05-16 09:28:58', NULL);
INSERT INTO `sys_user` VALUES ('1715823298893', '16645451122', 'e10adc3949ba59abbe56e057f20f883e', '潇潇', '16645451122', 0, 1, NULL, NULL, NULL, '2024-05-16 09:34:59', '2024-05-16 09:35:30', NULL);
INSERT INTO `sys_user` VALUES ('1715823425456', '16611112222', 'e35cf7b66449df565f93c607d5a81d09', '中国', '16611112222', 0, 1, 'http://localhost:8066/uploadImages/20240519104305697.jpg', NULL, NULL, '2024-05-16 09:37:05', '2024-05-19 11:52:51', NULL);
INSERT INTO `sys_user` VALUES ('1715836583052', '15588881111', 'e10adc3949ba59abbe56e057f20f883e', '15588881111', '15588881111', 0, 1, NULL, NULL, NULL, '2024-05-16 13:16:23', '2024-05-16 13:16:23', NULL);
INSERT INTO `sys_user` VALUES ('1716109753638', '18812341234', 'e10adc3949ba59abbe56e057f20f883e', '灰太狼', '18812341234', 0, 1, NULL, NULL, NULL, '2024-05-19 17:09:14', '2024-05-19 17:09:14', NULL);
INSERT INTO `sys_user` VALUES ('1743501557796', '15500001111', 'e10adc3949ba59abbe56e057f20f883e', 'Zhang', '15500001111', 0, 1, 'http://localhost:8091/uploadImages/20250405023136852.png', NULL, NULL, '2025-04-01 17:59:18', '2025-04-05 14:31:37', NULL);
INSERT INTO `sys_user` VALUES ('1743994846661', '17711112222', 'e10adc3949ba59abbe56e057f20f883e', 'nnda', '17711112222', 0, 1, NULL, NULL, NULL, '2025-04-07 11:00:47', '2025-04-07 11:00:47', NULL);
INSERT INTO `sys_user` VALUES ('1744509661548', '13455115511', 'e10adc3949ba59abbe56e057f20f883e', 'MD', '13455115511', 0, 1, NULL, NULL, NULL, '2025-04-13 10:01:02', '2025-04-13 10:01:02', NULL);
INSERT INTO `sys_user` VALUES ('aaa', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '1867777777', 0, 1, NULL, '111.22.22.1', '2021-03-03 12:41:15', '2024-04-23 17:41:38', '2024-05-10 21:43:51', '管理平台账号');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1715348622679', '1713970987194', 2);
INSERT INTO `sys_user_role` VALUES ('1715348631050', 'aaa', 1);
INSERT INTO `sys_user_role` VALUES ('1715348637519', '1714187751569', 2);
INSERT INTO `sys_user_role` VALUES ('1715780878198', '1715780878185', 2);
INSERT INTO `sys_user_role` VALUES ('1715822938126', '1715821849672', 2);
INSERT INTO `sys_user_role` VALUES ('1715823330025', '1715823298893', 2);
INSERT INTO `sys_user_role` VALUES ('1715823425459', '1715823425456', 2);
INSERT INTO `sys_user_role` VALUES ('1715836583083', '1715836583052', 2);
INSERT INTO `sys_user_role` VALUES ('1716109753653', '1716109753638', 2);
INSERT INTO `sys_user_role` VALUES ('1743501557804', '1743501557796', 2);
INSERT INTO `sys_user_role` VALUES ('1743994194263', '1743994194201', 2);
INSERT INTO `sys_user_role` VALUES ('1743994697982', '1743994697937', 2);
INSERT INTO `sys_user_role` VALUES ('1743994846769', '1743994846661', 2);
INSERT INTO `sys_user_role` VALUES ('1744509661661', '1744509661548', 2);

-- ----------------------------
-- Table structure for sys_user_zone
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_zone`;
CREATE TABLE `sys_user_zone`  (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zone_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_zone
-- ----------------------------

-- ----------------------------
-- Table structure for sys_zone
-- ----------------------------
DROP TABLE IF EXISTS `sys_zone`;
CREATE TABLE `sys_zone`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `zone_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '话题',
  `zone_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `status` int(2) NULL DEFAULT 1 COMMENT '状态',
  `zone_type_id` int(11) NULL DEFAULT NULL COMMENT '类型Id',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `zone_image_url` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `zone_capacity` int(10) NULL DEFAULT NULL COMMENT '数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_zone
-- ----------------------------
INSERT INTO `sys_zone` VALUES ('1715847431870', 'What is mental health', 'Psychological health refers to the state in which all aspects and activities of the mind are in a good or normal state. The ideal state of mental health is to maintain a perfect personality, normal intelligence, correct cognition, appropriate emotions, reasonable will, positive attitude, appropriate behavior, and good adaptation.', 1, 3, 'aaa', NULL, NULL, '2025-04-05 16:17:12', '2025-04-05 21:33:16', NULL);
INSERT INTO `sys_zone` VALUES ('1716037271034', 'Hello ', NULL, 1, 6, 'aaa', NULL, NULL, '2024-05-18 21:01:11', '2024-05-18 21:01:11', NULL);
INSERT INTO `sys_zone` VALUES ('1716037448739', 'The basis for constructing a psychological health rating scale', '<h2>1、Emotional state</h2>\n<ul>\n    <li>Have you been feeling down or depressed frequently in the past week?</li>\n    <li>What is your typical emotional response when facing pressure or difficulties?</li>\n    <li>Do you easily feel anxious or nervous, especially when facing unknown or important events?</li>\n</ul>\n \n<h2>2、Cognitive function</h2>\n<ul>\n    <li>Do you feel that your memory, attention, or clarity of thought have declined?</li>\n    <li>Do you often feel hesitant or lack confidence when making decisions?</li>\n    <li>Do you often worry about your performance or abilities, even without obvious reasons?</li>\n</ul>\n \n<h2>3、Social interaction</h2>\n<ul>\n    <li>Are you willing to share your feelings and thoughts with others?</li>\n    <li>Do you often feel uncomfortable or difficult to integrate when interacting with others?</li>\n    <li>Do you feel that you lack confidence or are easily overlooked in social situations?</li>\n</ul>\n \n<h2>4、Response mechanism</h2>\n<ul>\n    <li>How do you usually deal with setbacks or failures?</li>\n    <li>Do you have any effective methods for relaxing or reducing stress?</li>\n    <li>Can you remain calm and find solutions to problems when facing pressure?</li>\n</ul>\n \n<h2>5、Lifestyle habits and interests</h2>\n<ul>\n    <li>How is your sleep quality? Do you often suffer from insomnia or wake up early?</li>\n    <li>Do you maintain regular eating habits and moderate exercise?</li>\n    <li>Do you maintain a sustained interest and passion for certain things in life?</li>\n</ul>\n \n<h2>6、Self awareness and values</h2>\n<ul>\n    <li>Do you have a clear understanding of your own value and abilities?</li>\n    <li>Do you frequently reflect on your actions and decisions, and seek improvement?</li>\n    <li>Do you feel that your life has meaning and purpose?</li>\n</ul>\n \n<p class=\"note\">\n    Please note that these questions are only examples and do not constitute a complete mental health rating scale. In practical applications, you need to design more targeted and accurate questions based on specific evaluation purposes and objects, combined with professional mental health scales (such as anxiety scales, depression scales, etc.). At the same time, it is important to ensure that the problem is expressed clearly and concisely, avoiding the use of overly complex or vague language.\n</p>\n \n<p class=\"note\">\n    In addition, when designing scoring criteria, it is also necessary to consider the weight and scoring criteria of each question, in order to objectively and accurately quantify the mental health status of the evaluated person. This usually requires a combination of professional knowledge and practical experience.\n</p>', 1, 6, 'aaa', NULL, NULL, '2025-04-05 21:04:09', '2025-04-05 21:04:09', NULL);
INSERT INTO `sys_zone` VALUES ('1716105203088', 'Standards for mental health\n', '<p>(1) Normal intelligence.</p>\r\n<p></p>\r\n<p>(2) Harmonious interpersonal relationships.</p>\r\n<p></p>\r\n<p>(3) Emotionally positive and stable.</p>\r\n<p></p>\r\n<p>(4) Strong willpower and character.</p>\r\n<p></p>\r\n<p>(5) Self awareness is correct.</p>\r\n<p></p>\r\n<p>(6) Complete personality structure.</p>\r\n<p></p>\r\n<p>(7) Good adaptation to the environment.</p>', 1, 3, '1715823425456', NULL, NULL, '2025-04-05 15:53:23', '2025-04-05 15:53:23', NULL);
INSERT INTO `sys_zone` VALUES ('1716186410052', 'Basic principles for assessing mental health', '<p>(1) The identity between psychology and environment. </p>\r\n<p>(2) The unity of psychology and behavior. </p>\r\n<p>(3) The relative stability of personality.</p>', 1, 3, '1715823425456', NULL, NULL, '2025-04-05 14:26:50', '2025-04-05 14:26:50', '1792043635785068545');

-- ----------------------------
-- Table structure for sys_zone_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_zone_log`;
CREATE TABLE `sys_zone_log`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zone_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '话题id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间（无用）',
  `status` int(2) NULL DEFAULT 1,
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_zone_log
-- ----------------------------
INSERT INTO `sys_zone_log` VALUES ('1714204081982', '1714187751569', NULL, '1715847431870', '大家好啊，大家好', '2024-04-27 15:48:02', '2024-04-27 16:03:14', 1, '测试');
INSERT INTO `sys_zone_log` VALUES ('1714243652473', 'aaa', NULL, '1715847431870', '哈哈哈哈\r\n哈哈哈\r\n哈哈哈\r\n12321111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111\r\n我还要大苏打\r\n1232111111111111111111111111111111111111111111111111111', '2024-04-28 02:47:32', '2024-04-28 02:48:01', 1, '212');
INSERT INTO `sys_zone_log` VALUES ('1714270154520', 'aaa', NULL, '1713977507101', NULL, '2024-04-28 10:09:15', '2024-04-28 10:14:54', 3, '都是错的');
INSERT INTO `sys_zone_log` VALUES ('1714270439273', 'aaa', NULL, '1713977507101', NULL, '2024-04-28 10:13:59', '2024-04-28 10:14:55', 3, '12');
INSERT INTO `sys_zone_log` VALUES ('1716085561094', '1714187751569', NULL, '1715847431870', '我细化姑娘你单独', '2024-05-19 10:26:01', '2024-05-19 10:26:01', 1, NULL);
INSERT INTO `sys_zone_log` VALUES ('1716085710791', '1715823425456', NULL, '1715847431870', '社会社会', '2024-05-19 10:28:31', '2024-05-19 10:28:31', 1, NULL);

-- ----------------------------
-- Table structure for sys_zone_log_image
-- ----------------------------
DROP TABLE IF EXISTS `sys_zone_log_image`;
CREATE TABLE `sys_zone_log_image`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `image_url` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `zone_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_zone_log_image
-- ----------------------------
INSERT INTO `sys_zone_log_image` VALUES (12, '1783048338987462661', 'head.png', 'http://localhost:8055/uploadImages/20240508041409357-waterMark.png', '');
INSERT INTO `sys_zone_log_image` VALUES (13, '1783048338987462661', 'background.png', 'http://localhost:8055/uploadImages/20240508041453309-waterMark.png', '');

-- ----------------------------
-- Table structure for sys_zone_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_zone_type`;
CREATE TABLE `sys_zone_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sort` int(11) NULL DEFAULT 1,
  `status` int(2) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_zone_type
-- ----------------------------
INSERT INTO `sys_zone_type` VALUES (3, 'Popular Science', 100, 1, '2024-05-10 23:47:10', '2024-05-12 16:56:58');
INSERT INTO `sys_zone_type` VALUES (6, 'Evaluation\n', 1, 1, '2024-05-12 16:56:25', '2024-05-18 21:06:35');
INSERT INTO `sys_zone_type` VALUES (10, 'Communicate', 15, 1, '2024-05-13 10:48:10', '2024-05-13 10:48:10');

SET FOREIGN_KEY_CHECKS = 1;
