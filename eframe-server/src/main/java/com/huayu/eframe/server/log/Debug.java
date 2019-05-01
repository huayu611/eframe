package com.huayu.eframe.server.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Debug
{
    private Logger logger;

    public Debug(Class clazz)
    {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void debug(String info)
    {
        logger.debug(info);
    }

    public void info(String info)
    {
        logger.info(info);
    }

    public void error(String info)
    {
        logger.error(info);
    }

    public void warm(String info)
    {
        logger.warn(info);
    }
}
