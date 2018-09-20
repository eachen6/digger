package com.digger.vo;

import java.util.Date;

//獲取熱銷輪播圖
public class CarouseVO {
	private int id;
	private String carouseurl;
	private String name;
	private String price;
	private String discount;
	private Date shelftime;
	private String coverurl;
	private Date deadline;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarouseurl() {
		return carouseurl;
	}
	public void setCarouseurl(String carouseurl) {
		this.carouseurl = carouseurl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Date getShelftime() {
		return shelftime;
	}
	public void setShelftime(Date shelftime) {
		this.shelftime = shelftime;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}