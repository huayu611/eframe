package com.huayu.eframe.global.multipart;

import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Leo on 2019/4/14.
 */

public class MultipartResolve
{

    private final static LogDebug logDebug =  new LogDebug(MultipartResolve.class);

    public static void use(String fullPath, UploadRuler uploadRuler)
    {

        StringBuilder pathCut = new StringBuilder();
        pathCut.append(uploadRuler.downLoadPath()).append(uploadRuler.getUploadType()).append("/");
        String fileName = StringUtils.cutPrefix(fullPath, pathCut.toString());
        if (StringUtils.isNullOrEmpty(fileName))
        {
            return;
        }

        useByFileName(uploadRuler, fileName);
    }

    public static void useMore(List<String> fullPaths, UploadRuler uploadRuler)
    {
        CollectionUtils.iterator(fullPaths,(c,v,i)->{
            use(v,uploadRuler);
        });
    }

    public static void useByFileName(UploadRuler uploadRuler, String fileName)
    {
        String fileNameInSystem = uploadRuler.getUploadType() + File.separator + fileName;
        File file = new File(MultipartUtil.getSystemTempPath() + File.separator + fileNameInSystem);
        if (!file.exists())
        {
            return;
        }
        File folder = new File(MultipartUtil.getSystemPath() + File.separator + uploadRuler.getUploadType());
        if(!folder.exists())
        {
            folder.mkdir();
        }

        File newFile = new File(MultipartUtil.getSystemPath() + File.separator + fileNameInSystem);
        try
        {
            FileCopyUtils.copy(file, newFile);
        }
        catch (IOException e)
        {
            ;logDebug.errorLog(e);
        }
    }

    public static void useByFileNames(UploadRuler uploadRuler, List<String> fileNames)
    {
      CollectionUtils.iterator(fileNames,(c,v,i)->{
          useByFileName(uploadRuler,v);
      });
    }

}
