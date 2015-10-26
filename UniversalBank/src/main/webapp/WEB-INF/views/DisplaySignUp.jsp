<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src='https://www.google.com/recaptcha/api.js'></script>

<title>Universal Bank Edit Information page</title>

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
				<li><a href="${pageContext.request.contextPath}/transfer">Transfer</a></li>
				<li><a href="${pageContext.request.contextPath}/Credit">Credit</a></li>
				<li><a
					href="${pageContext.request.contextPath}/ViewTransactions">View
						My Account</a></li>
				<li><a href="${pageContext.request.contextPath}/Debit">Debit</a></li>
				<li><a href="${pageContext.request.contextPath}/DisplaySignUp">EditInfo</a></li>
				<li><a href="${pageContext.request.contextPath}/UserRequest">Pending
						Transactions</a></li>
				<li><a href="${pageContext.request.contextPath}/balance">Balance</a></li>
			</ul>
		</div>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Enter your information</h1>
			</div>
		</div>
		<div class="panel-body">
			<div id="morris-area-chart">

				<c:if test="${not empty successMsg}">
					<h4>
						${successMsg} <a href="index"> Click here to LogIn!</a>
					</h4>
				</c:if>
				<form:form method="POST" action="DisplaySignUp"
					modelAttribute="editForm" autocomplete="off">
					<br />
					<br />
					<c:if test="${not empty errorMsg}">
						<h3>${errorMsg}</h3>
					</c:if>

					<b>Email Address:</b>
					<br />
					<input type="text" name="emailAddress" size="10"
						class="form-control" id="email" style="color: #999;" />
					<br />
					<br />
					<b>SSN</b>
					<input type="text" name="socialSecurityNumber" size="10"
						class="form-control" id="socialSecurityNumber"
						style="color: #999;" />
					<br />

					<br />
					<h4>
						<input type="submit" style="margin-right: 5%" name="edit"
							id="edit" value="edit" />


					</h4>

				</form:form>
				<form:form method="POST" action="DisplaySignUp/delete"
					modelAttribute="delete" autocomplete="off">
					<input type="submit" style="margin-right: 5%" name="delete"
						id="delete" value="Delete Account" />
				</form:form>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/slib.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/keypress.closure.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>
</body>
</html>
