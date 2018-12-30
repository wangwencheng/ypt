package com.wwc.ypt.service;

import com.wwc.ypt.dao.FeedBackDAO;
import com.wwc.ypt.web.request.FeedBackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService {
    @Autowired
    FeedBackDAO feedBackDAO;

    public void create(FeedBackRequest feedBackRequest) {
        feedBackDAO.create(feedBackRequest);
    }
}
