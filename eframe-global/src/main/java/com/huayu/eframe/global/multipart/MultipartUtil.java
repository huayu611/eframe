package com.huayu.eframe.global.multipart;

import com.huayu.eframe.config.parameter.util.ParameterUtil;

/**
 * Created by Leo on 2019/3/5.
 */

public class MultipartUtil
{
    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE = "FileSystemPath";
    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT = "/filesystem";

    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE_TEMP = "FileSystemPathTemp";
    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT_TEMP = "/filesystem_temp";

    public static String getSystemPath()
    {
        return ParameterUtil.getParameterValueByCode(SYSTEM_FILE_UPLOAD_PATH_CODE, SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT);
    }

    public static String getSystemTempPath()
    {
        return ParameterUtil.getParameterValueByCode(SYSTEM_FILE_UPLOAD_PATH_CODE_TEMP, SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT_TEMP);
    }
}
