package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineShopping.DAO.ProductDao;
import com.OnlineShopping.model.Cart;
import com.OnlineShopping.model.Customer;
import com.OnlineShopping.model.ProductInfo;
import com.impl.ProductDaoImplHibernet;
import com.impl.ProductionDaoImpl;


@WebServlet("/product")
public class ProducServlet extends HttpServlet {

	ProductDao productDAO = new ProductDaoImplHibernet();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  	String operation = request.getParameter("operation");
		  	if(operation.equalsIgnoreCase("viewAllProduct")) {
			List<ProductInfo> allProduct = productDAO.viewAllProduct();
			
			request.setAttribute("allProduct", allProduct);
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
}else if(operation.equalsIgnoreCase("edit")){
				ProductInfo product = productDAO.searchProduct(Integer.parseInt(request.getParameter("productid")));
				request.setAttribute("product", product);
				request.getRequestDispatcher("editProduct.jsp").forward(request, response);
}else if(operation.equalsIgnoreCase("delete")) {
			int productid = Integer.parseInt(request.getParameter("productid"));
			
			int cartListAfterDetel = productDAO.deleteProduct(productid);
			if(cartListAfterDetel==1) {
			List<ProductInfo> allProduct = productDAO.viewAllProduct();
			request.setAttribute("allProduct", allProduct);
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
			}			
}else if(operation.equalsIgnoreCase("mobile")){
			String mobile1 = "mobile";
			List<ProductInfo> allProduct = productDAO.searchProductByCatagory(mobile1);
			request.setAttribute("allProduct", allProduct);
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
}else if(operation.equalsIgnoreCase("Tablet")){
			String mobile1 = "Tablet";
			List<ProductInfo> allProduct = productDAO.searchProductByCatagory(mobile1);
			request.setAttribute("allProduct", allProduct);
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
}else if(operation.equalsIgnoreCase("Laptop")){
		String mobile1 = "Laptop";
		List<ProductInfo> allProduct = productDAO.searchProductByCatagory(mobile1);
		request.setAttribute("allProduct", allProduct);
		request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
}else if(operation.equalsIgnoreCase("Watch")){
	String mobile1 = "Watch";
	List<ProductInfo> allProduct = productDAO.searchProductByCatagory(mobile1);
	request.setAttribute("allProduct", allProduct);
	request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
}
else if(operation.equalsIgnoreCase("All")){
		List<ProductInfo> allProduct = productDAO.viewAllProduct();	
		request.setAttribute("allProduct", allProduct);
		request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equalsIgnoreCase("addProduct")) {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductName(request.getParameter("Productname"));
			productInfo.setProductCategory(request.getParameter("Product category"));
			productInfo.setProductBrand(request.getParameter("Brand"));
			productInfo.setProductPrice(Double.parseDouble(request.getParameter("Price")));
			
			int productAdded = productDAO.addProduct(productInfo);
			
			List<ProductInfo> allProduct = productDAO.viewAllProduct();
			request.setAttribute("allProduct", allProduct);
			
			if (productAdded == 1) {
				request.setAttribute("ProdAddSuccess", "Product Added successfully !!!");
			} else {
				request.setAttribute("ProdFailSuccess", "Can't add Product !!!");
			}
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
		} else if (operation.equalsIgnoreCase("editProduct")) {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductName(request.getParameter("pName"));
			productInfo.setProductCategory(request.getParameter("Category"));
			productInfo.setProductBrand(request.getParameter("Brand"));
			productInfo.setProductPrice(Double.parseDouble(request.getParameter("Price")));
			productInfo.setProductId(Integer.parseInt(request.getParameter("pID")));
			
			int updateProduct = productDAO.updateProcuct(productInfo);
			
			List<ProductInfo> allProduct = productDAO.viewAllProduct();
			request.setAttribute("allProduct", allProduct);
			
			if (updateProduct == 1) {
				request.setAttribute("ProdUpdateSuccess", "Product updated successfully !!!");
			} else {
				request.setAttribute("ProdUpdateFail", "Can't update Product !!!");
			}
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
		} else if (operation.equalsIgnoreCase("Search")) {
			String ProductName = request.getParameter("Pname");
			String ProductCategory = request.getParameter("Category");
			String ProductBrand = request.getParameter("Bramd");
			Double ProductPrice = Double.parseDouble(request.getParameter("Price"));
			
			List<ProductInfo> filteredProduct = productDAO.searchProduct(ProductName, ProductCategory, ProductBrand, ProductPrice);
			request.setAttribute("allProduct", filteredProduct);
			
			request.getRequestDispatcher("viewAllProduct.jsp").forward(request, response);
		}
		}
}
