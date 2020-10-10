package com.huayu.eframe.flow.after;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2020/8/28.
 */
public class AfterProcessFlowExecute
{

    private final static String LOCAL_KEY = "AfterProcessFlowExecute_after_execute";

    private final static String LOCAL_KEY_EXECUTE_FLAG = "AfterProcessFlowExecute_after_execute_flag";

    public static void addAfterProcess(AfterProcessFlow afterProcessFlow)
    {
        List<AfterProcessFlow> afterProcesses = LocalAttribute.getValue(LOCAL_KEY);
        if(null == afterProcesses)
        {
            afterProcesses = new ArrayList<>();
        }
        afterProcesses.add(afterProcessFlow);
        LocalAttribute.addValue(LOCAL_KEY,afterProcesses);
    }

    public static void process()
    {
        List<AfterProcessFlow> flag = LocalAttribute.getValue(LOCAL_KEY_EXECUTE_FLAG);
        if(null == flag)
        {
            flag = new ArrayList<>();
            LocalAttribute.addValue(LOCAL_KEY_EXECUTE_FLAG,flag);
        }
        List<AfterProcessFlow> copyFlag = LocalAttribute.getValue(LOCAL_KEY_EXECUTE_FLAG);

        List<AfterProcessFlow> afterProcesses = LocalAttribute.getValue(LOCAL_KEY);
        if(CollectionUtils.isEmpty(afterProcesses))
        {
            return ;
        }
        CollectionUtils.iterator(afterProcesses,(c,v,i)->{
            if(null != v)
            {
                if(!copyFlag.contains(v))
                {
                    v.execute();
                    copyFlag.add(v);
                }

            }
        });

    }
}
