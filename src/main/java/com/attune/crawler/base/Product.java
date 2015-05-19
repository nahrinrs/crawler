package com.attune.crawler.base;

public class Product {
	
	private String id;
	private String productName;
	private String color;
	private String price;
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName
				+ ", color=" + color + ", price=" + price + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
