package com.huayu.eframe.timetask.entity.atom;

import com.huayu.eframe.timetask.entity.bo.AsynchronousOrder;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2020/10/30.
 */
public interface AsynchronousOrderAtom
{
    AsynchronousOrder saveAsynchronousOrder(AsynchronousOrder asynchronousOrder);



    List<AsynchronousOrder> queryNeedExecuteOrder(AsynchronousOrder asynchronousOrder, Date now);
}
