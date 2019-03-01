package com.huayu.eframe.server.config.table.flow.addparameter;

import com.huayu.eframe.server.common.i18n.table.bo.Languages;
import com.huayu.eframe.server.common.i18n.table.util.InternationalServiceFacade;
import com.huayu.eframe.server.config.table.bo.Parameter;
import com.huayu.eframe.server.config.table.bo.ParameterCategory;
import com.huayu.eframe.server.config.table.bo.ParameterDescription;
import com.huayu.eframe.server.config.table.common.Category;
import com.huayu.eframe.server.config.table.common.Description;
import com.huayu.eframe.server.config.table.flow.common.ParameterResponse;
import com.huayu.eframe.server.config.table.service.ParameterCategoryService;
import com.huayu.eframe.server.config.table.service.ParameterDescriptionService;
import com.huayu.eframe.server.config.table.service.ParameterDetail;
import com.huayu.eframe.server.config.table.service.ParameterService;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Add by Leo at 2018-08-25
 */
@Service
public class AddParameterBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddParameterBusiness.class);

    private final static String RESULT = "AddParameterBusiness_RESULT";

    @Autowired
    private ParameterService parameterService;

    @Override
    public void before(BusinessParameter param)
    {

    }

    @Override
    public void execute(BusinessParameter param)
    {
        AddParameterRequest addParameterRequest = param.getRequest();
        ParameterDetail parameterDetail = buildParameterDetail(addParameterRequest);
        ParameterDetail addResultParameterDetail = parameterService.addParameter(parameterDetail);
        param.addParameter(RESULT,addResultParameterDetail);

    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        ParameterResponse parameterResponse = new ParameterResponse();
        ParameterDetail parameterDetail = param.getParameter(RESULT);
        parameterResponse.setDetail(parameterDetail);
        return parameterResponse;
    }

    private ParameterDetail buildParameterDetail(AddParameterRequest addParameterRequest)
    {
        ParameterDetail parameterDetail = new ParameterDetail();
        parameterDetail.setParameterValue(addParameterRequest.getParameterValue());
        parameterDetail.setParameterName(addParameterRequest.getParameterName());
        parameterDetail.setParameterCode(addParameterRequest.getParameterCode());
        return parameterDetail;
    }


}
