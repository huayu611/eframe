package com.huayu.eframe.global.multipart.upload;

import java.util.List;

/**
 * Created by Leo on 2019/5/9.
 */
public class UploadMultipleResponse
{
    private List<String> names;

    private String type;

    private List<String> fullPaths;

    public List<String> getNames()
    {
        return names;
    }

    public void setNames(List<String> names)
    {
        this.names = names;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<String> getFullPaths()
    {
        return fullPaths;
    }

    public void setFullPaths(List<String> fullPaths)
    {
        this.fullPaths = fullPaths;
    }
}
