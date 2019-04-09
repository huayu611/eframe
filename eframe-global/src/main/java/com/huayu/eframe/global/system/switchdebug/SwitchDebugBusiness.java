package com.huayu.eframe.global.system.switchdebug;

import ch.qos.logback.classic.Level;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogUtils;
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
        final SwitchDebugRequest switchDebugRequest = param.getRequest();
        LogUtils.setDebugTurn(switchDebugRequest.getLevel().intValue() <= Level.ERROR_INTEGER);

        Set<String> logger = logLevelCustomization.getLogger();
        CollectionUtils.iterator(logger,(c,v,i)->{
            switchLog(v,switchDebugRequest.getLevel());
        });
    }


    private void switchLog(String clazz,Integer levelValue)
    {
        Logger logger = LoggerFactory.getLogger(clazz);
        Level level = Level.toLevel(levelValue);
        if(logger instanceof ch.qos.logback.classic.Logger)
        {
            ch.qos.logback.classic.Logger classicLogger = (ch.qos.logback.classic.Logger) logger;
            classicLogger.setLevel(level);
        }
    }

    @Override
    protected boolean isNeedTransaction()
    {
        return false;
    }
}
