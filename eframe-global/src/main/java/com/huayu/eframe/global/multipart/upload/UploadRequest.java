package com.huayu.eframe.global.multipart.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Leo on 2019/1/18.
 */
public class UploadRequest
{
    private MultipartFile multipartFile;

    private String type;

    public MultipartFile getMultipartFile()
    {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile)
    {
        this.multipartFile = multipartFile;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
