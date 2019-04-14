package com.huayu.eframe.copinfo.server.flow.copbaseinfo;

import com.huayu.eframe.copinfo.common.CopLogoRuler;
import com.huayu.eframe.copinfo.server.serivce.CopBaseInfoService;
import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;
import com.huayu.eframe.copinfo.util.ErrorCode;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.multipart.MultipartResolve;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class AddCopBaseInfoBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddCopBaseInfoBusiness.class);


    private final static String RESULT= "QueryCopBaseInfoBusiness_RESULT";

    @Autowired
    private CopBaseInfoService copBaseInfoService;

    @Autowired
    private CopLogoRuler copLogoRuler;

    @Override
    public void before(BusinessParameter param)
    {
        CopBaseInfoDetail result = copBaseInfoService.query();
        if(null != result)
        {
            throw new IFPException(ErrorCode.COP_INFO_EXIST,"Enterprise info exist already");
        }
    }

    @Override
    public void execute(BusinessParameter param)
    {
        CopBaseInfoMessage message = param.getRequest();
        debug.log(message);
        debug.log(message.getLogo());
        CopBaseInfoDetail detail = CopBaseInfoUtil.buildCopBaseInfoDetail(message);
        CopBaseInfoDetail addResult = copBaseInfoService.addCopBaseInfoDetail(detail);
        debug.log(addResult);
        MultipartResolve.use(message.getLogo(),copLogoRuler);
        param.addParameter(RESULT,addResult);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return  param.getParameter(RESULT);
    }

}
