package com.huayu.eframe.server.security.service;


import com.huayu.eframe.server.flow.Flow;
import com.huayu.eframe.server.flow.restful.PagingRequest;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.security.log.flow.QueryOperatorLogBusiness;
import com.huayu.eframe.server.security.log.message.QueryOperatorLogRequest;
import com.huayu.eframe.server.security.menu.flow.addmenu.AddMenuBusiness;
import com.huayu.eframe.server.security.menu.flow.addmenu.AddMenuRequest;
import com.huayu.eframe.server.security.menu.flow.delmenu.DeleteMenuBusiness;
import com.huayu.eframe.server.security.menu.flow.delmenu.DeleteMenuRequest;
import com.huayu.eframe.server.security.menu.flow.modmenu.ModifyMenuBusiness;
import com.huayu.eframe.server.security.menu.flow.modmenu.ModifyMenuRequest;
import com.huayu.eframe.server.security.menu.flow.querymenu.QueryMenuBusiness;
import com.huayu.eframe.server.security.menu.flow.querymenu.QueryMenuRequest;
import com.huayu.eframe.server.security.service.flow.login.LoginBusiness;
import com.huayu.eframe.server.security.service.flow.permission.*;
import com.huayu.eframe.server.security.service.flow.role.*;
import com.huayu.eframe.server.security.service.flow.staff.*;
import com.huayu.eframe.server.security.service.request.*;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.encrypt.Encrypt;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/13.
 */
@Configuration
@RestController
@RequestMapping(value = "/manage")
public class AuthenticationRestServer
{
    @ResponseBody
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object login(@RequestBody LoginRequest staffInfo, @RequestHeader HttpHeaders headers, EasyParam easyParam)
    {
        return loginAction(staffInfo, headers, easyParam);

    }

