package com.OnlineShopping.model;

public class orderProductInfo {

	private int orderProductInfoID;
	private int productID;
	private int quantity;
	private double subTotal;
	private int orderid;
	private ProductInfo OrderProduct;
	
	public ProductInfo getOrderProduct() {
		return OrderProduct;
	}
	public void setOrderProduct(ProductInfo orderProduct) {
		OrderProduct = orderProduct;
	}
	public int getOrderProductInfoID() {
		return orderProductInfoID;
	}
	public void setOrderProductInfoID(int orderProductInfoID) {
		this.orderProductInfoID = orderProductInfoID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	@Override
	public String toString() {
		return "orderProductInfo [orderProductInfoID=" + orderProductInfoID + ", productID=" + productID + ", quantity="
				+ quantity + ", subTotal=" + subTotal + ", orderid=" + orderid + ", OrderProduct=" + OrderProduct + "]";
	}	
		
}
