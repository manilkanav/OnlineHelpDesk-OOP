package com.model;

import java.util.Date;

public class User {
	
	private int id;
	private String username;
	private String first_name;
	private String last_name;
	private String password;
	private String email;
	private String phone;
	private Date date_of_birth;
	private String gender;
	private String profile_img;
	
	public User() {
		
	}

	public User(String username, String first_name, String last_name, String password, String email,
			String phone, Date date_of_birth, String gender, String profile_img) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.profile_img = profile_img;
		
	}
	
	public User(int id, String username, String first_name, String last_name, String password, String email,
			String phone, Date date_of_birth, String gender, String profile_img) {
		this.id = id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.profile_img = profile_img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	
}
