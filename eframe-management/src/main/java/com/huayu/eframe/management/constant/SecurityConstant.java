package com.huayu.eframe.management.constant;

/**
 * Created by Administrator on 2018/7/22.
 */
public interface SecurityConstant
{
    public interface STATUS
    {
        String NORMAL = "0";

        String LOCKED = "1";

        String DELETE = "D";
    }

    public interface PERMISSION_WHITE
    {
        String YES = "Y";

        String NO = "N";
    }

    public interface LOGIN_TYPE
    {
        String LOGIN_REFRESH_EXPIRE_TIME = "0";

        String LOGIN_ONCE = "1";

        String LOGIN_FIX_EXPIRE_TIME = "2";
    }
}
