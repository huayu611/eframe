package com.huayu.eframe.global.multipart.upload;

/**
 * Created by Leo on 2019/1/24.
 */
public class UploadResponse
{
    private String name;

    private String type;

    private String fullPath;

    private String httpPath;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getFullPath()
    {
        return fullPath;
    }

    public void setFullPath(String fullPath)
    {
        this.fullPath = fullPath;
    }

    public String getHttpPath()
    {
        return httpPath;
    }

    public void setHttpPath(String httpPath)
    {
        this.httpPath = httpPath;
    }
}
