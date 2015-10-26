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

<title>User Request Processing</title>

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
		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Process User Requests</h1>
			</div>
		</div>
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
		<form:form method="POST" action="process_requests">
			<table id="mytable2" class="table table-bordred table-striped">
				<thead>
					<tr>
						<th>Request By</th>
						<th>Request Type</th>
						<th>Approved By</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="requestFromUser" items="${requestFromUser}">
						<tr>
							<td>${requestFromUser.requesterName}</td>
							<td>${requestFromUser.requestType}</td>
							<td>${requestFromUser.approverName}</td>
							<td><button type="submit" class="btn btn-success"
									name="approveParam2"
									value="approveVal_${requestFromUser.requestByUserName}_${requestFromUser.requestType}">Approve</button></td>
							<td><button type="submit" class="btn btn-danger"
									name="approveParam2"
									value="denyVal_${requestFromUser.requestByUserName}_${requestFromUser.requestType}">Deny</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>