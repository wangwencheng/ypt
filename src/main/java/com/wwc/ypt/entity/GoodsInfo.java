package com.wwc.ypt.entity;

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

}