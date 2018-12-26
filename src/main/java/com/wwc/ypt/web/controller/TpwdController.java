package com.wwc.ypt.web.controller;

import com.taobao.api.ApiException;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import com.wwc.ypt.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.util.YptClient;
import com.wwc.ypt.util.config.CommonConfig;
import com.wwc.ypt.web.request.TpwdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("tpwd")
@Slf4j
public class TpwdController {
    @Autowired
    YptClient yptClient;
    @Autowired
    CommonConfig commonConfig;

    @RequestMapping("create")
    public BaseResponse create(@RequestBody TpwdRequest tpwdRequest) {
        try {
            TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
            req.setUserId("3406792668");
            req.setText("欢迎成为新的淘宝用户");
            req.setUrl(tpwdRequest.getUrl());
            req.setLogo(tpwdRequest.getLogo());
            req.setExt(tpwdRequest.getExt());
            TbkTpwdCreateResponse rsp = yptClient.getClient().execute(req);
            return Objects.isNull(rsp.getData()) ? BaseResponse.error("调用淘口令API返回错误异常") : BaseResponse.success(rsp.getData());
        } catch (ApiException e) {
            log.error("调用淘宝客淘口令API出错", e);
            throw new YPTException("调用淘宝客淘口令API出错", e);
        }
    }

    @RequestMapping("code")
    public BaseResponse getCode() {
        return BaseResponse.success("成功",commonConfig.getShareCode());
    }

}
