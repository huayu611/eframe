package com.huayu.eframe.management.bo;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/7/14.
 */
@Entity
@Table(name = "SYS_ROLE_PERMISSION")
public class RolePermission
{
    @Id
    @Column(name = "ROLE_PERMISSION_ID", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_ID", length = 20)
    private Long roleId;

    @Column(name = "PERMISSION_ID", length = 20)
    private Long permissionId;

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

    public Long getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(Long permissionId)
    {
        this.permissionId = permissionId;
    }
}
