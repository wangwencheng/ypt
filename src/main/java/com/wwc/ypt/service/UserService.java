package com.wwc.ypt.service;

import com.wwc.ypt.dao.UserDAO;
import com.wwc.ypt.entity.User;
import com.wwc.ypt.util.IpUtils;
import com.wwc.ypt.web.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void register(UserRequest userRequest) {
        User user=new User();
        BeanUtils.copyProperties(userRequest,user);
        user.setLastLoginIp(IpUtils.getSessionIp());
        user.setPassword(user.getPassword());
        user.setUserBirthday(new Date());
        userDAO.register(user);
    }

    public User login(UserRequest userRequest) {
       return userDAO.login(userRequest);
    }
}
