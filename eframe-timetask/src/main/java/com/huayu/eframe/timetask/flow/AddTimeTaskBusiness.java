package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.timetask.entity.service.TimeTaskDetail;
import com.huayu.eframe.timetask.entity.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 */
@Service("com.huayu.eframe.timetask.flow.AddTimeTaskBusiness")
public class AddTimeTaskBusiness extends AbstractExecuteBusiness
{

    private final static String RESULT = "AddTimeTaskBusiness_RESULT";

    private final static String DETAIL = "AddTimeTaskBusiness_RESULT";


    @Autowired
    private TimeTaskService timeTaskService;


    @Override
    public void before(BusinessParameter param)
    {
        AddTimeTaskRequest addTimeTaskRequest = param.getRequest();
        if (null == addTimeTaskRequest)
        {
            throw new IFPException(FlowErrorCode.REQUEST_IS_NULL, "Request is null");
        }
        TimeTaskDetail detail = buildTimeTaskDetail(addTimeTaskRequest);
        param.addParameter(DETAIL, detail);
        TimeTaskRequestValid.checkTimeTaskCycleData(detail);
    }

    @Override
    public void execute(BusinessParameter param)
    {

        TimeTaskDetail detail = param.getParameter(DETAIL);
        TimeTaskDetail result = timeTaskService.addTimeTask(detail);
        param.addParameter(RESULT, result);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private TimeTaskDetail buildTimeTaskDetail(AddTimeTaskRequest addTimeTaskRequest)
    {
        TimeTaskDetail timeTaskDetail = new TimeTaskDetail();
        timeTaskDetail.setCycle(addTimeTaskRequest.getCycle());
        timeTaskDetail.setUnit(addTimeTaskRequest.getUnit());
        timeTaskDetail.setTimeTaskType(addTimeTaskRequest.getTimeTaskType());
        timeTaskDetail.setTimeTaskName(addTimeTaskRequest.getTimeTaskName());
        timeTaskDetail.setExpireTime(addTimeTaskRequest.getExpireTime());
        timeTaskDetail.setEffectiveTime(addTimeTaskRequest.getEffectiveTime());
        timeTaskDetail.setStatus(addTimeTaskRequest.getStatus());
        timeTaskDetail.setTimeTaskCode(addTimeTaskRequest.getTimeTaskCode());
        timeTaskDetail.setService(addTimeTaskRequest.getService());
        return timeTaskDetail;

    }
}
