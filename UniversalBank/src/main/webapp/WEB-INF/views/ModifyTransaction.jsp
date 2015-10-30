<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src='https://www.google.com/recaptcha/api.js'></script>

<title>Universal Bank Modify Transactions</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</head>
<body oncontextmenu="return false">
	<sec:authorize access="hasRole('ROLE_CLERK')">
		<!-- Page Content -->
		<div class="container">

			<c:url var="logoutUrl" value="j_spring_security_logout" />
			<form action="${logoutUrl}" method="post">
				<input type="submit" class="btn btn-lg btn-info btn-block"
					value="Log out" /> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<h2>Welcome, ${userName }</h2>

			<div class="container">
				<ul class="nav nav-tabs">
					<li><a
						href="${pageContext.request.contextPath}/ViewTransactionRegularEmployee">View
							Transaction</a></li>
					<li><a
						href="${pageContext.request.contextPath}/ModifyTransaction">Modify
							Transaction</a></li>
					<li><a
						href="${pageContext.request.contextPath}/DeleteTransaction">Delete
							Transaction</a></li>
					<li><a
						href="${pageContext.request.contextPath}/AccountDeleteRequest">Account
							Delete Request</a></li>
				</ul>
			</div>
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Available Transactions For
						Modification</h1>
				</div>
			</div>


			<form:form method="POST" action="ModifyTransaction"
				modelAttribute="ApproveForm" autocomplete="off" htmlEscape="true">
				
				<c:if test="${not empty errorMsg}">
					<h3>${errorMsg}</h3>
				</c:if>
				<table style="width: 80%">
					<tr>
						<th>Transaction ID</th>
						<th>Transaction Type</th>
						<th>Old Amount($)</th>
						<th>Modified Amount($)</th>
						<th>Comment</th>
						<th></th>
					</tr>
					<c:forEach items="${userInformation}" var="transaction">
						<tr>
							<td><c:out value="${transaction.transactionID}" /></td>
							<td><c:out value="${transaction.transactionType}" /></td>
							<td><c:out value="${transaction.amount}" /></td>
							<td><input type="text"
								id="ModifiedAmount_${transaction.transactionID}"></td>
								<input type="hidden" id="newfield" name="newfield"
					value="${transaction.transactionType}" />
							<td><c:out value="${transaction.comments}" /></td>
							<td><button type="submit" name="modifyParamRegularEmployee"
									onclick="this.value=document.getElementById('ModifiedAmount_${transaction.transactionID}').value + '_' + ${transaction.transactionID} + '_' + ${transaction.transactionID};" />Submit
								</button></td>
						</tr>
					</c:forEach>
				</table>
			</form:form>
		</div>
	</sec:authorize>
	<!-- Framebreaker script from OWASP for clickjacking  https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE -->
	<script>
		if (top != self)
			top.location = location
	</script>
</body>
</html>