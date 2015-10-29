
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Government Portfolio</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<body>

	<div class="container">
		<h2>Welcome, ${userName }</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#home">Authorize
					PII</a></li>

			<li><a data-toggle="tab" href="#menu1">Modify Authorizations</a></li>

		</ul>

		<div class="tab-content">

			<div id="home" class="tab-pane fade in active">
				<h3>Pending Approvals</h3>
				<div class="table-responsive">
					<form:form method="POST" action="gov">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser" items="${PiiCustInfoFromDTO}">
									<tr>
										<td>${disabledUser.userName}</td>
										<td><button type="submit" class="btn btn-success"
												name="approveParam"
												value="approveVal_${disabledUser.userName}">Approve</button></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>

			<div id="menu1" class="tab-pane fade">
				<h3>Authorized Users</h3>
				<div class="table-responsive">
					<form:form method="POST" action="gov/view_piiusers">
						<table id="mytable" class="table table-bordred table-striped">
							<thead>
								<tr>
									<th>Username</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="disabledUser1" items="${AuthPiiCustInfoFromDTO}">
									<tr>
										<td>${disabledUser1.userName}</td>

										<td><button type="submit" class="btn btn-danger"
												name="approveParam1"
												value="denyVal_${disabledUser1.userName}">Deny</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>


	</div>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>


<!-- Framebreaker script from OWASP for clickjacking  https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE -->
<script>if (top != self) top.location=location</script>
</body>
</html>
