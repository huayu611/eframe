package com.huayu.eframe.global.dict.entity.bo;

import javax.persistence.*;

/**
 * Created by Leo on 2019/8/24.
 */
@Entity
@Table(name="sys_dict_param")
public class DictParamEntity
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dict_id", referencedColumnName = "ID")
    private DictEntity dictEntity;

    @Column(name="dict_order")
    private Integer order;

    @Column(name="code")
    private String code;

    @Column(name="dict_value")
    private String value;

    @Column(name="dict_key")
    private String key;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public DictEntity getDictEntity()
    {
        return dictEntity;
    }

    public void setDictEntity(DictEntity dictEntity)
    {
        this.dictEntity = dictEntity;
    }

    public Integer getOrder()
    {
        return order;
    }

    public void setOrder(Integer order)
    {
        this.order = order;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
