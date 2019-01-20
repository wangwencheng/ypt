package com.wwc.ypt.dao;

import com.wwc.ypt.entity.User;
import com.wwc.ypt.jpa.persistence.JPAAccess;
import com.wwc.ypt.jpa.persistence.QueryBuilder;
import com.wwc.ypt.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {
    @Autowired
    JPAAccess jpaAccess;

    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        jpaAccess.save(user);
    }

    public User login(UserRequest userRequest) {
        String hql = "from User where phone=:phone";
        QueryBuilder query = QueryBuilder.create(hql).param("phone", userRequest.getPhone());
        return jpaAccess.findOne(query);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyAvatar(UserRequest userRequest) {
        String hql = "update User set userAvatar=:userAvatar where userId=:userId";
        QueryBuilder query = QueryBuilder.create(hql).param("userAvatar", userRequest.getUserAvatar()).param("userId",userRequest.getUserId());
        jpaAccess.update(query);
    }

    @Transactional(rollbackFor = Exception.class)
    public void modifyUserInfo(User user) {
        jpaAccess.update(user);
    }
}
