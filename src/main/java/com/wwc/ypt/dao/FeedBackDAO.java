package com.wwc.ypt.dao;

import com.wwc.ypt.entity.FeedBack;
import com.wwc.ypt.jpa.persistence.JPAAccess;
import com.wwc.ypt.web.request.FeedBackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
public class FeedBackDAO {
    @Autowired
    JPAAccess jpaAccess;

    @Transactional(rollbackFor = Exception.class)
    public void create(FeedBackRequest feedBackRequest) {
        FeedBack feedBack = new FeedBack();
        feedBack.setUserId(UUID.randomUUID().toString());
        feedBack.setCreateTime(new Date());
        feedBack.setPhone(feedBackRequest.getPhone());
        feedBack.setFeedbackContent(feedBackRequest.getFeedbackContent());
        feedBack.setUpdateTime(new Date());
        jpaAccess.save(feedBack);
    }
}
