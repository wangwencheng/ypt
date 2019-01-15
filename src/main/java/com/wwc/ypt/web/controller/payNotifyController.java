package com.wwc.ypt.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("pay")
@Slf4j
public class payNotifyController {
    @RequestMapping("/alipay/notify/{payWayCode}")
     public String alipayNotify(@PathVariable("payWayCode")String payWayCode , HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) throws IOException {
        log.info("数据payWayCode是，{}",payWayCode);
        String encoding ="";
         String str = "fail";
         Map<String , String> notifyMap = new HashMap<>();
         Map<String, String[]> requestParams = httpServletRequest.getParameterMap();
         notifyMap = parseNotifyMsg(requestParams);
         // 1、签名
         if(notifyMap.size()>0) {
             // 异步通知业务操作
             str = "success";
             /* 回馈信息 */
             httpServletResponse.getWriter().print(str);
         }else {
             log.debug("签名失败返回结果：" + notifyMap);
         }
        return "gateway";
     }
    /**
     * @Description: 解析支付宝发来的请求
     * @author lc
     * @param requestParams
     * @param
     */
    private static Map<String , String> parseNotifyMsg(Map<String, String[]> requestParams){
        Map<String,String> params = new HashMap<String,String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
