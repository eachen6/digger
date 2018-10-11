package com.digger.vo;

import java.util.Date;

//獲取熱銷輪播圖
public class CarouseVO {
	private int id;
	private String carouseurl;
	private String name;
	private String price;
	private String discount;
	private String shelftime;
	private String coverurl;
	private String deadline;
	private String category;
	private String discountstate;
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
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDiscountstate() {
		return discountstate;
	}
	public void setDiscountstate(String discountstate) {
		this.discountstate = discountstate;
	}
	public String getShelftime() {
		return shelftime;
	}
	public void setShelftime(String shelftime) {
		this.shelftime = shelftime;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

}
