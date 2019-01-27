package com.huayu.eframe.feedback.atom;

import com.huayu.eframe.feedback.bo.FeedBackBO;
import com.huayu.eframe.server.common.FramePaging;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/11/24.
 */
public interface FeedBackAtom
{
    FeedBackBO insert(FeedBackBO backBO);

    List<FeedBackBO> queryAllFeedBack();

    Page<FeedBackBO> queryFeedBackByPage(FramePaging fp);

    Page<FeedBackBO> queryFeedBackByPageAndDateAndContent(FramePaging fp, Date start, Date end, String content);

    void deleteFeedBackById(Long id);
}
