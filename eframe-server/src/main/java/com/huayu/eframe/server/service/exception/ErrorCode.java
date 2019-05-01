package com.huayu.eframe.server.service.exception;

public interface ErrorCode
{
    String UNKNOW_ERROR = String.valueOf("10001");

    String AUTHENTICATION_FAILED = String.valueOf("10002");

    String VERSION_WRONG = String.valueOf("10003");

    String TOKEN_COVERT_ERROR = String.valueOf("10004");

    String REST_SESSION_EXPIRE = String.valueOf("10005");

    String REQUEST_EXPIRETIME_BEFORE_CURRET_TIME = String.valueOf("10006");

    String REQUEST_EXPIRETIME_INVALID = String.valueOf("10007");

    String REQUEST_EFFECITVE_INVALID = String.valueOf("10008");

    String T0KEN_SESSION_INVALID = String.valueOf("10008");

//
//
//
//
//	String REQUEST_VALID_FIELD_REQUIRED_FIELD_WITHOUT_VALUE = String.valueOf("10011");
//
//	String REQUEST_VALID_FIELD_VALUE_NOT_INCORRECT = String.valueOf("10012");
//
//	String TOKEN_ERROR = String.valueOf("10013");
//
//	String SERVICE_NOT_EXIST = String.valueOf("10014");
//
//	String LANG_CODE_INCORRECT = String.valueOf("10015");
//
//	String EMAIL_FORMATTING_INCORRECT = String.valueOf("10016");
//
//	String REQUEST_FIELD_LENGTH_REACH_MAX_LENGTH = String.valueOf("10017");


}
