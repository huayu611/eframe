package com.huayu.eframe.server.common.i18n.table.bo;

import javax.persistence.*;

/**
 * Created by Leo on 2019/7/22.
 */
@Entity
@Table(name = "sys_lang_define")
public class LangDefine
{
    @Id
    @Column(name = "ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
