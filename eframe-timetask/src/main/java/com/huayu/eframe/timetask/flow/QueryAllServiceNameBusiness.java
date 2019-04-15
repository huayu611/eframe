package com.huayu.eframe.timetask.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.tool.util.MapUtils;
import com.huayu.eframe.timetask.execute.Task;
import com.huayu.eframe.timetask.execute.TaskMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class QueryAllServiceNameBusiness extends AbstractExecuteBusiness
{
    @Autowired
    private TaskMap taskMap;

    @Override
    public void execute(BusinessParameter param)
    {

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        Map<String, Task> serviceMap = taskMap.getTimeTaskService();
        List<QueryAllServiceNameResponse> responseList = new ArrayList<>();
        MapUtils.iterator(serviceMap,(m,k,v) ->{
            QueryAllServiceNameResponse queryAllServiceNameResponse = new QueryAllServiceNameResponse();
            queryAllServiceNameResponse.setKey(k);
            queryAllServiceNameResponse.setName(k);
            responseList.add(queryAllServiceNameResponse);
        });
        return responseList;
    }

    @Override
    protected boolean isNeedTransaction()
    {
        return false;
    }
}