    @ResponseBody
    @RequestMapping(value = "/staff", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addStaff(@RequestBody AddStaffRequest staffinfo, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddStaffBusiness.class, staffinfo, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/staff", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryStaff(
            @RequestParam(name="size",required = false) Integer size,
            @RequestParam(name="page",required = false) Integer page,
            @RequestParam(name="name",required = false) String name,
            @RequestParam(name="login",required = false) String loginName,
            EasyParam easyParam)
    {
        QueryStaffRequest queryStaffRequest = new QueryStaffRequest();
        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setPage(page);
        pagingRequest.setSize(size);
        queryStaffRequest.setPage(pagingRequest);
        queryStaffRequest.setName(name);
        queryStaffRequest.setLogin(loginName);
        Object obj = Flow.execute(QueryStaffBusiness.class, queryStaffRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/staff/{loginCode}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryStaffByCode(@PathVariable String loginCode, EasyParam easyParam)
    {
        QueryStaffRequest queryStaffRequest = new QueryStaffRequest();
        queryStaffRequest.setLogin(loginCode);
        Object obj = Flow.execute(QueryStaffByCodeBusiness.class, queryStaffRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/staff/{loginCode}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteStaff(@PathVariable String loginCode, EasyParam easyParam)
    {
        DeleteStaffRequest deleteStaffRequest = new DeleteStaffRequest();
        deleteStaffRequest.setLogin(loginCode);
        Object obj = Flow.execute(DeleteStaffBusiness.class, deleteStaffRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/staff", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyStaff(@RequestBody ModifyStaffInformationRequest modifyStaffInfomation, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyStaffBusiness.class, modifyStaffInfomation, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/permission", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addPermission(@RequestBody AddPermissionRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddPermissionBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/permission", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyPermission(@RequestBody ModifyPermissionRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyPermissionBusiness.class, request, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/permission/{permissionCode}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object batchDeletePermission(@PathVariable String permissionCode, EasyParam easyParam)
    {
        DeletePermissionRequest deletePermissionRequest = new DeletePermissionRequest();
        deletePermissionRequest.setCode(permissionCode);
        Object obj = Flow.execute(DeletePermissionBusiness.class, deletePermissionRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/permission/{permissionCode}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryPermissionByCode(@PathVariable String permissionCode, EasyParam easyParam)
    {
        QueryPermissionRequest queryPermissionRequest = new QueryPermissionRequest();
        queryPermissionRequest.setCode(permissionCode);
        Object obj = Flow.execute(QueryPermissionByCodeBusiness.class, queryPermissionRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/permission", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryPermission(
            @RequestParam(name="size",required=false) Integer size,
            @RequestParam(name="page",required=false) Integer page,
            @RequestParam(name="code",required=false) String code,
            @RequestParam(name="name",required=false) String name,
            EasyParam easyParam)
    {
        QueryPermissionRequest request = new QueryPermissionRequest();
        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        request.setPage(pagingRequest);
        request.setCode(code);
        request.setName(name);
        Object obj = Flow.execute(QueryPermissionBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/role/{roleCode}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryRoleByCode(@PathVariable String roleCode, EasyParam easyParam)
    {
        QueryRoleRequest queryRoleRequest = new QueryRoleRequest();
        queryRoleRequest.setCode(roleCode);
        Object obj = Flow.execute(QueryRoleByCodeBusiness.class, queryRoleRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryRoleLike(
            @RequestParam(name="size",required = false) Integer size,
            @RequestParam(name="page",required = false) Integer page,
            @RequestParam(name="name",required = false) String name,
            EasyParam easyParam)
    {
        QueryRoleRequest queryRoleRequest = new QueryRoleRequest();
        queryRoleRequest.setName(name);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryRoleRequest.setPage(pagingRequest);
        queryRoleRequest.setName(name);
        Object obj = Flow.execute(QueryRoleBusiness.class, queryRoleRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/role", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addRole(@RequestBody AddRoleRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddRoleBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/role", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addRole(@RequestBody ModifyRoleRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyRoleBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/role/{roleCode}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteRole(@PathVariable String roleCode, EasyParam easyParam)
    {
        DeleteRoleRequest deleteRoleRequest = new DeleteRoleRequest();
        deleteRoleRequest.setCodes(roleCode);
        Object obj = Flow.execute(DeleteRoleBusiness.class, deleteRoleRequest, easyParam);
        return obj;
    }

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
    public Object queryMenuByCode(@PathVariable String code,EasyParam easyParam)
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
    @ResponseBody
    @RequestMapping(value = "/staff/password/{changeType}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object changeLoginPassword(@RequestBody ModifyPasswordRequest request,@PathVariable String changeType, EasyParam easyParam)
    {
        if(StringUtils.equalString("reset",changeType))
        {
            return Flow.execute(RestPasswordBusiness.class, request, easyParam);
        }
        else{
            return Flow.execute(ChangePasswordBusiness.class, request, easyParam);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryLog(
            @RequestParam(name="size",required = false) Integer size,
            @RequestParam(name="page",required = false) Integer page,
            @RequestParam(name="operatorCode",required = false) String operatorCode,
            @RequestParam(name="operatorType",required = false) String operatorType,
            @RequestParam(name="url",required = false) String url,
            @RequestParam(name="inTime",required = false) Date inTime,
            @RequestParam(name="outTime",required = false) Date outTime,
            EasyParam easyParam)
    {
        QueryOperatorLogRequest queryOperatorLogRequest = new QueryOperatorLogRequest();
        queryOperatorLogRequest.setInTime(inTime);
        queryOperatorLogRequest.setOutTime(outTime);
        queryOperatorLogRequest.setOperatorCode(operatorCode);
        queryOperatorLogRequest.setOperatorType(operatorType);
        queryOperatorLogRequest.setUrl(url);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryOperatorLogRequest.setPage(pagingRequest);
        Object obj = Flow.execute(QueryOperatorLogBusiness.class, queryOperatorLogRequest, easyParam);
        return obj;
    }

    private RestfulResponse buildError(String code, String desc)
    {
        RestfulResponse error = new RestfulResponse();
        error.setCode(code);
        error.setMsg(desc);
        return error;
    }

    private Object loginAction(@RequestBody LoginRequest staffinfo, @RequestHeader HttpHeaders headers, EasyParam easyParam)
    {
        Object resultObj = null;
        if (null == staffinfo || StringUtils.isNullOrEmpty(staffinfo.getLogin()))
        {
            List<String> authorizationList = headers.get("Authorization");
            if (CollectionUtils.isEmpty(authorizationList))
            {
                return buildError(ErrorCode.STAFFNAME_OR_PASSWORD_WRONG, "Staffname or password wrong!");
            }
            String basic = StringUtils.getString(authorizationList.get(0));
            if (StringUtils.isNullOrEmpty(basic))
            {
                return buildError(ErrorCode.STAFFNAME_OR_PASSWORD_WRONG, "Staffname or password wrong!");
            }
            String[] com = basic.split(" ");
            if (com.length < 2)
            {
                return buildError(ErrorCode.STAFFNAME_OR_PASSWORD_WRONG, "Staffname or password wrong!");
            }
            if (StringUtils.equalStringNoCareUpperAndLower(com[0], "Basic"))
            {
                String authDecode = Encrypt.decodeBase64(com[1]);
                String[] auths = authDecode.split("\\:");
                if (auths.length < 2)
                {
                    return buildError(ErrorCode.STAFFNAME_OR_PASSWORD_WRONG, "Staffname or password wrong!");
                }
                LoginRequest logReq = new LoginRequest();
                logReq.setLogin(auths[0]);
                logReq.setPassword(auths[1]);
                resultObj = Flow.execute(LoginBusiness.class, logReq, easyParam);

            }
        }
        else
        {
            resultObj = Flow.execute(LoginBusiness.class, staffinfo, easyParam);
        }

        return resultObj;
    }
}
