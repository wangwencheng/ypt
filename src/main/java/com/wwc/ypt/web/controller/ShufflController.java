package com.wwc.ypt.web.controller;

import com.wwc.ypt.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RequestMapping("shuffl")
@RestController
public class ShufflController {
    @RequestMapping("info")
    public BaseResponse getShufflData(){
        return BaseResponse.success(Arrays.asList(1,2,3));
    }
}
