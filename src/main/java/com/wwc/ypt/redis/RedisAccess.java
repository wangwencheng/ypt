package com.wwc.ypt.redis;

import com.wwc.ypt.utils.JSONMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author 王文城 wangwencheng
 * @Title:TODO 类描述
 * @Description:TODO 详细描述
 * @Copyright: 2014-现在 厦门神州鹰掌通家园项目组
 * @date: 2018/6/19 10:11
 */
public class RedisAccess {
    private final RedisSetting redisSetting;
    private final JedisPool jedisPool;

    private JedisPool init() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisSetting.getMaxIdle());
        config.setMaxTotal(redisSetting.getMaxTotal());
        config.setMaxWaitMillis(redisSetting.getMaxWaitMillis());
        return new JedisPool(config, redisSetting.getHost(), redisSetting.getPort(), redisSetting.getTimeout(), redisSetting.getPassword(), redisSetting.getDatabase());
    }

    public RedisAccess(JedisPool jedisPool) {
        this.redisSetting = null;
        this.jedisPool = jedisPool;
    }

    public RedisAccess(RedisSetting redisSetting) {
        this.redisSetting = redisSetting;
        this.jedisPool = init();
    }

    public <T> T execute(Function<Jedis, T> function) {
        try (Jedis jedis = jedisPool.getResource()) {
            return function.apply(jedis);
        }
    }

    public void accept(Consumer<Jedis> consumer) {
        try (Jedis jedis = jedisPool.getResource()) {
            consumer.accept(jedis);
        }
    }

    public Long expire(String key, Integer seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key, seconds);
        }
    }

    public Long expire(String key, Duration duration) {
        return expire(key, (int) duration.getSeconds());
    }

    public String set(String key, String value, Integer seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setex(key, seconds, value);
        }
    }

    public String set(String key, String value, Duration duration) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.setex(key, (int) duration.getSeconds(), value);
        }
    }

    public String set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        }
    }

    public <T> T get(String key, Class<T> clazz, Class... parametrizedType) {
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get(key);
            return getT(clazz, value, parametrizedType);
        }
    }

    private <T> T getT(Class<T> clazz, String value, Class... parametrizedType) {
        return Objects.nonNull(value) ? JSONMapper.bindingParametrized(value, clazz, parametrizedType) : null;
    }

    /**
     * 请用putIfAbsent 替代
     * 将会在0.0.7版本移除
     *
     * @param key
     * @param value
     * @return
     */
    @Deprecated
    public Long setnx(String key, String value) {
        return setnx(key, value, null);
    }

    public boolean putIfAbsent(String key, String value, Duration expire) {
        try (Jedis jedis = jedisPool.getResource()) {
            return "OK".equals(jedis.set(key, value, "NX", "PX", expire.toMillis()));
        }
    }

    public boolean putIfAbsent(String key, String value, Integer seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            return "OK".equals(jedis.set(key, value, "NX", "EX", seconds));
        }
    }

    /**
     * 请用putIfAbsent 替代
     * 将会在0.0.7版本移除
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @Deprecated
    public Long setnx(String key, String value, Integer seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long reply = jedis.setnx(key, value);
            if (null != seconds) {
                jedis.expire(key, seconds);
            }
            return reply;
        }
    }

    /**
     * 如果key 过期或者不存在 则返回false 且不操作
     *
     * @param key
     * @param expire
     * @return
     * @version 0.0.4
     */
    public Boolean expireThenIncrease(String key, Duration expire) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (Objects.equals(1L, jedis.pexpire(key, expire.toMillis()))) {
                jedis.incr(key);
                return true;
            }
        }
        return false;
    }

    public <T> T expireThenGet(Duration duration, String key, Class<T> clazz, Class... parametrizedType) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (Objects.equals(1L, jedis.pexpire(key, duration.toMillis()))) {
                return getT(clazz, jedis.get(key), parametrizedType);
            }
            return null;
        }
    }

    public Long del(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    public XLock getLock(String key) {
        return new RedisLock(key, jedisPool.getResource());
    }

    @PreDestroy
    public void destroy() {
        jedisPool.close();
    }
}
