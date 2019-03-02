package com.huayu.eframe.flow.valid;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2018/8/4.
 */
public class EffExpValid
{
    private static final LogDebug debug = new LogDebug(EffExpValid.class);

    private Date effDate;

    private Date expDate;

    public EffExpValid(Date effective,Date expireDate)
    {
        this.effDate = effective;

        this.expDate = expireDate;
    }

    public EffExpValid(String effective,String expireDate)
    {
        if(StringUtils.isNotNullAndEmpty(effective))
        {
            if (!DateUtils.checkStringDate(effective))
            {
                throw new IFPException(ErrorCode.REQUEST_EFFECITVE_INVALID, "Effective time invalid");
            }
        }
        if(StringUtils.isNotNullAndEmpty(expireDate))
        {
            if (!DateUtils.checkStringDate(expireDate))
            {
                throw new IFPException(ErrorCode.REQUEST_EXPIRETIME_INVALID, "Expire time invalid");
            }
        }
        this.effDate = DateUtils.stringToDate(effective);

        this.expDate = DateUtils.stringToDate(expireDate);
    }

    public EffExpValid validExpireDate()
    {
        if(null == expDate)
        {
            return this;
        }

        if(DateUtils.beforeDate(expDate, LocalAttribute.getNow()))
        {
            throw new IFPException(ErrorCode.REQUEST_EXPIRETIME_BEFORE_CURRET_TIME,"Expire time can not before current time");
        }
        return this;
    }

    public EffExpValid validExpireAndEffective()
    {
        if(expDate == null || effDate == null)
        {
            return this;
        }
        if(DateUtils.beforeDate(expDate, effDate))
        {
            throw new IFPException(ErrorCode.REQUEST_EXPIRETIME_BEFORE_CURRET_TIME,"Expire time can not before current time");
        }
        return this;
    }
}
