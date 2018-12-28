package com.wwc.ypt.web;


import com.wwc.ypt.jpa.persistence.JPAAccess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wangwencheng
 * @Title: 持久层bean配置
 * @Description: 其他bean不要在这里配置
 * @Copyright: 2014-现在 厦门神州鹰掌通家园项目组
 * @date: 2018-06-13 14:01
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class PersistenceConfig {


    @Bean
    public JPAAccess jpaAccess() {
        return new JPAAccess();
    }

}
