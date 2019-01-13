package com.wwc.ypt.utils.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MYJConfig {
    @Value("${myj.url}")
    private String url;
    @Value("${myj.apkey}")
    private String apkey;
    @Value("${myj.pid}")
    private String pid;
    @Value("${myj.tbname}")
    private String tbname;
}
