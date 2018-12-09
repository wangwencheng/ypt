package com.example.ypt.web.controller;

import com.example.ypt.base.BaseResponse;
import com.example.ypt.util.config.CategoryConfig;
import com.example.ypt.web.response.CategoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryConfig categoryConfig;

    @RequestMapping("info")
    public BaseResponse getCategory() {
        try {
            String[] catIds = categoryConfig.getCatIds().split(",");
            String[] catNames = categoryConfig.getCatNames().split(",");
            List<CategoryResponse> categoryList = new ArrayList<>(11);
            CategoryResponse categoryResponse=null;
            for(int i=0;i<catIds.length;i++){
                categoryResponse=new CategoryResponse();
                categoryResponse.setCatId(Long.parseLong(catIds[i]));
                categoryResponse.setCatName(catNames[i]);
                categoryList.add(categoryResponse);
            }
            return BaseResponse.success(categoryList);
        } catch (Exception e) {
            log.error("获取商品类目出错 ", e);
            return BaseResponse.success("获取商品类目出错");
        }
    }
}
