package com.shop.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Userinfo implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false,unique=true)
	private String name;
	@Column
	private int age;
	@Column
	private int ismale;
	@Column
	private String phone;
	@Column
	private String nickname;
	@Column
	private String password;
	@Column
	private String address;
	@Column
	private String Signature;
	@Column
	private String Icon;
	@Column
	private String photo;
	@Column
	private int money;
	@Column
	private int bigmoney;

	@Column
	private Date birthday;
	
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
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIsmale() {
		return ismale;
	}
	public void setIsmale(int ismale) {
		this.ismale = ismale;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getBigmoney() {
		return bigmoney;
	}
	public void setBigmoney(int bigmoney) {
		this.bigmoney = bigmoney;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Userinfo [id=" + id + ", name=" + name + ", age=" + age + ", ismale=" + ismale + ", phone=" + phone
				+ ", nickname=" + nickname + ", password=" + password + ", address=" + address + ", Signature="
				+ Signature + ", Icon=" + Icon + ", photo=" + photo + ", money=" + money + ", bigmoney=" + bigmoney
				+ ", birthday=" + birthday + "]";
	}
	
	
}
