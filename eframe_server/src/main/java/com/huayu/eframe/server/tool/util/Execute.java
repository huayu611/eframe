package com.huayu.eframe.server.tool.util;

public interface Execute
{
    default void mapProcess(Object key,Object value){}
    default void collectionProcess(Object obj){}
}
