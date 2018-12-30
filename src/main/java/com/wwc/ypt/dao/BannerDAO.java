package com.wwc.ypt.dao;

import com.wwc.ypt.entity.Banner;
import com.wwc.ypt.jpa.persistence.JPAAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDAO {
    @Autowired
    JPAAccess jpaAccess;

    public List<Banner> get() {
        return jpaAccess.find("from Banner where 1=1 order by create_time desc");
    }
}
