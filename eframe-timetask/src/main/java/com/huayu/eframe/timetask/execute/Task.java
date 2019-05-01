package com.huayu.eframe.timetask.execute;

import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by Leo on 2019/4/15.
 */
public interface Task extends BeanNameAware
{
    String getName();

    Integer execute();

}
