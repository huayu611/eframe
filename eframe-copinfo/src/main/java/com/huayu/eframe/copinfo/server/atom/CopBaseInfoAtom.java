package com.huayu.eframe.copinfo.server.atom;

import com.huayu.eframe.copinfo.server.bo.CopBaseInfo;

import java.util.List;

/**
 * Created by Leo on 2019/1/18.
 */
public interface CopBaseInfoAtom
{
    CopBaseInfo insert(CopBaseInfo copBaseInfo);

    CopBaseInfo update(CopBaseInfo copBaseInfo);

    List<CopBaseInfo> query();

}
