package com.huayu.eframe.server.common.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Leo on 2020/6/9.
 */
@Service
public class TransactionAdapter
{
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void executeTransaction(TransactionRunner spider)
    {
        spider.execute();
    }
}
