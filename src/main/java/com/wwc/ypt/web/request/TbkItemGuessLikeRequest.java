package com.wwc.ypt.web.request;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.response.TbkDgItemCouponGetResponse;
import lombok.Data;

import java.util.Map;

@Data
public class TbkItemGuessLikeRequest extends BaseTaobaoRequest<TbkDgItemCouponGetResponse> {
    private Long adzoneId;
    private String userNick;
    private Long userId;
    private String os;
    private String idfa;
    private String imei;
    private String imeiMd5;
    private String ip;
    private String ua;
    private String apnm;
    private String net;
    private String mn;
    private Long pageNo;
    private Long pageSize;












    @Override
    public Map<String, String> getTextParams() {
        return null;
    }

    @Override
    public Class<TbkDgItemCouponGetResponse> getResponseClass() {
        return null;
    }

    @Override
    public void check() throws ApiRuleException {

    }
}
