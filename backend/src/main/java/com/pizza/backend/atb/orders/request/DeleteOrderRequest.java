package com.pizza.backend.atb.orders.request;

public class DeleteOrderRequest {

    private String fbAuthToken;
    private String orderId;

    public String getFbAuthToken() {
        return fbAuthToken;
    }

    public void setFbAuthToken(String fbAuthToken) {
        this.fbAuthToken = fbAuthToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
