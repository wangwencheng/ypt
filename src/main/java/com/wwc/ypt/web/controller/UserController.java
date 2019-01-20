package com.wwc.ypt.web.controller;


import com.wwc.ypt.annotation.IgnoreLogin;
import com.wwc.ypt.entity.User;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.redis.RedisAccess;
import com.wwc.ypt.service.UserService;
import com.wwc.ypt.utils.CopyUtils;
import com.wwc.ypt.utils.JSONMapper;
import com.wwc.ypt.web.base.BaseResponse;
import com.wwc.ypt.web.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisAccess redisAccess;

    @IgnoreLogin
    @RequestMapping("register")
    public BaseResponse register(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        userService.register(userRequest, request);
        return BaseResponse.success("注册成功");
    }

    @RequestMapping("login")
    @ResponseBody
    public BaseResponse login(@RequestBody UserRequest userRequest, HttpSession session) {
        User user = userService.login(userRequest);
        if (Objects.isNull(user)) {
            throw new YPTException("用户名不存在");
        }
        if (!userRequest.getPassword().equals(user.getPassword())) {
            throw new YPTException("用户密码错误请重新输入");
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("user", user);
        map.put("token", session.getId());
        redisAccess.set(session.getId(), JSONMapper.json(user));
        return BaseResponse.success(map);
    }

    @IgnoreLogin
    @RequestMapping("modifyPassword")
    public BaseResponse modifyPassword(@RequestBody UserRequest userRequest) {
        userService.modifyPassword(userRequest);
        return BaseResponse.success("");
    }

    @RequestMapping("modifyAvatar")
    public BaseResponse modifyAvatar(@RequestBody UserRequest userRequest, HttpSession session) {
        User user = getUserCache(session);
        userRequest.setUserId(user.getUserId());
        userService.modifyAvatar(userRequest);
        return BaseResponse.success("修改用户头像成功");
    }

    @RequestMapping("modifyUserInfo")
    public BaseResponse modifyUserInfo(@RequestBody UserRequest userRequest, HttpSession session) {
        User user = getUserCache(session);
        CopyUtils.copyProperties(userRequest, user);
        userService.modifyUserInfo(user);
        return BaseResponse.success("修改用户个人信息成功");
    }

    private User getUserCache(HttpSession session) {
        String userString = redisAccess.execute(jedis -> jedis.get(session.getId()));
        return JSONMapper.binding(userString, User.class);
    }
}
