package com.example.ypt.web.controller;

import com.example.ypt.base.BaseResponse;
import com.example.ypt.util.YptClient;
import com.example.ypt.web.request.TpwdRequest;
import com.taobao.api.ApiException;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tpwd")
@Slf4j
public class TpwdController {
    @Autowired
    YptClient yptClient;

    @RequestMapping("create")
    public BaseResponse create(@RequestBody TpwdRequest tpwdRequest) {
        try {
            TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
            req.setUserId(tpwdRequest.getUserId());
            req.setText(tpwdRequest.getText());
            req.setUrl(tpwdRequest.getUrl());
            req.setLogo(tpwdRequest.getLogo());
            req.setExt(tpwdRequest.getExt());
            TbkTpwdCreateResponse rsp = yptClient.getClient().execute(req);
            return BaseResponse.success(rsp.getData());
        } catch (ApiException e) {
            log.error("调用淘宝客淘口令API出错", e);
            return BaseResponse.error("调用淘宝客淘口令API出错");
        }
    }
}
