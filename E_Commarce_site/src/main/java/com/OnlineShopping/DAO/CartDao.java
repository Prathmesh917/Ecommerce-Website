package com.OnlineShopping.DAO;

import java.util.List;

import com.OnlineShopping.model.Cart;

public interface CartDao {

	int addToCart(Cart cart);
	int updateCart(Cart cart);
	int deletecart(int CartID);
	List<Cart> viewAllCart(int customerID);
}
