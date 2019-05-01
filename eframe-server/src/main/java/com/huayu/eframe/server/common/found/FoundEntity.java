package com.huayu.eframe.server.common.found;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 若实体愿意被探知，可以单例autowire这个来查询实体
 * Created by Leo on 2019/3/25.
 */
@Service
public class FoundEntity
{
    private Map<String, ObjectEntity> objectEntityByClass;

    public FoundEntity(@Nullable List<ObjectEntity> objectEntityList)
    {
        objectEntityByClass = new HashMap<>();
        CollectionUtils.iterator(objectEntityList, (c, v, i) ->
        {
            objectEntityByClass.put(v.getName(), v);
        });
    }

    public ObjectEntity getObjectEntity(String name)
    {
        return objectEntityByClass.get(name);
    }
}
