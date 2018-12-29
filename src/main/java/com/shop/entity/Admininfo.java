package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admininfo {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false,unique=true)
	private String name;
	@Column
	private int age;
	@Column
	private String phone;
	@Column
	private String photo;
	@Column
	private String nickname;
	@Column
	private String password;
	@Column
	private String Icon;
	@Column
	private String Signature;
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "Userinfo [id=" + id + ", name=" + name + ", age=" + age + ", phone=" + phone
				+ ", photo=" + photo +", nickname=" + nickname + ",Icon=" + Icon + ", Signature=" + Signature + ", password=" + password
				+ "]";
	}
	
	
}
