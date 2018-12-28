package com.wwc.ypt.jpa.persistence;


import com.wwc.ypt.jpa.query.Order;
import com.wwc.ypt.jpa.query.OrderEnum;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by guocx on 2017/5/5.
 */
public final class QueryBuilder {
    private final String qlString;
    private Optional<Integer> offset = Optional.empty();
    private Optional<Integer> fetchSize = Optional.empty();
    private final Map<String, Object> params = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<String> groups = new ArrayList<>();

    /**
     * @author qipeng.yan
     */
    public QueryBuilder group(@NotNull String... columns) {
        groups.addAll(Arrays.asList(columns));
        return this;
    }

    public QueryBuilder order(String column, OrderEnum order) {
        orders.add(new Order(column, order));
        return this;
    }

    public QueryBuilder orders(List<Order> orders) {
        this.orders.addAll(orders);
        return this;
    }

    public QueryBuilder(String qlString) {
        this.qlString = qlString;
    }

    public QueryBuilder offset(Integer offset) {
        this.offset = Optional.ofNullable(offset);
        return this;
    }

    public QueryBuilder fetchSize(Integer fetchSize) {
        this.fetchSize = Optional.ofNullable(fetchSize);
        return this;
    }

    public static QueryBuilder create(String qlString) {
        return new QueryBuilder(qlString);
    }

    public QueryBuilder param(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public QueryBuilder params(Map<String, Object> map) {
        params.putAll(map);
        return this;
    }

    Query build(EntityManager entityManager) {
        StringBuilder sb = new StringBuilder(qlString);
        if (!groups.isEmpty()) {
            sb.append(" group by ").append(String.join(",", groups));
        }
        if (!orders.isEmpty()) {
            sb.append(" order by ").append(String.join(",", orders));
        }
        Query query = entityManager.createQuery(sb.toString());
        offset.ifPresent(query::setFirstResult);
        fetchSize.ifPresent(query::setMaxResults);
        this.params.forEach(query::setParameter);
        return query;
    }

    /**
     * @author caihd
     */
    Query buildNative(EntityManager entityManager, Class entityClass) {
        StringBuilder sb = new StringBuilder(qlString);
        if (!orders.isEmpty()) {
            sb.append(" order by ").append(String.join(",", orders));
        }
        Query query;
        if (entityClass != null) {
            query = entityManager.createNativeQuery(sb.toString(), entityClass);
        } else {
            query = entityManager.createNativeQuery(qlString);
        }
        offset.ifPresent(query::setFirstResult);
        fetchSize.ifPresent(query::setMaxResults);
        this.params.forEach(query::setParameter);
        return query;
    }
}
