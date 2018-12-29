package com.shop.entity;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class ordermanage implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private int sender;
	@Column(nullable=false)
	private int receiver;
	@Column(nullable=false)
	private int flowerid;
	@Column(nullable=false)
	private double value;
	@Column(name="finish_time")
	private Timestamp finish;
	@Column
	private Timestamp submit_time;
	@Column
	private int quantity;
	@Column
	private int sent;
	@Column
	private int accept;
	@Column
	private String memo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getFlowerid() {
		return flowerid;
	}
	public void setFlowerid(int flowerid) {
		this.flowerid = flowerid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Timestamp getFinish_time() {
		return finish;
	}
	public void setFinish_time(Timestamp finish_time) {
		this.finish = finish_time;
	}
	public Timestamp getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Timestamp submit_time) {
		this.submit_time = submit_time;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSent() {
		return sent;
	}
	public void setSent(int sent) {
		this.sent = sent;
	}
	public int getAccept() {
		return accept;
	}
	public void setAccept(int accept) {
		this.accept = accept;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "ordermanage [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", flowerid=" + flowerid
				+ ", value=" + value + ", finish_time=" + finish + ", submit_time=" + submit_time + ", quantity="
				+ quantity + ", sent=" + sent + ", accept=" + accept + ", memo=" + memo + "]";
	}


}
