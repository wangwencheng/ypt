package com.example.ypt.web.controller;


import com.example.ypt.dao.GoodsRepository;
import com.example.ypt.entity.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

//@RestController
public class GoodsController {

   /* @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("save")
    public String save() {
        GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
                "商品" + System.currentTimeMillis(), "这是一个测试商品");
        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("delete")
    public String delete(Long id) {
        goodsRepository.deleteById(id);
        return "success";
    }*/
}
