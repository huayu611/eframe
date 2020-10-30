package com.huayu.eframe.timetask.async;

import com.huayu.eframe.flow.after.AfterSuccessProcessFlow;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2020/10/30.
 */
public class AsynchronousAfterSuccess implements AfterSuccessProcessFlow
{

    private String bean;

    private Object param;

    public AsynchronousAfterSuccess()
    {

    }

    public AsynchronousAfterSuccess(String bean,Object param)
    {
        this.bean = bean;
        this.param = param;
    }

    @Override
    public void execute()
    {
        if(StringUtils.isNullOrEmpty(bean))
        {
            return;
        }
        AsynchronousInnerJob asynchronousJob = BeanPool.getService(AsynchronousInnerJob.class);
        asynchronousJob.putJob(bean,param);
    }

    public String getBean()
    {
        return bean;
    }

    public void setBean(String bean)
    {
        this.bean = bean;
    }

    public Object getParam()
    {
        return param;
    }

    public void setParam(Object param)
    {
        this.param = param;
    }
}
