package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineShopping.DAO.CartDao;
import com.OnlineShopping.DAO.CustomerDao;
import com.OnlineShopping.DAO.ProductDao;
import com.OnlineShopping.model.Cart;
import com.OnlineShopping.model.Customer;
import com.impl.CartDaoImpl;
import com.impl.CustomerDaoImpl;
import com.impl.ProductionDaoImpl;

@WebServlet("/cart")
public class CartServle extends HttpServlet {
	CartDao cartDAO = new CartDaoImpl();
	CustomerDao custDAO = new CustomerDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation =	request.getParameter("operation");
		if(operation.equalsIgnoreCase("addToCart")) {
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("un");
			
			Customer customer = custDAO.viewCustomer(username);
				if(customer!=null) {
					int productID = Integer.parseInt(request.getParameter("productid"));
					Cart cart = new Cart();
					cart.setProductID(productID);
					cart.setQuantity(1);
					cart.setCustomerID(customer.getUserID());
					int result = cartDAO.addToCart(cart);
					if(result == 1) {
						request.setAttribute("cartAddedSuccess", "Product Added to Cart");
					}else {
						request.setAttribute("cartAddedFailed", "Product failed to add cart");
					}
					List<Cart> cartListByCustomer = cartDAO.viewAllCart(customer.getUserID());
					request.setAttribute("cartListByCustomer", cartListByCustomer);
					request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
				}else {
					request.setAttribute("cartAddedFailed", "Product failed to add cart");
					request.setAttribute("cartListByCustomer", new ArrayList<Cart>());
					request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
				}
		}else if(operation.equalsIgnoreCase("viewAllCart")) {
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("un");
			
			Customer customer = custDAO.viewCustomer(username);
			List<Cart> cartListByCustomer = cartDAO.viewAllCart(customer.getUserID());
			request.setAttribute("cartListByCustomer", cartListByCustomer);
			request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
		}else if(operation.equalsIgnoreCase("delete")) {
			int cartid = Integer.parseInt(request.getParameter("cartid"));
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("un");
			Customer customer = custDAO.viewCustomer(username);
			int cartListAfterDetel = cartDAO.deletecart(cartid);
			if(cartListAfterDetel==1) {
			List<Cart> cartListByCustomer = cartDAO.viewAllCart(customer.getUserID());
			request.setAttribute("cartListByCustomer", cartListByCustomer);
			request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
		}}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation =	request.getParameter("operation");
		if(operation.equalsIgnoreCase("updateCart")) {
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("un");
			
			Customer customer= custDAO.viewCustomer(username);
			int productID = Integer.parseInt(request.getParameter("productID"));
			Cart cart = new Cart();
			cart.setProductID(productID);
			cart.setCartID(Integer.parseInt(request.getParameter("cartID")));
			cart.setQuantity(Integer.parseInt(request.getParameter("CartQuantity")));
			cart.setCustomerID(customer.getUserID());
			
			if(cart.getQuantity()<=0) {
				int cartid = Integer.parseInt(request.getParameter("cartID"));			
				int cartListAfterDetel = cartDAO.deletecart(cartid);
				if(cartListAfterDetel==1) {
				List<Cart> cartListByCustomer = cartDAO.viewAllCart(customer.getUserID());
				request.setAttribute("cartListByCustomer", cartListByCustomer);
				request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
				}
			}else {
				int result =cartDAO.updateCart(cart);
				if(result==1) {
					List<Cart> cartListByCustomer = cartDAO.viewAllCart(customer.getUserID());
					request.setAttribute("cartUpdateSuccess", "Cart updated successfully ");	
					request.setAttribute("cartListByCustomer", cartListByCustomer);
					request.getRequestDispatcher("viewAllCart.jsp").forward(request, response);
				}
			}			
	}
}
	}
