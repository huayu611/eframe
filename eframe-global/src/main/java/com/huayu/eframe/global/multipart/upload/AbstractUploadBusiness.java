package com.huayu.eframe.global.multipart.upload;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.multipart.MultipartUtil;
import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Leo on 2019/5/9.
 */
public abstract class AbstractUploadBusiness  extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(AbstractUploadBusiness.class);

    protected void validFileSuffix(UploadRuler upload, MultipartFile file)
    {
        String fileName = file.getOriginalFilename();

        String[] fileTypes = upload.getFileType();
        boolean checkResult = false;
        for (String s : fileTypes)
        {
            Pattern p = Pattern.compile(s);
            Matcher m = p.matcher(fileName);

            checkResult = m.matches();
            if (checkResult)
            {
                break;
            }
        }
        if (!checkResult)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_SUFFIX_ERROR, "file type incorrect");
        }

    }

    protected String saveFile(String path, MultipartFile file, boolean temp)
    {
        String fileName = file.getOriginalFilename();
        String name = RandomUtils.getUUID() + StringUtils.cutLastString(fileName,5);
        debug.log(name);
        String filePath = "";
        if (temp)
        {
            filePath = MultipartUtil.getSystemTempPath() + File.separator + path;

        }
        else
        {
            filePath = MultipartUtil.getSystemPath() + File.separator + path;
        }


        debug.log(filePath);
        File desFilePath = new File(filePath);
        if (!desFilePath.exists())
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



    protected void validFileName(MultipartFile file,UploadRuler upload){
        if (file.getSize() <= 0)
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_IS_EMPTY, "file is empty");
        }

        if (file.getSize() > upload.getSize())
        {
            throw new IFPException(GlobalErrorCode.UPLOAD_FILE_TOO_LARGE, "file is too large");
        }
        validFileSuffix(upload, file);
    }

}
