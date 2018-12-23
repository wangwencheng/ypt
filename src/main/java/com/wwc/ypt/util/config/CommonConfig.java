package com.wwc.ypt.util.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CommonConfig {
    @Value("${newuser.activityId}")
    private String activityId;
    @Value("${newuser.settleMonth}")
    private String settleMonth;
    @Value("${authCodeUrl}")
    private String authCodeUrl;
}
