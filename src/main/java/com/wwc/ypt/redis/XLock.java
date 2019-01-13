package com.wwc.ypt.redis;

import java.io.Closeable;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * @author 王文城 wangwencheng
 * @Title:TODO 类描述
 * @Description:TODO 详细描述
 * @Copyright: 2014-现在 厦门神州鹰掌通家园项目组
 * @date: 2018/6/19 10:12
 */
public interface XLock extends Closeable {
    boolean lock(Duration lockTime);

    /**
     * @param lockTime 锁住时长
     * @param supplier 锁住后执行的提供者
     * @param <T>
     * @return 此方法不会unlock
     */
    <T> XLockResponse<T> lockAndThen(Duration lockTime, Supplier<T> supplier);

    /**
     * @param lockTime  锁住时长
     * @param isRelease true-unlock,false-不会执行unlock
     * @param supplier  锁住后执行的提供者
     * @param <T>
     * @return
     */
    <T> XLockResponse<T> lockAndThen(Duration lockTime, boolean isRelease, Supplier<T> supplier);

    void unlock();

    @Override
    void close();
}