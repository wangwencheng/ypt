package com.wwc.ypt.web.request;

import lombok.Data;

@Data
public class GoodsInfoRequest {
    private Long adZoneId;
    private Long platform;
    private String cat;
    private Long pageSize;
    private String q;
    private Long pageNo;


    private Long endPrice;
    private Long endTkRate;
    private String fields;
    private Boolean isOverseas;
    private Boolean isTmall;
    private String itemloc;
    private String sort;
    private Long startPrice;
    private Long startTkRate;
    private Long itemId;
}
