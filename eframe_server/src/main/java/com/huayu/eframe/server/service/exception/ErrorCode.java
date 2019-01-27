package com.huayu.eframe.server.service.exception;

public interface ErrorCode
{
	String UNKNOW_ERROR = String.valueOf("101");

	String AUTHENTICATION_FAILED = String.valueOf("102");

	String VERSION_WRONG = String.valueOf("103");

	String TOKEN_COVERT_ERROR = String.valueOf("104");

	String REST_SESSION_EXPIRE = String.valueOf("105");

	String REQUEST_CAN_NOT_NULL = String.valueOf("106");

	String REQUEST_EXPIRETIME_BEFORE_EFFECITVE = String.valueOf("107");

	String REQUEST_EXPIRETIME_BEFORE_CURRET_TIME = String.valueOf("108");

	String REQUEST_EXPIRETIME_INVALID = String.valueOf("109");

	String REQUEST_EFFECITVE_INVALID = String.valueOf("110");

	String REQUEST_VALID_FIELD_REQUIRED_FIELD_WITHOUT_VALUE = String.valueOf("111");

	String REQUEST_VALID_FIELD_VALUE_NOT_INCORRECT = String.valueOf("112");

	String TOKEN_ERROR = String.valueOf("113");

	String SERVICE_NOT_EXIST = String.valueOf("114");

	String LANG_CODE_INCORRECT = String.valueOf("115");

	String EMAIL_FORMATTING_INCORRECT = String.valueOf("116");

	String REQUEST_FIELD_LENGTH_REACH_MAX_LENGTH = String.valueOf("117");

	//登陆错误码
	String STAFFNAME_OR_PASSWORD_WRONG = String.valueOf("1001");


	String LOGINNAME_CANNOT_NULL = String.valueOf("1002");

	String ROLE_CODE_CANNOT_NULL = String.valueOf("1003");

	String LOGIN_NAME_STATUS_ERROR =  String.valueOf("1004");

	String PERMISSION_CODE_CANNOT_NULL = String.valueOf("1005");
	//添加USER

	String ADD_STAFF_LOGIN_NAME_EXIST = String.valueOf("1007");

	//删除 Staff
	String STAFF_LOGIN_NAME_NOT_EXIST = String.valueOf("1010");

	//RoleCode 已经存在
	String ROLE_ROLE_CODE_NOT_EXIST = String.valueOf("1011");

	//PermissionCode 已经存在
	String P0ERMISSION_CODE_EXIST = String.valueOf("1012");

	String P0ERMISSION_ROLE_RELATION_NOT_EXIST = String.valueOf("1013");

	String STAFF_ROLE_RELATION_NOT_EXIST = String.valueOf("1014");

	//添加REST接口
	String ADD_PERMISSION_URL_CAN_NOT_NULL = String.valueOf("1050");

	String ADD_PERMISSION_METHOD_INVALID = String.valueOf("1051");

	String ADD_PERMISSION_PERMISSION_TYPE_INVALID = String.valueOf("1052");

	String ADD_PERMISSION_CODE_NOT_NULL = String.valueOf("1053");

	String ADD_PERMISSION_CODE_EXIST_ALREADY = String.valueOf("1054");

	//添加ROLE
	String ADD_ROLE_CODE_CAN_NOT_NULL = String.valueOf("1060");

	String ADD_ROLE_PARENT_ROLE_NOT_EXIST = String.valueOf("1061");

	String ADD_ROLE_ROLE_CODE_EXIST = String.valueOf("1062");

	//添加ROLE_USER
	String ADD_ROLE_STAFF_ROLE_CODE_CAN_NOT_NNULL = String.valueOf("1070");

	String ADD_ROLE_STAFF_LOGIN_NAME_CAN_NOT_NNULL = String.valueOf("1071");

	String ADD_ROLE_STAFF_ROLE_NOT_EXIST = String.valueOf("1072");

	String ADD_ROLE_STAFF_STAFF_NOT_EXIST = String.valueOf("1072");

	String ADD_ROLE_STAFF_ITEM_EXIST = String.valueOf("1073");

	//添加ROLE_PERMISSION
	String ADD_ROLE_PERMISSION_ROLE_CODE_CAN_NOT_NNULL = String.valueOf("1080");

	String ADD_ROLE_PERMISSION_PERMISSION_CODE_CAN_NOT_NNULL = String.valueOf("1081");

	String ADD_ROLE_PERMISSION_ROLE_NOT_EXIST = String.valueOf("1082");

	String ROLE_PERMISSION_PERMISSION_NOT_EXIST = String.valueOf("1082");

	String ADD_ROLE_PERMISSION_ITEM_EXIST = String.valueOf("1083");

	//添加菜单
	String ADD_MENU_PARENT_MENU_NOT_EXIST = String.valueOf("1085");

	String ADD_MENU_MENU_CODE_EXIST = String.valueOf("1085");


	//添加控参
	String ADD_PARAMETER_PARAMETER_EXIST = String.valueOf("1100");

	String ADD_PARAMETER_PARAMETER_PARENT_NOT_EXIST = String.valueOf("1101");

}
