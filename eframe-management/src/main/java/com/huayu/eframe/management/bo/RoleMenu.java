package com.huayu.eframe.management.bo;

import com.huayu.eframe.menu.bo.Menu;

import javax.persistence.*;

/**
 * Created by Leo on 2019/8/5.
 */
@Entity
@Table(name = "SYS_ROLE_MENU")
public class RoleMenu
{
    @Id
    @Column(name="ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "ROLE_ID")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "MENU_ID")
    private Menu menu;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public Menu getMenu()
    {
        return menu;
    }

    public void setMenu(Menu menu)
    {
        this.menu = menu;
    }
}
