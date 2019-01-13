package com.wwc.ypt.web.controller;


import com.wwc.ypt.base.BaseResponse;
import com.wwc.ypt.entity.User;
import com.wwc.ypt.exception.YPTException;
import com.wwc.ypt.service.UserService;
import com.wwc.ypt.web.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("register")
    public BaseResponse register(@RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        return BaseResponse.success("注册成功");
    }

    @RequestMapping("login")
    public BaseResponse login(@RequestBody UserRequest userRequest) {
        User user = userService.login(userRequest);
        if (Objects.isNull(user)) {
            throw new YPTException("用户名不存在");
        }
        if (!userRequest.getPassword().equals(user.getPassword())) {
            throw new YPTException("用户密码错误请重新输入");
        }
        return BaseResponse.success("登陆成功");
    }
}
