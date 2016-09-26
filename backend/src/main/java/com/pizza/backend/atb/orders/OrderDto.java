package com.pizza.backend.atb.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pizza.backend.atb.premade.OrderDetail;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private Integer orderId;

    private String userId;

    private OrderDetail orderDetail;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}