package com.tekmentor.customerservice.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Order {
    private String id;
    private Date orderDate;
    private String customerId;
    private Double totalAmount;
    private ShippingStatus status;

    public Order() {
    }

    public Order(String id, Date orderDate, String customerId, Double totalAmount) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderStatus(ShippingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("orderDate", orderDate)
                .append("customerId", customerId)
                .append("totalAmount", totalAmount)
                .append("status", status)
                .toString();
    }
}
