package com.wwc.ypt.redis;

import lombok.Data;

/**
 * @author 王文城 wangwencheng
 * @Title:TODO 类描述
 * @Description:TODO 详细描述
 * @Copyright: 2014-现在 厦门神州鹰掌通家园项目组
 * @date: 2018/6/19 10:08
 */
@Data
public class RedisSetting {
    private String host;
    private Integer port;
    private String password;
    private int database = 0;
    private Integer timeout = 3000;
    private Integer maxTotal = 500;
    private Integer maxIdle = 100;
    private Long maxWaitMillis = 3000L;
}
