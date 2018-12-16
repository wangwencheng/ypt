package com.example.ypt.web.request;

import lombok.Data;

@Data
public class NewUserRequest {
    private String activityId;
    private Long adZoneId;
    private Long pageNo;
    private Long pageSize;
    private String settleMonth;
    private Long siteId;
}
