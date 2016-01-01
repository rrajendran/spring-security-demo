<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">
<title>Welcome</title>
</head>
<body>
    <h1>Message : ${message}</h1>
   	<h2>Username : ${username}</h2>

 
	<a href="<c:url value="/login?logout" />" > Logout</a>
</body>
</html>
