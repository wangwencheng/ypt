package com.wwc.ypt.redis;

/**
 * @author 王文城 wangwencheng
 * @Title:TODO 类描述
 * @Description:TODO 详细描述
 * @Copyright: 2014-现在 厦门神州鹰掌通家园项目组
 * @date: 2018/6/19 10:13
 */
public class XLockResponse<T> {
    private final boolean status;
    private final T data;

    public XLockResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public XLockResponse(boolean status) {
        this(status, null);
    }

    public T get() {
        return data;
    }

    public boolean status() {
        return status;
    }
}