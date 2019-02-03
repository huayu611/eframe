package com.huayu.eframe.server.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.sun.org.apache.xml.internal.serialize.OutputFormat.Defaults.Encoding;

/**
 * Created by Leo on 2019/2/3.
 */
public class HttpUtils
{
    public static String enccodeURL(String msg)
    {
        String result;
        try
        {
            result = URLEncoder.encode(msg,Encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            return msg;
        }
        //+号替换成空格
        result = result.replace("+", "%20");
        //原来是+好的还原
        result = result.replace("%2b","+");
        return result;
    }
}
