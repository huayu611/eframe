package com.huayu.eframe.copinfo.server.bo;

import javax.persistence.*;

/**
 * Created by Leo on 2019/1/18.
 */
@Entity
@Table(name = "COP_BASEINFO")
public class CopBaseInfo
{
    @Id
    @Column(name = "ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "LOGO", length = 128)
    private String logo;

    @Column(name = "BRIEF", length = 1024)
    private String brief;

    @Column(name = "ADDRESS", length = 516)
    private String address;


    @Column(name = "EMAIL", length = 255)
    private String email;


    @Column(name = "COUNTRY", length = 64)
    private String country;


    @Column(name = "PHONE", length = 16)
    private String phone;


    @Column(name = "PROVINCE_CODE", length = 64)
    private String provinceCode;


    @Column(name = "PROVINCE_NAME", length = 64)
    private String provinceName;


    @Column(name = "CITY_CODE", length = 64)
    private String cityCode;

    @Column(name = "CITY_NAME", length = 64)
    private String cityName;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getBrief()
    {
        return brief;
    }

    public void setBrief(String brief)
    {
        this.brief = brief;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getProvinceCode()
    {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

    public String getCityCode()
    {
        return cityCode;
    }

    public void setCityCode(String cityCode)
    {
        this.cityCode = cityCode;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
}
