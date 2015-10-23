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

<title>Universal Bank DeleteTransactions</title>

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
				<a class="navbar-brand" href="#">Available Transaction</a>
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
						<h1 class="page-header">Available Transactions</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> <div id="morris-area-chart">

									
	<sec:authorize access="hasRole('ROLE_CLERK')">
		</sec:authorize>
		<div class="container">
		<ul class="nav nav-tabs">
			<li><a href="${pageContext.request.contextPath}/ViewTransactionRegularEmployee">View Transaction</a></li>
			<li><a data-toggle="tab" href="#menu2">Create Transaction</a></li>
			<li><a data-toggle="tab" href="#menu3">Modify Transaction</a></li>
			<li><a href="${pageContext.request.contextPath}/DeleteTransaction">Delete Transaction</a></li>
		</ul></div>
								</h3>
							</div>

							<div class="panel-body">
								<div id="morris-area-chart">

	<style>								
	<sec:authorize access="hasRole('ROLE_CLERK')">
		</sec:authorize>
		
		<div id="pdf">
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>
<form:form method="POST" action="ViewTransactionRegularEmployee"
 modelAttribute="ApproveForm" autocomplete="off">
    <table style="width:80%">
  			<tr>
  				<th>Transaction ID</th>
    			<th>Transaction Type</th>
    			<th>Amount($)</th>	
    			<th>Comment</th>		
   				  </tr>
        <c:forEach items="${userInformation}" var="transaction">
            <tr>
                <td><c:out value="${transaction.transactionID}" /></td>
                <td><c:out value="${transaction.transactionType}" /></td>
                <td><c:out value="${transaction.amount}" /></td>
                <td><c:out value="${transaction.comments}" /></td>
                <td><button type="submit" name="approveDenyParamRegularEmployee" value="approve_${transaction.transactionID}"/>Approve</button></td>
                <td><button type="submit" name="approveDenyParamRegularEmployee" value="deny_${transaction.transactionID}"/>Deny</button></td>
            </tr>
        </c:forEach>

    </table>
    </form:form>
   </div>

</body>
</html>