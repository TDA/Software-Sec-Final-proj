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

<title>Universal Bank HomePage-User</title>

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
				<a class="navbar-brand" href="#">Success</a>
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
						<h1 class="page-header">success</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> Acknowledgement
									
								</h3>
							</div>

							<div class="panel-body">
								<div id="morris-area-chart">

									
	<sec:authorize access="hasRole('ROLE_INDIVIDUAL')">
	
	<c:url var="logoutUrl" value="j_spring_security_logout" />
		<form action="${logoutUrl}" method="post">
			<input type="submit" class="btn btn-lg btn-info btn-block"
				value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		
		<div class="container">
		<h4>Dear, ${userName }</h4>
		<ul class="nav nav-tabs">
		
		<h4>Your Transaction Was Successful. Thank You for Using Universal Bank</h4>
			
			<li><a href="${pageContext.request.contextPath}/account">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/transfer" >Transfer</a></li>
			<c:if test="${role=='ROLE_MERCHANT'}">
				<li><a href="${pageContext.request.contextPath}/MerchantTransfer"> Merchant Transfer</a></li>
				</c:if>
			<li><a href="${pageContext.request.contextPath}/ViewTransactions" >View My Account</a></li>
			<li><a href="${pageContext.request.contextPath}/Credit" >Credit</a></li>
			<li><a href="${pageContext.request.contextPath}/DisplaySignUp" >EditInfo</a></li>
			<li><a href="${pageContext.request.contextPath}/UserRequest" >Pending tranactions</a></li>
		
		
		</sec:authorize>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
					<hr class="small">
					<p class="text-muted">Copyright &copy; Software Security Group 4 2015</p>
				</div>
			</div>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/slib.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/keypress.closure.js"></script>
	
    
<!-- Framebreaker script from OWASP for clickjacking  https://www.owasp.org/index.php/ClickjackFilter_for_Java_EE -->
<script>if (top != self) top.location=location</script>
</body>

</html>
		
