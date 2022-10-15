<%@page import="com.OnlineShopping.model.orderProductInfo"%>
<%@page import="com.OnlineShopping.model.Order"%>
<%@ page language="java" import="java.util.*"
	import="com.rakshit.model.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	padding-top: 50px;
	background-color: #34495e;
}

.hiddenRow {
	padding: 0 !important;
}
</style>
</head>
<body>

	<%
	String username=(String)session.getAttribute("un");
if(username==null){ %>
	<jsp:forward page="login.jsp" />
	<%} %>
	<%  
	List<Order> listOfOrders=(List<Order>) request.getAttribute("ListOfOrder"); 
	String userType =(String) session.getAttribute("userType");
	%>
<jsp:include page="Menu.jsp" />
<c:if test="${orderCancelledSuccessfull!=null}">
<p style ="color: red"> <c : out value="${orderCancelledSuccessfull}"/></p>
</c:if>
	<div class="container py-5">
		<div class="row">

			<div class="col-lg-12">

				<div class="row">
					<div class="col-md-12">

						<div class="container mt-5">
							<div class="d-flex justify-content-center row">
								<div class="col-md-10">
									<div class="rounded">
										
										<% int count=1; %>
										<div class="container">
											<div class="col-md-12">
												<div class="panel panel-default">
													<div class="panel-heading">Order Placed !!!!</div>
													<div class="panel-body">
														<table class="table table-condensed table-striped">
															<thead>
																<tr>
																	<th></th>
																	<th>Sr. no</th>
																	<th>order id</th>
																	<%if(!userType.equalsIgnoreCase("customer")){ %>
																	<th>CustomerID</th>
																	<%} %>
																	<th>Grand total</th>
																	<%if(userType.equalsIgnoreCase("customer")){ %>
																	<th>Action</th>
																	<%} %>
																</tr>
															</thead>

															<tbody>
																<% for(Order order:listOfOrders){ %>
																<tr data-toggle="collapse" data-target="#demo1"
																	class="accordion-toggle">
																	<td><button class="btn btn-default btn-xs">
																			<span class="glyphicon glyphicon-eye-open"></span>
																		</button></td>
																	<td><%=count++ %></td>
																	<td><%=order.getOdrerID() %></td>
																	<%if(!userType.equalsIgnoreCase("customer")){ %>
																	<td><%=order.getCustomerID() %></td>
																	<%} %>
																	<td><%=order.getTotalOrderAmount() %></td>
																	<%if(userType.equalsIgnoreCase("customer")){ %>
																	<td><a href="order?operation=cancelOrder&orderid=<%=order.getOdrerID()%>">Cancel Order</a></td>
																	<%} %>
																</tr>
																
																<tr>
																	<td colspan="12" >
																		<div  id="demo1">
																			<table class="table table-striped">
																				<thead>
																					<tr class="info">
																						<th>Product Name</th>
																						<th>Product Price</th>
																						<th>Quantity</th>
																						<th>Sub-total</th>
																					</tr>
																				</thead>
																<% for(orderProductInfo orderItem:order.getProductInfo()){ %>
																				<tbody>
																					<tr>
																						<td><%=orderItem.getOrderProduct().getProductName()%></td>
																						<td><%=orderItem.getOrderProduct().getProductPrice()%></td>
																						<td><%=orderItem.getQuantity() %></td>
																						<td><%=orderItem.getSubTotal() %></td>
																					</tr>
																				</tbody>
																				<%} %>
																			</table>
																		</div>
																	</td>
																</tr>
																<%} %>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- End Content -->


	<jsp:include page="Footer.jsp" />
</body>
</html>