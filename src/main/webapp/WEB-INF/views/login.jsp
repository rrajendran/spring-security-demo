<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">
</head>
<body onload='document.f.j_username.focus();'>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
		<table id="login">
			<tr>
				<td colspan="2" ><b>Login</b><hr/></td>
			</tr>
			<tr>
				<td align="right">User</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td align="right">Password</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					Remember Me?<input type="checkbox" id="rememberMe" name="rememberMe">
					<input name="submit" type="submit" value="Submit" />
					<input name="reset" type="reset" value="Reset"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>