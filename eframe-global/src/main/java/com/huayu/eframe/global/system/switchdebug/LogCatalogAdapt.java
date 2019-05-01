package com.huayu.eframe.global.system.switchdebug;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 2019/4/9.
 */
@Service
public class LogCatalogAdapt implements LogCatalog
{

    @Override
    public Set<String> getLoggers()
    {
        Set<String> set = new HashSet<>();
        set.add("com.huayu");
        set.add("org.springframework");
        set.add("org.hibernate");
        return set;
    }
}
