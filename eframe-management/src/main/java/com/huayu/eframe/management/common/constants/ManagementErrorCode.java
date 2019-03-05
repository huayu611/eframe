package com.huayu.eframe.management.common.constants;

/**
 * Created by Leo on 2019/3/5.
 */
public interface ManagementErrorCode
{
    //登陆错误码
    String STAFFNAME_OR_PASSWORD_WRONG = String.valueOf("13001");


    String LOGINNAME_CANNOT_NULL = String.valueOf("13002");

    String ROLE_CODE_CANNOT_NULL = String.valueOf("13003");

    String LOGIN_NAME_STATUS_ERROR = String.valueOf("13004");

    String PERMISSION_CODE_CANNOT_NULL = String.valueOf("13005");
    //添加USER

    String ADD_STAFF_LOGIN_NAME_EXIST = String.valueOf("13006");

    //删除 Staff
    String STAFF_LOGIN_NAME_NOT_EXIST = String.valueOf("13007");

    //RoleCode 已经存在
    String ROLE_ROLE_CODE_NOT_EXIST = String.valueOf("13008");

    //PermissionCode 已经存在
    String P0ERMISSION_CODE_EXIST = String.valueOf("13009");


    String ADD_PERMISSION_CODE_EXIST_ALREADY = String.valueOf("13010");


    String ADD_ROLE_PARENT_ROLE_NOT_EXIST = String.valueOf("13011");

    String ADD_ROLE_ROLE_CODE_EXIST = String.valueOf("13012");
}

