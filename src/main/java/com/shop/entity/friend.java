package com.shop.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class friend {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private int myid;
	
	@Column
	private int friendid;
	
	public friend(int myid, int friendid) {
		super();
		this.myid = myid;
		this.friendid = friendid;
	}
	public int getMyid() {
		return myid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMyid(int myid) {
		this.myid = myid;
	}
	public int getFriendid() {
		return friendid;
	}
	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}
	@Override
	public String toString() {
		return "friend [ " + myid + ", " + friendid + "]";
	}
	
}
