package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.OrderDao;
import com.OnlineShopping.DAO.OrderProductInfoDao;
import com.OnlineShopping.model.Order;

public class OrderDaoImpl implements OrderDao {
	
	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;
	OrderProductInfoDao orderProductInfoDaoImpl = new OrderProductInfoDaoImpl();

	@Override
	public int placeOrder(Order order) {
		try {
			String query = "INSERT INTO `onlineshoppingapp`.`order` (`customerid`, `totalamount`) VALUES (?, ?)";
			
			pst=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS );
			pst.setInt(1, order.getCustomerID());
			pst.setDouble(2, order.getTotalOrderAmount());
			
			int numberOfRowsUpdated = pst.executeUpdate();
			
			rs=pst.getGeneratedKeys();
			
			if(rs.next())
			{
				int orderID = rs.getInt(1);
				int result = orderProductInfoDaoImpl.addOrderProductInfoID(order.getProductInfo(), orderID);
				if(result>0)
				{
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

//	public static void main(String[] args) {
//		orderProductInfo product1= new orderProductInfo();
//		product1.setProductID(1);  product1.setQuantity(3); product1.setSubTotal(123);
//		orderProductInfo product2= new orderProductInfo();
//		product1.setProductID(2);  product1.setQuantity(4); product2.setSubTotal(421);
//		
//		List<orderProductInfo> listProductInfo = new ArrayList<orderProductInfo>();
//		listProductInfo.add(product1);
//		listProductInfo.add(product2);
//		
//		Order order = new Order();
//		order.setCustomerID(1); order.setTotalOrderAmount(20000);
//		order.setProductInfo(listProductInfo); 
//		
//		System.out.println(new OrderDaoImpl().placeOrder(order));
//		
//	}
	
	@Override
	public int cancelOrder(int OrderID) {
		try {
			String query="UPDATE `onlineshoppingapp`.`order` SET `customerid` = ? WHERE (`orderid` = ?)";
			pst = con.prepareStatement(query);
			pst.setObject(1, null);
			pst.setDouble(2, OrderID);
			
			int numberOfRowsupdated = pst.executeUpdate();
			if(numberOfRowsupdated==1)
			{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

//	public static void main(String[] args) {
//		System.out.println(new OrderDaoImpl().cancelOrder(4));
//	}
	
	@Override
	public Order viewOrder(int orderID) {
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.order where orderid = ? ");
			pst.setInt(1, orderID);
			rs = pst.executeQuery();
			
			if(rs.next())
			{
				Order order = new Order();
				order.setOdrerID(rs.getInt("orderid"));
				order.setCustomerID(rs.getInt("customerid"));
				order.setTotalOrderAmount(rs.getDouble("totalamount"));
				order.setProductInfo(orderProductInfoDaoImpl.viewProductByOrderID(orderID));
				return order;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
//	public static void main(String[] args) {
//		System.out.println(new OrderDaoImpl().viewOrder(6));
//	}

	@Override
	public List<Order> viewAllOrder() {
		List<Order> orderList = new ArrayList<Order>();
		try {		
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.order where customerid is not null");
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setOdrerID(rs.getInt("orderid"));
				order.setCustomerID(rs.getInt("customerid"));
				order.setTotalOrderAmount(rs.getDouble("totalamount"));
				order.setProductInfo(orderProductInfoDaoImpl.viewProductByOrderID(order.getOdrerID()));
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public static void main(String[] args) {
		System.out.println(new OrderDaoImpl().viewAllOrder());
	}

	@Override
	public List<Order> viewAllOrder(int UserID) {
		List<Order> orderList = new ArrayList<Order>();
		try {		
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.order where customerid = ?");
			pst.setInt(1, UserID);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setOdrerID(rs.getInt("orderid"));
				order.setCustomerID(rs.getInt("customerid"));
				order.setTotalOrderAmount(rs.getDouble("totalamount"));
				order.setProductInfo(orderProductInfoDaoImpl.viewProductByOrderID(order.getOdrerID()));
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}
}
