<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Forgot Password</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>

</head>

<script>
	document.onmousedown = disableclick;
	status = "Right Click Disabled";
	function disableclick(event) {
		if (event.button == 2) {
			alert(status);
			return false;
		}
	}
</script>
<body oncontextmenu="return false">

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="register">Home</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1"></div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<div class="col-lg-12 text-center">
				<br /> <br />
				<h4>Password Recovery</h4>
				<FONT color="red"> <c:if test="${not empty errorMessage}">
                	${errorMessage}
                </c:if>
				</FONT> <FONT color="green"> <c:if
						test="${not empty successMessage}">
                	${successMessage}
                </c:if>
				</FONT>
				<form:form method="POST" action="forgotPassword" autocomplete="off"
					htmlEscape="true">

					<b>User Name:</b>
					<FONT color="red"><form:errors path="userName" /></FONT>
					<input data-toggle="tooltip" title="Enter your user name" type=text
						name="username" id="username" class="form-control"
						style="color: #999;" />
					<br />
					<br />
					<b>Email Address:</b>
					<FONT color="red"><form:errors path="email" /></FONT>
					<input data-toggle="tooltip" title="Enter a valid email"
						type="email" name="email" id="email" class="form-control"
						style="color: #999;" />
					<br />
					<br />
					<b>New Password:</b>
					<FONT color="red"><form:errors path="new_passowrd" /></FONT>
					<input data-toggle="tooltip"
						title="Password criterias: 
											1. Must contain at least one Uppercase letter, one lowercase letter, one digit, one special character 
											2. Length must be between 6 and 15 
											3. Allowed special characters: ! @ # $ { } , % ^ & * + _ . -"
						type="password" name="new_passowrd" id="new_passowrd"
						class="form-control" style="color: #999;" />
					<br />
					<br />
					<b>Confirm New Password:</b>
					<FONT color="red"><form:errors path="confirm_new_passowrd" /></FONT>
					<input type="password" name="confirm_new_passowrd"
						id="confirm_new_passowrd" class="form-control"
						style="color: #999;" />
					<br />
					<br />
					<input type="submit" name="Change Password" id="changepassword"
						value="Change Password" />
				</form:form>
			</div>
		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- jQuery Version 1.11.0 -->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<!-- jQuery Version 1.11.0 -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>


	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>
		

	<!-- Framebreaker script from OWASP for clickjacking  https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE -->
	<script>if (top != self) top.location=location</script>
	<script>
	
		$('input[type=text][name=username]').tooltip({
			placement : "left",
			trigger : "focus"
		});
		$('input[type=password][name=new_passowrd]').tooltip({
			placement : "left",
			trigger : "focus"
		});
		$('input[type=email][name=email]').tooltip({
			placement : "left",
			trigger : "focus"
		});
		</script>
</body>

</html>
