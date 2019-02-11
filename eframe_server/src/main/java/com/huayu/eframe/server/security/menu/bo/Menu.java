package com.huayu.eframe.server.security.menu.bo;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/12.
 */
@Entity
@Table(name="SYS_MENU")
public class Menu
{
    @Id
    @Column(name="MENU_ID", length=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(name="MENU_NAME", length=128)
    private String menuName;

    @Column(name="MENU_PATH", length=128)
    private String menuPath;

    @Column(name="COMPONENT", length=255)
    private String component;

    @Column(name="REDIRECT", length=255)
    private String redirect;

    @Column(name="ICON", length=255)
    private String icon;

    @Column(name="MENU_LEVEL", length=3)
    private Integer menuLevel;

    @Column(name="PARENT_MENU", length=20)
    private Long parentMenu;

    @Column(name="TOP_MENU", length=20)
    private Long topMenu;

    @Column(name="MENU_DESCRIPTION", length=128)
    private String menuDescription;

    @Column(name="CODE", length=32)
    private String code;

    @Column(name="CREATE_TIME")
    private Date createTime;

    @Column(name="EXPIRE_TIME")
    private Date expireTime;

    @Column(name="EFFECTIVE_TIME")
    private Date effectiveTime;

    @Column(name="LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    @Column(name="LAST_UPDATE_STAFF")
    private Long lastUpdateStaffId;

    @Column(name="EXT_INFO", length=2048)
    private String extendsInfo;

    @Column(name="STATUS", length=2)
    private String status;

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getMenuPath()
    {
        return menuPath;
    }

    public void setMenuPath(String menuPath)
    {
        this.menuPath = menuPath;
    }

    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public String getRedirect()
    {
        return redirect;
    }

    public void setRedirect(String redirect)
    {
        this.redirect = redirect;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public Integer getMenuLevel()
    {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel)
    {
        this.menuLevel = menuLevel;
    }

    public Long getParentMenu()
    {
        return parentMenu;
    }

    public void setParentMenu(Long parentMenu)
    {
        this.parentMenu = parentMenu;
    }

    public Long getTopMenu()
    {
        return topMenu;
    }

    public void setTopMenu(Long topMenu)
    {
        this.topMenu = topMenu;
    }

    public String getMenuDescription()
    {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription)
    {
        this.menuDescription = menuDescription;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public Date getEffectiveTime()
    {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime)
    {
        this.effectiveTime = effectiveTime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getLastUpdateStaffId()
    {
        return lastUpdateStaffId;
    }

    public void setLastUpdateStaffId(Long lastUpdateStaffId)
    {
        this.lastUpdateStaffId = lastUpdateStaffId;
    }

    public String getExtendsInfo()
    {
        return extendsInfo;
    }

    public void setExtendsInfo(String extendsInfo)
    {
        this.extendsInfo = extendsInfo;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
