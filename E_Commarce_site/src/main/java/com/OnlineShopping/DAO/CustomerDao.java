package com.OnlineShopping.DAO;

import java.util.List;

import com.OnlineShopping.model.Address;
import com.OnlineShopping.model.Customer;

public interface CustomerDao {

	int addCustomer(Customer customer);
	boolean customerLogin(String username, String password);
	int updateAddress(Address custAdd);
	int updateCustomerDetails(Customer customer);
	boolean updateCustomerPassword(String username, String OldPassword, String NewPassword);
	List<Customer> viewAllCustomer();
	Customer viewCustomer(String username);
}
