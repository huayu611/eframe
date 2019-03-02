package com.huayu.eframe.management.request;

import com.huayu.eframe.server.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.server.flow.valid.ValidBeanDefined;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Leo on 2018/12/16.
 */
@Configuration
public class RequestExecuteBean
{
    private static final LogDebug debug = new LogDebug(AbstractExecuteEFrameRequest.class);

    @Bean("_e_eframe_rolecode")
    public ValidBeanDefined setDefaultRoleCode()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                String nowStr = "ROLECODE_" + RandomUtils.getRandomUUID();
                try
                {
                    field.set(request, nowStr);
                }
                catch (IllegalAccessException e)
                {
                    debug.errorLog("Set Default Role Code Error");
                }
            }
        };
        return v;
    }

    @Bean("_e_eframe_permissioncode")
    public ValidBeanDefined setDefaultPermissionCode()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                String nowStr = "PERCODE_" + RandomUtils.getRandomUUID();
                try
                {
                    field.set(request, nowStr);
                }
                catch (IllegalAccessException e)
                {
                    debug.errorLog("Set Default Role Code Error");
                }
            }
        };
        return v;
    }
}
