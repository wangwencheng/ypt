package com.wwc.ypt.jpa.query;

public final class Order implements CharSequence {
    private final String column;
    private final OrderEnum order;
    private final String value;

    public Order(String column, OrderEnum order) {
        this.column = column;
        this.order = order;
        this.value = this.column + " " + this.order;
    }

    public Order(String column) {
        this(column, OrderEnum.ASC);
    }

    public String getColumn() {
        return this.column;
    }

    public OrderEnum getOrder() {
        return this.order;
    }

    public String toString() {
        return this.value;
    }

    public int length() {
        return this.toString().length();
    }

    public char charAt(int index) {
        return this.toString().charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
        return this.toString().subSequence(start, end);
    }
}
