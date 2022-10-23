package com.project.frontend;

public class User {
	
	private String username;
	private String password;
	private int pk;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	
	@Override
	public String toString() {
		return "User [getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getPk()=" + getPk()
				+ "]";
	}
	

}
