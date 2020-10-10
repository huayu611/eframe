package com.huayu.eframe.flow.after;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2020/8/28.
 */
public class AfterSuccessProcessFlowExecute
{

    private final static String LOCAL_KEY = "AfterSuccessProcessFlowExecute_after_execute";

    public static void addAfterSuccessProcess(AfterSuccessProcessFlow afterSuccessProcessFlow)
    {
        List<AfterSuccessProcessFlow> afterSuccessProcesses = LocalAttribute.getValue(LOCAL_KEY);
        if(null == afterSuccessProcesses)
        {
            afterSuccessProcesses = new ArrayList<>();

        }
        afterSuccessProcesses.add(afterSuccessProcessFlow);
        LocalAttribute.addValue(LOCAL_KEY,afterSuccessProcesses);

    }

    public static void process()
    {

        List<AfterSuccessProcessFlow> afterProcesses = LocalAttribute.getValue(LOCAL_KEY);
        if(CollectionUtils.isEmpty(afterProcesses))
        {
            return ;
        }
        CollectionUtils.iterator(afterProcesses,(c,v,i)->{
            if(null != v)
            {
               v.execute();
            }
        });

    }
}
