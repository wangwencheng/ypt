package com.wwc.ypt.utils.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CategoryConfig {
    @Value("${category.catIds}")
    private String catIds;
    @Value("${category.catNames}")
    private String catNames;


}
