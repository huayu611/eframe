package com.huayu.eframe.global.system.switchdebug;

import ch.qos.logback.classic.Level;
import com.huayu.eframe.config.parameter.util.ParameterUtil;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * Created by Leo on 2019/4/9.
 * 功能暂不成熟，暂不开放接口。
 */
@Service("com.huayu.eframe.global.system.switchdebug.SwitchDebugBusiness")
public class SwitchDebugBusiness extends AbstractExecuteBusiness
{

    @Autowired
    private LogLevelCustomization logLevelCustomization;

    @Override
    public void execute(BusinessParameter param)
    {
        SwitchDebugRequest switchDebugRequest = param.getRequest();
        switchLog(switchDebugRequest.getLevel());
    }

    private void switchLog(Integer level)
    {
        Set<String> logger = logLevelCustomization.getLogger();
        CollectionUtils.iterator(logger, (c, v, i) ->
        {
            switchLogs(v, level);
        });
    }


    private void switchLogs(String clazz, Integer levelValue)
    {
        Logger logger = LoggerFactory.getLogger(clazz);
        Level level = Level.toLevel(levelValue);
        if (logger instanceof ch.qos.logback.classic.Logger)
        {
            ch.qos.logback.classic.Logger classicLogger = (ch.qos.logback.classic.Logger) logger;
            classicLogger.setLevel(level);
        }
    }


    private Long getDurationForSwitchDebug()
    {
        String timeDuration = ParameterUtil.getParameterValueByCode("system_debug_open_duration", "300");
        return Long.valueOf(timeDuration);
    }

    @Override
    protected boolean isNeedTransaction()
    {
        return false;
    }
}
