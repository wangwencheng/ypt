package com.wwc.ypt.jpa.persistence;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * 帮助构造querybuilder对象
 * eg:
 * QueryBuilder = QueryBuilders.of(" from entityName ")
 * .and("columnName",value)
 * 当value为空时 该条件被排除
 * .and("columnName",value,true)
 * 传入集合 条件 是in
 * .and("columnName,collectionValue,"in")
 * <p>
 * .and("columnName",value,">",true)
 * <p>
 * .build();
 * <p>
 * and----拼凑where 后面的条件
 * <p>
 * Created by nick.guo on 17/12/22.
 */
public class QueryBuilders {
    static final char BLANK = ' ';
    static final char COLON_SIGN = ':';
    static final char LEFT_BRACKET = '(';
    static final char RIGHT_BRACKET = ')';

    static final String WHERE = " where 1=1";
    static final String AND = "and";
    static final String OR = "or";

    private final String qlString;
    private Optional<String> custom = Optional.empty();
    private final List<Condition> conditions = new ArrayList<>();
    private Map<String, Object> params = new HashMap<>();

    /**
     * 此方法传入的参数用于在sql最后进行追加，方法调用者有特殊需要
     *
     * @param custom
     * @return
     */
    public QueryBuilders custom(String custom) {
        Preconditions.checkNotNull(custom);
        this.custom = Optional.of(BLANK + this.custom.orElse("") + BLANK + custom + BLANK);
        return this;
    }

    /* */

    /**
     * value为null的 是否不进行 where条件拼凑
     *
     * @return
     *//*
    public QueryBuilders skipEmpty() {
        skipEmpty = true;
        return this;
    }*/
    public QueryBuilders(String qlString) {
        this.qlString = qlString;
    }

    public static QueryBuilders of(String qlString) {
        return new QueryBuilders(qlString);
    }

    /**
     * 追加 where条件
     *
     * @param column
     * @param value
     * @return
     */
    public QueryBuilders and(String column, Object value) {
        conditions.add(new Condition(AND, column, value));
        return this;
    }


    /**
     * 追加where条件
     *
     * @param column   列名
     * @param value    列值条件
     * @param operator 操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @return
     */
    public QueryBuilders and(String column, Object value, String operator) {
        conditions.add(new Condition(AND, column, value, operator));
        return this;
    }

    /**
     * 追加where条件
     *
     * @param column   列名
     * @param value    列值条件
     * @param operator 操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @param key      参数key
     * @return
     */
    public QueryBuilders and(String column, Object value, String operator, String key) {
        conditions.add(new Condition(AND, column, value, operator, key));
        return this;
    }

    /**
     * 追加where条件
     *
     * @param column    列名
     * @param value     列值条件
     * @param allowSkip 空时排除
     * @return
     */
    public QueryBuilders and(String column, Object value, boolean allowSkip) {
        conditions.add(new Condition(AND, column, value, allowSkip));
        return this;
    }


    /**
     * @param column    列名
     * @param value     列值条件
     * @param operator  操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @param allowSkip 空时排除
     * @return
     */
    public QueryBuilders and(String column, Object value, String operator, boolean allowSkip) {
        conditions.add(new Condition(AND, column, value, operator, allowSkip));
        return this;
    }

    /**
     * @param column    列名
     * @param value     列值条件
     * @param operator  操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @param key       参数key
     * @param allowSkip 空时排除
     * @return
     */
    public QueryBuilders and(String column, Object value, String operator, String key, boolean allowSkip) {
        conditions.add(new Condition(AND, column, value, operator, key, allowSkip));
        return this;
    }


    /**
     * 追加 where条件
     *
     * @param column
     * @param value
     * @return
     */
    public QueryBuilders or(String column, Object value) {
        conditions.add(new Condition(OR, column, value));
        return this;
    }


    /**
     * 追加where条件
     *
     * @param column   列名
     * @param value    列值条件
     * @param operator 操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @return
     */
    public QueryBuilders or(String column, Object value, String operator) {
        conditions.add(new Condition(OR, column, value, operator));
        return this;
    }

    /**
     * 追加where条件
     *
     * @param column   列名
     * @param value    列值条件
     * @param operator 操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @param key      参数key
     * @return
     */
    public QueryBuilders or(String column, Object value, String operator, String key) {
        conditions.add(new Condition(OR, column, value, operator, key));
        return this;
    }

    /**
     * 追加where条件
     *
     * @param column    列名
     * @param value     列值条件
     * @param allowSkip 空时排除
     * @return
     */
    public QueryBuilders or(String column, Object value, boolean allowSkip) {
        conditions.add(new Condition(OR, column, value, allowSkip));
        return this;
    }


    /**
     * @param column    列名
     * @param value     列值条件
     * @param operator  操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @param allowSkip 空时排除
     * @return
     */
    public QueryBuilders or(String column, Object value, String operator, boolean allowSkip) {
        conditions.add(new Condition(OR, column, value, operator, allowSkip));
        return this;
    }

    /**
     * @param column    列名
     * @param value     列值条件
     * @param operator  操作符 eg: '=' '!=' '>' '<'  "in"  "not in"...
     * @param key       参数key
     * @param allowSkip 空时排除
     * @return
     */
    public QueryBuilders or(String column, Object value, String operator, String key, boolean allowSkip) {
        conditions.add(new Condition(OR, column, value, operator, key, allowSkip));
        return this;
    }

    public QueryBuilders param(String k, Object v) {
        params.put(k, v);
        return this;
    }

    public QueryBuilders params(Map<String, Object> params) {
        this.params = params;
        return this;
    }


    public QueryBuilder build() {
        StringBuilder sqlBuilder = new StringBuilder(qlString);
        sqlBuilder.append(WHERE);
        conditions.stream().filter(condition -> !condition.allowSkip || !condition.isEmpty())
                .forEach(condition -> injectCondition(sqlBuilder, condition));
        return new QueryBuilder(sqlBuilder.append(custom.orElse("")).toString()).params(params);
    }

    private void injectCondition(StringBuilder sqlBuilder, Condition condition) {
        sqlBuilder.append(BLANK).append(condition.orAnd).append(BLANK).append(condition);
        params.put(condition.key, condition.value);
    }


}
