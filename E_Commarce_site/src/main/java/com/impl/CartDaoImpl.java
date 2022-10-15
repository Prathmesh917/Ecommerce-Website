package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.CartDao;
import com.OnlineShopping.DAO.ProductDao;
import com.OnlineShopping.model.Cart;

public class CartDaoImpl implements CartDao {

	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;
	ProductDao productDAO = new ProductionDaoImpl();
	
	@Override
	public int addToCart(Cart cart) {
		try {
			int quantity = checkifproductAlreadyAdded(cart.getProductID(), cart.getCustomerID());
			System.out.println(cart.getProductID()+" "+cart.getCustomerID()+"quantity "+quantity);
			if(quantity==0)
			{
			pst=con.prepareStatement("INSERT INTO cart (productid, quantity, status, customerid) "
					+ "VALUES (?, ?, ?, ?)");
			pst.setInt(1, cart.getProductID());
			pst.setInt(2, 1);
			pst.setString(3, cart.getStatus());
			pst.setInt(4, cart.getCustomerID());
			
			int noOfRowsInserted = pst.executeUpdate();
			
			if(noOfRowsInserted == 1)
			{
				return 1;
			}
			}
			else
			{
				cart.setQuantity(quantity+1);
				return updateCart(cart);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//return(int)quantity if product is already added by customer else 1(first time added)
	private int checkifproductAlreadyAdded(int productID, int customerID)
	{
		try {
			pst=con.prepareStatement("Select quantity FROM cart where productid=? and customerid=? and "
					+ "status is null ");
			pst.setInt(1, productID);
			pst.setInt(2, customerID);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("quantity");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	@Override
	public int updateCart(Cart cart) {
		try {
			pst=con.prepareStatement("UPDATE cart set quantity =? where productid=? AND customerid=? and status is null");
			pst.setInt(1, cart.getQuantity());
			pst.setInt(2, cart.getProductID());
			pst.setInt(3, cart.getCustomerID());
			
			int noOFRowsUpdates = pst.executeUpdate();
			
			if(noOFRowsUpdates==1)
			{
				return 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
//	public static void main(String[] args) {
//		Cart cart = new Cart();
//		cart.setProductID(1);
//		cart.setCustomerID(2);
//		cart.setStatus("Ordered");
//		
//		new CartDaoImpl().addToCart(cart);
//	}

	@Override
	public int deletecart(int CartID) {
		try {
		      
		      
		      pst=con.prepareStatement("delete from cart where cartid=?");
		      pst.setInt(1, CartID);
		      
		      int numberOFRowsDeleted=pst.executeUpdate();
		      
		      if(numberOFRowsDeleted==1) {
		        return 1;
		      }                
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		return 0;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new CartDaoImpl().deletecart(1));
//	}

	@Override
	public List<Cart> viewAllCart(int customerID) {
		List<Cart> cartList = new ArrayList<Cart>();
		try {
		      pst=con.prepareStatement("select * from cart where status is null");
		      rs=pst.executeQuery();
		      while(rs.next())
		      {
		        Cart cart=new Cart();
		        cart.setCartID(rs.getInt("cartid"));
		        cart.setProductID(rs.getInt("productid"));
		        cart.setProduct(productDAO.searchProduct(rs.getInt("productid")));
		        cart.setQuantity(rs.getInt("quantity"));
		        cart.setStatus(rs.getString("status"));
		        cart.setCustomerID(rs.getInt("customerid"));
		        cartList.add(cart);
		      }
		      
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
		return cartList;
	}

//	public static void main(String[] args) {
//		System.out.println(new CartDaoImpl().viewAllCart(1));
//	}
	public static void main(String[] args) {
		System.out.println("123".hashCode());
	}
}
