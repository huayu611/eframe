package com.huayu.eframe.config.bo;

import javax.persistence.*;

@Entity
@Table(name = "SYS_PARAMETER_CATEGORY")
public class ParameterCategory
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CATEGORY_CODE")
    private String categoryCode;

    @Column(name="LEVEL")
    private Integer level;

    @Column(name="PARENT_CODE")
    private String parentCode;

    @Column(name="TOP_PARENT_CODE")
    private String topParentCode;

    @Column(name="CATEGORY_NAME", length=255)
    private String name;

    @Column(name="CATEGORY_NAME_CODE", length=255)
    private String nameCode;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCategoryCode()
    {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public String getTopParentCode()
    {
        return topParentCode;
    }

    public void setTopParentCode(String topParentCode)
    {
        this.topParentCode = topParentCode;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNameCode()
    {
        return nameCode;
    }

    public void setNameCode(String nameCode)
    {
        this.nameCode = nameCode;
    }
}
