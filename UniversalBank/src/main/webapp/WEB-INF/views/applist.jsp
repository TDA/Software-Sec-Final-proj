<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Secured Bank Login Page</title>
</head>  Internal LoginPage   <head>
<body>

<form  method="post" action="${pageContext.request.contextPath}/loginsuccess">
<table>
<tr><td>Customername:</td><td><input name="customer_user_name" type="text" /></td></tr>
<tr><td>Password </td><td><input name="ID" type="password" /></td></tr>
<tr><td>Token </td><td><input name="ID" type="password"></td></tr>
<tr><td> </td><td><input value="Log In" type="submit" /></td></tr>
</table>


</form>



</body>
</html>