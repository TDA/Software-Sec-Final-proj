<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Page</title>


<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body oncontextmenu="return false">
	<sec:authorize access="hasRole('ROLE_USER')">
		<h1>View Your Transactions </h1>
		</sec:authorize>
	<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>
    <table style="width:80%">
  			<tr>
    			<th>Account Number</th>
    			<th>Transferred to</th>		
   				 <th>Amount Transferred($)</th>
   				 <th>balance($)</th>
   				 <th>Transaction Type</th>
  </tr>
        <c:forEach items="${userInformation}" var="employee">
            <tr>
                <td><c:out value="${employee.customer_id}" /></td>
                <td><c:out value="${employee.merchant_id}" /></td>
                <td><c:out value="${employee.amount}" /></td>
                <td><c:out value="${employee.balance}" /></td>
                <td><c:out value="${employee.transaction_type}" /></td>
            </tr>
        </c:forEach>

    </table>
   

</body>
</html>