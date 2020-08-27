package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.timetask.entity.service.TimeTaskDetail;
import com.huayu.eframe.timetask.entity.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/15.
 */
@Service("com.huayu.eframe.timetask.flow.ModifyTimeTaskExecuteCommandBusiness")
public class ModifyTimeTaskExecuteCommandBusiness extends AbstractExecuteBusiness
{
    @Autowired
    private TimeTaskService timeTaskService;

    @Override
    public void execute(BusinessParameter param)
    {
        ModifyTimeTaskExecuteCommandRequest modifyTimeTaskExecuteCommandRequest = param.getRequest();
        TimeTaskDetail timeTaskDetail = new TimeTaskDetail();
        timeTaskDetail.setTimeTaskCode(modifyTimeTaskExecuteCommandRequest.getTimeTaskCode());
//        timeTaskDetail.setNextTime(modifyTimeTaskExecuteCommandRequest.getNextTime());
        timeTaskService.updateTimeTaskExecute(timeTaskDetail);
    }

    protected boolean getLogFlag()
    {
        return false;
    }

    @Override
    protected boolean isNeedTransaction()
    {
        //没有get。所有要单独加一个事务处理
        return true;
    }
}
