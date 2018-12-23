package com.wwc.ypt.web.request;

import lombok.Data;

@Data
public class ShopSearchRequest {
    private Long endAuctionCount;
    private Long endCommissionRate;
    private Long endCredit;
    private Long endTotalAction;
    private String fields;
    private Boolean isTmall;
    private Long pageNo;
    private Long pageSize;
    private Long platform;
    private String q;
    private String sort;
    private Long startAuctionCount;
    private Long startCommissionRate;
    private Long startCredit;
    private Long startTotalAction;
}
