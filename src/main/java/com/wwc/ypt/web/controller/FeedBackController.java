package com.wwc.ypt.web.controller;

import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.service.FeedBackService;
import com.wwc.ypt.web.request.FeedBackRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("feedback")
@RestController
public class FeedBackController {
    @Autowired
    FeedBackService feedBackService;

    @RequestMapping("create")
    public BaseResponse create(@RequestBody FeedBackRequest feedBackRequest){
        feedBackService.create(feedBackRequest);
        return BaseResponse.success("感谢反馈，优U淘已经收到您的反馈");
    }
}
