package com.huayu.eframe.feedback.repository;

import com.huayu.eframe.feedback.bo.FeedBackBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Leo on 2018/11/24.
 */
public interface FeedBackRepository extends JpaRepository<FeedBackBO,Long>, JpaSpecificationExecutor<FeedBackBO>
{
}
