package com.shop.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class flower implements Serializable{
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false,unique=true)
	private String name;
	@Column
	private double price;
	@Column
	private int quantity;
	@Column
	private String means;
	@Column
	private String pic;
	@Column
	private int type;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMeans() {
		return means;
	}
	public void setMeans(String means) {
		this.means = means;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "flower [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", means="
				+ means + ", pic=" + pic + ", type=" + type + "]";
	}
	
}
