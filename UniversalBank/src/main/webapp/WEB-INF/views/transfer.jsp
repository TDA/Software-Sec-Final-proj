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

<title>Universal Bank Transfer page</title>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css"rel="stylesheet">

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"rel="stylesheet" type="text/css">
<style>
body {
	padding-top: 70px;
	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
}
</style>


</head>
<body oncontextmenu="return false">

	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Transfer for an account</a>
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
						<h1 class="page-header">Transfer</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
									<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i><div class="panel-body">
								<div id="morris-area-chart">

									
	<sec:authorize access="hasRole('ROLE_INDIVIDUAL')">
		</sec:authorize>
		<div class="container">
		<ul class="nav nav-tabs">
			<li><a href="${pageContext.request.contextPath}/account">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/Credit" >Credit</a></li>
			<li><a href="${pageContext.request.contextPath}/ViewTransactions" >View My Account</a></li>
			<li><a href="${pageContext.request.contextPath}/Debit" >Debit</a></li>
			<li><a href="${pageContext.request.contextPath}/DisplaySignUp" >EditInfo</a></li>
			<li><a href="${pageContext.request.contextPath}/UserRequest" >pendingRequests</a></li>
		</ul></div>
								</h3>
							</div>

							<div class="panel-body">
								<div id="morris-area-chart">
								
										<c:if test="${not empty successMsg}">
											<h4>
												${successMsg} <a href="index"> Click here to LogIn!</a>
											</h4>
										</c:if>
									<form:form method="POST" action="transfer"
										modelAttribute="transferForm" autocomplete="off">
										<br />
										<br />
										<c:if test="${not empty errorMsg}">
											<h3>${errorMsg}</h3>
										</c:if>
										<br />
										<b>Account Type:</b>
										<FONT color="red"><form:errors path="accountType" /></FONT>
										<br />
										<select class="selectpicker form-control" name="accountType">
											<option value="">Select</option>
											<c:forEach var="bankval" items="${mylist}">
												<option value="${bankval}">${bankval}</option>
											</c:forEach>
										</select>
										<br />
										
										<b> Enter to AccountId</b>
										<FONT color="red"><form:errors path="ToTransactionAccountID" /></FONT>
										
										<br />
										<input type="text" name="ToTransactionAccountID" size="10" class="form-control" id="toTransactionAccountID""
											style="color: #999;" />
										<br />
										<br />
										<br />
										<b>Amount</b>
										<FONT color="red"><form:errors path="amount" /></FONT>
										<br />
										<input type="text" name="amount" size="10" class="form-control" id="amount"
											style="color: #999;" />
										<br />
										
										<br />
										<br />
										
										 <Font Color="red">By performing the Transfer you  authroise the bank to modify,delete and access your transactions</Font>
										<br />
										<br />
										<h4>
											<input type="submit" style="margin-right: 5%" name="Transfer"
												id="transfer" value="Transfer" />
										</h4>

										
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
