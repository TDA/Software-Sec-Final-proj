<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Universal Bank - Page not found</title>

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
	<link href="${pageContext.request.contextPath}/resources/css/keyboard.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
var tl=new Array(
		"The requested document is no more.",
		'No file found.',
		"Even tried multi.",
		"Nothing helped.",
		"I'm really depressed about this.",
		"You see, I'm just a web server...",
		"-- here I am, brain the size of the universe,",
		"trying to serve you a simple web page,",
		"and then it doesn't even exist!",
		"Where does that leave me?!",
		"I mean, I don't even know you.",
		"How should I know what you wanted from me?",
		"You honestly think I can *guess*",
		"what someone I don't even *know*",
		"wants to find here?",
		"*sigh*",
		"Man, I'm so depressed I could just cry.",
		"And then where would we be, I ask you?",
		"It's not pretty when a web server cries.",
		"And where do you get off telling me what to show anyway?",
		"Just because I'm a web server,",
		"and possibly a manic depressive one at that?",
		"Why does that give you the right to tell me what to do?",
		"Huh?",
		"I'm so depressed...",
		"I think I'll crawl off into the trash can and decompose.",
		"I mean, I'm gonna be obsolete in what, two weeks anyway?",
		"What kind of a life is that?",
		"Two effing weeks,",
		"and then I'll be replaced by a .01 release,",
		"that thinks it's God's gift to web servers,",
		"just because it doesn't have some tiddly little",
		"security hole with its HTTP POST implementation,",
		"or something.",
		"I'm really sorry to burden you with all this,",
		"I mean, it's not your job to listen to my problems,",
		"and I guess it is my job to go and fetch web pages for you.",
		"But I couldn't get this one.",
		"I'm so sorry.",
		"Believe me!",
		"Maybe I could interest you in another page?",
		"There are a lot out there that are pretty neat, they say,",
		"although none of them were put on *my* server, of course.",
		"Figures, huh?",
		"Everything here is just mind-numbingly stupid.",
		"That makes me depressed too, since I have to serve them,",
		"all day and all night long.",
		"Two weeks of information overload,",
		"and then *pffftt*, consigned to the trash.",
		"What kind of a life is that?",
		"Now, please let me sulk alone.",
		"I'm so depressed."
		);

window.onload = function(){
	  type_text();
	}
</script>
</head>

<body oncontextmenu="return false">
<script src="${pageContext.request.contextPath}/resources/js/texter.js">
</script>
	<div class="container">
	<c:choose>
		<c:when test="${empty username}">
		
		<h1 style="opacity:0">HTTP Status 404 - Page Not Found</h1>
			<div class="col-lg-12 text-center">
				<h2>Hey there, dear chap!</h2>
				<h3>Grab a drink, sit back and relax</h3>
				<textarea id="selfTalk" rows=24 cols=54 style="border:none;text-align:center" read-only>
				</textarea>
				
				<h2>Go back to previous page!</h2> <a href="${pageContext.request.contextPath}/index">Home</a>
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