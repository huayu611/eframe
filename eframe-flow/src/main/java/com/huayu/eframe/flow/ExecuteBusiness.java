package com.huayu.eframe.flow;

/**
 * Created by Administrator on 2018/7/7.
 */
public interface ExecuteBusiness
{
    void init(BusinessParameter param);

    void before(BusinessParameter param);

    void execute(BusinessParameter param);

    void after(BusinessParameter param);

    void process(BusinessParameter param);

    default void exception(BusinessParameter param, Exception e)
    {
    }

    ;

    Object getResult(BusinessParameter param);
}
