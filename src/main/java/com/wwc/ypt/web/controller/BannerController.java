package com.wwc.ypt.web.controller;

import com.wwc.ypt.base.BaseResponse;
import com.wwc.ypt.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@Slf4j
public class BannerController {
    @Autowired
    BannerService bannerService;

    public BaseResponse get() {
        bannerService.get();
        log.info("数据是，{}", bannerService.get());
        return BaseResponse.success("");
    }
}
