//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wwc.ypt.jpa.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Iterable<T> {
    private Integer offset;
    private Integer fetchSize;
    private boolean isCount;
    private Long count;
    private List<T> result;
    private final List<Order> orders;

    public Page(Integer offset, Integer fetchSize, boolean isCount, Long count, List<T> result) {
        this.orders = new ArrayList();
        this.offset = offset;
        this.fetchSize = fetchSize;
        this.isCount = isCount;
        this.count = count;
        this.result = result;
    }

    public Page(Integer offset, Integer fetchSize, boolean isCount) {
        this(offset, fetchSize, isCount, (Long)null, Collections.emptyList());
    }

    public Page(Integer offset, Integer fetchSize) {
        this(offset, fetchSize, false);
    }

    public Page(Long count, List<T> result) {
        this((Integer)null, (Integer)null, true, count, result);
    }

    public Page(List<T> result) {
        this((Integer)null, (Integer)null, false, (Long)null, result);
    }

    public Page() {
        this((Integer)null, (Integer)null, false);
    }

    public Page<T> order(String column, OrderEnum order) {
        this.orders.add(new Order(column, order));
        return this;
    }

    public Page<T> offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Page<T> fetchSize(Integer fetchSize) {
        this.fetchSize = fetchSize;
        return this;
    }

    public Page<T> isCount(boolean isCount) {
        this.isCount = isCount;
        return this;
    }

    public Page<T> count(Long count) {
        this.count = count;
        this.isCount = true;
        return this;
    }

    public Page<T> result(List<T> result) {
        this.result = result;
        return this;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public Integer getFetchSize() {
        return this.fetchSize;
    }

    public boolean isCount() {
        return this.isCount;
    }

    public List<T> getResult() {
        return this.result;
    }

    public Long getCount() {
        return this.count;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(this.orders);
    }

    public Iterator<T> iterator() {
        return this.result.iterator();
    }
}
