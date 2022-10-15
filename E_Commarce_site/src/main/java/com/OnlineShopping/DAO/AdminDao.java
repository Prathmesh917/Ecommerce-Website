package com.OnlineShopping.DAO;

public interface AdminDao {

	boolean adminLogIN(String username, String password);
	boolean updateAdminPassword(String username, String OldPassword, String NewPassword);

}
