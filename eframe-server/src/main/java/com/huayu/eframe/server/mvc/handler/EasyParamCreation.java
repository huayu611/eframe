package com.huayu.eframe.server.mvc.handler;

import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Leo on 2018/9/25.
 */
public class EasyParamCreation
{
    /**
     *
     * @param request
     * @param response
     * @return
     */
    public static EasyParam createEasyParam(HttpServletRequest request,HttpServletResponse response)
    {
        EasyParam easyParam = new EasyParam();
        easyParam.setRequest(request);
        easyParam.setResponse(response);

        Map<String, String> map = new HashMap<>();
        Map<String, String[]> mapValues = new HashMap<>();


        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements())
        {
            String headerName = headers.nextElement();
            String value = request.getHeader(headerName);
            if (StringUtils.isNotNullAndEmpty(value))
            {
                map.put(headerName, value);
            }

            Enumeration<String> valuesEnum = request.getHeaders(headerName);
            List<String> valueList = new ArrayList<>();
            while(valuesEnum.hasMoreElements())
            {
                valueList.add(valuesEnum.nextElement());
            }
            if(CollectionUtils.isNotEmpty(valueList))
            {
                String[] strings = new String[valueList.size()];
                valueList.toArray(strings);
            }

        }
        easyParam.setRequestHeader(map);
        easyParam.setRequestHeaders(mapValues);
        return easyParam;

    }
}
