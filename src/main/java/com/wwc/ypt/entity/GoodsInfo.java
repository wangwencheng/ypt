package com.wwc.ypt.entity;

import com.wwc.ypt.util.JSONMapper;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class GoodsInfo{
    private Long id;
    @NonNull
    private String name;
    private String description;


    public static void main(String[] args) {
        GoodsInfo goodsInfo= GoodsInfo.builder().id(1L).name("王文城").description("测试建造者模式").build();
        System.out.println(JSONMapper.json(goodsInfo));
    }
}