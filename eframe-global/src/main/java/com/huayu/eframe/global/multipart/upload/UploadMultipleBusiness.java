package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRulerExecute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/5/9.
 */
@Service
public class UploadMultipleBusiness extends AbstractUploadBusiness
{
    private static final LogDebug debug = new LogDebug(UploadBusiness.class);

    private static final String RESULT = "UploadMultipleBusiness_RESULT";

    private static final String UPLOAD_RULER = "UploadMultipleBusiness_RULER";

    @Autowired
    private UploadRulerExecute uploadRulerExecute;

    @Override
    public void before(BusinessParameter param)
    {
        debug.log("before and valid");
        UploadMultipleRequest uploadRequest = param.getRequest();
        String type = uploadRequest.getType();
        UploadRuler upload = uploadRulerExecute.getUploadRuler(type);
        if (null == upload)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_TYPE_NOT_EXIST, "Upload type not exist");
        }


        List<MultipartFile> files = uploadRequest.getMultipartFiles();
        if(CollectionUtils.getSize(files)>upload.getFileNumber())
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_REACH_MAX_FILE_NUMBER, "The upload file reach max number");
        }
        CollectionUtils.iterator(files,(c,v,i)->{
            validFileName(v,upload);
        });

        param.addParameter(UPLOAD_RULER, upload);
        debug.log("finish valid");
    }

    @Override
    public void execute(BusinessParameter param)
    {
        UploadMultipleRequest uploadRequest = param.getRequest();
        List<MultipartFile> files = uploadRequest.getMultipartFiles();

        debug.log(uploadRequest);

        List<String> resultList = new ArrayList<>();
        CollectionUtils.iterator(files,(c,v,i)->{
            String result = saveFile(uploadRequest.getType(), v, uploadRequest.getTemp());
            resultList.add(result);
        });
        param.addParameter(RESULT, resultList);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        UploadMultipleRequest uploadRequest = param.getRequest();
        UploadMultipleResponse uploadResponse = new UploadMultipleResponse();
        List<String> results = param.getParameter(RESULT);
        uploadResponse.setNames(results);
        uploadResponse.setType(uploadRequest.getType());

        UploadRuler upload = param.getParameter(UPLOAD_RULER);
        List<String> fullPaths = new ArrayList<>();
        CollectionUtils.iterator(fullPaths,(c,v,i)->{
            String res = upload.downLoadPath() + uploadRequest.getType() + "/" + v;
            fullPaths.add(res);
        });

        uploadResponse.setFullPaths(fullPaths);
        return uploadResponse;
    }





}
