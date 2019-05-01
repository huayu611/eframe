package com.huayu.eframe.server.tool.util;

import java.util.Map;

/**
 * Created by Leo on 2019/3/22.
 */
@FunctionalInterface
public interface MapIteratorProcess<Key, Value>
{
    void process(Map<Key, Value> map, Key key, Value value);
}
