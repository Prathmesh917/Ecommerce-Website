package com.DBconnection;
import java.awt.SystemColor;
import java.sql.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBConnection {
	private static Connection con=null;
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getdatabaseHibernateConnecton() {
		if(sessionFactory==null) {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
			sessionFactory = meta.getSessionFactoryBuilder().build();
		}
		return sessionFactory;
	}
	
	public static Connection getDateBaseConnenction()
	 {
	  String driverClassName="com.mysql.cj.jdbc.Driver";	  
	  try
	  {
	   if(con==null)
	   {
	   Class.forName(driverClassName);
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineshoppingapp", 
			  "root", "Prathu@917");
	   }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return con;
	 }
	 
	 public static void main(String[] args) {
		 StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build(); 
		 
		    Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();  
		      
		    SessionFactory factory=meta.getSessionFactoryBuilder().build();  
		    Session session=factory.openSession();  
		    System.out.println(session);
		    
		    System.out.println(session.createQuery("from ProductInfo").getResultList());
	}
}
