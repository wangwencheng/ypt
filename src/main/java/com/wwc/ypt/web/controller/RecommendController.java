package com.wwc.ypt.web.controller;

import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.utils.YptClient;
import com.wwc.ypt.web.request.RecommendRequest;
import com.taobao.api.ApiException;
import com.taobao.api.request.TbkShopRecommendGetRequest;
import com.taobao.api.response.TbkShopRecommendGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("recommend")
public class RecommendController {
    @Autowired
    YptClient yptClient;

    @RequestMapping("info")
    public BaseResponse get(@RequestBody RecommendRequest recommendRequest) {
        try {
            TbkShopRecommendGetRequest req = new TbkShopRecommendGetRequest();
            req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
            req.setUserId(recommendRequest.getUserId());
            req.setCount(recommendRequest.getCount());
            req.setPlatform(Objects.isNull(recommendRequest.getPlatform()) ? 2L : recommendRequest.getPlatform());
            TbkShopRecommendGetResponse rsp = yptClient.getClient().execute(req);
            return CollectionUtils.isEmpty(rsp.getResults()) ? BaseResponse.error("调用店铺关联查询API返回错误异常"): BaseResponse.success(rsp.getResults());
        } catch (ApiException e) {
            log.error("调用店铺关联推荐API出错", e);
            throw new YPTException("调用店铺关联推荐API出错",e);
        }
    }
}
