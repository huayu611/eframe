package com.huayu.eframe.timetask.execute;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.NamedBean;

/**
 * Created by Leo on 2019/4/15.
 */
public interface Task extends BeanNameAware
{
    String getName();

    Integer execute();

}
