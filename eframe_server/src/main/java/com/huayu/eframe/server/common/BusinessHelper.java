package com.huayu.eframe.server.common;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.FlowConstant;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Leo on 2018/11/24.
 */
public class BusinessHelper
{

    private static final LogDebug debug = new LogDebug(BusinessHelper.class);
    // 返回用IP地址
    public  static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        debug.log(ip);
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            debug.log(ip);
        }
        debug.log(ip);
        return ip;
    }

    public  static String getIpFromEasyParam(HttpServletRequest request) {

        EasyParam easyParam = LocalAttribute.getValue(FlowConstant.EASY_SERVLET);
        if(null == easyParam)
        {
            return getIpAddr(request);
        }
        Map<String, String> header = easyParam.getRequestHeader();
        String ip = header.get("x-forwarded-for");
        debug.log(ip);
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = header.get("Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = header.get("WL-Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            debug.log(ip);
        }
        debug.log(ip);
        return ip;
    }
}
