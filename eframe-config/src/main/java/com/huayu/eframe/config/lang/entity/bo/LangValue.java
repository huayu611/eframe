package com.huayu.eframe.config.lang.entity.bo;

import javax.persistence.*;

@Entity
@Table(name = "SYS_LANG_VALUE")
public class LangValue
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

    @Column(name = "VALUE", length = 1024)
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

    public String getPrimaryCode()
    {
        return primaryCode;
    }

    public void setPrimaryCode(String primaryCode)
    {
        this.primaryCode = primaryCode;
    }
}
