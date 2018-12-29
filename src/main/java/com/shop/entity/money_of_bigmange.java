package com.shop.entity;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class money_of_bigmange implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private int uid;
	@Column
	private Timestamp time;
	@Column
	private int gotrealmoney;
	@Column
	private int	leftBigmoney;
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
	public int getGotrealmoney() {
		return gotrealmoney;
	}
	public void setGotrealmoney(int gotrealmoney) {
		this.gotrealmoney = gotrealmoney;
	}
	public int getLeftBigmoney() {
		return leftBigmoney;
	}
	public void setLeftBigmoney(int leftBigmoney) {
		this.leftBigmoney = leftBigmoney;
	}
	@Override
	public String toString() {
		return "money_of_bigmange [id=" + id + ", uid=" + uid + ", time=" + time + ", gotrealmoney=" + gotrealmoney
				+ ", leftBigmoney=" + leftBigmoney + "]";
	}
	
	
	
}
