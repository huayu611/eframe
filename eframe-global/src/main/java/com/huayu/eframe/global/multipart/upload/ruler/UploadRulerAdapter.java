package com.huayu.eframe.global.multipart.upload.ruler;

import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/3/30.
 */
@Service
public class UploadRulerAdapter implements UploadRuler
{
    @Override
    public String getUploadType()
    {
        return "default";
    }
}
