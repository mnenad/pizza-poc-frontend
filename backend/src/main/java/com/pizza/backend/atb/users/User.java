package com.pizza.backend.atb.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name="userId")
	private String userId;
	
	@Column(name="userEmail")
	private String userEmail;

	public String getUserId() {
		return userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserName(String userId) {
		this.userId = userId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + "]";
	}
}
