package com.huayu.eframe.server.cache;


import java.util.List;

public interface Cache<T>
{
    List<T> load();

    void refresh();

    String cacheName();
}
