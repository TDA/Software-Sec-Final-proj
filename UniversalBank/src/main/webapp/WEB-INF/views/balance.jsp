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

<title>Universal Bank Balance-User</title>

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
					<c:if test="${role=='ROLE_MERCHANT'}">
				<li><a href="${pageContext.request.contextPath}/MerchantTransfer"> Merchant Transfer</a></li>
				</c:if>
			
				<li><a href="${pageContext.request.contextPath}/Credit">Credit</a></li>
				<li><a
					href="${pageContext.request.contextPath}/ViewTransactions">View
						My Account</a></li>
				<li><a href="${pageContext.request.contextPath}/Debit">Debit</a></li>
				<li><a href="${pageContext.request.contextPath}/DisplaySignUp">EditInfo</a></li>
				<li><a href="${pageContext.request.contextPath}/UserRequest">Pending
						Transactions</a></li>
			</ul>
		</div>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Balance</h1>
			</div>
		</div>
		<div class="panel-body">
			<br /> <b>Your Checking Account Balance is: $ ${bal} <br />
				
					Your Saving Account Balance is: $ ${bal1} <br />
				
					Please Follow the View My Account Link to Check your Transactions <br />
				<a href="${pageContext.request.contextPath}/ViewTransactions">View My Account</a>
						 <br /></b> <FONT color="red"> * Please Note that
				Balance will be Updated after Approval of Transaction by the Bank</FONT>	
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>
<!-- Framebreaker script from OWASP for clickjacking  https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE -->
<script>if (top != self) top.location=location</script>
</body>
</html>
