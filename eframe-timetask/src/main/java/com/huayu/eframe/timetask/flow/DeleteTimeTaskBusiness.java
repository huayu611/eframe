package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.timetask.entity.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 */
@Service("com.huayu.eframe.timetask.flow.DeleteTimeTaskBusiness")
public class DeleteTimeTaskBusiness  extends AbstractExecuteBusiness
{
    private final static String RESULT = "DeleteTimeTaskBusiness_RESULT";

    @Autowired
    private TimeTaskService timeTaskService;

    @Override
    public void execute(BusinessParameter param)
    {
        DeleteTimeTaskRequest deleteTimeTaskRequest = param.getRequest();
        String result = deleteInBatch(code -> timeTaskService.deleteTimeTask(code), deleteTimeTaskRequest.getTimeTaskCode());
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }


}
