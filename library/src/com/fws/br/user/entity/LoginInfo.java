package com.fws.br.user.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonRootName("LoginInfo")
public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 785985534546311626L;

	private boolean active;
	private int accessCounter;
	private boolean blocked;
	private String email;
	private String login;
	private String name;
	private String password;
	private String userId;

	public LoginInfo(boolean active, int accessCounter, boolean blocked, String email, String login, String name,
			String password, String userId) {
		super();
		this.active = active;
		this.accessCounter = accessCounter;
		this.blocked = blocked;
		this.email = email;
		this.login = login;
		this.name = name;
		this.password = password;
		this.userId = userId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAccessCounter() {
		return accessCounter;
	}

	public void setAccessCounter(int accessCounter) {
		this.accessCounter = accessCounter;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginInfo [active=" + active + ", accessCounter=" + accessCounter + ", blocked=" + blocked + ", "
				+ (email != null ? "email=" + email + ", " : "") + (login != null ? "login=" + login + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (password != null ? "password=" + password + ", " : "")
				+ (userId != null ? "userId=" + userId : "") + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginInfo other = (LoginInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
