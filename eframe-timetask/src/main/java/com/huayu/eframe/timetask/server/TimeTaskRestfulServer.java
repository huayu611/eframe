package com.huayu.eframe.timetask.server;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.timetask.flow.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2019/4/15.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/manage")
public class TimeTaskRestfulServer
{


    @ResponseBody
    @RequestMapping(value = "/time-task", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addTimeTask(@RequestBody AddTimeTaskRequest addTimeTaskRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddTimeTaskBusiness.class, addTimeTaskRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/time-task", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object updateTimeTask(@RequestBody UpdateTimeTaskRequest updateTimeTaskRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(UpdateTimeTaskBusiness.class, updateTimeTaskRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/time-task/{timeTaskCode}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteTimeTask(@PathVariable String timeTaskCode, EasyParam easyParam)
    {
        DeleteTimeTaskRequest deleteTimeTaskRequest = new DeleteTimeTaskRequest();
        deleteTimeTaskRequest.setTimeTaskCode(timeTaskCode);
        Object obj = Flow.execute(DeleteTimeTaskBusiness.class, deleteTimeTaskRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/time-task", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryTimeTask(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "timeTaskName", required = false) String timeTaskName,
            EasyParam easyParam)
    {
        QueryTimeTaskRequest queryTimeTaskRequest = new QueryTimeTaskRequest();
        queryTimeTaskRequest.setTimeTaskName(timeTaskName);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryTimeTaskRequest.setPage(pagingRequest);
        Object obj = Flow.execute(QueryTimeTaskBusiness.class, queryTimeTaskRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/time-task-instance", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryTimeTaskInstance(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "timeTaskCode", required = false) String timeTaskName,
            EasyParam easyParam)
    {
        QueryTimeTaskInstanceRequest queryTimeTaskInstanceRequest = new QueryTimeTaskInstanceRequest();
        queryTimeTaskInstanceRequest.setTimeTaskCode(timeTaskName);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryTimeTaskInstanceRequest.setPage(pagingRequest);
        Object obj = Flow.execute(QueryTimeTaskInstanceBusiness.class, queryTimeTaskInstanceRequest, easyParam);
        return obj;
    }

    @RequestMapping(value = "/time-task-service", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryTimeTaskService(EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryAllServiceNameBusiness.class, null, easyParam);
        return obj;
    }
}
