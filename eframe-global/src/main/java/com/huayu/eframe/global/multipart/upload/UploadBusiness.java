package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class UploadBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(UploadBusiness.class);

    private static final String RESULT = "UploadBusiness_RESULT";
    @Override
    public void execute(BusinessParameter param)
    {
        UploadRequest uploadRequest = param.getRequest();
        MultipartFile file = uploadRequest.getMultipartFile();
        debug.log(uploadRequest);
        String result= saveFile(uploadRequest.getType(),file);
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        UploadRequest uploadRequest = param.getRequest();
        UploadResponse uploadResponse = new UploadResponse();
        String result = param.getParameter(RESULT);
        uploadResponse.setName(result);
        uploadResponse.setType(uploadRequest.getType());
        return uploadResponse;
    }

    private String saveFile(String path,MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        String name = RandomUtils.getUUID() + fileName;
        debug.log(name);
        String filePath = ConfigurationUtils.getDefaultUploadFilePath()+  "\\" + path ;

        debug.log(filePath);
        File desFilePath = new File(filePath);
        if(!desFilePath.exists())
        {
            desFilePath.mkdirs();
        }
        File desFile = new File(filePath + "\\" + name);
        try
        {
            file.transferTo(desFile);
        }
        catch (IllegalStateException | IOException e)
        {
            e.printStackTrace();
            debug.log(e);
        }
        return name;
    }


}