package com.example.ypt.web.request;

import lombok.Data;

@Data
public class GoodsInfoRequest {
    private Long adZoneId;
    private Long platForm;
    private String cat;
    private Long pageSize;
    private String q;
    private Long pageNo;
}
