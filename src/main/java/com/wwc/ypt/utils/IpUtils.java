package com.wwc.ypt.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 描述： 获取客户端 IP 地址
 */
public class IpUtils implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -32053458691256359L;

    /**
     * 获取 IP 地址
     *
     * @param request 客户端请求对象
     * @return 返回客户端 IP 地址
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String sip = request.getHeader("x-forwarded-for");
        if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
            sip = request.getHeader("Proxy-Client-IP");
        }
        if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
            sip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (sip == null || sip.length() == 0 || "unknown".equalsIgnoreCase(sip)) {
            sip = request.getRemoteAddr();
        }
        return sip;
    }

    /**
     * 获取ip
     *
     * @return
     */
    public static String getSessionIp() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Object ip = request.getParameter("IP");
        if (ip == null) {
            ip = IpUtils.getRemoteIP(request);
        }
        if (ip != null) {
            String loginIps[] = String.valueOf(ip).split(",");
            if (loginIps.length > 1) {
                ip = loginIps[1];
            } else {
                ip = loginIps[0];
            }
        }
        return String.valueOf(ip).trim();
    }

}