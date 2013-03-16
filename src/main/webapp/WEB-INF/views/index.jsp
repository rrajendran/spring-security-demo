<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"></script>
<title>Welcome</title>
</head>
<body style="background-color: #fff">
	<h1>Message : ${message}</h1>	
	<h3>Username : ${username}</h3>	
 
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</body>
</html>
