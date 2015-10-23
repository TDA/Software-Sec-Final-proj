<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
	<c:url var="logoutUrl" value="j_spring_security_logout"/>
	<form action="${logoutUrl}" method="post">
	  <input type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	<div class="container">
		<h2>Welcome, ${userName }</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#home">Account
					Approvals</a></li>
			<li><a data-toggle="tab" href="#menu1">Critical Transactions</a></li>
			<li><a data-toggle="tab" href="#menu2">Pending Requests</a></li>
			<li><a data-toggle="tab" href="#menu3">Menu 3</a></li>
		</ul>

		<div class="tab-content">
			<div id="home" class="tab-pane fade in active">
				<h3>Pending Approvals</h3>
				<div class="table-responsive">
					<form:form method="POST" action="manager">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
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
			<div id="menu1" class="tab-pane fade">
				<h3>Pending Critical Transactions</h3>
				<div class="table-responsive">
					<form:form method="POST" action="manager/critical_transaction">
						<table id="mytable1" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Transaction ID</th>
									<th>Transaction Type</th>
									<th>Amount</th>
									<th>Comment</th>
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
												value="approveVal_${userTransaction.transactionID}">Approve</button></td>
										<td><button type="submit" class="btn btn-danger"
												name="approveParam1" value="denyVal_${userTransaction.transactionID}">Deny</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="menu2" class="tab-pane fade">
				<h3>Process Requests</h3>
				<div class="table-responsive">
					<form:form method="POST" action="manager/process_requests">
						<table id="mytable2" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Request By</th>
									<th>Request Type</th>
									<th>Approved By</th>
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
												name="approveParam2" value="denyVal_${requestFromUser.requestByUserName}_${requestFromUser.requestType}">Deny</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="menu3" class="tab-pane fade">
				<h3>Menu 3</h3>
				<p>Eaque ipsa quae ab illo inventore veritatis et quasi
					architecto beatae vitae dicta sunt explicabo.</p>
			</div>
		</div>
	</div>

</body>
</html>
