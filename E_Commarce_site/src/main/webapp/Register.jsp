<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

	function CheckPassword(inputtxt) {
		var passw = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})";
		if (inputtxt.value.match(passw)) {
			$("#passwordValidation").html("")
			document.getElementById("register").disabled = false;
			return true;
		} else {
			$("#passwordValidation")
					.html("min 8 characters which contain at least one numeric digit and a special character");
			document.getElementById("register").disabled = true;
			return false;
		}
	}
</script>
</head>
<body>
<jsp:include page="Menu.jsp"/>
<c:if test="${regFailMsg!=null}">
<p style ="color: red"> <c : out value="${regFailMsg}"/></p>
</c:if>
<center>
<h1 class="h1">Customer Registration</h1>
  <form method="post" action="register">
  <input type="hidden" name ="operation" value="save">
<table>
	<tr>
		<td>Customer name :</td>
		<td> <input name="Customername" type="text" /></td>
	</tr>
	<tr>
		<td>Customer contact :</td>
		<td> <input name="Customercontact" type="text" /></td>
	</tr>
	<tr>
		<td>username :</td>
		<td> <input name="userName" type="text" /></td>
	</tr>
	<tr>
		<td>password :</td>
		<td> <input name="password" required="true" id="password" onkeyup="CheckPassword(this)" type="password" /></td>
	</tr>
	<tr>
	<td></td>
	<td><div id="passwordValidation" style="clor: red"></div></td>
	</tr>
	<tr>
		<td>Address Line 1:</td>
		<td><textarea name="addressline1"></textarea></td>
	</tr>
	<tr>
		<td>Address Line 2:</td>
		<td><textarea name="addressline2"></textarea></td>
	</tr>
	<tr>
		<td>State :</td>
		<td> <input name="State" type="text" /></td>
	</tr>
	<tr>
		<td>Pincode :</td>
		<td> <input name="Pincode" type="text" /></td>
	</tr>
	<tr>
		<td>City :</td>
		<td> <input name="City" type="text" /></td>
	</tr>
	<tr>
		<td></td>
		<td> <input type="submit" value="register" id="register" /></td>
	</tr>
</table>
</form>
</center>
<jsp:include page="Footer.jsp"/>
</body>
</html>