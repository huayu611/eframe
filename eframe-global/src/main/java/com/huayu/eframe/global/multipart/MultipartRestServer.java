package com.huayu.eframe.global.multipart;

import com.huayu.eframe.global.multipart.download.DownloadBusiness;
import com.huayu.eframe.global.multipart.download.DownloadRequest;
import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.system.currentlogin.QueryCurrentLoginBusiness;
import com.huayu.eframe.global.multipart.upload.UploadBusiness;
import com.huayu.eframe.global.multipart.upload.UploadRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Leo on 2019/1/18.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/multipart-manager")
public class MultipartRestServer
{
    @ResponseBody
    @RequestMapping(value = "/upload/{type}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object uploadTemp(MultipartFile file,@PathVariable("type") String type,
                         EasyParam easyParam)
    {
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setMultipartFile(file);
        uploadRequest.setTemp(true);
        uploadRequest.setType(type);
        Object obj = Flow.execute(UploadBusiness.class, uploadRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/upload/{type}/temp",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object uploadAdvance(MultipartFile file,
                                @PathVariable("type") String type,
                                @PathVariable("temp") String temp,
                         EasyParam easyParam)
    {
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setMultipartFile(file);
        uploadRequest.setType(type);

        uploadRequest.setTemp(StringUtils.equalString(temp,"true"));
        Object obj = Flow.execute(UploadBusiness.class, uploadRequest, easyParam);
        return obj;
    }

    //文件下载相关代码
    @RequestMapping(value = "/download/{type}/{fileName:.+}*", method = RequestMethod.GET)
    public String downloadImage(@PathVariable("type") String type,
                                @PathVariable("fileName") String fileName,
                                EasyParam easyParam)
    {
        DownloadRequest downloadRequest = new DownloadRequest();
        downloadRequest.setFileName(fileName);
        downloadRequest.setType(type);
        Flow.execute(DownloadBusiness.class, downloadRequest, easyParam);
        return null;
//        String fileNameInSystem = type + File.separator + fileName;
//        File file = new File(MultipartUtil.getSystemPath() + File.separator + fileNameInSystem);
//        debug.log(file.exists());
//        debug.log(file.getAbsolutePath());
//        if (file.exists())
//        {
//            easyParam.getResponse().addHeader("Content-Disposition",
//                    "attachment;fileName="+fileName);// 设置文件名
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null;
//            BufferedInputStream bis = null;
//            try
//            {
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                OutputStream os = easyParam.getResponse().getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1)
//                {
//                    os.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//                System.out.println("success");
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//            finally
//            {
//                if (bis != null)
//                {
//                    try
//                    {
//                        bis.close();
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//                if (fis != null)
//                {
//                    try
//                    {
//                        fis.close();
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return null;
    }


}
