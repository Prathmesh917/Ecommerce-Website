package com.DBconnection;

import java.sql.Connection;
import static com.DBconnection.connection.getDBconnection;

public class MySqlConnection {

	public static void main(String[] args) {
		try(Connection cn =getDBconnection())
		{
			System.out.println("Connected !!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
