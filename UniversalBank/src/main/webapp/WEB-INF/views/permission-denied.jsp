<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Universal Bank - Permission Denied</title>

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
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/resources/css/keyboard.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/resources/js/browserVerifier.js"></script>
<script>
var tl=new Array(
		"I don\'t know who you are.",
		'I don\'t know where you are.',
		"But my logs do."
		);
var UA = checkUA();
var string = "I know you are using " + UA.browser;
var string2 = "I know you are on " + UA.os;
var string3 = "And If you stay here, I am going to find a lot more";
var string4 = "I can log you into your e-mail, or worse, your other accounts"
tl.push(string);
tl.push(string2);		
tl.push(string3);
tl.push(string4);
tl.push("And I swear to God.",
		"I will find you, and I will hack your site.",
		"You see, I'm just an automated bot...",
		"But I was written by Sai Pc",
		"and I promise you, I am kinda lenient",
		"But he's not.",
		"And when he comes to know of this,",
		"You might as well shut your server down",
		"I'm sorry. But you brought this upon yourself."
		); // <-- Even this looks like its crying :D
</script>
<script src="${pageContext.request.contextPath}/resources/js/texter.js"></script>
    
</head>

<body oncontextmenu="return true">
<script>
window.onload = function(){
	
	  type_text();
	  var link = selectRedirectLink();
    console.log(link);
    document.querySelector("#redirector").href = link;
    history.pushState({"link" : link}, "", "${pageContext.request.contextPath}/index");
    setTimeout(function () {window.location = link;}, 17200);
	}
</script>
	<div class="container">
	<c:choose>
		<c:when test="${empty username}">
		
		<h1 style="opacity:0">HTTP Status 403 - Access is denied</h1>
			<div class="col-lg-12 text-center">
				<h2>We need to talk</h2>
				<textarea id="selfTalk" rows=12 cols=54 style="border:none;text-align:center" read-only>
				</textarea>
				
				<h2>Go home!</h2> <a id="redirector">Home</a>
				<h3>This attempt has been logged, and will be reported.</h3>
				<div id="hackScare"></div>
				</div>

		</c:when>
		<c:otherwise>
			<h2>
				Username : ${userName} <br />&nbsp;Go back to previous page! <a href="${pageContext.request.contextPath}/index">Home</a>
			</h2>
		</c:otherwise>
	</c:choose>
	</div>
</body>
</html>