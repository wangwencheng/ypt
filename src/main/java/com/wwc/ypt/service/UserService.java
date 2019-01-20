package com.wwc.ypt.service;

import com.wwc.ypt.dao.UserDAO;
import com.wwc.ypt.entity.User;
import com.wwc.ypt.utils.CopyUtils;
import com.wwc.ypt.utils.IpUtils;
import com.wwc.ypt.web.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void register(UserRequest userRequest, HttpServletRequest request) {
        User user=new User();
        CopyUtils.copyProperties(userRequest,user);
        user.setLastLoginIp(IpUtils.getRemoteIP(request));
        user.setPassword(user.getPassword());
        user.setUserBirthday(new Date());
        user.setUserSex(Objects.isNull(userRequest.getUserSex())?1:userRequest.getUserSex());
        userDAO.register(user);
    }

    public User login(UserRequest userRequest) {
       return userDAO.login(userRequest);
    }

    public void modifyPassword(UserRequest userRequest) {
    }

    public void modifyAvatar(UserRequest userRequest) {
        userDAO.modifyAvatar(userRequest);
    }

    public void modifyUserInfo(User user) {
        userDAO.modifyUserInfo(user);
    }
}
