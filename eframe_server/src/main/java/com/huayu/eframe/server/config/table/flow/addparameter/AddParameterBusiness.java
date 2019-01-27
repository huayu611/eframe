package com.huayu.eframe.server.config.table.flow.addparameter;

import com.huayu.eframe.server.common.i18n.table.bo.Languages;
import com.huayu.eframe.server.common.i18n.table.util.InternationalServiceFacade;
import com.huayu.eframe.server.config.table.bo.Parameter;
import com.huayu.eframe.server.config.table.bo.ParameterCategory;
import com.huayu.eframe.server.config.table.bo.ParameterDescription;
import com.huayu.eframe.server.config.table.common.Category;
import com.huayu.eframe.server.config.table.common.Description;
import com.huayu.eframe.server.config.table.service.ParameterCategoryService;
import com.huayu.eframe.server.config.table.service.ParameterDescriptionService;
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
@Service("AddParameterBusiness")
public class AddParameterBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AddParameterBusiness.class);

    private final static String PARENT_CATEGORY = "AddParameterBusiness_PARENT_CATEGORY";

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private ParameterDescriptionService parameterDescriptionService;

    @Autowired
    private InternationalServiceFacade internationalServiceFacade;

    @Autowired
    private ParameterCategoryService parameterCategoryService;

    @Override
    public void before(BusinessParameter param)
    {
        debug.beginLog();
        AddParameterRequest request = param.getRequest();
        String code = request.getParamCode();

        Parameter parameter = parameterService.getParameterByCode(code);

        if (null != parameter)
        {
            throw new IFPException(ErrorCode.ADD_PARAMETER_PARAMETER_EXIST, "parameter exist already");
        }

        Category category = request.getCategory();
        if (null != category && StringUtils.isNotNullAndEmpty(category.getParentCategory()))
        {
            ParameterCategory parentCategory = parameterCategoryService.getParameterCategory(category.getParentCategory());
            if (null == parentCategory)
            {
                throw new IFPException(ErrorCode.ADD_PARAMETER_PARAMETER_PARENT_NOT_EXIST, "Parent category not exist!");
            }
            param.addParameter(PARENT_CATEGORY, parentCategory);
            debug.log(parentCategory);
        }

        debug.endLog();
    }

    @Override
    public void execute(BusinessParameter param)
    {
        debug.beginLog();

        AddParameterRequest request = param.getRequest();

        ParameterCategory categoryParameter = processCategory(param);

        processDescription(request);

        processParameter(request, categoryParameter);

        debug.endLog();
    }

    private Parameter processParameter(AddParameterRequest request, ParameterCategory category)
    {
        debug.beginLog();
        Parameter parameter = new Parameter();
        parameter.setParameterCode(request.getParamCode());
        parameter.setValue(request.getParamValue());
        parameter.setParameterName(request.getName());

        Languages language = request.getLanguages();
        if (null != category)
        {
            parameter.setCategory(category.getCategoryCode());
        }
        String languageCode = internationalServiceFacade.add(language);
        if (StringUtils.isNotNullAndEmpty(languageCode))
        {
            parameter.setNameCode(languageCode);
        }
        debug.log(parameter);
        debug.endLog();
        return parameterService.save(parameter);
    }

    private void processDescription(AddParameterRequest request)
    {
        debug.beginLog();
        ParameterDescription parameterDescription = new ParameterDescription();
        Description desc = request.getDescription();
        if (null == desc)
        {
            return;
        }

        parameterDescription.setParameterCode(request.getParamCode());
        parameterDescription.setValue(desc.getDescrition());

        Languages language = desc.getLanguages();
        String key = internationalServiceFacade.add(language);
        if (StringUtils.isNotNullAndEmpty(key))
        {
            parameterDescription.setNameCode(key);
        }
        debug.log(parameterDescription);
        if (StringUtils.isNullOrEmpty(parameterDescription.getValue()) && StringUtils.isNullOrEmpty(parameterDescription.getNameCode()))
        {
            debug.endLog();
            return;
        }
        debug.endLog();
        parameterDescriptionService.addParameterDescription(parameterDescription);

    }


    private ParameterCategory processCategory(BusinessParameter param)
    {
        debug.beginLog();
        AddParameterRequest request = param.getRequest();
        Category category = request.getCategory();
        if (null == category)
        {
            debug.endLog();
            return null;
        }
        String code = category.getCategoryCode();
        String name = category.getCategoryName();
        Languages languages = category.getLanguages();
        String parent = category.getParentCategory();
        if (StringUtils.isAllNotExist(code, name, parent))
        {
            debug.endLog();
            return null;
        }


        ParameterCategory parameterCategory = null;
        if (StringUtils.isNotNullAndEmpty(code))
        {
            parameterCategory = parameterCategoryService.getParameterCategory(code);
        }
        if (null == parameterCategory)
        {
            ParameterCategory parentCategory = param.getParameter(PARENT_CATEGORY);
            ParameterCategory pc = new ParameterCategory();
            if (StringUtils.isNotNullAndEmpty(code))
            {
                code = RandomUtils.getRandomUUID();
            }
            pc.setCategoryCode(code);
            if (null != parentCategory)
            {
                pc.setParentCode(parentCategory.getCategoryCode());
                pc.setTopParentCode(parentCategory.getTopParentCode());
                pc.setLevel(parentCategory.getLevel() + 1);
            }
            else
            {
                pc.setParentCode(code);
                pc.setTopParentCode(code);
                pc.setLevel(1);
            }

            String languageCode = internationalServiceFacade.add(languages);
            if (StringUtils.isNotNullAndEmpty(languageCode))
            {
                pc.setNameCode(languageCode);
            }
            debug.log(pc);
            debug.endLog();
            return parameterCategoryService.addParameterCategory(pc);
        }
        else
        {
            debug.log(parameterCategory);
            debug.endLog();
            return parameterCategory;
        }


    }
}
