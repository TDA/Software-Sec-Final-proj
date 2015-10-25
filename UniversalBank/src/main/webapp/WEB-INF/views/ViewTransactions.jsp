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
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
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
				<a class="navbar-brand" href="#">Account HomePage</a>
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
						<h1 class="page-header">Home</h1>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> <div id="morris-area-chart">

									
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

	<sec:authorize access="hasRole('ROLE_INDIVIDUAL')">
		</sec:authorize>
		
<div id="printable" data-name="Transactions">

	
    <table style="width:80%">
  			<tr>
  				<th>Transaction ID</th>
  				<th>Type</th>
  				<th> Amount($)</th>
    			<th>From Account</th>
    			<th>To account</th>	
    			<th> Time </th>	
   				  </tr>
        <c:forEach items="${userInformation}" var="transaction">
            <tr>
                <td><c:out value="${transaction.transactionID}" /></td>
                <td><c:out value="${transaction.transactionType}" /></td>
                <td><c:out value="${transaction.amount}" /></td>
                <td><c:out value="${transaction.transactionAccountID}" /></td>
                <td><c:out value="${transaction.totransactionAccountID}" /></td>
                <td><c:out value="${transaction.transactionTime}" /></td>
                
            </tr>
            
        </c:forEach>
			
    </table>
</div>
<input type="button" value="Click to PDF" id="pdfConvertor">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="scripts/jquery.js"><\/script>')</script>
<script src="${pageContext.request.contextPath}/resources/js/jspdfdebug.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pdf.js"></script>

</body>
</html>
