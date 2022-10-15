package com.OnlineShopping.DAO;

import com.OnlineShopping.model.Address;

public interface AddressDao {

	int addAddress(Address address);
	
	int updateAddress(Address address);
	
	Address getAddress(int customerID);

}
