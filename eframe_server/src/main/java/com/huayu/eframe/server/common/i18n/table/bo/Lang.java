package com.huayu.eframe.server.common.i18n.table.bo;

import javax.persistence.*;

@Entity
@Table(name="SYS_LANG")
public class Lang
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="LANG_CODE", length=255)
    private String langCode;

    @Column(name="LANG_LANGUAGE", length=255)
    private String language;

    @Column(name="VALUE", length=255)
    private String value;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLangCode()
    {
        return langCode;
    }

    public void setLangCode(String langCode)
    {
        this.langCode = langCode;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
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
