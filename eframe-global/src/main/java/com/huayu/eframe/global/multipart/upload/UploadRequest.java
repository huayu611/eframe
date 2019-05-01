package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Leo on 2019/1/18.
 */
public class UploadRequest
{
    @EFrameRequest(required = true)
    private MultipartFile multipartFile;

    @EFrameRequest(required = true, length = 32)
    private String type;

    //如果使用临时目录，上传之后需要调用MultipartResolve.use 或MultipartResolve.useByFileName
    @EFrameRequest(required = true)
    private Boolean temp;

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

    public Boolean getTemp()
    {
        return temp;
    }

    public void setTemp(Boolean temp)
    {
        this.temp = temp;
    }
}
