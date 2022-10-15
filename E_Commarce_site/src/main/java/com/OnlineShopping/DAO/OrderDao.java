package com.OnlineShopping.DAO;

import java.util.List;

import com.OnlineShopping.model.Order;

public interface OrderDao {

	int placeOrder(Order order);
	int cancelOrder(int OrderID);
	Order viewOrder(int orderID);	
	List<Order> viewAllOrder();
	List<Order> viewAllOrder(int UserID);
}
