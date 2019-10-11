package com.huayu.eframe.flow.intercept;

import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/8/14.
 */
@Service
public class ProcessStep
{
    private List<ProcessIntercept> processAll;

    public ProcessStep(List<ProcessIntercept> processIntercepts)
    {
        processAll = processIntercepts;
    }

    public void execute( BusinessParameter param)
    {
        CollectionUtils.iterator(processAll,(c, v, i)->{
            if(null != v)
            {
                v.execute(param);
            }
        });
    }
}
