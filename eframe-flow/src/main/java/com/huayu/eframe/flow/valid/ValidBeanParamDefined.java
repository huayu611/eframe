package com.huayu.eframe.flow.valid;

import java.lang.reflect.Field;

/**
 * Created by Leo on 2018/9/26.
 */
public interface ValidBeanParamDefined
{
    void process(Field field, Object filedObject, Object request,String parameter);

}
