package com.wwc.ypt.web.request;

import lombok.Data;

@Data
public class JuRequest {
    private Long pageNo;
    private Long pageSize;
    private String pid;
    private Boolean postage;
    private Long status;
    private Long categoryId;
    private String word;
}
