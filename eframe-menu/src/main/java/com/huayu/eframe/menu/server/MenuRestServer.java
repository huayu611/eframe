package com.huayu.eframe.menu.server;


import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.menu.flow.addmenu.AddMenuBusiness;
import com.huayu.eframe.menu.flow.addmenu.AddMenuRequest;
import com.huayu.eframe.menu.flow.delmenu.DeleteMenuBusiness;
import com.huayu.eframe.menu.flow.delmenu.DeleteMenuRequest;
import com.huayu.eframe.menu.flow.modmenu.ModifyMenuBusiness;
import com.huayu.eframe.menu.flow.modmenu.ModifyMenuRequest;
import com.huayu.eframe.menu.flow.querymenu.QueryMenuBusiness;
import com.huayu.eframe.menu.flow.querymenu.QueryMenuRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/7/13.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/manage")
public class MenuRestServer
{


    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addMenu(@RequestBody AddMenuRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddMenuBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryMenu(EasyParam easyParam)
    {
        QueryMenuRequest request = new QueryMenuRequest();
        Object obj = Flow.execute(QueryMenuBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/{code}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryMenuByCode(@PathVariable String code, EasyParam easyParam)
    {
        QueryMenuRequest request = new QueryMenuRequest();
        request.setCode(code);
        Object obj = Flow.execute(QueryMenuBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyMenu(@RequestBody ModifyMenuRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyMenuBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/{menuCodes}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteMenu(@PathVariable String menuCodes, EasyParam easyParam)
    {
        DeleteMenuRequest deleteMenuRequest = new DeleteMenuRequest();
        deleteMenuRequest.setCodes(menuCodes);
        Object obj = Flow.execute(DeleteMenuBusiness.class, deleteMenuRequest, easyParam);
        return obj;
    }
}
