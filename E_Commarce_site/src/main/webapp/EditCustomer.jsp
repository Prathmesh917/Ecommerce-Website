<%@page import="com.OnlineShopping.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updating Customer</title>
</head>
<body>
<c:if test="${un==null}"><jsp:forward page="Login.jsp"/> </c:if>
<%Customer customer = (Customer)request.getAttribute("LogInCustomer"); %>
<jsp:include page="Menu.jsp"/>
<c:if test="${regFailMsg!=null}">
<p style ="color: red"> <c : out value="${regFailMsg}"/></p>
</c:if>
<center>
<a style ="color: white">! </a>
<h1 class="h1" style="font-family:'Comic Sans MS'; color:green">Update yourself !!! </h1>
<a style ="color: white">! </a>
  <form method="post" action="register">
  <input type="hidden" name ="operation" value="editCustomer">
<table>
	<tr>
		<td>Customer name :</td>
		<td> <input name="Customername" value="<%=customer.getCustomerName() %>" required type="text" /></td>
	</tr>
	<tr>
		<td>Contact :</td>
		<td> <input name="Customercontact" value="<%=customer.getCustomerContact() %>" required type="text" /></td>
	</tr>
	<tr>
		<td>Username :</td>
		<td> <input name="userName" value="<%=customer.getUserName() %>" readonly type="text" /></td>
	</tr>
	<tr>
	<tr>
		<td> <input type="hidden" name="Addressid" value="<%=customer.getCustomerAddress().getAddressID() %>" /></td>
	</tr>
	<tr>
	<td><a style ="color: white">! </a></td>
	</tr>
	<tr>
		<td>Address Line 1:</td>
		<td><input name="addressline1" value="<%=customer.getCustomerAddress().getAddressLine1() %>"  type="text" /></td>
	</tr>
	<tr>
		<td>Address Line 2:</td>
		<td><input name="addressline2" value=<%=customer.getCustomerAddress().getAddressLine2() %>  type="text" /></td>
	</tr>
	<tr>
		<td>State :</td>
		<td> <input name="State" value="<%=customer.getCustomerAddress().getState() %>"  type="text" /></td>
	</tr>
	<tr>
		<td>Pincode :</td>
		<td> <input name="Pincode" value="<%=customer.getCustomerAddress().getPincode() %>"  type="text" /></td>
	</tr>
	<tr>
		<td>City :</td>
		<td><input name="City" value="<%=customer.getCustomerAddress().getCity() %>" type="text" /></td>
	</tr>
	<tr>
	<td><a style ="color: white">! </a></td>
	</tr>
	<tr>
		<td></td>
		<td> <input type="submit" value="Update" /></td>
	</tr>
</table>
<a style ="color: white">! </a>
</form>
</center>
<jsp:include page="Footer.jsp"/>
</body>
</html>