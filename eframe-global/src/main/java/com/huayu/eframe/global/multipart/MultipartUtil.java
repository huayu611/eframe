package com.huayu.eframe.global.multipart;

import com.huayu.eframe.config.parameter.util.ParameterUtil;
import com.huayu.eframe.flow.common.HttpUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2019/3/5.
 */

public class MultipartUtil
{
    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE = "FileSystemPath";
    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT = "/filesystem";

    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE_TEMP = "FileSystemPathTemp";
    private final static String SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT_TEMP = "/filesystem_temp";

    private final static String FILE_SERVER_URL = "FileServerPath";
    private final static String FILE_SERVER_URL_TEMP = "";

    public static String getSystemPath()
    {
        return ParameterUtil.getParameterValueByCode(SYSTEM_FILE_UPLOAD_PATH_CODE, SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT);
    }

    public static String getSystemTempPath()
    {
        return ParameterUtil.getParameterValueByCode(SYSTEM_FILE_UPLOAD_PATH_CODE_TEMP, SYSTEM_FILE_UPLOAD_PATH_CODE_DEFAULT_TEMP);
    }

    public static String getFileSystemUrlPath()
    {
        String value = ParameterUtil.getParameterValueByCode(FILE_SERVER_URL, FILE_SERVER_URL_TEMP);
        if(StringUtils.isNullOrEmpty(value))
        {
            String port = System.getProperty("web.port");
            String ip = HttpUtils.getInetIp();
            StringBuilder httpIp = new StringBuilder();
            httpIp.append("http://").append(ip).append(":").append(port).append("/");
            value = httpIp.toString();
        }
        return value;
    }
}
