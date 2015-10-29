
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

<title>Universal Bank Transfer page</title>

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
<body oncontextmenu="return false">
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
	%>
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
					<li><a
						href="${pageContext.request.contextPath}/MerchantTransfer">
							Merchant Transfer</a></li>
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
				<h1 class="page-header">Transfer</h1>
			</div>
		</div>
		<div class="panel-body">
			<div id="morris-area-chart">

				<c:if test="${not empty successMsg}">
					<h4>
						${successMsg} <a href="index"> Click here to LogIn!</a>
					</h4>
				</c:if>
				<form:form method="POST" action="transfer"
					modelAttribute="transferForm" autocomplete="off" htmlEscape="true">
					<br />
					<br />
					<Font Color="green">NOTE: You need one time password to
						perform transaction, click on 'Generate OTP' and check your email
						for password. </Font>
					<br />
					<br />
					<c:if test="${not empty errorMsg}">
						<h3>${errorMsg}</h3>
					</c:if>
					<br />
					<b>Account Type:</b>
					<FONT color="red"><form:errors path="accountType" /></FONT>
					<br />
					<select class="selectpicker form-control" name="accountType">
						<option value="">Select</option>
						<option value="checking">checking</option>
						<option value="savings">savings</option>
						<%-- <c:forEach var="bankval" items="${mylist}">
												<option value="${bankval}">${bankval}</option>
											</c:forEach> --%>
					</select>
					<br />
					<br />
					<b> Enter to AccountId</b>
					<FONT color="red"><form:errors path="ToTransactionAccountID" /></FONT>

					<br />
					<input type="text" name="ToTransactionAccountID" size="10"
						class="form-control" id="toTransactionAccountID"
						style="color: #999;" />
					<br />
					<br />
					<b>Amount</b>
					<FONT color="red"><form:errors path="amount" /></FONT>
					<br />
					<input type="text" name="amount" size="10" class="form-control"
						id="amount" style="color: #999;" />
					<br />
					<br />
					<b>OTP</b>
					<FONT color="red"><form:errors path="otp" /></FONT>
					<br />
					<input type="text" name="otp" size="10" class="form-control"
						id="otp" style="color: #999;" />
					<br />
					<br />

					<Font Color="red">By performing the Transfer you authroise
						the bank to modify,delete and access your transactions</Font>
					<br />
					<br />
					<h4>
						<input type="submit" style="margin-right: 5%" name="Transfer"
							id="transfer" value="Transfer" /> <br /> <br /> <input
							type="submit" style="margin-right: 5%" name="Generate OTP"
							id="Generate OTP" value="Generate OTP" />

					</h4>


				</form:form>

			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>


	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>
	<!-- Framebreaker script from OWASP for clickjacking  https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE -->
	<script>
		if (top != self)
			top.location = location
	</script>
</body>
</html>