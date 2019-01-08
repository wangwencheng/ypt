package com.wwc.ypt.service;

import com.wwc.ypt.dao.UserDAO;
import com.wwc.ypt.entity.User;
import com.wwc.ypt.util.IpUtils;
import com.wwc.ypt.util.MD5;
import com.wwc.ypt.web.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void register(UserRequest userRequest) {
        User user=new User();
        BeanUtils.copyProperties(userRequest,user);
        user.setLastLoginIp(IpUtils.getSessionIp());
        user.setPassword(MD5.toMD5(user.getPassword()));
        userDAO.register(user);
    }

    public User login(UserRequest userRequest) {
       return userDAO.login(userRequest);
    }
}
