package com.huayu.eframe.server.common.i18n.table.bo;

import javax.persistence.*;

/**
 * Created by Leo on 2019/8/3.
 */
@Entity
@Table(name = "sys_lang_text")
public class LangText
{
    @Id
    @Column(name = "ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primary_code", length = 255)
    private String primaryCode;

    @Column(name = "LANG_CODE", length = 255)
    private String langCode;

    @ManyToOne
    @JoinColumn(name = "LANG_LANGUAGE", referencedColumnName = "ID")
    private LangDefine langDefine;

    @Column(name = "VALUE")
    private String value;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPrimaryCode()
    {
        return primaryCode;
    }

    public void setPrimaryCode(String primaryCode)
    {
        this.primaryCode = primaryCode;
    }

    public String getLangCode()
    {
        return langCode;
    }

    public void setLangCode(String langCode)
    {
        this.langCode = langCode;
    }

    public LangDefine getLangDefine()
    {
        return langDefine;
    }

    public void setLangDefine(LangDefine langDefine)
    {
        this.langDefine = langDefine;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
