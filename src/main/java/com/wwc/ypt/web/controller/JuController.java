package com.wwc.ypt.web.controller;

import com.taobao.api.ApiException;
import com.taobao.api.request.JuItemsSearchRequest;
import com.taobao.api.response.JuItemsSearchResponse;
import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.utils.constants.JuStatusEnum;
import com.wwc.ypt.utils.YptClient;
import com.wwc.ypt.web.request.JuRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("ju")
public class JuController {
    @Autowired
    YptClient yptClient;

    @RequestMapping("search")
    public BaseResponse JuSearch(@RequestBody JuRequest juRequest) {
        try {
            JuItemsSearchRequest req = new JuItemsSearchRequest();
            JuItemsSearchRequest.TopItemQuery obj1 = new JuItemsSearchRequest.TopItemQuery();
            obj1.setCurrentPage(juRequest.getPageNo());
            obj1.setPageSize(juRequest.getPageSize());
            obj1.setPid(juRequest.getPid());
            obj1.setPostage(Objects.isNull(juRequest.getPostage()) ? true : juRequest.getPostage());
            obj1.setStatus(Objects.isNull(juRequest.getStatus()) ? JuStatusEnum.hold.getStatus() : juRequest.getStatus());
            obj1.setTaobaoCategoryId(juRequest.getCategoryId());
            obj1.setWord(juRequest.getWord());
            req.setParamTopItemQuery(obj1);
            JuItemsSearchResponse rsp = yptClient.getClient().execute(req);
            return  Objects.isNull(rsp.getResult()) ? BaseResponse.error("调用聚划算商品API返回错误异常") : BaseResponse.success(rsp.getResult());
        } catch (ApiException e) {
            log.error("调用聚划算商品API出错", e);
            throw new YPTException("调用聚划算商品API出错", e);
        }
    }
}
