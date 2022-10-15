<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updating Password</title>
</head>
<body>
 <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<jsp:include page="Menu.jsp"/>
<p style ="color: red; text-align: center;"> <c:out value="${CheckNewPass}"/></p>
<p style ="color: red; text-align: center;"> <c:out value="${CheckOldPass}"/></p>
<form method="post" action="register">
  <input type="hidden" name ="operation" value="ChangePassword">
 <center>
 <a style ="color: white">! </a>
 <h2 class="h2" style="font-family:'Comic Sans MS'; color:green">Change Password !!! </h2>
 <a style ="color: white">! </a>
<table>
	<tr>
		<td> Old Password: </td>
		<td><input type="password" name= "oldPass"/></td>
	</tr>
	<tr>
		<td> New Password: </td>
		<td><input type="password" name= "NewPassword"/></td>
	</tr>
	<tr>
		<td> Confirm Password: </td>
		<td><input type="password" name= "ConfirmPassword"/></td>
	</tr>
	<tr>
		<td> </td>
		<td><input type="submit" value= "Update Password"/></td>
	</tr>
</table>
<a style ="color: white">Welcome !!!! </a>
</center>
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>