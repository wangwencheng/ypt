package com.example.ypt.web.controller;

import com.example.ypt.base.BaseResponse;
import com.example.ypt.util.JSONMapper;
import com.example.ypt.util.config.AppConfig;
import com.example.ypt.web.request.GoodsInfoRequest;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class GoodsInfoController {
    @Autowired
    private AppConfig appConfig;

    @RequestMapping("good/info")
    public BaseResponse getGoodsInfo(@RequestBody GoodsInfoRequest goodsInfoRequest) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(appConfig.getUrl(), appConfig.getAppKey(), appConfig.getAppSelect());
            TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
            req.setAdzoneId(Objects.isNull(goodsInfoRequest.getAdZoneId()) ? appConfig.getAdZoneId() : goodsInfoRequest.getAdZoneId());
            req.setPlatform(Objects.isNull(goodsInfoRequest.getPlatForm()) ? 2L : goodsInfoRequest.getPlatForm());
            req.setCat(goodsInfoRequest.getCat());
            req.setPageSize(goodsInfoRequest.getPageSize());
            req.setQ(goodsInfoRequest.getQ());
            req.setPageNo(goodsInfoRequest.getPageNo());
            TbkDgItemCouponGetResponse rsp = client.execute(req);
            rsp.getResults();
            log.info("返回的消息体为，{}", JSONMapper.json(rsp.getResults()));
            return BaseResponse.success("信息返回成功", rsp.getResults());
        } catch (ApiException e) {
            log.error("调用好券商品API出错", e);
            return BaseResponse.error("调用好券商品API出错");
        }
    }
}
