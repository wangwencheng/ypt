package com.wwc.ypt.dao;

import com.wwc.ypt.entity.Banner;
import com.wwc.ypt.jpa.persistence.JPAAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BannerDAO {
    @Autowired
    JPAAccess jpaAccess;
    public Banner get() {
      return   jpaAccess.get("1",Banner.class);
    }
}
