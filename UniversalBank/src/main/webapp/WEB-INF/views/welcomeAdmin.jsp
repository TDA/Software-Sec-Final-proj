<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Administrator Portfolio</title>
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
		<h2>Welcome, ${userName }</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#home">Account
					Approvals</a></li>
					
			<li><a data-toggle="tab" href="#menu1">PII Access</a></li>
			<li><a data-toggle="tab" href="#menu2">Delete Accounts</a></li>
			<li><a data-toggle="tab" href="#menu3">Deleted Accounts</a></li>
			<li><a data-toggle="tab" href="#menu4">Change Role Accounts</a></li>
			
		</ul>

		<div class="tab-content">
			
			<div id="home" class="tab-pane fade in active">
				<h3>Internal Users Pending Approvals</h3>
				<div class="table-responsive">
					<form:form method="POST" action="admin">
						<table id="mytable1" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser1" items="${disabledIntInfoFromDTO}">
									<tr>
										<td>${disabledUser1.userName}</td>
										<td>${disabledUser1.firstName}</td>
										<td>${disabledUser1.lastName}</td>
										<td>${disabledUser1.accountType}</td>
										<td><button type="submit" class="btn btn-success"
												name="approveParam"
												value="approveVal_${disabledUser1.userName}">Approve</button></td>
										<td><button type="submit" class="btn btn-danger"
												name="approveParam" value="denyVal1_${disabledUser1.userName}">Delete</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="menu1" class="tab-pane fade">
				<h3>PII Authorized Users</h3>
				<div class="table-responsive">
					<form:form method="POST" action="admin/pii_users">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
									<th>Email</th>
									<th>SSN</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser1" items="${AuthPiiCustInfoFromDTO1}">
									<tr>
										<td>${disabledUser1.userName}</td>
										<td>${disabledUser1.firstName}</td>
										<td>${disabledUser1.lastName}</td>
										<td>${disabledUser1.accountType}</td>
										<td>${disabledUser1.emailAddress}</td>
										<td>${disabledUser1.socialSecurityNumber}</td>
										<td><button type="submit" class="btn btn-danger"
												name="approveParam1" value="denyVal_${disabledUser1.userName}">Deny</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="menu2" class="tab-pane fade">
				<h3>Delete Internal Users</h3>
				<div class="table-responsive">
					<form:form method="POST" action="admin/modify_users">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
							
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser1" items="${IntInfoFromDTO}">
									<tr>
										<td>${disabledUser1.userName}</td>
										<td>${disabledUser1.firstName}</td>
										<td>${disabledUser1.lastName}</td>
										<td>${disabledUser1.accountType}</td>
										<td><button type="submit" class="btn btn-danger"
												name="approveParam2" value="denyVal1_${disabledUser1.userName}">Delete</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="menu3" class="tab-pane fade">
				<h3>Deleted Users</h3>
				<div class="table-responsive">
					<form:form method="POST" action="admin/deleted_users">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
							
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser1" items="${DelIntInfoFromDTO}">
									<tr>
										<td>${disabledUser1.userName}</td>
										<td>${disabledUser1.firstName}</td>
										<td>${disabledUser1.lastName}</td>
										<td>${disabledUser1.accountType}</td>
										<td><button type="submit" class="btn btn-success"
												name="approveParam3" value="approveVal1_${disabledUser1.userName}">Add Again</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			<div id="menu4" class="tab-pane fade">
				<h3>Deleted Users</h3>
				<div class="table-responsive">
					<form:form method="POST" action="admin/change_users">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Account Type</th>
							
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser1" items="${IntInfoFromDTO}">
									<tr>
										<td>${disabledUser1.userName}</td>
										<td>${disabledUser1.firstName}</td>
										<td>${disabledUser1.lastName}</td>
										<td><FONT color="red"><form:errors path="accountType" /></FONT>
										
										<select class="selectpicker form-control" name="accountType">
											<option value="">Select</option>


											<c:forEach var="listValue" items="${myList1}">
												<option value="${listValue}">${listValue}</option>
											</c:forEach>
										</select></td>
										<td><button type="submit" class="btn btn-success"
												name="approveParam4" value="approveVal2_${disabledUser1.userName}">Add Again</button></td>
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
