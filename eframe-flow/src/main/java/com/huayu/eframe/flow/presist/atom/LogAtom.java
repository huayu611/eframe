package com.huayu.eframe.flow.presist.atom;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.flow.presist.bo.LogEntity;
import org.springframework.data.domain.Page;

/**
 * Created by Leo on 2019/2/13.
 */
public interface LogAtom
{
    LogEntity addLog(LogEntity logEntity);

    LogEntity updateLog(LogEntity logEntity);

    LogEntity getLogByCode(String logCode);

    LogEntity getLogById(Long  id);

    Page<LogEntity> queryLog(FramePaging fp, LogEntity condition);
}
