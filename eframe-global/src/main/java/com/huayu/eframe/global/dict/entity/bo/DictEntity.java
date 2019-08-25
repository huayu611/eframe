package com.huayu.eframe.global.dict.entity.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2019/8/24.
 */
@Entity
@Table(name="sys_dict")
public class DictEntity
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="status")
    private String status;

    @Column(name="dict_code")
    private String dictCode;

    @Column(name="dict_name")
    private String dictName;

    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
