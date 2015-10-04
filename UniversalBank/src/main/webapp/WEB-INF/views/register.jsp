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

<title>Universal Bank Sign Up page</title>

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
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>

<script>
    	function check() {
    		 document.getElementById("male").checked = true;
    	}
    	
    	function uncheck() {
   		 document.getElementById("female").checked = false;
   		}
    </script>

</head>
<body oncontextmenu="return false">

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Sign up for an account</a>
			</div>
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Sign Up</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> Enter your information
									or <a href="index"><b>Log In</b></a>
								</h3>
							</div>

							<div class="panel-body">
								<div id="morris-area-chart">
									<form:form method="POST" action="register"
										modelAttribute="registerForm" autocomplete="off">
										<br />
										<br />
										<c:if test="${not empty errorMsg}">
											<h3>${errorMsg}</h3>
										</c:if>
										<b>First Name:</b>
										<FONT color="red"><form:errors path="firstName" /></FONT>
										<br />
										<input type="text" name="firstName" size="10" class="form-control" id="f_name"
											style="color: #999;" />
										<br />
										<br />
										<b>Last Name:</b>
										<FONT color="red"><form:errors path="lastName" /></FONT>
										<br />
										<input type="text" name="lastName"size="10" class="form-control" id="l_name"
											style="color: #999;" />
										<br />
										<br />
										<b>User Name:</b>
										<FONT color="red"><form:errors path="userName" /></FONT>
										<br />
										<input type="text" name="userName" size="10" class="form-control" id="username"
											style="color: #999;" />
										<br />
										<br />
										<b>Password:</b>
										<FONT color="red"><form:errors path="password" /></FONT>
										<br />
										<input type="password" name="password" size="10" class="form-control" id="password"
											style="color: #999;" />
										<br />
										<br />
										<b>Confirm Password:</b>
										<br />
										<input type="password" name="confirmPassword" size="10" class="form-control" id="cfrm_pwd"
											style="color: #999;" />
										<br />
										<br />
										<b>Account Type:</b>
										<FONT color="red"><form:errors path="accountType" /></FONT>
										<br />
										<select class="selectpicker form-control" name="accountType">
											<option value="">Select</option>
											<option value="Individual">Individual</option>
											<option value="Merchant">Merchant</option>
										</select>
										<br />
										<br />
										<b>Email Address:</b>
										<br />
										<input type="email" name="emailAddress" size="10" class="form-control" id="email"
											style="color: #999;" />
										<br />
										<br />
										<b>Social Security Number:</b>
										<FONT color="red"><form:errors
												path="socialSecurityNumber" /> </FONT>
										<br />
										<input type="text" value="" name="socialSecurityNumber" size="10" class="form-control"
											id="socialSecurityNumber" style="color: #999;" />
										<br />
										<br />

										<br />
										<h4>
											<input type="submit" style="margin-right: 5%" name="login"
												id="log_in" value="Register" />
										</h4>

										<c:if test="${not empty successMsg}">
											<h4>
												${successMsg} <a href="index"> Click here to LogIn!</a>
											</h4>
										</c:if>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /.container -->

	<!-- jQuery Version 1.11.0 -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>

</body>

</html>
