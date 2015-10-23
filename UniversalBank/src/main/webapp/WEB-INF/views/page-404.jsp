<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body oncontextmenu="return false">
	<h1>HTTP Status 404 - Page Not Found</h1>

	<c:choose>
		<c:when test="${empty username}">
			<div class="col-md-6 text-center">
				<div class="portfolio-item">
					<a href="#"> <img class="img-portfolio img-responsive"
						src="${pageContext.request.contextPath}/resources/img/portfolio-1.jpg">
					</a>
				</div>
				
				<h2>Go back to previous page!</h2>
			</div>

		</c:when>
		<c:otherwise>
			<h2>
				Username : ${userName} <br />&nbsp;Go back to previous page!
			</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>