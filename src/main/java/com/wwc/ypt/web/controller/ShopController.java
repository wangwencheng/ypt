package com.wwc.ypt.web.controller;

import com.wwc.ypt.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.util.YptClient;
import com.wwc.ypt.web.request.ShopSearchRequest;
import com.taobao.api.ApiException;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkShopGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RequestMapping("shop")
@RestController
public class ShopController {
    @Autowired
    YptClient yptClient;

    @RequestMapping("search")
    public BaseResponse search(@RequestBody ShopSearchRequest shopSearchRequest) {
        try {
            TbkShopGetRequest req = new TbkShopGetRequest();
            req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
            req.setQ(shopSearchRequest.getQ());
            req.setSort(shopSearchRequest.getSort());
            req.setIsTmall(Objects.isNull(shopSearchRequest.getIsTmall()) ? false : shopSearchRequest.getIsTmall());
            req.setStartCredit(shopSearchRequest.getStartCredit());
            req.setEndCredit(shopSearchRequest.getEndCredit());
            req.setStartCommissionRate(shopSearchRequest.getStartCommissionRate());
            req.setEndCommissionRate(shopSearchRequest.getEndCommissionRate());
            req.setStartTotalAction(shopSearchRequest.getStartTotalAction());
            req.setEndTotalAction(shopSearchRequest.getEndTotalAction());
            req.setStartAuctionCount(shopSearchRequest.getStartAuctionCount());
            req.setEndAuctionCount(shopSearchRequest.getEndAuctionCount());
            req.setPlatform(Objects.isNull(shopSearchRequest.getPlatform()) ? 2L : shopSearchRequest.getPlatform());
            req.setPageNo(Objects.isNull(shopSearchRequest.getPageNo())?1L:shopSearchRequest.getPageNo());
            req.setPageSize(Objects.isNull(shopSearchRequest.getPageSize())?20L:shopSearchRequest.getPageSize());
            TbkShopGetResponse rsp = yptClient.getClient().execute(req);
            return BaseResponse.success(rsp.getResults());
        }catch (ApiException e){
            log.error("调用店铺搜索API出错", e);
            throw new YPTException("调用店铺搜索API出错",e);
        }
    }
}
