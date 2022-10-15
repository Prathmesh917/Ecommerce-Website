<%@page import="com.OnlineShopping.model.Cart"%>
<%@page import="com.OnlineShopping.model.ProductInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Menu.jsp"/>
<c:if test="${cartAddedSuccess!=null}">
<p style ="color: green"> <c:out value="${cartAddedSuccess}"/></p>
</c:if>
<% List<Cart> cartListByCustomer = (List<Cart>) request.getAttribute("cartListByCustomer"); 

String userType=(String) session.getAttribute("userType");%>
 <% if(cartListByCustomer!=null && cartListByCustomer.size()>0){%>
 <table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Cart ID </th>
				<th scope="col">Product Name</th>
				<th scope="col">Quantity</th>
				<th scope="col">Update Cart</th>
				<th scope="col">Delete</th>
			</tr>
		</thead>
		<tbody>
		<%
		for (Cart each: cartListByCustomer) {%>
		<form action="cart" method="post">
		<input type="hidden" name="operation" value="updateCart"/>
		<input type="hidden" name="productID" value="<%=each.getProductID()%>"/>
		<input type="hidden" name="cartID" value="<%=each.getCartID()%>"/>
			<tr scpoe ="row">
				<td><%=each.getCartID() %></td>
				<td><%=each.getProduct().getProductName() %></td>
				<td><input type="number" name="CartQuantity" value="<%=each.getQuantity() %>"/></td>
				<td><input type="submit" value="Update Quantity"/>
					</td>
				<td></td>
				<td><a
					href="cart?operation=delete&cartid=<%=each.getCartID()%>">Delete</a></td>
				<td></td>
			</tr>
			</form>
			<%} %>			
			<tr>
			<td><a class="banner-button btn rounded-pill btn-outline-primary btn-lg px-1" href="order?operation=placeOrder" role="button">Place order</a></td>
			<td><a class="banner-button btn rounded-pill btn-outline-primary btn-lg px-1" href="product?operation=viewAllProduct" role="button">Continue Shopping</a></td>
			</tr>			 
		</tbody>
	</table>
	<%} else { %>
	<center>
	<p style color ="red"><h3>Your cart is Empty !!!!! </h3> <a class="banner-button btn rounded-pill btn-outline-primary btn-lg px-1" href="product?operation=viewAllProduct" role="button" >Continue Shopping</a></p>
	</center>
	<%} %>
	<jsp:include page="Footer.jsp"/>
</body>
</html>