package com.huayu.eframe.management.bo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/7/14.
 */
@Entity
@Table(name="SYS_ROLE_STAFF")
public class RoleStaff
{
    @Id
    @Column(name="ROLE_STAFF_ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ROLE_ID", length=20)
    private Long roleId;

    @Column(name="STAFF_ID", length=20)
    private Long staffId;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getStaffId()
    {
        return staffId;
    }

    public void setStaffId(Long staffId)
    {
        this.staffId = staffId;
    }
}
