package com.pizza.backend.atb.premade;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class OrderDetail {

    private String userId;
    private String orderDate;
    private String premadeName;
    private String size;
    private String crustStyle;
    private String baseSauce;
    private String cheese;
    private List<String> meats;
    private List<String> otherToppings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPremadeName() {
        return premadeName;
    }

    public void setPremadeName(String premadeName) {
        this.premadeName = premadeName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrustStyle() {
        return crustStyle;
    }

    public void setCrustStyle(String crustStyle) {
        this.crustStyle = crustStyle;
    }

    public String getBaseSauce() {
        return baseSauce;
    }

    public void setBaseSauce(String baseSauce) {
        this.baseSauce = baseSauce;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public List<String> getMeats() {
        return meats;
    }

    public void setMeats(List<String> meats) {
        this.meats = meats;
    }

    public List<String> getOtherToppings() {
        return otherToppings;
    }

    public void setOtherToppings(List<String> otherToppings) {
        this.otherToppings = otherToppings;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}