package com.wwc.ypt.utils.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {
    @Value("${youpintao.url}")
    private String url;
    @Value("${youpintao.appKey}")
    private String appKey;
    @Value("${youpintao.appSecret}")
    private String appSelect;
    @Value("${youpintao.adZoneId}")
    private Long adZoneId;
    @Value("${youpintao.siteId}")
    private Long siteId;
}
