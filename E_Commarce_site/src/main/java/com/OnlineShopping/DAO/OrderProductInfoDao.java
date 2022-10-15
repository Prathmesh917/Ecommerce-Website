package com.OnlineShopping.DAO;

import java.util.List;

import com.OnlineShopping.model.orderProductInfo;

public interface OrderProductInfoDao {

	public int addOrderProductInfoID(List<orderProductInfo> orderProductInfo, int orderID);
	//when order is cancelled - update order ID to null
	public int updateProductInfoID(int orderID);
	
	public List<orderProductInfo> viewProductByOrderID(int orderID);
	
}
