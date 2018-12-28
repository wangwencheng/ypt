package com.wwc.ypt.service;

import com.wwc.ypt.dao.BannerDAO;
import com.wwc.ypt.entity.Banner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BannerService {
    @Autowired
    BannerDAO bannerDAO;

    public Banner get() {
       return bannerDAO.get();
    }
}
