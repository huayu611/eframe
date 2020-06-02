package com.huayu.eframe.flow.common;

import com.huayu.eframe.server.common.Constant;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.MapUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Leo on 2019/3/6.
 */
public class HttpUtils
{
    private static final LogDebug debug = new LogDebug(HttpUtils.class);

    public static String getBrowserLang()
    {
        EasyParam easyParam = LocalAttribute.getValue(FlowConstant.EASY_SERVLET);
        if (null == easyParam)
        {
            return "";
        }

        Map<String, String> header = easyParam.getRequestHeader();
        if (MapUtils.isEmpty(header))
        {
            return "";
        }
        String acceptLanguage = header.get("accept-language");
        if (StringUtils.isNullOrEmpty(acceptLanguage))
        {
            return "";
        }
        String[] langs = org.springframework.util.StringUtils.tokenizeToStringArray(acceptLanguage, Constant.MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        if (langs.length > 0)
        {
            return langs[0];
        }
        return "";
    }


    // 返回用IP地址
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        debug.log(ip);
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            debug.log(ip);
        }
        debug.log(ip);
        return ip;
    }

    public static String getRequestURL()
    {
        EasyParam easyParam = LocalAttribute.getValue(FlowConstant.EASY_SERVLET);
        if (null == easyParam)
        {
            return "";
        }
        HttpServletRequest request = easyParam.getRequest();
        if (null == request)
        {
            return "";
        }
        StringBuffer url = request.getRequestURL();
        if(null == url)
        {
            return "";
        }
        return url.toString();
    }

    public static String getIpFromEasyParam(HttpServletRequest request)
    {

        EasyParam easyParam = LocalAttribute.getValue(FlowConstant.EASY_SERVLET);
        if (null == easyParam)
        {
            return getIpAddr(request);
        }
        Map<String, String> header = easyParam.getRequestHeader();
        String ip = header.get("x-forwarded-for");
        debug.log(ip);
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = header.get("Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))
        {
            ip = header.get("WL-Proxy-Client-IP");
            debug.log(ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            debug.log(ip);
        }
        debug.log(ip);
        return ip;
    }

    public static  String getInetIp(){
        InetAddress inetAddress = null;
        try
        {
            inetAddress = getLocalHostLANAddress();
        }
        catch(Exception e)
        {
            return "localhost";
        }
        if(null == inetAddress)
        {
            return "localhost";
        }

        return inetAddress.getHostAddress();
    }

    public static InetAddress getLocalHostLANAddress() throws Exception {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
