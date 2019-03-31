/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-31 11:54:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cuid` int(11) NOT NULL,
  `crid` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `comment_cuid` (`cuid`),
  KEY `comment_crid` (`crid`),
  CONSTRAINT `comment_crid` FOREIGN KEY (`crid`) REFERENCES `user` (`uid`) ON DELETE CASCADE,
  CONSTRAINT `comment_cuid` FOREIGN KEY (`cuid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `cname` varchar(50) CHARACTER SET utf8 NOT NULL,
  `tags` varchar(255) CHARACTER SET utf8 NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 NOT NULL,
  `type` int(255) NOT NULL DEFAULT '0',
  `content` varchar(1000) CHARACTER SET utf8 DEFAULT '',
  `intro` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `course_tid` (`tid`),
  CONSTRAINT `course_tid` FOREIGN KEY (`tid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('2', '2', 'Python', '[\"选项4\"]', 'default.jpg', '1', '[{\"id\":0,\"chapterName\":\"1\",\"sections\":[]},{\"id\":1,\"chapterName\":\"1\",\"sections\":[]},{\"id\":2,\"chapterName\":\"1\",\"sections\":[]},{\"id\":3,\"chapterName\":\"1\",\"sections\":[]}]', 'react综合案例操作，技能掌握更纯熟');
INSERT INTO `course` VALUES ('3', '5', '机器视觉', '[\"选项3\",\"选项2\",\"选项5\",\"选项4\"]', 'default.jpg', '1', '[{\"id\":0,\"chapterName\":\"人工智能的发展历史\",\"sections\":[]},{\"id\":1,\"chapterName\":\"人工智能的未来\",\"sections\":[]},{\"id\":2,\"chapterName\":\"机器视觉入门\",\"sections\":[]},{\"id\":3,\"chapterName\":\"机器视觉应用\",\"sections\":[]}]', 'react综合案例操作，技能掌握更纯熟');
INSERT INTO `course` VALUES ('4', '2', 'JAVA技术开发', '[\"后端开发\"]', 'd1919c1e-bee8-4983-b651-1d71aa2aeb08.jpg', '1', '[{\"id\":1,\"chapterName\":\"java简介\",\"sections\":[{\"id\":1,\"sectionName\":\"java的发展\",\"time\":\"2019-02-28T16:00:00.000Z\"}]},{\"id\":2,\"chapterName\":\"java基础\",\"sections\":[{\"id\":1,\"sectionName\":\"java的命名规则\",\"time\":\"2019-02-28T17:00:00.000Z\"},{\"id\":2,\"sectionName\":\"java的循环\",\"time\":\"2019-02-28T18:00:00.000Z\"},{\"id\":3,\"sectionName\":\"java的方法\",\"time\":\"2019-02-28T19:00:00.000Z\"}]},{\"id\":3,\"chapterName\":\"面向对象\",\"sections\":[{\"id\":1,\"sectionName\":\"java类\",\"time\":\"2019-03-01T19:00:00.000Z\"},{\"id\":2,\"sectionName\":\"继承\",\"time\":\"2019-03-02T19:00:00.000Z\"},{\"id\":3,\"sectionName\":\"抽象方法\",\"time\":\"2019-03-03T19:00:00.000Z\"},{\"id\":4,\"sectionName\":\"抽象类\",\"time\":\"2019-03-03T19:00:00.000Z\"},{\"id\":5,\"sectionName\":\"接口\",\"time\":\"2019-03-04T19:00:00.000Z\"}]},{\"id\":4,\"chapterName\":\"集合框架\",\"sections\":[]},{\"id\":5,\"chapterName\":\"线程\",\"sections\":[]},{\"id\":6,\"chapterName\":\"IO框架\",\"sections\":[]}]', '本课程讲解各种java框架');
INSERT INTO `course` VALUES ('5', '2', '数学之美', '[\"算法&数学\"]', '5ade006c-ac22-491e-ad6e-a367aba96190.jpg', '1', '[{\"id\":1,\"chapterName\":\"测试\",\"sections\":[{\"id\":1,\"sectionName\":\"数学之美\",\"time\":\"2019-03-28T06:43:33.000Z\"}]},{\"id\":2,\"chapterName\":\"第二章\",\"sections\":[{\"id\":1,\"sectionName\":\"数学之美\",\"time\":\"2019-03-28T06:43:33.000Z\"}]}]', '数学之美');
INSERT INTO `course` VALUES ('6', '2', '数学之美啊', '[\"算法&数学\"]', 'fefb212f-197f-45b1-88a2-d92af84e3d23.jpg', '1', '[{\"id\":1,\"chapterName\":\"测试\",\"sections\":[{\"id\":1,\"sectionName\":\"数学之美\",\"time\":\"2019-03-28T06:43:33.000Z\"},{\"id\":2,\"sectionName\":\"测试\",\"time\":\"2019-03-28T08:15:08.000Z\"}]},{\"id\":2,\"chapterName\":\"第二章\",\"sections\":[{\"id\":1,\"sectionName\":\"数学之美\",\"time\":\"2019-03-28T06:43:33.000Z\"}]}]', '数学之美');
INSERT INTO `course` VALUES ('7', '2', '算法导论', '[\"算法&数学\"]', 'fe5202e8-3810-4079-b62c-bb60b3e5b601.jpg', '1', '[{\"id\":1,\"chapterName\":\"贪心算法\",\"sections\":[],\"chapterIntro\":\"我们将介绍著名的迪杰斯特拉贪心算法\"},{\"id\":2,\"chapterName\":\"背包问题\",\"sections\":[],\"chapterIntro\":\"介绍关于01背包问题\"}]', '介绍各种主流算法');

-- ----------------------------
-- Table structure for eassy
-- ----------------------------
DROP TABLE IF EXISTS `eassy`;
CREATE TABLE `eassy` (
  `uid` int(11) NOT NULL,
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `content` varchar(3000) CHARACTER SET utf8 DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8 NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE,
  KEY `eassy_uid` (`uid`),
  CONSTRAINT `eassy_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of eassy
-- ----------------------------

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromid` int(11) DEFAULT NULL,
  `fromname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `toid` int(11) DEFAULT NULL,
  `toname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  `message` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) /*!50100 STORAGE MEMORY */ ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('-1', '2', '123', '3', '123', '4', '哈哈<喷血>');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `scid` int(255) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `schedule` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`scid`),
  KEY `scuid` (`uid`),
  KEY `sccid` (`cid`),
  CONSTRAINT `sccid` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE,
  CONSTRAINT `scuid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('1', '6', '5', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `head_img` varchar(100) CHARACTER SET utf8 DEFAULT 'default.jpg',
  `account` varchar(50) CHARACTER SET utf8 NOT NULL,
  `passwd` varchar(100) CHARACTER SET utf8 NOT NULL,
  `role` int(255) NOT NULL,
  `addr` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 NOT NULL,
  `sex` int(255) DEFAULT '0',
  `friends` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '邓松松', '2783214886.jpg', '2783214886', '289d37c91e7d594ada43712153e962a6', '0', null, '', null, null);
INSERT INTO `user` VALUES ('3', '123', '1234.jpg', '1234', '202cb962ac59075b964b07152d234b70', '0', null, '', '0', '[4]');
INSERT INTO `user` VALUES ('4', '12345', 'default.jpg', '12345', '202cb962ac59075b964b07152d234b70', '0', null, '', null, '[3,2]');
INSERT INTO `user` VALUES ('5', '吴丛明', '353060075.jpg', '353060075', '289d37c91e7d594ada43712153e962a6', '0', null, '', null, null);
INSERT INTO `user` VALUES ('6', '学生', 'student.jpg', 'student', '202cb962ac59075b964b07152d234b70', '1', null, '', null, null);
