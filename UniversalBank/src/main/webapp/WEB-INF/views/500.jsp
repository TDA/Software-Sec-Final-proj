<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/stylish-portfolio.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/keyboard.css"
	rel="stylesheet" type="text/css">
</head>
<body oncontextmenu="return false">
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
	%>

	<div class="container">
		<c:url var="logoutUrl" value="j_spring_security_logout" />
		<form action="${logoutUrl}" method="post" >
			<input type="submit" class="btn btn-lg btn-info btn-block"
				value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<h2>Welcome, Error</h2>

		<ul class="nav nav-tabs">
		
		 	<li><a href="${pageContext.request.contextPath}/500">Transfer</a></li>
		 		
				
			<li><a href="${pageContext.request.contextPath}/500">Credit</a></li>
			<li><a
				href="${pageContext.request.contextPath}/500">View
					My Account</a></li>
			
			<li><a href="${pageContext.request.contextPath}/500">Debit</a></li>
			<li><a href="${pageContext.request.contextPath}/500">EditInfo</a></li>
			<li><a href="${pageContext.request.contextPath}/500">Pending
					Transactions</a></li>
			<li><a href="${pageContext.request.contextPath}/500">Balance</a></li>
		</ul>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Account Home Page</h1>
				<h4>Postgres Error: 03000	sql_statement_not_yet_complete</h4>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>
</html>
