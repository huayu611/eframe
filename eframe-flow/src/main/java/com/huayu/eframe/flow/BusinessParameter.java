package com.huayu.eframe.flow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/7.
 */
public class BusinessParameter
{
    private Object request;

    private Map<String, Object> param;

    public Map<String, Object> getParam()
    {
        return param;
    }

    public void setParam(Map<String, Object> param)
    {
        this.param = param;
    }

    public <T> T getRequest()
    {
        return (T) request;
    }

    public void setRequest(Object request)
    {
        this.request = request;
    }

    public void addParameter(String key, Object value)
    {
        createMap();
        param.put(key, value);
    }

    public <T> T getParameter(String key)
    {
        if (null != param)
        {
            return (T) param.get(key);
        }
        return null;
    }


    private void createMap()
    {
        if (null == param)
        {
            param = new HashMap<String, Object>();
        }
    }
}
