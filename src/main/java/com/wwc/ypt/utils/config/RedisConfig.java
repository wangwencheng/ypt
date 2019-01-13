package com.wwc.ypt.utils.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wenchengwang 2018/6/26
 */
@Getter
@Component
public class RedisConfig {

    /**
     * host
     */
    @Value("${spring.redis.host}")
    private volatile String host;
    /**
     * port
     */
    @Value("${spring.redis.port}")
    private volatile Integer port;
    /**
     * password
     */
    @Value("${spring.redis.password}")
    private volatile String password;

    /**
     * db default 0
     */
    @Value("${spring.redis.database}")
    private volatile Integer database;
}
