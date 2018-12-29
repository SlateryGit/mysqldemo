package com.shop.entity;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class moneymanage implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private int uid;
	@Column
	private Timestamp time;
	@Column
	private int gotmoney;
	@Column
	private double	price;
	@Column
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getGotmoney() {
		return gotmoney;
	}
	public void setGotmoney(int gotmoney) {
		this.gotmoney = gotmoney;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "moneymanage [id=" + id + ", uid=" + uid + ", time=" + time + ", gotmoney=" + gotmoney + ", price="
				+ price + ", memo=" + memo + "]";
	}

	
}
