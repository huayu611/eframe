package com.huayu.eframe.server.global.restful;

import com.huayu.eframe.server.common.ConfigurationUtils;
import com.huayu.eframe.server.flow.Flow;
import com.huayu.eframe.server.global.restful.current.QueryCurrentLoginBusiness;
import com.huayu.eframe.server.global.restful.upload.UploadBusiness;
import com.huayu.eframe.server.global.restful.upload.UploadRequest;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
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
@RequestMapping(value = "/global")
public class GlobalRestServer
{

    private static final LogDebug debug = new LogDebug(GlobalRestServer.class);
    @ResponseBody
    @RequestMapping(value = "/upload/{type}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object upload(MultipartFile file,@PathVariable("type") String type,
                         EasyParam easyParam)
    {
        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setMultipartFile(file);
        uploadRequest.setType(type);
        Object obj = Flow.execute(UploadBusiness.class, uploadRequest, easyParam);
        return obj;
    }

    //文件下载相关代码
    @RequestMapping(value = "/download/{type}/{fileName:.+}*", method = RequestMethod.GET)
    public String downloadImage(@PathVariable("type") String type,
                                @PathVariable("fileName") String fileName,
                                EasyParam easyParam)
    {
        String fileNameInSystem = type + "\\" + fileName;
        File file = new File(ConfigurationUtils.getDefaultUploadFilePath() + "\\" + fileNameInSystem);
        debug.log(file.exists());
        debug.log(file.getAbsolutePath());
        if (file.exists())
        {
            easyParam.getResponse().addHeader("Content-Disposition",
                    "attachment;fileName="+fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try
            {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = easyParam.getResponse().getOutputStream();
                int i = bis.read(buffer);
                while (i != -1)
                {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (bis != null)
                {
                    try
                    {
                        bis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/current/login",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object getCurrentLogin(EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryCurrentLoginBusiness.class, null, easyParam);
        return obj;
    }
}
