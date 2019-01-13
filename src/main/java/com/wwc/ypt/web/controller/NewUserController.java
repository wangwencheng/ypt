package com.wwc.ypt.web.controller;

import com.taobao.api.ApiException;
import com.taobao.api.request.TbkDgNewuserOrderSumRequest;
import com.taobao.api.request.TbkScNewuserOrderSumRequest;
import com.taobao.api.response.TbkDgNewuserOrderSumResponse;
import com.taobao.api.response.TbkScNewuserOrderSumResponse;
import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.utils.YptClient;
import com.wwc.ypt.utils.config.AppConfig;
import com.wwc.ypt.utils.config.CommonConfig;
import com.wwc.ypt.web.request.NewUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("newUser")
public class NewUserController {
    @Autowired
    YptClient yptClient;
    @Autowired
    AppConfig appConfig;
    @Autowired
    CommonConfig commonConfig;

    @RequestMapping("commerce")
    public BaseResponse commerce(@RequestBody NewUserRequest userRequest) {
        try {
            TbkScNewuserOrderSumRequest req = new TbkScNewuserOrderSumRequest();
            req.setPageSize(Objects.isNull(userRequest.getPageSize()) ? 20L : userRequest.getPageSize());
            req.setAdzoneId(Objects.isNull(userRequest.getAdZoneId()) ? appConfig.getAdZoneId() : userRequest.getAdZoneId());
            req.setPageNo(Objects.isNull(userRequest.getPageNo()) ? 1L : userRequest.getPageNo());
            req.setSiteId(Objects.isNull(userRequest.getSiteId()) ? appConfig.getSiteId() : userRequest.getSiteId());
            req.setActivityId(Objects.isNull(userRequest.getActivityId()) ? commonConfig.getActivityId() : userRequest.getActivityId());
            req.setSettleMonth(Objects.isNull(userRequest.getSettleMonth()) ? commonConfig.getSettleMonth() : userRequest.getSettleMonth());
            TbkScNewuserOrderSumResponse rsp = yptClient.getClient().execute(req, "61005138ef839fb9860ff179afeae6a7e7adc8e84728ba53406792668");
            return BaseResponse.success(rsp.getResults());
        } catch (ApiException e) {
            log.error("调用拉新社交API出错", e);
            return BaseResponse.error("调用拉新导购API出错");
        }
    }

    @RequestMapping("guide")
    public BaseResponse guide(@RequestBody NewUserRequest userRequest) {
        try {
            TbkDgNewuserOrderSumRequest req = new TbkDgNewuserOrderSumRequest();
            req.setPageSize(Objects.isNull(userRequest.getPageSize()) ? 20L : userRequest.getPageSize());
            req.setAdzoneId(Objects.isNull(userRequest.getAdZoneId()) ? appConfig.getAdZoneId() : userRequest.getAdZoneId());
            req.setPageNo(Objects.isNull(userRequest.getPageNo()) ? 1L : userRequest.getPageNo());
            req.setSiteId(Objects.isNull(userRequest.getSiteId()) ? appConfig.getSiteId() : userRequest.getSiteId());
            req.setActivityId(Objects.isNull(userRequest.getActivityId()) ? commonConfig.getActivityId() : userRequest.getActivityId());
            req.setSettleMonth(Objects.isNull(userRequest.getSettleMonth()) ? commonConfig.getSettleMonth() : userRequest.getSettleMonth());
            TbkDgNewuserOrderSumResponse rsp = yptClient.getClient().execute(req);
            return Objects.isNull(rsp.getResults()) ? BaseResponse.error("调用拉新导购API返回错误异常"): BaseResponse.success(rsp.getResults());
        } catch (ApiException e) {
            log.error("调用拉新导购API出错", e);
            throw new YPTException("调用拉新导购API出错",e);
        }
    }
}
