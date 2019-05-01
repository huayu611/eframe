package com.huayu.eframe.global.multipart;

import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Leo on 2019/4/14.
 */

public class MultipartResolve
{
    public static void use(String fullPath, UploadRuler uploadRuler)
    {

        String downLoadPath = uploadRuler.downLoadPath();
        String fileName = StringUtils.cutPrefix(fullPath, downLoadPath);
        if (StringUtils.isNullOrEmpty(fileName))
        {
            return;
        }

        useByFileName(uploadRuler, fileName);
    }

    public static void useByFileName(UploadRuler uploadRuler, String fileName)
    {
        String fileNameInSystem = uploadRuler.getUploadType() + File.separator + fileName;
        File file = new File(MultipartUtil.getSystemTempPath() + File.separator + fileNameInSystem);
        if (!file.exists())
        {
            return;
        }
        File newFile = new File(MultipartUtil.getSystemPath() + File.separator + fileNameInSystem);
        try
        {
            FileCopyUtils.copy(file, newFile);
        }
        catch (IOException e)
        {
            ;
        }
    }

}
