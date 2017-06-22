<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h3>Welcome to Register</h3>
<form:form modelAttribute="user" action="/register" method="POST">
	<%-- <form:label path="name">Name:</form:label>
	<form:input path="name" value="${user.name }" type="text" />
	<br/> --%>
	<form:label path="email">User Email:</form:label>
	<form:input path="email" value="${user.email }" type="email" />
	<br/>
	<form:label path="password">Password:</form:label>
	<form:input path="password" value="${user.password }" type="password" />
	<br/>
	<input type="submit" value="save" />
</form:form>
<br />  
<a href="/index">go home</a>

</body>
</html>