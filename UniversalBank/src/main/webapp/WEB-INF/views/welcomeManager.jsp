<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager Portfolio</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

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
				<h1 class="page-header">Pending Account Approvals</h1>
			</div>
		</div>
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

		<div class="tab-content">
			<div id="home" class="tab-pane fade in active">
				<div class="table-responsive">
					<form:form method="POST" action="manager">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser" items="${disabledCustInfoFromDTO}">
									<tr>
										<td>${disabledUser.firstName}</td>
										<td>${disabledUser.lastName}</td>
										<td>${disabledUser.accountType}</td>
										<td><button type="submit" class="btn btn-success"
												name="approveParam"
												value="approveVal_${disabledUser.userName}">Approve</button></td>
										<td><button type="submit" class="btn btn-danger"
												name="approveParam" value="denyVal_${disabledUser.userName}">Deny</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
