package com.wwc.ypt.web.request;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.internal.util.RequestCheckUtils;
import com.taobao.api.internal.util.TaobaoHashMap;
import com.wwc.ypt.web.response.TbkItemGuessLikeResponse;
import lombok.Data;

import java.util.Map;

@Data
public class TbkItemGuessLikeRequest extends BaseTaobaoRequest<TbkItemGuessLikeResponse> {
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
    public String getApiMethodName() {
        return "taobao.tbk.item.guess.like";
    }

    @Override
    public Map<String, String> getTextParams() {
        TaobaoHashMap txtParams = new TaobaoHashMap();
        txtParams.put("adzone_id", this.adzoneId);
        txtParams.put("user_nick", this.userNick);
        txtParams.put("user_id", this.userId);
        txtParams.put("os", this.os);
        txtParams.put("idfa", this.idfa);
        txtParams.put("imei", this.imei);
        txtParams.put("imei_md5", this.imeiMd5);
        txtParams.put("ip", this.ip);
        txtParams.put("ua", this.ua);
        txtParams.put("apnm", this.apnm);
        txtParams.put("mn", this.mn);
        txtParams.put("net", this.net);
        txtParams.put("page_no", this.pageNo);
        txtParams.put("page_size", this.pageSize);
        if (this.udfParams != null) {
            txtParams.putAll(this.udfParams);
        }
        return txtParams;
    }

    @Override
    public Class<TbkItemGuessLikeResponse> getResponseClass() {
        return TbkItemGuessLikeResponse.class;
    }

    @Override
    public void check() throws ApiRuleException {
        RequestCheckUtils.checkNotEmpty(this.adzoneId, "adzoneId");
    }
}
