package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineShopping.DAO.AdminDao;
import com.OnlineShopping.DAO.CustomerDao;
import com.OnlineShopping.model.Address;
import com.OnlineShopping.model.Customer;
import com.impl.AdminDaoImpl;
import com.impl.CustomerDaoImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	CustomerDao customerDAO = new CustomerDaoImpl();
	AdminDao adminDAO = new AdminDaoImpl();
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }		
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher rd = null;
			rd=request.getRequestDispatcher("Home.jsp");
			request.setAttribute("LogOutSuccess", "user logged out successfully !!!");
			rd.forward(request, response);
		} else if (operation.equalsIgnoreCase("viewCustomer")||operation.equalsIgnoreCase("editCustomer")) {
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("un");
			RequestDispatcher rd = null;
			Customer customer =customerDAO.viewCustomer(username);
			if(operation.equalsIgnoreCase("viewCustomer")) {
			rd = request.getRequestDispatcher("DashBoard.jsp");
			}else {
				rd = request.getRequestDispatcher("EditCustomer.jsp");
			}
			request.setAttribute("LogInCustomer", customer);
			rd.forward(request, response);
		} else if(operation.equalsIgnoreCase("ChangePassword"))
		{
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equalsIgnoreCase("save")) {
			Customer customer = new Customer();
			customer.setCustomerName(request.getParameter("Customername"));
			customer.setCustomerContact(request.getParameter("Customercontact"));
			customer.setUserName(request.getParameter("userName"));
			customer.setPassword(request.getParameter("password").hashCode()+"");

			Address address = new Address();
			address.setAddressLine1(request.getParameter("addressline1"));
			address.setAddressLine2(request.getParameter("addressline2"));
			address.setState(request.getParameter("State"));
			address.setCity(request.getParameter("City"));
			address.setPincode(request.getParameter("Pincode"));

			customer.setCustomerAddress(address);
			RequestDispatcher rd = null;
			if (customerDAO.addCustomer(customer) == 1) {
				rd = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("regSuccessMsg", "Restristration successfull !!!");
			} else {
				rd = request.getRequestDispatcher("Register.jsp");
				request.setAttribute("regFailMsg", "Restristration successfull !!!");
			}
			rd.forward(request, response);
		}
		else if(operation.equalsIgnoreCase("Customerlogin"))
		{
			String username = request.getParameter("userName");
			String password = request.getParameter("password").hashCode()+"";
			RequestDispatcher rd = null;
			if (customerDAO.customerLogin(username, password)) {
				Customer customer = customerDAO.viewCustomer(username);
				rd = request.getRequestDispatcher("DashBoard.jsp");
				HttpSession session = request.getSession();
				session.setAttribute("un", username);
				//session.setAttribute("Pass", password);
				session.setAttribute("userType", "customer");
				request.setAttribute("LogInSuccesfull", "Welcome " + username + " !!");
				request.setAttribute("LogInCustomer", customer);
			} else {
				rd = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("LoginFailed", "Login failed please check username or password !!!");
			}
			rd.forward(request, response);
		}
			else if(operation.equalsIgnoreCase("adminlogin"))
			{
				String username = request.getParameter("userName");
				String password = request.getParameter("password").hashCode()+"";
				RequestDispatcher rd = null;
				if (adminDAO.adminLogIN(username, password)) {
					rd = request.getRequestDispatcher("DashBoard.jsp");
					HttpSession session = request.getSession();
					session.setAttribute("un", username);
					//session.setAttribute("Pass", password);
					session.setAttribute("userType", "admin");
					request.setAttribute("LogInSuccesfull", "Welcome admin !!");
					
				} else {
					rd = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("LoginFailed", "Login failed please check username or password !!!");
				}
				rd.forward(request, response);
		} else if (operation.equalsIgnoreCase("editCustomer")) {
			Customer customer = new Customer();
			customer.setCustomerName(request.getParameter("Customername"));
			customer.setCustomerContact(request.getParameter("Customercontact"));
			customer.setUserName(request.getParameter("userName"));

			Address address = new Address();
			address.setAddressID(Integer.parseInt(request.getParameter("Addressid")));
			address.setAddressLine1(request.getParameter("addressline1"));
			address.setAddressLine2(request.getParameter("addressline2"));
			address.setState(request.getParameter("State"));
			address.setCity(request.getParameter("City"));
			address.setPincode(request.getParameter("Pincode"));

			customer.setCustomerAddress(address);
			RequestDispatcher rd = null;
			if (customerDAO.updateCustomerDetails(customer) == 1) {
				rd = request.getRequestDispatcher("DashBoard.jsp");
				request.setAttribute("updatesuccess", "Customer updated successfully !!!");
				request.setAttribute("LogInCustomer", customer);
			} else {
				rd = request.getRequestDispatcher("DashBoard.jsp");
				request.setAttribute("upadeFailed", "Problem in updating customer !!!");
				request.setAttribute("LogInCustomer", customer);
			}
			rd.forward(request, response);
		}
		else if (operation.equalsIgnoreCase("ChangePassword"))
		{
			HttpSession session = request.getSession();
			//String OldPass = ((String)session.getAttribute("Pass")).hashCode()+"";
			String username = (String)session.getAttribute("un");
			String oldPass = request.getParameter("oldPass").hashCode()+"";
			String newPass = request.getParameter("NewPassword").hashCode()+"";
			String confirmPass = request.getParameter("ConfirmPassword").hashCode()+"";
			RequestDispatcher rd = null;
			
				if(confirmPass.equalsIgnoreCase(newPass)) {
					if(customerDAO.updateCustomerPassword(username, oldPass, newPass)) {
						session.invalidate();
						rd=request.getRequestDispatcher("Login.jsp");
						request.setAttribute("PassChangeSuccess", "Your Password updated !!! plese try login again with new Password..");
					}else {
						rd=request.getRequestDispatcher("ChangePassword.jsp");
						request.setAttribute("CheckNewPass", "your old password mismacth !!!");
					}
				}else {
					rd=request.getRequestDispatcher("ChangePassword.jsp");
					request.setAttribute("CheckNewPass", "Your new & confirmed password should match !!!");
				}
			
		rd.forward(request, response);
		}
	}
}
