package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.AddressDao;
import com.OnlineShopping.model.Address;

public class AddressDaoImpl implements AddressDao {
	
	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public int addAddress(Address address) {
		try {
			String query ="INSERT INTO address (addressline1, addressline2, pincode, city, state)"
					+ "VALUES (?, ?, ?, ?, ?)";
			
		
			pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, address.getAddressLine1());
			pst.setString(2, address.getAddressLine2());
			pst.setString(3, address.getPincode());
			pst.setString(4, address.getCity());
			pst.setString(5, address.getState());
			
			int numberOFRowsInserted = pst.executeUpdate();
			rs=pst.getGeneratedKeys();
			
			if(rs.next()){
				return rs.getInt(1);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

//	public static void main(String[] args) {
//		Address add = new Address();
//		add.setAddressLine1("line 1");
//		add.setAddressLine2("L2");
//		add.setCity("Mumbai");
//		add.setPincode("410206");
//		add.setState("Mah");
//		System.out.println(new AddressDaoImpl().addAddress(add));
//		}
//	
	@Override
	public int updateAddress(Address address) {
		try {
			pst = con.prepareStatement("update address set addressline1=?,"
					+ "addressline2=?,"
					+ "state=?,"
					+ "city=?,"
					+ "pincode=? where addressid=? ");
			pst.setString(1, address.getAddressLine1());
			pst.setString(2, address.getAddressLine2());
			pst.setString(3, address.getState());
			pst.setString(4, address.getCity());
			pst.setString(5, address.getPincode());
			pst.setInt(6, address.getAddressID());
			
			int noOfRowsUpdated = pst.executeUpdate();
			if(noOfRowsUpdated == 1) {
				return 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Address getAddress(int addressID) {
		Address add = null;
		try {
			pst = con.prepareStatement("Select * from address where addressid = ? ");
			pst.setInt(1, addressID);
			rs = pst.executeQuery();
			
			if(rs.next())
			{
				add=new Address();
				add.setAddressID(rs.getInt("addressid"));
				add.setAddressLine1(rs.getString("addressline1"));
				add.setAddressLine2(rs.getString("addressline2"));
				add.setState(rs.getString("state"));
				add.setCity(rs.getString("city"));	
				add.setPincode(rs.getString("pincode"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return add;
	}

}
