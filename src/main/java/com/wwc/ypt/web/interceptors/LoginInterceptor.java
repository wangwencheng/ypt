package com.wwc.ypt.web.interceptors;

import com.google.common.base.Strings;
import com.wwc.ypt.annotation.IgnoreLogin;
import com.wwc.ypt.exception.SessionException;
import com.wwc.ypt.redis.RedisAccess;
import com.wwc.ypt.utils.AnnotationUtils;
import com.wwc.ypt.utils.constants.LoginEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RedisAccess redisAccess;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
       if(handler instanceof HandlerMethod && AnnotationUtils.isPresentTypeOrMethod((HandlerMethod) handler, IgnoreLogin.class)){
           return true;
       }
        String token = request.getHeader(LoginEnum.LoginToken.getToken());
        if (!Strings.isNullOrEmpty(token)) {
            String userString = redisAccess.execute(jedis -> jedis.get(token));
            if (!Strings.isNullOrEmpty(userString)) {
                return true;
            }
            throw new SessionException("传入的token非法，请重新登陆");
        }
        throw new SessionException("session登陆异常");
    }
}
