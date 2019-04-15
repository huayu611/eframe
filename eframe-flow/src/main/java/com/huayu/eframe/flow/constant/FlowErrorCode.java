package com.huayu.eframe.flow.constant;

/**
 * Created by Leo on 2019/3/5.
 */
public interface FlowErrorCode
{
    String REQUEST_VALID_FIELD_REQUIRED_FIELD_WITHOUT_VALUE = String.valueOf("11001");

    String REQUEST_VALID_FIELD_VALUE_NOT_INCORRECT = String.valueOf("11002");


    String LANG_CODE_INCORRECT = String.valueOf("11003");

    String EMAIL_FORMATTING_INCORRECT = String.valueOf("11004");

    String REQUEST_FIELD_LENGTH_REACH_MAX_LENGTH = String.valueOf("11005");

    String SERVICE_NOT_EXIST = String.valueOf("11006");

    String MOBILE_NUMBER_FORMATTING_INCORRECT = String.valueOf("11007");

    String REQUEST_VALUE_NOT_SUIT_REQUIRE = String.valueOf("11008");

    String REQUEST_IS_NULL = String.valueOf("11009");
}
