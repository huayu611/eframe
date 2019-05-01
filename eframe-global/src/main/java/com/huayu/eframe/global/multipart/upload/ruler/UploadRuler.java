package com.huayu.eframe.global.multipart.upload.ruler;

/**
 * Created by Leo on 2019/3/30.
 */
public interface UploadRuler
{

    String getUploadType();


    default long getSize()
    {
        return 2 * 1024 * 1024;
    }


    default String[] getFileType()
    {
        return new String[]{".+?"};
    }


    default String downLoadPath()
    {
        return "/eframe/multipart-manager/download/";
    }

    ;
}
