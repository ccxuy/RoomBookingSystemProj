package com.rbc.model;
public class User {

	private int uid;
	private String name;
	private String password;
	private String permission;
	private String phone;
	private String email;
	public User() {
		super();
	}
	public User(int uid, String name, String password, String permission,
			String phone, String email) {
		super();
		this.uid = uid;
		this.name = name;
		this.password = password;
		this.permission = permission;
		this.phone = phone;
		this.email = email;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}