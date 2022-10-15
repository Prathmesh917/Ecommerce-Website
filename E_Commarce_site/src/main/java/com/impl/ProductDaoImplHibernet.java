package com.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.ProductDao;
import com.OnlineShopping.model.ProductInfo;

public class ProductDaoImplHibernet implements ProductDao {
	
	SessionFactory sessionFactory=DBConnection.getdatabaseHibernateConnecton();

	@Override
	public int addProduct(ProductInfo product) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(product);
		t.commit();
		session.close();
		return 1;
	}

	@Override
	public int updateProcuct(ProductInfo product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(int ProductID) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		ProductInfo product=new ProductInfo();
		product.setProductId(ProductID);
		session.delete(product);
		t.commit();
		session.close();
		return 1;
	}

	@Override
	public List<ProductInfo> viewAllProduct() {
		Session session = sessionFactory.openSession();
		Query query =session.createQuery("from ProductInfo");
		return (List<ProductInfo>)query.getResultList();
	}

	@Override
	public ProductInfo searchProduct(int ProductID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductInfo> searchProduct(String catagory, double Price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductInfo> searchProduct(double Price, String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductInfo> searchProduct(String Name, String Category, String Brand, Double Price) {
		
		Session session =sessionFactory.openSession();
		Query query=session.createQuery("from ProductInfo where productName like ?0 and productCategory like ?1 and productBrand like ?2 and productPrice < ?3");
		query.setParameter(0, "%"+Name+"%");
		query.setParameter(1, "%"+Category+"%");
		query.setParameter(2, "%"+Brand+"%");
		query.setParameter(3, Price);
		List<ProductInfo> result=(List<ProductInfo>)query.getResultList();
		session.close();
		return result;
	}

	@Override
	public List<ProductInfo> searchProductByCatagory(String Catogary) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from ProductInfo where product_category like ?0");
		query.setParameter(0, "%"+Catogary+"%");
		List<ProductInfo> result = (List<ProductInfo>)query.getResultList();
		session.close();
		return result;
	}

	@Override
	public List<ProductInfo> searchProductByBrand(String Brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductInfo> searchProductByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

}
