package com.huayu.eframe.copinfo.common;

import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/30.
 */
@Service
public class CopLogoRuler implements UploadRuler
{
    @Override
    public String getUploadType()
    {
        return "logo";
    }
}
