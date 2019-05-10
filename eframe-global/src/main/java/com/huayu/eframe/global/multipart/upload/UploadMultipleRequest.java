package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Leo on 2019/5/9.
 */
public class UploadMultipleRequest
{
    @EFrameRequest(required = true)
    private List<MultipartFile> multipartFiles;

    @EFrameRequest(required = true, length = 32)
    private String type;

    //如果使用临时目录，上传之后需要调用MultipartResolve.useMore 或MultipartResolve.useByFileNames
    @EFrameRequest(required = true)
    private Boolean temp;

    public List<MultipartFile> getMultipartFiles()
    {
        return multipartFiles;
    }

    public void setMultipartFiles(List<MultipartFile> multipartFiles)
    {
        this.multipartFiles = multipartFiles;
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
