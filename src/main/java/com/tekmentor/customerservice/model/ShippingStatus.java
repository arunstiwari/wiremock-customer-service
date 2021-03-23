package com.tekmentor.customerservice.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShippingStatus {
    String orderId;
    String status;

    public ShippingStatus() {
    }

    public ShippingStatus(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("status", status)
                .toString();
    }

}
