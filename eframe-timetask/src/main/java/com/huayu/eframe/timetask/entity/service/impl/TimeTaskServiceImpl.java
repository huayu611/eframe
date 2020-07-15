package com.huayu.eframe.timetask.entity.service.impl;

import com.huayu.eframe.flow.constant.FlowErrorCode;
import com.huayu.eframe.global.dict.common.DictionaryUtils;
import com.huayu.eframe.global.dict.flow.Dict;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.common.found.CurrentEntity;
import com.huayu.eframe.server.common.found.CurrentObject;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.timetask.common.Constants;
import com.huayu.eframe.timetask.common.TimeTaskErrorCode;
import com.huayu.eframe.timetask.entity.atom.TimeTaskAtom;
import com.huayu.eframe.timetask.entity.atom.TimeTaskInstanceAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import com.huayu.eframe.timetask.entity.cache.TimeTaskCache;
import com.huayu.eframe.timetask.entity.service.TimeTaskDetail;
import com.huayu.eframe.timetask.entity.service.TimeTaskInstanceDetail;
import com.huayu.eframe.timetask.entity.service.TimeTaskService;
import com.huayu.eframe.timetask.execute.TimeTaskTimeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskServiceImpl implements TimeTaskService
{
    @Autowired
    private TimeTaskAtom timeTaskAtom;

    @Autowired
    private CurrentObject currentObject;

    @Autowired
    private TimeTaskTimeManager timeTaskTimeManager;

    @Autowired
    private TimeTaskInstanceAtom timeTaskInstanceAtom;

    @Override
    public TimeTaskDetail addTimeTask(TimeTaskDetail timeTaskDetail)
    {
        if (null == timeTaskDetail)
        {
            throw new IFPException(FlowErrorCode.REQUEST_IS_NULL, "Request is null");
        }
        TimeTaskBO timeTaskBO = buildCreateTimeTaskBO(timeTaskDetail);

        if (StringUtils.equalStringNoCareUpperAndLower(Constants.TimeTaskType.ONCE, timeTaskBO.getTimeTaskType()))
        {
            timeTaskBO.setNextTime(LocalAttribute.getNow());
        }
        else
        {
            Date nextTime = timeTaskTimeManager.nextTime(timeTaskBO);
            timeTaskBO.setNextTime(nextTime);
        }

        timeTaskBO.setCount(0);
        TimeTaskBO newResult = timeTaskAtom.addTimeTask(timeTaskBO);
        if (null == newResult)
        {
            throw new IFPException(TimeTaskErrorCode.ADD_TIME_TASK_ERROR, "Add time task error");
        }
        LocalAttribute.addNeedRefreshCache(TimeTaskCache.CACHE_NAME);
        return buildTimeTaskDetail(newResult);
    }

    @Override
    public TimeTaskDetail updateTimeTask(TimeTaskDetail timeTaskDetail)
    {
        if (null == timeTaskDetail)
        {
            throw new IFPException(FlowErrorCode.REQUEST_IS_NULL, "Request is null");
        }
        TimeTaskBO timeTaskBo = buildUpdateTimeTask(timeTaskDetail);
        TimeTaskBO newResult = timeTaskAtom.updateTimeTask(timeTaskBo);
        if (null == newResult)
        {
            throw new IFPException(TimeTaskErrorCode.UPDATE_TIME_TASK_ERROR, "Update time task error");
        }
        LocalAttribute.addNeedRefreshCache(TimeTaskCache.CACHE_NAME);
        return buildTimeTaskDetail(newResult);
    }

    @Override
    public TimeTaskDetail queryTimeTaskByCode(String timeTaskCode)
    {
        if (StringUtils.isNullOrEmpty(timeTaskCode))
        {
            return null;
        }
        TimeTaskBO queryResult = timeTaskAtom.queryTimeTaskByCode(timeTaskCode);
        return buildTimeTaskDetail(queryResult);
    }

    @Override
    public PageObject queryTimeTaskByPage(TimeTaskDetail timeTaskDetail, PagingRequest pagingRequest)
    {
        TimeTaskBO timeTaskBO = new TimeTaskBO();
        putInformation(timeTaskBO, timeTaskDetail);
        FramePaging framePaging = new FramePaging();
        if (null != pagingRequest)
        {
            framePaging.setPage(pagingRequest.getPage());
            framePaging.setSize(pagingRequest.getSize());
        }
        Page<TimeTaskBO> timeTaskPaging = timeTaskAtom.queryTimeTaskByPage(timeTaskBO, framePaging);

        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(timeTaskPaging.getTotalElements());
        pagingResponse.setCurrentPage(timeTaskPaging.getNumber());
        pagingResponse.setTotalPage(timeTaskPaging.getTotalPages());
        PageObject pageObject = new PageObject();
        pageObject.setPagingResponse(pagingResponse);
        pageObject.setResponse(buildTimeTaskDetailList(timeTaskPaging.getContent()));
        return pageObject;
    }

    @Override
    public String deleteTimeTask(String code)
    {
        TimeTaskBO timeTask = timeTaskAtom.queryTimeTaskByCode(code);
        if (null == timeTask)
        {
            throw new IFPException(TimeTaskErrorCode.TIME_TASK_NOT_EXIST_WHEN_DELETING);
        }
        timeTask.setStatus("D");
        fixUpdateInfo(timeTask);
        TimeTaskBO newResult = timeTaskAtom.updateTimeTask(timeTask);
        if (null == newResult)
        {
            throw new IFPException(TimeTaskErrorCode.DELETING_TIME_TASK_ERROR, "Update time task error");
        }
        LocalAttribute.addNeedRefreshCache(TimeTaskCache.CACHE_NAME);
        return newResult.getCode();
    }

    @Override
    public void updateTimeTaskExecute(TimeTaskDetail timeTaskDetail)
    {
        TimeTaskBO timeTask = timeTaskAtom.queryTimeTaskByCode(timeTaskDetail.getTimeTaskCode());
        if (null == timeTask)
        {
            throw new IFPException(TimeTaskErrorCode.RUNTING_TIME_TASK_CODE_NOT_EXIST, "Time task not exist");
        }
        timeTaskAtom.updateTimeTaskAddExecuteTime(timeTask.getId(), timeTaskDetail.getNextTime());
        LocalAttribute.addNeedRefreshCache(TimeTaskCache.CACHE_NAME);


    }

    @Override
    public PageObject queryTimeTaskInstanceByPage(TimeTaskInstanceDetail timeTaskInstanceDetail, PagingRequest pagingRequest)
    {
        TimeTaskInstance timeTaskInstance = new TimeTaskInstance();
        TimeTaskBO timeTaskBO = timeTaskAtom.queryTimeTaskByCode(timeTaskInstanceDetail.getTimeTaskCode());
        if (null == timeTaskBO)
        {
            return null;
        }
        timeTaskInstance.setTimeTaskId(timeTaskBO.getId());
        FramePaging framePaging = new FramePaging();
        if (null != pagingRequest)
        {
            framePaging.setPage(pagingRequest.getPage());
            framePaging.setSize(pagingRequest.getSize());
        }
        Page<TimeTaskInstance> timeTaskPaging = timeTaskInstanceAtom.queryTimeTaskInstanceByPage(timeTaskInstance, framePaging);

        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(timeTaskPaging.getTotalElements());
        pagingResponse.setCurrentPage(timeTaskPaging.getNumber());
        pagingResponse.setTotalPage(timeTaskPaging.getTotalPages());
        PageObject pageObject = new PageObject();
        pageObject.setPagingResponse(pagingResponse);
        pageObject.setResponse(buildTimeTaskInstanceDetailList(timeTaskPaging.getContent(), timeTaskBO));
        return pageObject;
    }

    private TimeTaskBO buildCreateTimeTaskBO(TimeTaskDetail timeTaskDetail)
    {
        if (null == timeTaskDetail)
        {
            return null;
        }
        TimeTaskBO timeTaskBO = new TimeTaskBO();
        timeTaskBO.setCode(timeTaskDetail.getTimeTaskCode());
        putInformation(timeTaskBO, timeTaskDetail);
        fixUpdateInfo(timeTaskBO);
        fixCreateInfo(timeTaskBO);
        return timeTaskBO;
    }

    private TimeTaskBO buildUpdateTimeTask(TimeTaskDetail timeTaskDetail)
    {
        TimeTaskBO timeTask = timeTaskAtom.queryTimeTaskByCode(timeTaskDetail.getTimeTaskCode());
        if (null == timeTask)
        {
            throw new IFPException(TimeTaskErrorCode.TIME_TASK_NOT_EXIST_WHEN_UPDATING);
        }
        putInformation(timeTask, timeTaskDetail);
        fixUpdateInfo(timeTask);
        return timeTask;
    }

    private void putInformation(TimeTaskBO timeTaskBO, TimeTaskDetail timeTaskDetail)
    {
        if (null != timeTaskDetail.getCycle())
        {
            timeTaskBO.setCycle(timeTaskDetail.getCycle());
        }
        if (null != timeTaskDetail.getEffectiveTime())
        {
            timeTaskBO.setEffectiveTime(timeTaskDetail.getEffectiveTime());
        }
        if (null != timeTaskDetail.getExpireTime())
        {
            timeTaskBO.setExpireTime(timeTaskDetail.getExpireTime());
        }
        if (null != timeTaskDetail.getStatus())
        {
            timeTaskBO.setStatus(timeTaskDetail.getStatus());
        }
        if (null != timeTaskDetail.getTimeTaskName())
        {
            timeTaskBO.setName(timeTaskDetail.getTimeTaskName());
        }
        if (null != timeTaskDetail.getTimeTaskType())
        {
            timeTaskBO.setTimeTaskType(timeTaskDetail.getTimeTaskType());
        }
        if (null != timeTaskDetail.getUnit())
        {
            timeTaskBO.setUnit(timeTaskDetail.getUnit());
        }
        if (null != timeTaskDetail.getService())
        {
            timeTaskBO.setServiceBean(timeTaskDetail.getService());
        }

    }

    private void fixCreateInfo(TimeTaskBO timeTaskBO)
    {
        CurrentEntity currentEntity = currentObject.getCurrentEntity();
        timeTaskBO.setCreateTime(currentEntity.getNow());
        timeTaskBO.setCreateObjType(currentEntity.getType());
        timeTaskBO.setCreateObjId(currentEntity.getId());
    }

    private void fixUpdateInfo(TimeTaskBO timeTaskBO)
    {
        CurrentEntity currentEntity = currentObject.getCurrentEntity();
        timeTaskBO.setLastUpdateTime(currentEntity.getNow());
        timeTaskBO.setLastUpdateObjType(currentEntity.getType());
        timeTaskBO.setLastUpdateObjId(currentEntity.getId());
    }

    private List<TimeTaskDetail> buildTimeTaskDetailList(List<TimeTaskBO> timeTaskBOList)
    {
        List<TimeTaskDetail> details = new ArrayList<>();
        CollectionUtils.iterator(timeTaskBOList, (c, v, i) ->
        {
            TimeTaskDetail timeTaskDetail = buildTimeTaskDetail(v);
            if (null != timeTaskDetail)
            {
                details.add(timeTaskDetail);
            }

        });
        return details;
    }

    private TimeTaskDetail buildTimeTaskDetail(TimeTaskBO timeTaskBO)
    {
        if (null == timeTaskBO)
        {
            return null;
        }
        TimeTaskDetail timeTaskDetail = new TimeTaskDetail();
        timeTaskDetail.setTimeTaskCode(timeTaskBO.getCode());
        timeTaskDetail.setCycle(timeTaskBO.getCycle());
        timeTaskDetail.setEffectiveTime(timeTaskBO.getEffectiveTime());
        timeTaskDetail.setExpireTime(timeTaskBO.getExpireTime());
        timeTaskDetail.setTimeTaskName(timeTaskBO.getName());
        timeTaskDetail.setTimeTaskType(timeTaskBO.getTimeTaskType());
        timeTaskDetail.setUnit(timeTaskBO.getUnit());
        timeTaskDetail.setCycle(timeTaskBO.getCycle());
        timeTaskDetail.setStatus(timeTaskBO.getStatus());
        timeTaskDetail.setService(timeTaskBO.getServiceBean());
        String statusName = DictionaryUtils.getDictNameDictKeyAndValue("time-task-status",timeTaskDetail.getStatus());
        timeTaskDetail.setStatusName(statusName);
        return timeTaskDetail;
    }

    private List<TimeTaskInstanceDetail> buildTimeTaskInstanceDetailList(List<TimeTaskInstance> timeTaskInstances, TimeTaskBO timeTaskBO)
    {
        List<TimeTaskInstanceDetail> details = new ArrayList<>();
        CollectionUtils.iterator(timeTaskInstances, (c, v, i) ->
        {
            TimeTaskInstanceDetail timeTaskInstanceDetail = buildTimeTaskInstanceDetail(v, timeTaskBO);
            if (null != timeTaskInstanceDetail)
            {
                details.add(timeTaskInstanceDetail);
            }

        });
        return details;
    }

    private TimeTaskInstanceDetail buildTimeTaskInstanceDetail(TimeTaskInstance timeTaskInstance, TimeTaskBO timeTaskBO)
    {
        if (null == timeTaskInstance)
        {
            return null;
        }
        TimeTaskInstanceDetail timeTaskInstanceDetail = new TimeTaskInstanceDetail();
        timeTaskInstanceDetail.setTimeTaskCode(timeTaskBO.getCode());
        timeTaskInstanceDetail.setEndTime(timeTaskInstance.getEndTime());
        timeTaskInstanceDetail.setStartTime(timeTaskInstance.getStartTime());
        timeTaskInstanceDetail.setStatus(timeTaskInstance.getStatus());
        String statusName = DictionaryUtils.getDictNameDictKeyAndValue("time-task-instance-status",timeTaskInstance.getStatus());
        timeTaskInstanceDetail.setErrorStack(timeTaskInstance.getErrorStack());
        timeTaskInstanceDetail.setStatusName(statusName);
        return timeTaskInstanceDetail;

    }


}
