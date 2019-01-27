package com.huayu.eframe.server.context;

import com.huayu.eframe.server.tool.basic.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/18.
 */
public class LocalContext
{
    private Map<String,Object> map;

    private Date nowDate;

    public Map<String, Object> getMap()
    {
        return map;
    }

    public void setMap(Map<String, Object> map)
    {
        this.map = map;
    }

    public Date getNowDate()
    {
        return nowDate;
    }

    public void setNowDate(Date nowDate)
    {
        this.nowDate = nowDate;
    }

    public void addValue(String key, Object obj)
    {
        if(null == map)
        {
            map = new HashMap<String ,Object>();
        }
        map.put(key,obj);
    }

    public <T> T getValue(String key)
    {
        if(null != map && StringUtils.isNotNullAndEmpty(key))
        {
            return (T)map.get(key);
        }
        return null;
    }

}
