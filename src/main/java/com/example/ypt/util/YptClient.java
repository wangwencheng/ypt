package com.example.ypt.util;

import com.example.ypt.util.config.AppConfig;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YptClient {
    @Autowired
    private  AppConfig appConfig;

    public TaobaoClient getClient() {
        return new DefaultTaobaoClient(appConfig.getUrl(), appConfig.getAppKey(), appConfig.getAppSelect());
    }
}
