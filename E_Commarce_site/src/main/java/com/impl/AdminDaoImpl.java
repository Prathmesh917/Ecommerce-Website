package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.AdminDao;

public class AdminDaoImpl implements AdminDao {
	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;
	@Override
	public boolean adminLogIN(String username, String password) {
		try {
			pst = con.prepareStatement("SELECT * FROM admin where username=? and password=?");
			
			pst.setString(1, username);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			if(rs.next())
			{
			return true; 
			}
		} catch (SQLException e) {
			System.out.println("Check query"+e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean updateAdminPassword(String username, String OldPassword, String NewPassword) {
		try {
			  boolean isUserExist = adminLogIN(username, OldPassword);
			  	if(isUserExist) {
			  	pst = con.prepareStatement("Update admin SET password=? WHERE username =?");
			  	pst.setString(1, NewPassword);
			  	pst.setString(2, username);
			  	
			  	int numberOfRowsUpdated = pst.executeUpdate();
			  	if(numberOfRowsUpdated==1)
			  	{
			  		return true;
			  	}
			  	}			  							  
		} catch (SQLException e) {
			System.out.println("Check query"+e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String Admin = "admin1";
	    String s = "Abcd@123";
		System.out.println(s.hashCode());
	}
	
}
