package com.OnlineShopping.model;

public class Cart 
{
	private int cartID;
	private int productID;
	private int quantity;
	private String status;
	private int customerID;
	private ProductInfo product;
	
	public ProductInfo getProduct() {
		return product;
	}
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", productID=" + productID + ", quantity=" + quantity + ", status=" + status
				+ ", customerID=" + customerID + "]";
	}
	
	
}
