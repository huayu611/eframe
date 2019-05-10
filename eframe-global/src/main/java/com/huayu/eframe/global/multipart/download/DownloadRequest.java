package com.huayu.eframe.global.multipart.download;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/4/14.
 */
public class DownloadRequest
{
    @EFrameRequest(required = true, length = 32)
    private String type;

    @EFrameRequest(required = true, length = 256)
    private String fileName;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
}
