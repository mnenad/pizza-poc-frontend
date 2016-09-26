package com.pizza.backend.atb.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="order_json")
	private String orderJSON;

	public Integer getOrderId() {
		return orderId;
	}

	public String getUserId() {
		return userId;
	}

	public String getOrderJSON() {
		return orderJSON;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setOrderJSON(String orderJSON)  {
		this.orderJSON=orderJSON;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", userId=" + userId + ", orderJSON=" + orderJSON + "]";
	}

}