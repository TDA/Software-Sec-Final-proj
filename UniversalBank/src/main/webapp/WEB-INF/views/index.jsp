<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Universal Bank - Home Page</title>

<!-- Bootstrap Core CSS -->
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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Navigation -->
	<a id="menu-toggle" href="#" class="btn btn-dark btn-lg toggle"><i
		class="fa fa-bars"></i></a>
	<nav id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<a id="menu-close" href="#"
				class="btn btn-light btn-lg pull-right toggle"><i
				class="fa fa-times"></i></a>
			<li class="sidebar-brand"><a href="#top" onclick=$("#menu-close").click(); >Universal Bank</a></li>
			<li><a href="#top" onclick=$("#menu-close").click(); >Home</a></li>
			<li><a href="#about" onclick=$("#menu-close").click(); >About</a>
			</li>
			<li><a href="#services" onclick=$("#menu-close").click(); >Log in</a>
			</li>
			<li><a href="#contact" onclick=$("#menu-close").click(); >Contact</a>
			</li>
		</ul>
	</nav>

	<!-- Header -->
	<header id="top" class="header">
		<div class="text-vertical-center">
			<h1>Universal Bank</h1>
			<h3>Software Security Group 4</h3>
			<br> 
			<a href="register" class="btn btn-dark btn-lg">Sign Up</a>&nbsp;&nbsp;
			<a href="#about" class="btn btn-dark btn-lg">&nbsp;&nbsp;Log in&nbsp;&nbsp; </a>
		</div>
	</header>

	<!-- About -->
	<section id="about" class="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Secure banking system for secure financial transactions.</h2>
					<p class="lead">
						This application is build for the Software Security class offered at <a
							target="_blank" href="http://www.asu.edu/">Arizona State University</a>.
					</p>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</section>

	<!-- Services -->
	<!-- The circle icons use Font Awesome's stacked icon classes. For more information, visit http://fontawesome.io/examples/ -->
	<section id="services" class="services bg-primary">
		<div class="container">
			<div class="row text-center">
			<div class="col-lg-8 col-lg-offset-2">
				<h2>Login</h2>
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form name='loginForm' class="form-signin" role="form"
					action="<c:url value='/j_spring_security_check' />" method='POST'
					autocomplete="off">

					<input type="text" name="userName" size="10" class="form-control"
						placeholder="Username" required> <input
						type="password" name="password" id="password" size="10"
						class="form-control keyboardInput" placeholder="Password" required>
					
					<button class="btn btn-lg btn-info btn-block" type="submit">Sign
						in</button>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>
		</div>
		</div>
		<!-- /.container -->
	</section>

	<!-- Footer -->
	<section id="contact" class="map">
		<div class="container">
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 text-center">
					<h4>
						<strong>Universal Bank</strong>
					</h4>
					<p>
						Arizona State University<br>Tempe, AZ 85281
					</p>
					<ul class="list-unstyled">
						<li><i class="fa fa-phone fa-fw"></i> (123) 456-7890</li>
						<li><i class="fa fa-envelope-o fa-fw"></i> <a
							href="mailto:name@example.com">name@example.com</a></li>
					</ul>
					<br>

					<hr class="small">
					<p class="text-muted">Copyright &copy; Software Security Group 4 2015</p>
				</div>
			</div>
		</div>
	</section>

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- jQuery Version 1.11.0 -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script>
    // Closes the sidebar menu
    $("#menu-close").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Opens the sidebar menu
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
    });

    // Scrolls to the selected menu item on the page
    $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
            if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {

                var target = $(this.hash);
                target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
                if (target.length) {
                    $('html,body').animate({
                        scrollTop: target.offset().top
                    }, 1000);
                    return false;
                }
            }
        });
    });
    </script>

</body>

</html>