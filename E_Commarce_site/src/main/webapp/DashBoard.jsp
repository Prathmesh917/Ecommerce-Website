<%@page import="com.OnlineShopping.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<c:if test="${un==null}">
	<jsp:forward page="Login.jsp"/>
</c:if>
<body>
<c:if test="${un==null}">
<jsp:forward page="Login.jsp"/>
</c:if>
<%Customer customer = (Customer)request.getAttribute("LogInCustomer"); %>
<jsp:include page="Menu.jsp"/>

<c:if test="${LogInSuccesfull!=null}">
<p style ="color: green; text-align: center;"> <c:out value="${LogInSuccesfull}"/></p>
</c:if>
<c:if test="${updatesuccess!=null}">
<p style ="color: green; text-align: center;"> <c:out value="${updatesuccess}"/></p>
</c:if>
<c:if test="${upadeFailed!=null}">
<p style ="color: gree; text-align: center;n"> <c:out value="${upadeFailed}"/></p>
</c:if>
<a style ="color: white">! </a>
<c:if test="${un!=null }">
<h2 style="font-family:'Comic Sans MS';  text-align: center;"> <c:out value="${un}"/>'s Dash board</h2>
</c:if>
<style type ="text/css">
lable {
width:100px;
display:inline-block;
}
legend{
text-align: center;
color: royalBlue;
}
#pwd{
text-align: center;
}
<% if (((String)session.getAttribute("userType")).equalsIgnoreCase("customer")){%>
</style>
	<form action="#">
		<fieldset disable>
		<center>
		<legend style="font-family:'Comic Sans MS'"> <b>Personal Details</b>
		</legend>		
			<table>
	<tr>
		<td>Customer name :</td>
		<td> <input name="Customername" value="<%=customer.getCustomerName() %>" readonly type="text" /></td>
	</tr>
	<tr>
		<td>Contact :</td>
		<td> <input name="Customercontact" value="<%=customer.getCustomerContact() %>" readonly type="text" /></td>
	</tr>
	<tr>
		<td>Username :</td>
		<td> <input name="userName" value="<%=customer.getUserName() %>" readonly type="text" /></td>
	</tr></table>		
			</center>
		</fieldset>
	</form>
	<a style ="color: white">! </a>
	<form action="#">
		<fieldset disable>
		<center>
		<legend style="font-family:'Comic Sans MS'"> <b>Address Details</b>
		</legend>
	<table>
	<tr>
		<td>Address Line 1:</td>
		<td><input name="addressline1" value="<%=customer.getCustomerAddress().getAddressLine1() %>" readonly type="text" /></td>
	</tr>
	<tr>
		<td>Address Line 2:</td>
		<td><input name="addressline2" value=<%=customer.getCustomerAddress().getAddressLine2() %> readonly type="text" /></td>
	</tr>
	<tr>
		<td>State :</td>
		<td> <input name="State" value="<%=customer.getCustomerAddress().getState() %>" readonly type="text" /></td>
	</tr>
	<tr>
		<td>Pincode :</td>
		<td> <input name="Pincode" value="<%=customer.getCustomerAddress().getPincode() %>" readonly type="text" /></td>
	</tr>
	<tr>
		<td>City :</td>
		<td><input name="City" value="<%=customer.getCustomerAddress().getCity() %>" readonly type="text" /></td>
	</tr>
	<tr>
	</table>
			<a style ="color: white">! </a>
			</center>
		</fieldset>
	</form>
	<%} if (((String)session.getAttribute("userType")).equalsIgnoreCase("admin")){ %>
	<p>Welcomer Admin !!! </p>
	<%} %>
	<jsp:include page="Footer.jsp"/>
</body>
</html>