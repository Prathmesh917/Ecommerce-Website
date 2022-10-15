package com.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

	public static Connection getDBconnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/onlineshoppingapp"; 
		return DriverManager.getConnection(url, "root", "Prathu@917");
	}
}
