<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">
</head>
<body onload='document.f.username.focus();'>
	<c:if test="${param.error != null}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
    <div id="login">

		<h2><span class="fontawesome-lock"></span>Sign In</h2>
        <form name='f' action="<c:url value='login' />"
            method='POST'>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <!--<table id="login">
                <tr>
                    <td colspan="2" ><b>Login</b><hr/></td>
                </tr>
                <tr>
                    <td align="right">User</td>
                    <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                    <td align="right">Password</td>
                    <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                        Remember Me?<input type="checkbox" id="rememberMe" name="rememberMe">
                        <input name="submit" type="submit" value="Submit" />
                        <input name="reset" type="reset" value="Reset"/>
                    </td>
                </tr>
            </table>-->
            <fieldset>

                <p><label>User Name</label></p>
                <p><input type='text' id="username" name='username' /></p> <!-- JS because of IE support; better: placeholder="mail@address.com" -->

                <p><label>Password</label></p>
                <p><input type='password' name='password' /></p> <!-- JS because of IE support; better: placeholder="password" -->

                <p><input name="submit" type="submit" value="Sign In"></p>

            </fieldset>
        </form>
    </div>
</body>
</html>