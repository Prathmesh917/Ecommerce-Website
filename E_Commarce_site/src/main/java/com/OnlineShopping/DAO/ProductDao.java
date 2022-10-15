package com.OnlineShopping.DAO;

import java.util.List;

import com.OnlineShopping.model.ProductInfo;

public interface ProductDao {

	int addProduct(ProductInfo product);
	//int - o/p       addProduct -> function name      ProductInfo -> i/p type     product -> variable name
	int updateProcuct(ProductInfo product);
	int deleteProduct(int ProductID);
	
	List<ProductInfo> viewAllProduct();
	
	//Customer operation
	//Overloading example - searchProduct
	ProductInfo searchProduct(int ProductID);
	List<ProductInfo> searchProduct(String catagory,double Price);
	List<ProductInfo> searchProduct(double Price, String brand);
	List<ProductInfo> searchProduct(String Name, String Category, String Brand, Double Price);
	
	List<ProductInfo> searchProductByCatagory(String Catogary);
	List<ProductInfo> searchProductByBrand(String Brand);
	List<ProductInfo> searchProductByName(String Name);
}
