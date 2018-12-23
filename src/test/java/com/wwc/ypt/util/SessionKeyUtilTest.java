package com.wwc.ypt.util;

import com.wwc.ypt.util.config.CommonConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionKeyUtilTest {
    @Autowired
    CommonConfig commonConfig;
    @Test
    public  void getAuthCode() {
        RestTemplate restTemplate=new RestTemplate();
        String authCodeUrl = commonConfig.getAuthCodeUrl() + "?appkey=" + 25256525;
        System.out.println(restTemplate.getForEntity(authCodeUrl, String.class).getBody());
    }
}