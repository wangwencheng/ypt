package com.wwc.ypt.web.controller;


import com.taobao.api.ApiException;
import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.utils.YptClient;
import com.wwc.ypt.utils.config.AppConfig;
import com.wwc.ypt.web.request.TbkItemGuessLikeRequest;
import com.wwc.ypt.web.response.TbkItemGuessLikeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("guess")
public class GuessController {
    @Autowired
    YptClient yptClient;
    @Autowired
    AppConfig appConfig;
    @RequestMapping("like")
    public BaseResponse like()  {
        try {
            TbkItemGuessLikeRequest req = new TbkItemGuessLikeRequest();
            req.setAdzoneId(appConfig.getAdZoneId());
            req.setUserNick("abc");
            req.setUserId(123456L);
            req.setOs("ios");
            req.setIdfa("65A509BA-227C-49AC-91EC-DE6817E63B10");
            req.setImei("641221321098757");
            req.setImeiMd5("115d1f360c48b490c3f02fc3e7111111");
            req.setIp("59.57.171.87");
            req.setUa("Mozilla/5.0");
            req.setApnm("com.xxx");
            req.setNet("wifi");
            req.setMn("iPhone7%2C2");
            req.setPageNo(1L);
            req.setPageSize(20L);
            TbkItemGuessLikeResponse rsp=yptClient.getClient().execute(req);
            return CollectionUtils.isEmpty(rsp.getResults()) ? BaseResponse.error("调用猜你喜欢API返回错误异常"): BaseResponse.success(rsp.getResults());
        }catch (ApiException e){
            log.error("调用猜你喜欢API出错",e);
            throw new YPTException("调用猜你喜欢API出错");
        }
    }
}
