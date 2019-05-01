package com.huayu.eframe.global.multipart.download;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.flow.common.FlowConstant;
import com.huayu.eframe.flow.presist.constant.LogConstants;
import com.huayu.eframe.global.multipart.MultipartUtil;
import com.huayu.eframe.server.common.restful.RestfulResponse;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Leo on 2019/4/14.
 */
@Service("com.huayu.eframe.global.multipart.download.DownloadBusiness")
public class DownloadBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(DownloadBusiness.class);

    @Override
    public void execute(BusinessParameter param)
    {
        DownloadRequest downloadRequest = param.getRequest();
        String fileNameInSystem = downloadRequest.getType() + File.separator + downloadRequest.getFileName();
        File file = new File(MultipartUtil.getSystemPath() + File.separator + fileNameInSystem);
        if (!file.exists())
        {
            file = new File(MultipartUtil.getSystemTempPath() + File.separator + fileNameInSystem);
        }
        debug.log(file.exists());
        debug.log(file.getAbsolutePath());

        EasyParam easyParam = LocalAttribute.getValue(FlowConstant.EASY_SERVLET);
        if (file.exists())
        {
            easyParam.getResponse().addHeader("Content-Disposition",
                    "attachment;fileName=" + downloadRequest.getFileName());// 设置文件名
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

    }

    public Object getResult(BusinessParameter param)
    {
        RestfulResponse response = new RestfulResponse();
        response.setCode("0");
        response.setMsg(getSuccessResultDescrption());
        response.setData(tidyData(param));
        tidyResponse(response, param);
        recordLog(LogConstants.LOG_FINISH_STATUS, response);
        return null;
    }
}
