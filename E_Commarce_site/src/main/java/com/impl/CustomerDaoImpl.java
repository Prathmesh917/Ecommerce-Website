package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.AddressDao;
import com.OnlineShopping.DAO.CustomerDao;
import com.OnlineShopping.model.Address;
import com.OnlineShopping.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;
	AddressDao addDao = new AddressDaoImpl();
	
	@Override
	public int addCustomer(Customer customer) {
		try
		{
			int autoGeneratedID = addDao.addAddress(customer.getCustomerAddress());
		if(autoGeneratedID!=-1)
		{
		pst=con.prepareStatement("INSERT INTO customer (username, customerpassword, customername, customercontact, addressid) "
				+ "VALUES (?, ?, ?, ?, ?)");
		pst.setString(1, customer.getUserName());
		pst.setString(2, customer.getPassword());
		pst.setString(3, customer.getCustomerName());
		pst.setString(4, customer.getCustomerContact());
		pst.setInt(5, autoGeneratedID);
		
		int noOfRowsInserted=pst.executeUpdate();
		if(noOfRowsInserted==1)
		{
			return 1;
		}
		}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
//	public static void main(String[] args) {
//		Address customerAddress = new Address();
//		customerAddress.setAddressLine1("Panvel");
//		customerAddress.setAddressLine2("Navi Mumbai");
//		customerAddress.setCity("Panvel");
//		customerAddress.setPincode("410206");
//		customerAddress.setState("Mah");
//
//		Customer cust = new Customer();
//		cust.setUserName("Prathmesh");
//		cust.setPassword("123456".hashCode()+"");
//		cust.setCustomerName("Prathmesh");
//		cust.setCustomerContact("928371282");
//		cust.setCustomerAddress(customerAddress);
//		
//		System.out.println(new CustomerDaoImpl().addCustomer(cust));		
//	}

	@Override
	public boolean customerLogin(String username, String password) {
		try {
			pst = con.prepareStatement("SELECT * FROM customer where username=? and customerpassword=?");
			pst.setString(2, password);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next())
			{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int updateAddress(Address custAdd) {		
		return addDao.updateAddress(custAdd);
	}
	
	public static void main(String[] args) {
		CustomerDaoImpl CustDaoImp = new CustomerDaoImpl();
		
		List<Customer> custList = CustDaoImp.viewAllCustomer();
		Customer firstCustomer = custList.get(0);
		
		System.out.println(firstCustomer.getCustomerAddress());
		
		firstCustomer.getCustomerAddress().setAddressLine2("Chenged address line 2");
		
		CustDaoImp.updateAddress(firstCustomer.getCustomerAddress());
		System.out.println(firstCustomer.getCustomerAddress());
	}		

	@Override
	public int updateCustomerDetails(Customer customer) {
		try {
			pst = con.prepareStatement("UPDATE customer SET customername = ?, customercontact = ? WHERE username = ?");
			pst.setString(1, customer.getCustomerName());
			pst.setString(2, customer.getCustomerContact());
			pst.setString(3, customer.getUserName());
			int noOfRowsUpdated = pst.executeUpdate();
			if(noOfRowsUpdated==1)
			{
				updateAddress(customer.getCustomerAddress());
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean updateCustomerPassword(String username, String OldPassword, String NewPassword) {
		if(customerLogin(username, OldPassword)) {
			try {
				pst = con.prepareStatement("UPDATE customer SET customerpassword = ? WHERE username=?");
				pst.setString(1, NewPassword);
				pst.setString(2, username);
				int noOfRowsupdate = pst.executeUpdate();
				if(noOfRowsupdate==1)
				{
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}

	@Override
	public List<Customer> viewAllCustomer() {
		List<Customer> custList = new ArrayList<Customer>();
		try {
			pst = con.prepareStatement("select * from customer");
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				Customer cust = new Customer();
				cust.setUserID(rs.getInt("userid"));
				cust.setUserName(rs.getString("username"));
				cust.setCustomerName(rs.getString("customername"));
				cust.setCustomerContact(rs.getString("customercontact"));
				Address custAddress = addDao.getAddress(rs.getInt("addressid"));
				cust.setCustomerAddress(custAddress);
				custList.add(cust);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custList;
	}

	@Override
	public Customer viewCustomer(String username) {
		Customer cust = null;
		try {
			pst = con.prepareStatement("select * from customer where username=?");
			pst.setString(1, username);
			rs = pst.executeQuery();
			
			if (rs.next())
			{
				cust = new Customer();
				cust.setUserID(rs.getInt("userid"));
				cust.setUserName(rs.getNString("username"));
				cust.setCustomerName(rs.getNString("customername"));
				cust.setCustomerContact(rs.getString("customercontact"));
				Address custAddress = addDao.getAddress(rs.getInt("addressid"));
				cust.setCustomerAddress(custAddress);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cust;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new CustomerDaoImpl().viewAllCustomer());
//	}

}
