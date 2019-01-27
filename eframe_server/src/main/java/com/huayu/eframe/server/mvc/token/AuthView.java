package com.huayu.eframe.server.mvc.token;

/**
 * Created by Leo on 2018/9/19.
 */
public class AuthView
{
    private Long permissionID;

    private String url;

    private String method;

    public Long getPermissionID()
    {
        return permissionID;
    }

    public void setPermissionID(Long permissionID)
    {
        this.permissionID = permissionID;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }
}
