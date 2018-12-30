package com.wwc.ypt.web.controller;

import com.wwc.ypt.base.BaseResponse;
import com.wwc.ypt.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("banner")
@RestController
public class BannerController {
    @Autowired
    BannerService bannerService;

    @RequestMapping("info")
    public BaseResponse getBannerData() {
        return BaseResponse.success(bannerService.get());
    }
}
