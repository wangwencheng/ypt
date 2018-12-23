package com.wwc.ypt.web.request;

import lombok.Data;

@Data
public class RecommendRequest {
    private Long count;
    private String fields;
    private Long platform;
    private Long userId;
}
