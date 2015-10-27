<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Universal Bank HomePage-User</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body oncontextmenu="return false">
<% response.setHeader("Cache-Control","no-cache");    
response.setHeader("Pragma","no-cache"); %>

	<div class="container">
		<c:url var="logoutUrl" value="j_spring_security_logout" />
		<form action="${logoutUrl}" method="post">
			<input type="submit" class="btn btn-lg btn-info btn-block"
				value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<h2>Welcome, ${userName }</h2>

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
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Account Home Page</h1>
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
</body>
</html>
