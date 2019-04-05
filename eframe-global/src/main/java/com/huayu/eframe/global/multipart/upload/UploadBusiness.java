package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.multipart.MultipartUtil;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRulerExecute;
import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class UploadBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(UploadBusiness.class);

    private static final String RESULT = "UploadBusiness_RESULT";

    private static final String UPLOAD_RULER = "UPLOAD_RULER";

    @Autowired
    private UploadRulerExecute uploadRulerExecute;

    @Override
    public void before(BusinessParameter param)
    {
        debug.log("before and valid");
        UploadRequest uploadRequest = param.getRequest();
        String type = uploadRequest.getType();
        UploadRuler upload = uploadRulerExecute.getUploadRuler(type);
        if(null == upload)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_TYPE_NOT_EXIST,"Upload type not exist");
        }

        MultipartFile file = uploadRequest.getMultipartFile();
        if(file.getSize() <=0)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_IS_EMPTY,"file is empty");
        }

        if(file.getSize() > upload.getSize())
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_TOO_LARGE,"file is too large");
        }
        validFileSuffix(upload,file);
        param.addParameter(UPLOAD_RULER,upload);
        debug.log("finish valid");
    }

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

        UploadRuler upload = param.getParameter(UPLOAD_RULER);
        String res = upload.downLoadPath()  + uploadRequest.getType() + "/" + result;
        uploadResponse.setFullPath(res);
        return uploadResponse;
    }

    private String saveFile(String path,MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        String name = RandomUtils.getUUID() + fileName;
        debug.log(name);
        String filePath = MultipartUtil.getSystemPath()+  File.separator + path ;

        debug.log(filePath);
        File desFilePath = new File(filePath);
        if(!desFilePath.exists())
        {
            desFilePath.mkdirs();
        }
        File desFile = new File(filePath + File.separator + name);
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

    private void validFileSuffix(UploadRuler upload,MultipartFile file)
    {
        String fileName = file.getOriginalFilename();

        String[] fileTypes = upload.getFileType();
        boolean checkResult = false;
        for(String s : fileTypes)
        {
            Pattern p=Pattern.compile(s);
            Matcher m = p.matcher(fileName);

            checkResult = m.matches();
            if(checkResult)
            {
               break;
            }
        }
        if(!checkResult)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_SUFFIX_ERROR,"file type incorrect");
        }



    }


}
