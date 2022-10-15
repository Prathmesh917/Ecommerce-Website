package com.OnlineShopping.model;

import java.util.List;

public class Order 
{

	private int odrerID;
	private int customerID;
	private double totalOrderAmount;
	
	private List<orderProductInfo> productInfo;
	public int getOdrerID() {
		return odrerID;
	}
	public void setOdrerID(int odrerID) {
		this.odrerID = odrerID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public double getTotalOrderAmount() {
		return totalOrderAmount;
	}
	public void setTotalOrderAmount(double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	public List<orderProductInfo> getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(List<orderProductInfo> productInfo) {
		this.productInfo = productInfo;
	}
	@Override
	public String toString() {
		return "Order [odrerID=" + odrerID + ", customerID=" + customerID + ", totalOrderAmount=" + totalOrderAmount
				+ ", productInfo=" + productInfo + "]\n";
	}	
	
	
}
