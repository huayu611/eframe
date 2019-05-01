package com.huayu.eframe.global.system.switchdebug;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 2019/4/9.
 */
@Service
public class LogLevelCustomization
{
    private Set<String> loggerSet = new HashSet<>();

    public LogLevelCustomization(Set<LogCatalog> loggers)
    {
        if (CollectionUtils.isNotEmpty(loggers))
        {
            CollectionUtils.iterator(loggers, (c, v, i) ->
            {
                loggerSet.addAll(v.getLoggers());
            });
        }
    }

    public Set<String> getLogger()
    {
        return loggerSet;
    }
}
