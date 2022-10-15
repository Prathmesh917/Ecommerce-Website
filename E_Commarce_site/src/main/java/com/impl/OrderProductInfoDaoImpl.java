package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.OrderProductInfoDao;
import com.OnlineShopping.DAO.ProductDao;
import com.OnlineShopping.model.ProductInfo;
import com.OnlineShopping.model.orderProductInfo;

public class OrderProductInfoDaoImpl implements OrderProductInfoDao {

	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ProductDao productDAO = new ProductionDaoImpl();
	
	@Override
	public int addOrderProductInfoID(List<orderProductInfo> orderProductInfo, int orderID) {
		try {
			int numberOfRowsinserted = 0;
			for(orderProductInfo eachProduct:orderProductInfo)
			{
				pst=con.prepareStatement("INSERT into order_product_info (productid,quantity,subtotal,orderid)"
						+"Values"
						+"(?,?,?,?)");
				
				pst.setInt(1, eachProduct.getProductID());
				pst.setInt(2, eachProduct.getQuantity());
				pst.setDouble(3, eachProduct.getSubTotal());
				pst.setInt(4, orderID);
				
				numberOfRowsinserted = numberOfRowsinserted+pst.executeUpdate();	
			}
			if(numberOfRowsinserted==orderProductInfo.size())
			{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateProductInfoID(int orderID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<orderProductInfo> viewProductByOrderID(int orderID) {
		List<orderProductInfo> listOfProduct = new ArrayList<orderProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.order_product_info where orderid=?");
			pst.setInt(1, orderID);
			rs= pst.executeQuery();
			
			while(rs.next())
			{
				orderProductInfo productRecord = new orderProductInfo();
				
				productRecord.setOrderid(orderID);
				productRecord.setOrderProductInfoID(rs.getInt("order_product_info_id"));
				productRecord.setProductID(rs.getInt("productid"));
				productRecord.setQuantity(rs.getInt("quantity"));
				productRecord.setSubTotal(rs.getDouble("subtotal"));
				productRecord.setOrderProduct(productDAO.searchProduct(productRecord.getProductID()));
				
				listOfProduct.add(productRecord);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfProduct; 
	}

}
