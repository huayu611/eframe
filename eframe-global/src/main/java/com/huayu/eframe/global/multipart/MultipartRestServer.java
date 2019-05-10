package com.huayu.eframe.global.multipart;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.multipart.download.DownloadBusiness;
import com.huayu.eframe.global.multipart.download.DownloadRequest;
import com.huayu.eframe.global.multipart.upload.UploadBusiness;
import com.huayu.eframe.global.multipart.upload.UploadMultipleBusiness;
import com.huayu.eframe.global.multipart.upload.UploadMultipleRequest;
import com.huayu.eframe.global.multipart.upload.UploadRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public Object uploadTemp(MultipartFile file, @PathVariable("type") String type,
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
    @RequestMapping(value = "/upload-multiple/{type}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object uploadMultipleFile(List<MultipartFile> file, @PathVariable("type") String type,
                                     EasyParam easyParam)
    {
        UploadMultipleRequest uploadMultipleRequest = new UploadMultipleRequest();
        uploadMultipleRequest.setMultipartFiles(file);
        uploadMultipleRequest.setTemp(true);
        uploadMultipleRequest.setType(type);
        Object obj = Flow.execute(UploadMultipleBusiness.class, uploadMultipleRequest, easyParam);
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

        uploadRequest.setTemp(StringUtils.equalString(temp, "true"));
        Object obj = Flow.execute(UploadBusiness.class, uploadRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/upload-multiple/{type}/temp",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object uploadMultipleAdvance(List<MultipartFile> files,
                                @PathVariable("type") String type,
                                @PathVariable("temp") String temp,
                                EasyParam easyParam)
    {
        UploadMultipleRequest uploadMultipleRequest = new UploadMultipleRequest();
        uploadMultipleRequest.setMultipartFiles(files);
        uploadMultipleRequest.setType(type);

        uploadMultipleRequest.setTemp(StringUtils.equalString(temp, "true"));
        Object obj = Flow.execute(UploadMultipleBusiness.class, uploadMultipleRequest, easyParam);
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
    }


}
