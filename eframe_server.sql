

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_email
-- ----------------------------
DROP TABLE IF EXISTS `sys_email`;
CREATE TABLE `sys_email`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL_CODE` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL_USERNAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL_PROTOCOL` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL_HOST` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL_PORT` int(8) NULL DEFAULT NULL,
  `EMAIL_TIMEOUT` bigint(20) NULL DEFAULT NULL,
  `AUTH` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL_SENDER` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PERSONAL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SUBJECT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `CREATE_OBJ_TYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_OBJ_ID` bigint(20) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `LAST_UPDATE_OBJ_TYPE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_UPDATE_OBJ_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_lang
-- ----------------------------
DROP TABLE IF EXISTS `sys_lang`;
CREATE TABLE `sys_lang`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LANG_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LANG_LANGUAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_parameter` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `response` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_millionsecond` bigint(255) NULL DEFAULT NULL,
  `in_time` datetime(0) NULL DEFAULT NULL,
  `out_millionsecond` bigint(255) NULL DEFAULT NULL,
  `out_time` datetime(0) NULL DEFAULT NULL,
  `oper_obj_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oper_obj_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `errorstack` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2266 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_PATH` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `COMPONENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REDIRECT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ICON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_LEVEL` int(3) NULL DEFAULT NULL,
  `PARENT_MENU` bigint(20) NULL DEFAULT NULL,
  `TOP_MENU` bigint(20) NULL DEFAULT NULL,
  `MENU_DESCRIPTION` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `EXPIRE_TIME` datetime(0) NULL DEFAULT NULL,
  `EFFECTIVE_TIME` datetime(0) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `LAST_UPDATE_STAFF` bigint(20) NULL DEFAULT NULL,
  `EXT_INFO` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'MANAGER', '管理系统', '/', '../layouts/BasicLayout', NULL, NULL, 0, 0, 0, NULL, '0', '2018-08-19 13:58:50', '2098-12-31 16:00:00', '2018-08-19 13:58:50', '2019-03-05 08:42:58', NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, 'MANAGER2', 'dashboard', '/dashboard', '', NULL, 'dashboard', 1, 1, 1, NULL, '0', '2018-08-19 13:58:50', '2098-12-31 16:00:00', '2018-08-19 13:58:50', '2018-08-19 13:58:50', NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, 'MANAGER3', '分析页', '/dashboard/analysis', './Dashboard/Analysis', NULL, '', 2, 3, 1, NULL, '0', '2018-08-19 13:58:50', '2098-12-31 16:00:00', '2018-08-19 13:58:50', '2019-02-13 13:33:19', NULL, NULL);
