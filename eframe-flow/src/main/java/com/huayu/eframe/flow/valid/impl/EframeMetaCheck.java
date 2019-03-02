package com.huayu.eframe.flow.valid.impl;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.flow.valid.AbstractExecuteEFrameRequest;
import com.huayu.eframe.flow.valid.ValidAnnotation;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class EframeMetaCheck extends AbstractExecuteEFrameRequest
{

    @Autowired
    private ValidAnnotation validAnnotation;

    private static Integer META_DATA_CHECK = Integer.valueOf("2");

    @Override
    public void doExtend(EFrameRequest ef, Field field, Object request) throws Exception
    {
        Object value = field.get(request);
        doObject(value);

    }

    public boolean check(EFrameRequest ef, Field field, Object request)
    {
        boolean baseMeta = ef.baseMeta();
        Object value = getValueInField(field,request);
        if(null == value)
        {
            return false;
        }
        return baseMeta;
    }

    public int order()
    {
        return META_DATA_CHECK.intValue();
    }

    private void doObject(Object obj)
    {
        if(obj instanceof Collection )
        {
            checkListData((Collection)obj);
        }
        else if(obj instanceof Map )
        {
            checkMapData((Map)obj);
        }
        else
        {
            validAnnotation.checkEFrameRequest(obj);
        }
    }

    private void checkListData(Collection collection)
    {
        if(CollectionUtils.isEmpty(collection))
        {
            return;
        }
        for(Object obj :  collection)
        {
            doObject(obj);
        }
    }

    private void checkMapData(Map<Object,Object> map)
    {
        if(MapUtils.isEmpty(map))
        {
            return;
        }
        Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
        Iterator<Map.Entry<Object, Object>> entrySetIterator = entrySet.iterator();
        while(entrySetIterator.hasNext())
        {
            Map.Entry<Object, Object> obj = entrySetIterator.next();
            doObject(obj.getValue());
        }
    }


}
