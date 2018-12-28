package com.wwc.ypt.jpa.persistence;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Collection;
import java.util.Objects;

class Condition {
    static final String EQUAL_SIGN = "=";
    final String column;
    final Object value;
    private final String operator;
    final boolean allowSkip;
    final String key;
    final String orAnd;

    Condition(String orAnd, String column, Object value) {
        this(orAnd, column, value, EQUAL_SIGN);
    }

    Condition(String orAnd, String column, Object value, String operator) {
        this(orAnd, column, value, operator, column);
    }

    Condition(String orAnd, String column, Object value, String operator, String key) {
        this(orAnd, column, value, operator, key, false);
    }

    Condition(String orAnd, String column, Object value, boolean allowSkip) {
        this(orAnd, column, value, EQUAL_SIGN, column, allowSkip);
    }


    Condition(String orAnd, String column, Object value, String operator, boolean allowSkip) {
        this(orAnd, column, value, operator, column, allowSkip);
    }

    Condition(String orAnd, String column, Object value, String operator, String key, boolean allowSkip) {
        if (Strings.isNullOrEmpty(orAnd)) {
            throw new IllegalArgumentException(" OrAnd is empty");
        }
        if (Strings.isNullOrEmpty(column)) {
            throw new IllegalArgumentException(" Column is empty ");
        }
        if (Strings.isNullOrEmpty(operator)) {
            throw new IllegalArgumentException(" Operator is empty ");
        }
        this.orAnd = orAnd;
        this.allowSkip = allowSkip;
        this.column = column;
        this.value = value;
        this.operator = operator;
        this.key = key;
        Preconditions.checkArgument(!isCollection()
                || !EQUAL_SIGN.equals(operator), String.format("Operator[%s] is illegal when value addType is collection!!!", operator));
    }


    final boolean isEmpty() {
        if (Objects.isNull(value)) {
            return true;
        }

        if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        }
        return Strings.isNullOrEmpty(value.toString());
    }

    final boolean isCollection() {
        return Objects.nonNull(value) && (value instanceof Collection);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(column);
        sb.append(QueryBuilders.BLANK).append(operator).append(isCollection() ? QueryBuilders.LEFT_BRACKET : "")
                .append(QueryBuilders.COLON_SIGN).append(key).append(isCollection() ? QueryBuilders.RIGHT_BRACKET : "");
        return sb.toString();
    }
}
