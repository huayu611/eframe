package com.huayu.eframe.server.cache.frame;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/10/18.
 */
@Service
public class AbstractFrameCacheAdapter extends AbstractFrameCache
{
    @Override
    public List load()
    {
        return new ArrayList();
    }

    @Override
    public String cacheName()
    {
        return "_DEFAULT_";
    }
}
