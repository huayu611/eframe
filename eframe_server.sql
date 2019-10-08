SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_lang_define
-- ----------------------------
DROP TABLE IF EXISTS `sys_lang_define`;
CREATE TABLE `sys_lang_define`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_lang_define
-- ----------------------------
INSERT INTO `sys_lang_define` VALUES (1, 'en', 'English');
INSERT INTO `sys_lang_define` VALUES (2, 'zh', '中文');

-- ----------------------------
-- Table structure for sys_lang_text
-- ----------------------------
DROP TABLE IF EXISTS `sys_lang_text`;
CREATE TABLE `sys_lang_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lang_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `primary_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `LANG_LANGUAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `VALUE` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_lang_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_lang_value`;
CREATE TABLE `sys_lang_value`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `primary_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LANG_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LANG_LANGUAGE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VALUE` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `request` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `request_parameter` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `request_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `response` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `in_millionsecond` bigint(255) NULL DEFAULT NULL,
  `in_time` datetime(0) NULL DEFAULT NULL,
  `out_millionsecond` bigint(255) NULL DEFAULT NULL,
  `out_time` datetime(0) NULL DEFAULT NULL,
  `oper_obj_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `oper_obj_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `errorstack` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5718 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;


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
  `MENU_RANGE` int(11) NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'MANAGER', '管理系统', '/web', '../layouts/BasicLayout', NULL, NULL, 5, 0, 0, 0, NULL, '0', '2018-08-19 13:58:50', '2098-12-31 16:00:00', '2018-08-19 13:58:50', '2019-03-05 08:42:58', NULL, NULL);
INSERT INTO `sys_menu` VALUES (12, 'MENU_CODE_1549982179270czkuM3', '管理员管理', '/web/manager', NULL, NULL, 'tool', 5, 1, 1, NULL, NULL, '0', '2019-02-12 14:36:19', '2098-12-31 16:00:00', '2019-02-12 14:36:19', '2019-06-26 03:27:20', NULL, NULL);
INSERT INTO `sys_menu` VALUES (13, 'MENU_CODE_1549982212322QMjCV4', '权限管理', '/web/manager/main/permission/view', './manager/SystemManager/permission/PermissionTableList', NULL, NULL, 5, 3, 31, NULL, NULL, '0', '2019-02-12 14:36:52', '2098-12-31 16:00:00', '2019-02-12 14:36:52', '2019-07-20 06:12:33', NULL, NULL);
INSERT INTO `sys_menu` VALUES (14, 'MENU_CODE_1549982255086pC4xY5', '角色管理', '/web/manager/main/role/view', './manager/SystemManager/role/RoleTableList', NULL, NULL, 5, 3, 31, NULL, NULL, '0', '2019-02-12 14:37:35', '2098-12-31 16:00:00', '2019-02-12 14:37:35', '2019-07-20 06:12:46', NULL, NULL);
INSERT INTO `sys_menu` VALUES (15, 'MENU_CODE_1549982296506AIDmR6', '员工管理', '/web/manager/main/staff/view', './manager/SystemManager/staff/StaffTableList', NULL, NULL, 5, 3, 31, NULL, NULL, '0', '2019-02-12 14:38:16', '2098-12-31 16:00:00', '2019-02-12 14:38:16', '2019-07-20 06:12:52', NULL, NULL);
INSERT INTO `sys_menu` VALUES (16, 'MENU_CODE_15499823270858ZpRm7', '菜单管理', '/web/manager/setting/menu', './manager/SystemConfiguration/menu/MenuTableList', NULL, NULL, 5, 3, 45, NULL, NULL, '0', '2019-02-12 14:38:47', '2098-12-31 16:00:00', '2019-02-12 14:38:47', '2019-07-20 06:13:12', NULL, NULL);
INSERT INTO `sys_menu` VALUES (21, 'MENU_CODE_1550473105411qHDZY7', '日志查询', '/web/manager/log/log-table', './manager/log/LogTableList', NULL, NULL, 5, 2, 12, NULL, NULL, '0', '2019-02-18 06:58:25', '2098-12-31 16:00:00', '2019-02-18 06:58:25', '2019-07-20 06:11:10', NULL, NULL);
INSERT INTO `sys_menu` VALUES (28, 'MENU_CODE_1551342774158QtJC64', '系统参数', '/web/manager/setting/parameter', './manager/SystemConfiguration/setting/SettingTableList', NULL, NULL, 5, 3, 45, NULL, NULL, '0', '2019-02-28 08:32:54', '2098-12-31 16:00:00', '2019-02-28 08:32:54', '2019-07-20 06:13:30', NULL, NULL);
INSERT INTO `sys_menu` VALUES (31, 'MENU_CODE_1555898582196reR2W1', '系统管理', '/web/manager/main', NULL, NULL, NULL, 1, 2, 12, NULL, NULL, '0', '2019-04-22 02:03:02', '2098-12-31 16:00:00', '2019-04-22 02:03:02', '2019-04-22 09:11:17', NULL, NULL);
INSERT INTO `sys_menu` VALUES (44, 'MENU_CODE_15552973369890FQJ41', '定时任务', '/web/manager/setting/time-task', './manager/SystemConfiguration/TimeTask/SystemTimeTask', NULL, NULL, 5, 3, 45, NULL, NULL, '0', '2019-04-15 03:02:16', '2098-12-31 16:00:00', '2019-04-15 03:02:16', '2019-07-20 06:13:53', NULL, NULL);
INSERT INTO `sys_menu` VALUES (45, 'MENU_CODE_155220959704696gAz1', '系统设置', '/web/manager/setting', NULL, '/manager/setting/SettingTableList', NULL, 2, 2, 12, NULL, NULL, '0', '2019-03-10 09:19:56', '2098-12-31 16:00:00', '2019-03-10 09:19:56', '2019-04-22 09:11:22', NULL, NULL);
INSERT INTO `sys_menu` VALUES (46, 'MENU_CODE_1555899885311VoCaq1', '个人中心', '/web/manager/center', './manager/StaffInformation/StaffInformationMain', NULL, NULL, 3, 2, 12, NULL, NULL, '0', '2019-04-22 02:24:45', '2098-12-31 16:00:00', '2019-04-22 02:24:45', '2019-04-22 09:11:26', NULL, NULL);
INSERT INTO `sys_menu` VALUES (47, 'MENU_CODE_1555899943003Zlz8U3', '个人信息查看', '/web/manager/center/information/view', './manager/StaffInformation/WebAccount/WebAccountManager', NULL, NULL, 5, 3, 46, NULL, NULL, '0', '2019-04-22 02:25:42', '2098-12-31 16:00:00', '2019-04-22 02:25:42', '2019-07-20 06:11:50', NULL, NULL);
INSERT INTO `sys_menu` VALUES (48, 'MENU_CODE_1555913109559MPfPS3', '个人信息修改', '/web/manager/center/information/edit', './manager/StaffInformation/InformationEdit/StaffInformationEdit', NULL, NULL, 5, 3, 46, NULL, NULL, '0', '2019-04-22 06:05:09', '2098-12-31 16:00:00', '2019-04-22 06:05:09', '2019-07-20 06:12:24', NULL, NULL);


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
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES (3, 'FileSystemPath', '上传文件目录', NULL, NULL, 'E:\\filesystem', NULL, NULL);
INSERT INTO `sys_parameter` VALUES (5, 'FileSystemPathTemp', '上传文件临时目录', NULL, NULL, 'E:\\filesystem_temp', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '/**', '所有权限', 'ALL', '1', 'ALL', 'N', '2018-08-04 07:54:14', '2018-08-04 07:54:14', '2098-12-31 16:00:00', '0', '2019-02-22 05:49:14');
INSERT INTO `sys_permission`(`PERMISSION_URL`, `PERMISSION_NAME`, `PERMISSION_CODE`, `PERMISSION_TYPE`, `PERMISSION_METHOD`, `WHITE`, `CREATE_TIME`, `EFFECTIVE_TIME`, `EXPIRE_TIME`, `STATUS`, `LAST_UPDATE_TIME`) VALUES ('/eframe/manage/login', '登陆', 'LOGIN', '1', 'POST', 'Y', '2018-08-04 07:54:14', '2018-08-04 07:54:14', '2098-12-31 16:00:00', '0', '2018-11-14 13:59:38');
INSERT INTO `sys_permission`( `PERMISSION_URL`, `PERMISSION_NAME`, `PERMISSION_CODE`, `PERMISSION_TYPE`, `PERMISSION_METHOD`, `WHITE`, `CREATE_TIME`, `EFFECTIVE_TIME`, `EXPIRE_TIME`, `STATUS`, `LAST_UPDATE_TIME`) VALUES ( '/eframe/multipart-manager/**', '下载图片', 'PERCODE_1551085964590K2XYw1', '1', 'GET', 'Y', '2019-02-25 09:12:44', '2019-02-25 09:12:44', '2098-12-31 16:00:00', '0', '2019-03-06 01:38:43');
INSERT INTO `sys_permission`( `PERMISSION_URL`, `PERMISSION_NAME`, `PERMISSION_CODE`, `PERMISSION_TYPE`, `PERMISSION_METHOD`, `WHITE`, `CREATE_TIME`, `EFFECTIVE_TIME`, `EXPIRE_TIME`, `STATUS`, `LAST_UPDATE_TIME`) VALUES ('/eview/**', 'WEB显示', 'PERCODE_1551945074596Sqkv51', '1', 'ALL', 'Y', '2019-03-07 07:51:14', '2019-03-07 07:51:14', '2098-12-31 16:00:00', '0', '2019-03-07 07:51:14');

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'root', '超级管理员', 4, 4, '2018-08-04 07:52:06', '2018-08-04 07:52:06', '2099-01-01 16:00:00', '0', '2019-06-25 16:21:29');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `ROLE_PERMISSION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `PERMISSION_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_PERMISSION_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_staff
-- ----------------------------
INSERT INTO `sys_staff` VALUES (1, 'admin', '超级管理员', 'huayu611@sina.com', '18652064082', 'zh', '1e2d332704495b11cc8fb4f342ad8f25', 'ugmtnZt1JXLVs1Kt', '2018-12-18 21:18:37', '0', '2019-08-05 06:11:44', '2018-06-27 22:57:29', '2020-12-31 22:57:53', '2019-07-02 12:07:59', '个人开发');
-- ----------------------------
-- Table structure for sys_staff_ext
-- ----------------------------
DROP TABLE IF EXISTS `sys_staff_ext`;
CREATE TABLE `sys_staff_ext`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staff_id` bigint(20) NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity_id` varchar(28) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wechat` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weibo` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signature` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_tel_number` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `alipay` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_staff_ext
-- ----------------------------
INSERT INTO `sys_staff_ext` VALUES (1, 1, '1', '李志华', '', '悔格拉', '', 'huayu611', 'huayu611', '', '', '试着就只是做你自己，并且要优雅地放手所有不属于你的东西。生活不是用来幻想和回忆的，生活是用来思考和追求的！生活如茶不仅要懂得如何泡制，更是要懂得如何去品尝！', '', 'huayu611@sina.com', '2019-04-03');


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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_timetask
-- ----------------------------
INSERT INTO `sys_timetask` VALUES (1, 'timetask_15553166000817R0AC1', '清理数据', 'Clean Up Deletion Data', '2019-04-15 16:00:00', '2098-12-31 16:00:00', '2', 1, 'day', 'cycle', '2019-04-15 08:23:19', 'STAFF', 1, '2019-07-24 06:17:54', 'STAFF', 1, 539, '2019-08-05 16:42:39');
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
) ENGINE = InnoDB AUTO_INCREMENT = 1248 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_param`;
CREATE TABLE `sys_dict_param`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dict_id` bigint(20) NULL DEFAULT NULL,
  `dict_order` int(11) NULL DEFAULT NULL,
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dict_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `menu_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
