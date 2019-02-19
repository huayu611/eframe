package com.huayu.eframe.copinfo.server;

import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;

/**
 * Created by Leo on 2019/2/19.
 */
public class TRe extends AbstractExecuteBusiness
{
    @Override
    public void execute(BusinessParameter param)
    {
        param.addParameter("parameter_test",new Object());
    }

    @Override
    public void after(BusinessParameter param)
    {
       Object obj = param.getParameter("parameter_test");
    }
}
