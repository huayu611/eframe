package com.huayu.eframe.global.email;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.email.presist.flow.add.AddEmailBusiness;
import com.huayu.eframe.global.email.presist.flow.add.AddEmailRequest;
import com.huayu.eframe.global.email.presist.flow.query.QueryEmailBusiness;
import com.huayu.eframe.global.email.presist.flow.query.QueryEmailRequest;
import com.huayu.eframe.global.email.presist.flow.remove.RemoveEmailBusiness;
import com.huayu.eframe.global.email.presist.flow.remove.RemoveEmailRequest;
import com.huayu.eframe.global.email.presist.flow.update.ModifyEmailBusiness;
import com.huayu.eframe.global.email.presist.flow.update.ModifyEmailRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2019/3/10.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/email-setting")
public class EmailRestfulServer
{
    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addEmail(@RequestBody AddEmailRequest addEmailRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddEmailBusiness.class, addEmailRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object updateEmail(@RequestBody ModifyEmailRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyEmailBusiness.class, request, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/email/{emailCode}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object removeEmail(@PathVariable String emailCode, EasyParam easyParam)
    {
        RemoveEmailRequest removeEmailRequest = new RemoveEmailRequest();
        removeEmailRequest.setEmailCode(emailCode);
        Object obj = Flow.execute(RemoveEmailBusiness.class, removeEmailRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryEmail(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "emailCode", required = false) String emailCode,
            @RequestParam(name = "name", required = false) String name,
            EasyParam easyParam)
    {
        QueryEmailRequest queryEmailRequest = new QueryEmailRequest();
        queryEmailRequest.setEmailCode(emailCode);
        queryEmailRequest.setName(name);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryEmailRequest.setPage(pagingRequest);
        Object obj = Flow.execute(QueryEmailBusiness.class, queryEmailRequest, easyParam);
        return obj;
    }
}