INSERT INTO `sys_menu` VALUES (5, 'MENU_CODE_15499589338624a7iB0', '用户首页', '/user', '../layouts/UserLayout', NULL, NULL, 0, NULL, NULL, NULL, '0', '2019-02-12 08:08:53', '2098-12-31 16:00:00', '2019-02-12 08:08:53', '2019-02-12 08:08:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES (7, 'MENU_CODE_1549959237922Ka8ki2', '注册', '/user/register', './User/Register', NULL, NULL, 1, 5, NULL, NULL, '0', '2019-02-12 08:13:57', '2098-12-31 16:00:00', '2019-02-12 08:13:57', '2019-02-12 08:13:57', NULL, NULL);
INSERT INTO `sys_menu` VALUES (8, 'MENU_CODE_1549959479883RAnBP3', '注册结果', '/user/register-result', './User/RegisterResult', NULL, NULL, 1, 5, NULL, NULL, '0', '2019-02-12 08:17:59', '2098-12-31 16:00:00', '2019-02-12 08:17:59', '2019-02-12 08:17:59', NULL, NULL);
INSERT INTO `sys_menu` VALUES (9, 'MENU_CODE_1549981792210C4oUQ0', '登陆', '/user/login', './User/Login', NULL, NULL, 1, 5, NULL, NULL, '0', '2019-02-12 14:29:52', '2098-12-31 16:00:00', '2019-02-12 14:29:52', '2019-02-12 14:29:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (10, 'MENU_CODE_1549982067257E0wnQ1', '监控页', '/dashboard/monitor', './Dashboard/Monitor', NULL, '', 2, 3, NULL, NULL, '0', '2019-02-12 14:34:27', '2098-12-31 16:00:00', '2019-02-12 14:34:27', '2019-02-13 13:33:15', NULL, NULL);
INSERT INTO `sys_menu` VALUES (11, 'MENU_CODE_1549982105258tUCVZ2', '工作台', '/dashboard/workplace', './Dashboard/Workplace', NULL, '', 2, 3, NULL, NULL, '0', '2019-02-12 14:35:05', '2098-12-31 16:00:00', '2019-02-12 14:35:05', '2019-02-13 13:33:10', NULL, NULL);
INSERT INTO `sys_menu` VALUES (12, 'MENU_CODE_1549982179270czkuM3', '管理员管理', '/manager', NULL, NULL, 'table', 1, 1, NULL, NULL, '0', '2019-02-12 14:36:19', '2098-12-31 16:00:00', '2019-02-12 14:36:19', '2019-02-12 14:36:19', NULL, NULL);
INSERT INTO `sys_menu` VALUES (13, 'MENU_CODE_1549982212322QMjCV4', '权限管理', '/manager/permission/PermissionTableList', './manager/permission/PermissionTableList', NULL, NULL, 2, 12, NULL, NULL, '0', '2019-02-12 14:36:52', '2098-12-31 16:00:00', '2019-02-12 14:36:52', '2019-02-12 14:36:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (14, 'MENU_CODE_1549982255086pC4xY5', '角色管理', '/manager/role/RoleTableList', './manager/role/RoleTableList', NULL, NULL, 2, 12, NULL, NULL, '0', '2019-02-12 14:37:35', '2098-12-31 16:00:00', '2019-02-12 14:37:35', '2019-02-12 14:37:35', NULL, NULL);
INSERT INTO `sys_menu` VALUES (15, 'MENU_CODE_1549982296506AIDmR6', '员工管理', '/manager/staff/StaffTableList', './manager/staff/StaffTableList', NULL, NULL, 2, 12, NULL, NULL, '0', '2019-02-12 14:38:16', '2098-12-31 16:00:00', '2019-02-12 14:38:16', '2019-02-12 14:38:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES (16, 'MENU_CODE_15499823270858ZpRm7', '菜单管理', '/manager/setting/MenuTableList', './manager/SystemConfiguration/menu/MenuTableList', NULL, NULL, 3, 31, NULL, NULL, '0', '2019-02-12 14:38:47', '2098-12-31 16:00:00', '2019-02-12 14:38:47', '2019-03-11 02:17:06', NULL, NULL);
INSERT INTO `sys_menu` VALUES (17, 'MENU_CODE_1549982367033eyW9r8', '系统管理', '/enterprise', NULL, NULL, 'profile', 1, 1, NULL, NULL, '0', '2019-02-12 14:39:27', '2098-12-31 16:00:00', '2019-02-12 14:39:27', '2019-02-12 14:39:27', NULL, NULL);
INSERT INTO `sys_menu` VALUES (18, 'MENU_CODE_1549982400028dpo8R9', '基本信息管理', '/Enterprise/baseinfo', './Enterprise/baseinfo/EnterpriseBasicInfo', NULL, NULL, 2, 17, NULL, NULL, '0', '2019-02-12 14:40:00', '2098-12-31 16:00:00', '2019-02-12 14:40:00', '2019-02-12 14:40:00', NULL, NULL);
INSERT INTO `sys_menu` VALUES (21, 'MENU_CODE_1550473105411qHDZY7', '日志查询 ', '/manager/log/LogTableList', './manager/log/LogTableList', NULL, NULL, 2, 12, NULL, NULL, '0', '2019-02-18 06:58:25', '2098-12-31 16:00:00', '2019-02-18 06:58:25', '2019-02-18 06:58:25', NULL, NULL);
INSERT INTO `sys_menu` VALUES (28, 'MENU_CODE_1551342774158QtJC64', '系统参数', '/manager/setting/SettingTableList', './manager/SystemConfiguration/setting/SettingTableList', NULL, NULL, 3, 31, NULL, NULL, '0', '2019-02-28 08:32:54', '2098-12-31 16:00:00', '2019-02-28 08:32:54', '2019-03-10 09:20:39', NULL, NULL);
INSERT INTO `sys_menu` VALUES (31, 'MENU_CODE_155220959704696gAz1', '系统设置', '/manager/setting', NULL, '/manager/setting/SettingTableList', NULL, 2, 12, NULL, NULL, '0', '2019-03-10 09:19:56', '2098-12-31 16:00:00', '2019-03-10 09:19:56', '2019-03-10 09:20:45', NULL, NULL);
INSERT INTO `sys_menu` VALUES (32, 'MENU_CODE_1552209812142tV6oG5', '邮件管理', '/manager/setting/EmailSetting', './manager/SystemConfiguration/Email/EmailTableList', NULL, NULL, 3, 31, NULL, NULL, '0', '2019-03-10 09:23:32', '2098-12-31 16:00:00', '2019-03-10 09:23:32', '2019-03-10 09:23:32', NULL, NULL);
INSERT INTO `sys_menu` VALUES (44, 'MENU_CODE_15552973369890FQJ41', '定时任务', '/manager/setting/TimeTaskManager', './manager/SystemConfiguration/TimeTask/SystemTimeTask', NULL, NULL, 3, 31, NULL, NULL, '0', '2019-04-15 03:02:16', '2098-12-31 16:00:00', '2019-04-15 03:02:16', '2019-04-15 03:05:20', NULL, NULL);

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAMETER_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARAMETER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CATEGORY_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `LAST_UPDATE_OPERATOR` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_parameter_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter_category`;
CREATE TABLE `sys_parameter_category`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LEVEL` int(10) NULL DEFAULT NULL,
  `PARENT_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TOP_PARENT_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CATEGORY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CATEGORY_NAME_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_parameter_desc
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter_desc`;
CREATE TABLE `sys_parameter_desc`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARAMETER_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VALUE` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NAME_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `PERMISSION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERMISSION_URL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PERMISSION_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PERMISSION_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PERMISSION_TYPE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PERMISSION_METHOD` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `WHITE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `EFFECTIVE_TIME` datetime(0) NULL DEFAULT NULL,
  `EXPIRE_TIME` datetime(0) NULL DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`PERMISSION_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '/**', '所有权限', 'ALL', '1', 'ALL', 'N', '2018-08-04 07:54:14', '2018-08-04 07:54:14', '2098-12-31 16:00:00', '0', '2019-02-22 05:49:14');
INSERT INTO `sys_permission` VALUES (2, '/eframe/manage/login', '登陆', 'LOGIN', '1', 'POST', 'Y', '2018-08-04 07:54:14', '2018-08-04 07:54:14', '2098-12-31 16:00:00', '0', '2018-11-14 13:59:38');
INSERT INTO `sys_permission` VALUES (4, '/eframe/multipart-manager/**', '下载图片', 'PERCODE_1551085964590K2XYw1', '1', 'GET', 'Y', '2019-02-25 09:12:44', '2019-02-25 09:12:44', '2098-12-31 16:00:00', '0', '2019-03-06 01:38:43');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REMARK` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `TOP_ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `EFFECTIVE_TIME` datetime(0) NULL DEFAULT NULL,
  `EXPIRE_TIME` datetime(0) NULL DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'root', '超级管理员', 4, 4, '2018-08-04 07:52:06', '2018-08-04 07:52:06', '2098-12-31 16:00:00', '0', '2019-04-08 05:48:27');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `ROLE_PERMISSION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `PERMISSION_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_PERMISSION_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for sys_role_staff
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_staff`;
CREATE TABLE `sys_role_staff`  (
  `ROLE_STAFF_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `STAFF_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_STAFF_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_staff
-- ----------------------------
INSERT INTO `sys_role_staff` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for sys_staff
-- ----------------------------
DROP TABLE IF EXISTS `sys_staff`;
CREATE TABLE `sys_staff`  (
  `STAFF_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STAFF_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TEL_NUM` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LANG` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STAFF_PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SALT` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `STATUS` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_LOGIN_TIME` datetime(0) NULL DEFAULT NULL,
  `EFFECTIVE_TIME` datetime(0) NULL DEFAULT NULL,
  `EXPIRE_TIME` datetime(0) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  `REMARK` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`STAFF_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_staff
-- ----------------------------
INSERT INTO `sys_staff` VALUES (1, 'admin', '超级管理员', 'huayu611@sina.com', '18652064082', 'zh', '1e2d332704495b11cc8fb4f342ad8f25', 'ugmtnZt1JXLVs1Kt', '2018-12-18 21:18:37', '0', '2019-04-09 01:55:48', '2018-06-27 22:57:29', '2020-12-31 22:57:53', '2019-04-08 05:48:35', '我是超级管理员。.');




-- ----------------------------
-- Table structure for sys_timetask
-- ----------------------------
DROP TABLE IF EXISTS `sys_timetask`;
CREATE TABLE `sys_timetask`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `service_bean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `effective_time` datetime(0) NULL DEFAULT NULL,
  `expire_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cycle` int(255) NULL DEFAULT NULL,
  `unit` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timetask_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_obj_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_obj_id` bigint(20) NULL DEFAULT NULL,
  `last_update_time` datetime(0) NULL DEFAULT NULL,
  `last_update_obj_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_update_obj_id` bigint(20) NULL DEFAULT NULL,
  `execute_count` int(10) NULL DEFAULT NULL,
  `next_execute_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_timetask_instance
-- ----------------------------
DROP TABLE IF EXISTS `sys_timetask_instance`;
CREATE TABLE `sys_timetask_instance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time_task_id` bigint(20) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `error_stack` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `retry_time` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
SET FOREIGN_KEY_CHECKS = 1;
