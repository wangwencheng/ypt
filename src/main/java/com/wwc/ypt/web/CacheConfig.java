package com.wwc.ypt.web;

import com.wwc.ypt.redis.RedisAccess;
import com.wwc.ypt.redis.RedisSetting;
import com.wwc.ypt.utils.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author qipeng.yan 2018/6/26
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    protected RedisAccess redisAccess() {
        return new RedisAccess(redisSetting());
    }

    @Bean
    protected RedisSetting redisSetting() {
        RedisSetting redisSetting = new RedisSetting();
        redisSetting.setHost(redisConfig.getHost());
        redisSetting.setPort(redisConfig.getPort());
        redisSetting.setPassword(redisConfig.getPassword());
        redisSetting.setDatabase(redisConfig.getDatabase());
        return redisSetting;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 设置 redisTemplate 序列化方式
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
