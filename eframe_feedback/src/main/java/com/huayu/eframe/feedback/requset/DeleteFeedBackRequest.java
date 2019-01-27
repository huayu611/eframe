package com.huayu.eframe.feedback.requset;

/**
 * Created by Leo on 2018/12/2.
 */
public class DeleteFeedBackRequest
{
    private Long id;

    private String ids;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }
}
