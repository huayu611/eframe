package com.huayu.eframe.config.bo;

import com.huayu.eframe.server.common.i18n.table.bo.StandardLang;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYS_PARAMETER")
public class Parameter extends StandardLang
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="PARAMETER_CODE", length=255)
    private String parameterCode;

    @Column(name="PARAMETER_NAME", length=255)
    private String parameterName;

    @Column(name="CATEGORY_CODE", length=255)
    private String category;

    @Column(name="NAME_CODE", length=255)
    private String nameCode;

    @Column(name="VALUE", length=255)
    private String value;

    @Column(name="LAST_UPDATE_TIME", length=255)
    private Date lastUpdateTime;

    @Column(name="LAST_UPDATE_OPERATOR", length=255)
    private Long operatorId;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getParameterCode()
    {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode)
    {
        this.parameterCode = parameterCode;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }

    public String getNameCode()
    {
        return nameCode;
    }

    public void setNameCode(String nameCode)
    {
        this.nameCode = nameCode;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
}
