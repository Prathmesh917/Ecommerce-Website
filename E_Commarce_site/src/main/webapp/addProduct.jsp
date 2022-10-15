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
<c:if test="${regFailMsg!=null}">
<p style ="color: red"> <c : out value="${regFailMsg}"/></p>
</c:if>
<center>
<h1 class="h1">Customer Registration</h1>
  <form method="post" action="product">
  <input type="hidden" name ="operation" value="addProduct">
<table>
	<tr>
		<td>Product name :</td>
		<td> <input name="Productname" type="text" /></td>
	</tr>
	<tr>
		<td>Product category :</td>
		<td> <input name="Product category" type="text" /></td>
	</tr>
	<tr>
		<td>Brand :</td>
		<td> <input name="Brand" type="text" /></td>
	</tr>
	<tr>
		<td>Price :</td>
		<td> <input name="Price" type="number" /></td>
	</tr>
	<tr>
		<td></td>
		<td> <input type="submit" value="Add Product" /></td>
	</tr>
</table>
</form>
</center>
<jsp:include page="Footer.jsp"/>
</body>
</html>