package com.huayu.eframe.copinfo.server.flow.copbaseinfo;

import com.huayu.eframe.copinfo.common.CopLogoRuler;
import com.huayu.eframe.copinfo.server.serivce.CopBaseInfoService;
import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.multipart.MultipartResolve;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class ModifyCopBaseInfoBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(ModifyCopBaseInfoBusiness.class);

    private final static String RESULT = "ModifyCopBaseInfoBusiness_RESULT";

    @Autowired
    private CopBaseInfoService copBaseInfoService;

    @Autowired
    private CopLogoRuler copLogoRuler;


    @Override
    public void execute(BusinessParameter param)
    {
        CopBaseInfoMessage message = param.getRequest();
        debug.log(message);
        CopBaseInfoDetail detail = CopBaseInfoUtil.buildCopBaseInfoDetail(message);
        CopBaseInfoDetail existItem = copBaseInfoService.query();
        CopBaseInfoDetail result;
        if (null == existItem)
        {
            result = copBaseInfoService.addCopBaseInfoDetail(detail);
        }
        else
        {
            result = copBaseInfoService.updateCopBaseInfoDetail(detail);
        }
        MultipartResolve.use(message.getLogo(), copLogoRuler);
        param.addParameter(RESULT, result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);

    }
}
