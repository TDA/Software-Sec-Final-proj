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
					<b>Address:</b>
					<FONT color="red"><form:errors path="address" /> </FONT>
					<br />
					<input data-toggle="tooltip"
						title="Should not exceed 50 characters!" type="text" value=""
						name="address" size="10" class="form-control" id="address"
						maxlength="50" style="color: #999;" />
					<br />
					<br />
					<b>Phone Number:</b>
					<FONT color="red"><form:errors path="phoneNumber" /> </FONT>
					<br />
					<input data-toggle="tooltip"
						title="10 digit phone number without dashes" type="text" value=""
						name="phoneNumber" size="10" class="form-control" id="phoneNumber"
						maxlength="10" style="color: #999;" />
					<br />
					<br />
					<b>Gender:</b>
					<FONT color="red"><form:errors path="sex" /></FONT>
					<br />
					<select data-toggle="tooltip" title="Select One Option"
						class="selectpicker form-control" id="selector2" name="sex">
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
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
