package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBconnection.DBConnection;
import com.OnlineShopping.DAO.ProductDao;
import com.OnlineShopping.model.ProductInfo;

public class ProductionDaoImpl implements ProductDao {

	Connection con = DBConnection.getDateBaseConnenction();
	PreparedStatement pst = null;
	ResultSet rs = null;	
	ProductInfo Prod = null;
	
	@Override
	public int addProduct(ProductInfo product) {
		try {
			String query = "INSERT INTO products (product_name, product_price, product_category, product_brand)"
					+ "VALUES (?,?,?,?)";
			pst = con.prepareStatement(query);
			
			pst.setString(1, product.getProductName());
			pst.setDouble(2, product.getProductPrice());
			pst.setString(3, product.getProductCategory());
			pst.setString(4, product.getProductBrand());
			
			int numberOfRowInserted = pst.executeUpdate();		
			if(numberOfRowInserted==1)
			{
				return 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
//	public static void main(String[] args) {
//		ProductInfo prod = new ProductInfo();
//		prod.setProductName("OnePlus Nord");
//		prod.setProductPrice(25000);
//		prod.setProductCategory("Mobile");
//		prod.setProductBrand("OnePlus");
//		
//		System.out.println(new ProductionDaoImpl().addProduct(prod));
//	}

	@Override
	public int updateProcuct(ProductInfo product) {
		try {
			pst = con.prepareStatement("UPDATE products SET product_name = ?, product_price = ?, product_category = ?, product_brand = ? WHERE productid = ?");
			pst.setString(1, product.getProductName());
			pst.setDouble(2, product.getProductPrice());
			pst.setString(3, product.getProductCategory());
			pst.setString(4, product.getProductBrand());
			pst.setInt(5, product.getProductId());
			
			int noOfRowsUpdated = pst.executeUpdate();
			if(noOfRowsUpdated==1)
			{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteProduct(int ProductID) {
		try {
			pst = con.prepareStatement("DELETE FROM products WHERE productid ="+ProductID);
			
			int noOfRowsUpdated = pst.executeUpdate();
			if(noOfRowsUpdated==1)
			{
				return 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public List<ProductInfo> viewAllProduct() {
		List<ProductInfo> ProdList = new ArrayList<>();
		try {
			pst = con.prepareStatement("select * from products ");
			rs = pst.executeQuery();
			
			while(rs.next()){
				
				ProductInfo Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));
				ProdList.add(Prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProdList;
	}

//	public static void main(String[] args) {
//		System.out.println(new ProductionDaoImpl().searchProduct());
//	}
	
	@Override
	public ProductInfo searchProduct (int ProductID) {
		ProductInfo Prod = null;
		try {
			pst = con.prepareStatement("select * from products where productid = ? ");
			pst.setInt(1, ProductID);
			
			rs = pst.executeQuery();	
			if(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Prod;
	}
	
//	public static void main(String[] args) {
//		ProductDao product = new ProductionDaoImpl();
//		int ProdID = 4;
//		System.out.println(product.searchProduct(ProdID));
//		
//	}

	@Override
	public List<ProductInfo> searchProduct(String catagory, double Price) {
		List<ProductInfo> ProdList = new ArrayList<ProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.products where product_category = ? AND product_price < ?");
			pst.setString(1, catagory);
			pst.setDouble(2, Price);
			
			rs = pst.executeQuery();	
			
			while(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));
				
				ProdList.add(Prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProdList;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new ProductionDaoImpl().searchProduct("Mobile", 50000));		
//	}

	@Override
	public List<ProductInfo> searchProduct(double Price, String brand) {
		List<ProductInfo> ProdList = new ArrayList<ProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.products where product_price < ? AND product_brand = ?");
			pst.setDouble(1, Price);
			pst.setString(2, brand);
			
			rs = pst.executeQuery();	
			
			while(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));
				
				ProdList.add(Prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProdList;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new ProductionDaoImpl().searchProduct(50000, "apple"));
//	}

	@Override
	public List<ProductInfo> searchProductByCatagory(String Catogary) {
		List<ProductInfo> ProdList = new ArrayList<ProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.products where product_category = ? ");
			pst.setString(1, Catogary);
			
			rs = pst.executeQuery();	
			
			while(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));
				
				ProdList.add(Prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProdList;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new ProductionDaoImpl().searchProductByCatagory("Mobile"));
//	}

	@Override
	public List<ProductInfo> searchProductByBrand(String Brand) {
		List<ProductInfo> ProdList = new ArrayList<ProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.products where product_brand = ? ");
			pst.setString(1, Brand);
			
			rs = pst.executeQuery();	
			
			while(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));
				
				ProdList.add(Prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProdList;
	}
	
//	public static void main(String[] args) {
//		System.out.println(new ProductionDaoImpl().searchProductByBrand("Apple"));
//	}

	@Override
	public List<ProductInfo> searchProductByName(String Name) {
		List<ProductInfo> ProdList = new ArrayList<ProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * FROM onlineshoppingapp.products where product_name = ? ");
			pst.setString(1, Name);
			
			rs = pst.executeQuery();	
			
			while(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getNString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getNString("product_category"));
				Prod.setProductBrand(rs.getNString("product_brand"));
				
				ProdList.add(Prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProdList;
	}

//	public static void main(String[] args) {
//		System.out.println(new ProductionDaoImpl().searchProductByName("iPad"));
//	}
	public static void main(String[] args) {
		System.out.println("123".hashCode());
	}

	@Override
	public List<ProductInfo> searchProduct(String Name, String Category, String Brand, Double Price) {
		List<ProductInfo> ProdList = new ArrayList<ProductInfo>();
		try {
			pst = con.prepareStatement("SELECT * from products where product_name like ? and product_brand like ? and product_category like ? and  product_price < ?");
			pst.setString(1, "%"+Name+"%");
			pst.setString(2, "%"+Brand+"%");
			pst.setString(3, "%"+Category+"%");
			pst.setDouble(4, Price);			
			rs=pst.executeQuery();
			while(rs.next()){		
				Prod = new ProductInfo();
				Prod.setProductId(rs.getInt("productid"));
				Prod.setProductName(rs.getString("product_name"));
				Prod.setProductPrice(rs.getDouble("product_price"));
				Prod.setProductCategory(rs.getString("product_category"));
				Prod.setProductBrand(rs.getString("product_brand"));
				ProdList.add(Prod);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ProdList;
	}
}
