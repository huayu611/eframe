package com.huayu.eframe.menu.flow.common;

import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.flow.valid.ValidBeanDefined;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Leo on 2019/2/12.
 */
@Configuration
public class MenuExecuteBean
{
    private static final LogDebug debug = new LogDebug(AbstractExecuteEFrameRequest.class);

    @Bean("_e_eframe_menucode")
    public ValidBeanDefined setDefaultRoleCode()
    {
        ValidBeanDefined v = (field, value, request) ->
        {
            String valueString = StringUtils.getString(value);
            if (StringUtils.isNullOrEmpty(valueString))
            {
                String nowStr = "MENU_CODE_" + RandomUtils.getRandomUUID();
                try
                {
                    field.set(request, nowStr);
                }
                catch (IllegalAccessException e)
                {
                    debug.errorLog("Set Default Menu Code Error");
                }
            }
        };
        return v;
    }
}
