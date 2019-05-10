package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRulerExecute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class UploadBusiness extends AbstractUploadBusiness
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
        if (null == upload)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_TYPE_NOT_EXIST, "Upload type not exist");
        }
        MultipartFile file = uploadRequest.getMultipartFile();
        validFileName(file,upload);
        param.addParameter(UPLOAD_RULER, upload);
        debug.log("finish valid");
    }

    @Override
    public void execute(BusinessParameter param)
    {
        UploadRequest uploadRequest = param.getRequest();
        MultipartFile file = uploadRequest.getMultipartFile();

        debug.log(uploadRequest);
        String result = saveFile(uploadRequest.getType(), file, uploadRequest.getTemp());
        param.addParameter(RESULT, result);
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
        String res = upload.downLoadPath() + uploadRequest.getType() + "/" + result;
        uploadResponse.setFullPath(res);
        return uploadResponse;
    }


}
