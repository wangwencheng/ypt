package com.wwc.ypt.web.controller;

import com.taobao.api.ApiException;
import com.taobao.api.request.TbkDgItemCouponGetRequest;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import com.taobao.api.response.TbkItemGetResponse;
import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.utils.JSONMapper;
import com.wwc.ypt.utils.YptClient;
import com.wwc.ypt.utils.config.AppConfig;
import com.wwc.ypt.utils.config.MYJConfig;
import com.wwc.ypt.web.request.GoodsInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("good")
public class GoodsInfoController {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    YptClient yptClient;
    @Autowired
    MYJConfig myjConfig;

    @RequestMapping("info")
    public BaseResponse getGoodsInfo(@RequestBody GoodsInfoRequest goodsInfoRequest) {
        log.info("请求参数为，{}", JSONMapper.json(goodsInfoRequest));
        try {
            TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
            req.setAdzoneId(Objects.isNull(goodsInfoRequest.getAdZoneId()) ? appConfig.getAdZoneId() : goodsInfoRequest.getAdZoneId());
            req.setPlatform(Objects.isNull(goodsInfoRequest.getPlatform()) ? 2L : goodsInfoRequest.getPlatform());
            req.setCat(goodsInfoRequest.getCat());
            req.setPageSize(Objects.isNull(goodsInfoRequest.getPageSize()) ? 20L : goodsInfoRequest.getPageSize());
            req.setQ(goodsInfoRequest.getQ());
            req.setPageNo(Objects.isNull(goodsInfoRequest.getPageNo()) ? 1L : goodsInfoRequest.getPageNo());
            TbkDgItemCouponGetResponse rsp = yptClient.getClient().execute(req);
            return CollectionUtils.isEmpty(rsp.getResults()) ? BaseResponse.error("调用好券商品API返回错误异常"):BaseResponse.success("信息返回成功", rsp.getResults());
        } catch (ApiException e) {
            log.error("调用好券商品API出错", e);
            throw new YPTException("调用好券商品API出错",e);
        }
    }

    @RequestMapping("search")
    public BaseResponse search(@RequestBody GoodsInfoRequest goodsInfoRequest) {
        log.info("请求参数为，{}", JSONMapper.json(goodsInfoRequest));
        try {
            TbkItemGetRequest req = new TbkItemGetRequest();
            req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,coupon_click_url,nick");
            req.setQ(goodsInfoRequest.getQ());
            req.setCat(goodsInfoRequest.getCat());
            req.setItemloc(goodsInfoRequest.getItemloc());
            req.setSort(goodsInfoRequest.getSort());
            req.setIsTmall(Objects.isNull(goodsInfoRequest.getIsTmall()) ? false : goodsInfoRequest.getIsTmall());
            req.setIsOverseas(Objects.isNull(goodsInfoRequest.getIsOverseas()) ? false : goodsInfoRequest.getIsOverseas());
            req.setStartPrice(goodsInfoRequest.getStartPrice());
            req.setEndPrice(goodsInfoRequest.getEndPrice());
            req.setStartTkRate(goodsInfoRequest.getStartTkRate());
            req.setEndTkRate(goodsInfoRequest.getEndTkRate());
            req.setPlatform(Objects.isNull(goodsInfoRequest.getPlatform()) ? 2L : goodsInfoRequest.getPlatform());
            req.setPageNo(goodsInfoRequest.getPageNo());
            req.setPageSize(goodsInfoRequest.getPageSize());
            TbkItemGetResponse rsp = yptClient.getClient().execute(req);
            return CollectionUtils.isEmpty(rsp.getResults()) ? BaseResponse.error("调用商品查询API返回错误异常"): BaseResponse.success(rsp.getResults());
        } catch (ApiException e) {
            log.error("调用淘宝客商品查询API出错 ", e);
            throw new YPTException("调用淘宝客商品查询API出错",e);
        }
    }

    @RequestMapping("QuanLink")
    public BaseResponse getQuanLink(@RequestBody GoodsInfoRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = myjConfig.getUrl() + "?apkey=" + myjConfig.getApkey() + "&pid=" + myjConfig.getPid() + "&tbname=" + myjConfig.getTbname() + "&itemid=" + request.getItemId();
            ResponseEntity<Map> map = restTemplate.getForEntity(url, Map.class);
            Map<String, Map> mapItem = map.getBody();
            log.info("数据结构是", JSONMapper.json(mapItem.get("result").get("data")));
            return  BaseResponse.success(mapItem.get("result").get("data"));
        } catch (Exception e) {
            log.error("调用喵有劵API出错", e);
            throw new YPTException("调用喵有劵API出错",e);
        }
    }
}
