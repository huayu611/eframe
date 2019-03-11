package com.huayu.eframe.global.email.presist.common;

import com.huayu.eframe.server.tool.encrypt.AESEncrypt;

/**
 * Created by Leo on 2019/3/10.
 */
public class EmailUtils
{
    private final static String KEY = "Z234567890ZYXWVU";

    public static String encryptPassword(String password)
    {
       return AESEncrypt.aesEcbEncode(password, KEY);
    }

    public static String decodePassword(String password)
    {
        return AESEncrypt.aesEcbDecode(password, KEY);
    }
}
