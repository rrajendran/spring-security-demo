<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en-US">
<head>

	<meta charset="utf-8">

	<title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Varela+Round">

	<!--[if lt IE 9]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

</head>

<body>
    <c:if test="${param.error != null}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<div id="login">

		<h2><span class="fontawesome-lock"></span>Sign In</h2>

		<form name="f" action="<c:url value='login' />"	method='POST'>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<fieldset>

				<p><label for="username">User Name</label></p>
				<p><input type="text" id="username" value="username" onBlur="if(this.value=='')this.value='username" onFocus="if(this.value=='username')this.value=''"></p> <!-- JS because of IE support; better: placeholder="mail@address.com" -->

				<p><label for="password">Password</label></p>
				<p><input type="password" id="password" value="password" onBlur="if(this.value=='')this.value='password'" onFocus="if(this.value=='password')this.value=''"></p> <!-- JS because of IE support; better: placeholder="password" -->

				<p><input type="submit" value="Sign In"></p>

			</fieldset>

		</form>

	</div> <!-- end login -->

</body>
</html>