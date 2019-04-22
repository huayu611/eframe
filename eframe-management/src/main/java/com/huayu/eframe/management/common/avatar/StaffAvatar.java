package com.huayu.eframe.management.common.avatar;

import com.huayu.eframe.global.multipart.upload.ruler.UploadRuler;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/4/22.
 */
@Service
public class StaffAvatar implements UploadRuler
{
    @Override
    public String getUploadType()
    {
        return "staff-avatar";
    }
}
