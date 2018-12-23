package com.wwc.ypt.util;

import com.wwc.ypt.util.config.CommonConfig;
import com.taobao.api.internal.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SessionKeyUtil {
    @Autowired
    CommonConfig commonConfig;


    public String getAuthCode(String appKey) {
        String authCodeUrl = commonConfig.getAuthCodeUrl() + "?appkey" + appKey;
        return new RestTemplate().getForEntity(authCodeUrl, String.class).getBody();
    }


    public String getSessionKey(String authCode) {
        return "";
    }


    public static String getToken() throws IOException {
        String url = "";
        Map<String, String> param = new HashMap<String, String>();
        param.put("grant_type", "authorization_code");
       // param.put("code", "授权码");
        param.put("client_id", "25256525");
        param.put("client_secret", "1f439c0212f0e7d69525edeb82a1ba93");
        param.put("redirect_uri", "www.baidu.com");
        param.put("view", "web");
        param.put("state", "state");
        String responseJson = WebUtils.doPost(url, param, 3000, 3000);
        return responseJson;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
