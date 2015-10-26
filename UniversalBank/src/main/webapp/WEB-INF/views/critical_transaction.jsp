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

<title>Universal Bank Critical Transactions</title>

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
			<li><a href="${pageContext.request.contextPath}/manager">Pending
					Account Approvals</a></li>
			<li><a
				href="${pageContext.request.contextPath}/critical_transaction">Critical
					Transactions</a></li>
			<li><a
				href="${pageContext.request.contextPath}/process_requests">Process
					User Requests</a></li>
		</ul>
		</div>
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Critical Transactions</h1>
			</div>
		</div>
		<form:form method="POST" action="critical_transaction"
			modelAttribute="ApproveForm" autocomplete="off">
			<table id="mytable1" class="table table-bordred table-striped">
				<thead>
					<tr>
						<th>Transaction ID</th>
						<th>Transaction Type</th>
						<th>Amount</th>
						<th>Comment</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="userTransaction" items="${userTransactions}">
						<tr>
							<td>${userTransaction.transactionID}</td>
							<td>${userTransaction.transactionType}</td>
							<td>${userTransaction.amount}</td>
							<td>${userTransaction.comments}</td>
							<td><button type="submit" class="btn btn-success"
									name="approveParam1"
									value="approveVal_${userTransaction.transactionID}_${userTransaction.amount}">Approve</button></td>
							<td><button type="submit" class="btn btn-danger"
									name="approveParam1"
									value="denyVal_${userTransaction.transactionID}_${userTransaction.amount}">Deny</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>
