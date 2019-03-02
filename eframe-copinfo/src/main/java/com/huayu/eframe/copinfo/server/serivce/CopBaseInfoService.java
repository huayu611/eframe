package com.huayu.eframe.copinfo.server.serivce;

import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;

/**
 * Created by Leo on 2019/1/18.
 */
public interface CopBaseInfoService
{
    CopBaseInfoDetail addCopBaseInfoDetail(CopBaseInfoDetail detail);

    CopBaseInfoDetail updateCopBaseInfoDetail(CopBaseInfoDetail detail);

    CopBaseInfoDetail query();
}
