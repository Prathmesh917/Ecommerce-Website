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
import com.OnlineShopping.DAO.OrderDao;
import com.OnlineShopping.model.Cart;
import com.OnlineShopping.model.Customer;
import com.OnlineShopping.model.Order;
import com.OnlineShopping.model.orderProductInfo;
import com.impl.CartDaoImpl;
import com.impl.CustomerDaoImpl;
import com.impl.OrderDaoImpl;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDao orderDAO = new OrderDaoImpl();
	CustomerDao custDAO = new CustomerDaoImpl();
	CartDao cartDAO = new CartDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation =	request.getParameter("operation");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("un");
		String userType = (String)session.getAttribute("userType");
		Customer customer = null;
		
		if(userType.equalsIgnoreCase("customer")) {
			customer=custDAO.viewCustomer(username);
		}
		
		if(operation.equalsIgnoreCase("placeorder")) {
			Order order = new Order();
			List<Cart> userCartList = cartDAO.viewAllCart(customer.getUserID());
			if(userCartList.size()>0) {
				double totalAmount=0;
				List<orderProductInfo> orderProductInfo = new ArrayList<orderProductInfo>();
				
				for (Cart eachCart:userCartList) {
					orderProductInfo orderProduct = new orderProductInfo();
					orderProduct.setSubTotal(eachCart.getProduct().getProductPrice()*eachCart.getQuantity());
					orderProduct.setProductID(eachCart.getProductID());
					orderProduct.setQuantity(eachCart.getQuantity());
					orderProductInfo.add(orderProduct);
					totalAmount=totalAmount+orderProduct.getSubTotal();					
				}
				order.setCustomerID(customer.getUserID());
				order.setTotalOrderAmount(totalAmount);
				order.setProductInfo(orderProductInfo);
					
					int result = orderDAO.placeOrder(order);
					if(result==1) {
						request.setAttribute("orderPlacedSuccessfull", "order Placed successfull");
						for(Cart eachCart:userCartList) {
							cartDAO.deletecart(eachCart.getCartID());
						}
						List<Order> listOfOrder = orderDAO.viewAllOrder(customer.getUserID());
						request.setAttribute("ListOfOrder", listOfOrder);
						request.getRequestDispatcher("viewYourOrders.jsp").forward(request, response);
					}
			}
		} else if(operation.equalsIgnoreCase("viewYourOrer")) {
			List<Order> listOfOrder = null;
			if(userType.equalsIgnoreCase("customer")) {
				listOfOrder = orderDAO.viewAllOrder(customer.getUserID());
			}else {
				listOfOrder = orderDAO.viewAllOrder();
			}	
			request.setAttribute("ListOfOrder", listOfOrder);
			request.getRequestDispatcher("viewYourOrders.jsp").forward(request, response);
		}else if(operation.equalsIgnoreCase("cancelOrder")) {
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			if(orderDAO.cancelOrder(orderid)==1) {
				request.setAttribute("orderCancelledSuccessfull", "Order Cancelled Successfull");
			}else {
				request.setAttribute("orderCancelledFailed", "Order Cancellation failed !!");
			}
			List<Order> listOfOrder = orderDAO.viewAllOrder(customer.getUserID());
			request.setAttribute("ListOfOrder", listOfOrder);
			request.getRequestDispatcher("viewYourOrders.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
