package com.wwc.ypt.redis;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * 通过Lock("参数key")获取一个redis实现的分布式lock对象
 * 通过lock方法 获取锁，如果true 则获取成功 false 失败，入参为过期时间
 * eg
 * try (RLock lock = redisAccess.getLock("key")) {  通过try catch resource方式 关闭资源  等同于最后调用 close方法
 * if (lock.lock(111, TimeUnit.SECONDS)) {
 * 获取锁成功执行代码处
 * }
 * }
 * unlock释放锁
 * lockWithAction 为拓展函数 用于支持 通过lambada表达式 实现如果获取锁成功则执行第三参数逻辑且返回结果
 * <p>
 * eg
 * RLockResponse<String> stringRLockResponse = lock.lockWithAction(10, TimeUnit.SECONDS, this::ifLockGoAction);
 * * 需要手动调用close关闭资源 建议使用try catch resource方式
 * <p>
 * Created by Nick Guo on 2017/9/29.
 */
public final class RedisLock implements XLock {
    private final String key;
    private final Jedis jedis;

    RedisLock(String key, Jedis jedis) {
        this.key = key;
        this.jedis = jedis;
    }

    @Override
    public void close() {
        jedis.close();
    }

    @Override
    public boolean lock(Duration lockTime) {
        return "OK".equals(jedis.set(key, "1", "NX", "PX", lockTime.toMillis()));
    }

    public <T> XLockResponse<T> lockAndThen(Duration lockTime, Supplier<T> supplier) {
        return this.lockAndThen(lockTime, false, supplier);
    }

    @Override
    public <T> XLockResponse<T> lockAndThen(Duration lockTime, boolean isRelease, Supplier<T> supplier) {
        boolean lock = lock(lockTime);
        try {
            if (lock) {
                return new XLockResponse<>(true, supplier.get());
            } else {
                return new XLockResponse<>(false);
            }
        } finally {
            if (isRelease && lock)
                unlock();
        }

    }

    @Override
    public void unlock() {
        jedis.del(key);
    }
}
