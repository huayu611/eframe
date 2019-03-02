package com.huayu.eframe.feedback;

import com.huayu.eframe.feedback.flow.AddFeedBackBusiness;
import com.huayu.eframe.feedback.flow.DeleteFeedBackBusiness;
import com.huayu.eframe.feedback.flow.QueryFeedBackBusiness;
import com.huayu.eframe.feedback.requset.DeleteFeedBackRequest;
import com.huayu.eframe.feedback.requset.FeedBackRequest;
import com.huayu.eframe.feedback.requset.QueryFeedBackRequest;
import com.huayu.eframe.server.common.BusinessHelper;
import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2018/11/24.
 */
@Configuration
@RestController
@RequestMapping(value = "/feedback")
public class FeedBackRestfulServer
{
    @ResponseBody
    @RequestMapping(value = "/addFeedBack",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addFeedBack(@RequestBody FeedBackRequest feedBackRequest,  EasyParam easyParam)
    {
        String ip = BusinessHelper.getIpAddr(easyParam.getRequest());
        feedBackRequest.setIp(ip);
        Object obj = Flow.execute(AddFeedBackBusiness.class,feedBackRequest,easyParam);
        return obj;

    }

    @ResponseBody
    @RequestMapping(value = "manager/queryFeedBack/{size}/{page}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryFeedBack(@PathVariable String size ,@PathVariable String page, EasyParam easyParam)
    {
        QueryFeedBackRequest queryFeedBackRequest = new QueryFeedBackRequest();
        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setPage(NumberUtils.getIntegerFromObject(page));
        pagingRequest.setSize(NumberUtils.getIntegerFromObject(size));
        queryFeedBackRequest.setPagingRequest(pagingRequest);
       Object obj = Flow.execute(QueryFeedBackBusiness.class,queryFeedBackRequest,easyParam);
       return obj;

    }

    @ResponseBody
    @RequestMapping(value = "manager/queryFeedBack/{size}/{page}/{duration}/{content}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryFeedBackByDurationContentCondition(@PathVariable String size ,@PathVariable String page,@PathVariable String content,@PathVariable String duration, EasyParam easyParam)
    {
        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setPage(NumberUtils.getIntegerFromObject(page));
        pagingRequest.setSize(NumberUtils.getIntegerFromObject(size));
        QueryFeedBackRequest queryFeedBackRequest = new QueryFeedBackRequest();
        queryFeedBackRequest.setPagingRequest(pagingRequest);
        queryFeedBackRequest.setDateDuration(duration);
        queryFeedBackRequest.setContent(content);
        Object obj = Flow.execute(QueryFeedBackBusiness.class,queryFeedBackRequest,easyParam);
        return obj;

    }

    @ResponseBody
    @RequestMapping(value = "manager/queryFeedBack/{size}/{page}/{duration}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryFeedBackByDurationCondition(@PathVariable String size ,@PathVariable String page,@PathVariable String duration, EasyParam easyParam)
    {
        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setPage(NumberUtils.getIntegerFromObject(page));
        pagingRequest.setSize(NumberUtils.getIntegerFromObject(size));
        QueryFeedBackRequest queryFeedBackRequest = new QueryFeedBackRequest();
        queryFeedBackRequest.setPagingRequest(pagingRequest);
        queryFeedBackRequest.setDateDuration(duration);
        Object obj = Flow.execute(QueryFeedBackBusiness.class,queryFeedBackRequest,easyParam);
        return obj;

    }

    @ResponseBody
    @RequestMapping(value = "manager/deleteFeedBack/{ids}",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteFeedBack(@PathVariable String ids , EasyParam easyParam)
    {
        DeleteFeedBackRequest feedBackRequest = new DeleteFeedBackRequest();
        feedBackRequest.setIds(ids);
        Object obj = Flow.execute(DeleteFeedBackBusiness.class,feedBackRequest,easyParam);
        return obj;

    }


}
