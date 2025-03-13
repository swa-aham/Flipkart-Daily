package com.flipkart.daily.model;

public class SortCriteria {

    public enum SortField {
        PRICE,
        QUANTITY
    }

    public enum SortOrder {
        ASC,
        DESC
    }

    private SortField field;
    private SortOrder order;

    public SortCriteria() {
        // Default sorting: Price ascending
        this.field = SortField.PRICE;
        this.order = SortOrder.ASC;
    }

    public SortCriteria(SortField field, SortOrder order) {
        this.field = field;
        this.order = order;
    }

    public static SortCriteria fromArray(Object[] sortBy) {
        if (sortBy == null || sortBy.length < 2) {
            return new SortCriteria();
        }

        String fieldStr = sortBy[0].toString().toUpperCase();
        String orderStr = sortBy[1].toString().toUpperCase();

        SortField field;
        if ("PRICE".equals(fieldStr)) {
            field = SortField.PRICE;
        } else if ("ITEMQTY".equals(fieldStr) || "QUANTITY".equals(fieldStr)) {
            field = SortField.QUANTITY;
        } else {
            field = SortField.PRICE;
        }

        SortOrder order;
        if ("DESC".equals(orderStr)) {
            order = SortOrder.DESC;
        } else {
            order = SortOrder.ASC;
        }

        return new SortCriteria(field, order);
    }

    public SortField getField() {
        return field;
    }

    public void setField(SortField field) {
        this.field = field;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }
}
