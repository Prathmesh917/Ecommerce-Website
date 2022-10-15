<%@page import="com.OnlineShopping.model.ProductInfo"%>
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
<%ProductInfo Product =(ProductInfo)request.getAttribute("product"); %>
<form method="post" action="product">
  <input type="hidden" name ="operation" value="editProduct">
 <center>
 <a style ="color: white">Welcome !!!! </a>
<table>
	<tr>
		<td> Name: </td>
		<td><input name="pName" value="<%=Product.getProductName()%>" required="true" type="text" /></td>
	</tr>
	<tr>
		<td> Brand: </td>
		<td><input name="Brand" value="<%=Product.getProductBrand()%>" required="true" type="text" /></td>
	</tr>
	<tr>
		<td> Category: </td>
		<td><input name="Category" value="<%=Product.getProductCategory()%>" required="true" type="text" /></td>
	</tr>
	<tr>
		<td> Price: </td>
		<td><input name="Price" value="<%=Product.getProductPrice()%>" required="true" type="text" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="hidden" name="pID" value="<%=Product.getProductId() %>"/></td>
	</tr>
	<tr>
		<td> </td>
		<td><input type="submit" value= "Update Product Info"/></td>
	</tr>
</table>
<a style ="color: white">Welcome !!!! </a>
</center>
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>