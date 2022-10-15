<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Menu.jsp"/>
<center>
<p style ="color: green; text-align: center;"> <c:out value="${regSuccessMsg}"/></p>
<p style ="color: red; text-align: center;"> <c:out value="${LoginFailed}"/></p>
<p style ="color: green; text-align: center;"> <c:out value="${LogOutSuccess}"/></p>
<p style ="color: green; text-align: center;"> <c:out value="${PassChangeSuccess}"/></p>
<h1 class="h1">User Login</h1>
  <form method="post" action="register">
<table>
	<tr>
		<td>username :</td>
		<td> <input name="userName" type="text" /></td>
	</tr>
	<tr>
		<td>password :</td>
		<td> <input name="password" type="password" /></td>
	</tr>
	<tr>
		<td>type</td>
			<td><select name="operation">
				<option value="customerLogin">Customer</option>
				<option value="adminLogin">Admin</option>
			</select></td>
	</tr>
	<tr>
		<td></td>
		<td> <input type="submit" value="Login" /></td>
	</tr>
</table>
<a style ="color: white">! </a>
<p><h5 style="font-family:'Comic Sans MS';  text-align: center; color: red" >New Member ? <br> Please register first !!! <a href="Register.jsp">register here ... </a></h5></p>
</form>
<a style ="color: white">! </a>
</center>
<jsp:include page="Footer.jsp"/>
</body>
</html>