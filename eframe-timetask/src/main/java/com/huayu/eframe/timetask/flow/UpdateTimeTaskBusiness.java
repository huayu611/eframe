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
@Service("com.huayu.eframe.timetask.flow.UpdateTimeTaskBusiness")
public class UpdateTimeTaskBusiness extends AbstractExecuteBusiness
{
    private final static String RESULT = "UpdateTimeTaskBusiness_RESULT";

    private final static String DETAIL = "UpdateTimeTaskBusiness_DETAIL";

    @Autowired
    private TimeTaskService timeTaskService;


    @Override
    public void before(BusinessParameter param)
    {
        UpdateTimeTaskRequest updateTimeTaskRequest = param.getRequest();
        if (null == updateTimeTaskRequest)
        {
            throw new IFPException(FlowErrorCode.REQUEST_IS_NULL, "Request is null");
        }
        TimeTaskDetail detail = buildTimeTaskDetail(updateTimeTaskRequest);
        param.addParameter(DETAIL, detail);
        TimeTaskRequestValid.checkTimeTaskCycleData(detail);
    }

    @Override
    public void execute(BusinessParameter param)
    {
        UpdateTimeTaskRequest updateTimeTaskRequest = param.getRequest();
        TimeTaskDetail detail = buildTimeTaskDetail(updateTimeTaskRequest);
        TimeTaskDetail result = timeTaskService.updateTimeTask(detail);
        param.addParameter(RESULT, result);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

    private TimeTaskDetail buildTimeTaskDetail(UpdateTimeTaskRequest updateTimeTaskRequest)
    {
        TimeTaskDetail timeTaskDetail = new TimeTaskDetail();
        timeTaskDetail.setCycle(updateTimeTaskRequest.getCycle());
        timeTaskDetail.setUnit(updateTimeTaskRequest.getUnit());
        timeTaskDetail.setService(updateTimeTaskRequest.getService());
        timeTaskDetail.setTimeTaskType(updateTimeTaskRequest.getTimeTaskType());
        timeTaskDetail.setTimeTaskCode(updateTimeTaskRequest.getTimeTaskCode());
        timeTaskDetail.setTimeTaskName(updateTimeTaskRequest.getTimeTaskName());
        timeTaskDetail.setExpireTime(updateTimeTaskRequest.getExpireTime());
        timeTaskDetail.setEffectiveTime(updateTimeTaskRequest.getEffectiveTime());
        timeTaskDetail.setStatus(updateTimeTaskRequest.getStatus());
        return timeTaskDetail;

    }
}
