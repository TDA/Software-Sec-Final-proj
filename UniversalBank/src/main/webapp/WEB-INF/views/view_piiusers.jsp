<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<sec:authorize access="hasRole('ROLE_GOV')">
		<div class="container">
			<c:url var="logoutUrl" value="j_spring_security_logout" />
			<form action="${logoutUrl}" method="post">
				<input type="submit" class="btn btn-lg btn-info btn-block"
					value="Log out" /> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<h2>Welcome, ${userName }</h2>

			<ul class="nav nav-tabs">
				<li><a href="${pageContext.request.contextPath}/gov">
						Authorize PII</a></li>
				<li><a href="${pageContext.request.contextPath}/view_piiusers">Modify PII Authorizations</a></li>
		

			</ul>
			<!-- Page Heading -->
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Modify PII Authorizations</h1>
				</div>
			</div>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<div class="table-responsive">
						<form:form method="POST" action="view_piiusers" htmlEscape="true">
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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/keyboard.js"
			charset="UTF-8"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/slib.js"></script>
		<script
			src="${pageContext.request.contextPath}/resources/js/keypress.closure.js"></script>
	</sec:authorize>
</body>
</html>
