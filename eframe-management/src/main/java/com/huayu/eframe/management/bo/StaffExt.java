package com.huayu.eframe.management.bo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Leo on 2019/4/22.
 */
@Entity
@Table(name = "sys_staff_ext")
public class StaffExt
{
    @Id
    @Column(name = "ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "identity_id")
    private String identityId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "qq")
    private String qq;

    @Column(name = "wechat")
    private String weChat;

    @Column(name = "weibo")
    private String weiBo;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "remark")
    private String remark;

    @Column(name = "signature")
    private String signature;

    @Column(name = "alipay")
    private String alipay;

    @Column(name = "other_tel_number")
    private String otherTelNumber;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private String gender;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Staff getStaff()
    {
        return staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getIdentityId()
    {
        return identityId;
    }

    public void setIdentityId(String identityId)
    {
        this.identityId = identityId;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getWeChat()
    {
        return weChat;
    }

    public void setWeChat(String weChat)
    {
        this.weChat = weChat;
    }

    public String getWeiBo()
    {
        return weiBo;
    }

    public void setWeiBo(String weiBo)
    {
        this.weiBo = weiBo;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }

    public String getAlipay()
    {
        return alipay;
    }

    public void setAlipay(String alipay)
    {
        this.alipay = alipay;
    }

    public String getOtherTelNumber()
    {
        return otherTelNumber;
    }

    public void setOtherTelNumber(String otherTelNumber)
    {
        this.otherTelNumber = otherTelNumber;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
