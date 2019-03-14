package com.huayu.eframe.config.parameter.bo;


import javax.persistence.*;

@Entity
@Table(name = "SYS_PARAMETER_DESC")
public class ParameterDescription
{

    @Id
    @Column(name = "ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PARAMETER_CODE")
    private String parameterCode;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "NAME_CODE")
    private String nameCode;

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

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
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
