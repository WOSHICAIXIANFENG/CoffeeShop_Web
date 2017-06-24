<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>CoffeeShop Web Application</h2>
<br/>

<a href="/productlist">Products Dashboard</a><br/><br/>
<hr/>
<sec:authorize access="hasRole('ADMIN')">	
<a href="/personlist">Persons Dashboard</a><br/><br/>
<hr/>
</sec:authorize>
<sec:authorize access="hasRole('USER')">	
<a href="/editPerson/${person.id}">Update Profile + ${person.id}</a><br/><br/>
<hr/>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN') || hasRole('USER')">
<a href="/orderlist">Orders Dashboard</a><br/><br/>
<hr/>
</sec:authorize>

<sec:authorize access="isAnonymous()">
<a href="/login">Login TO Access Other Features</a><br/><br/>
</sec:authorize>
<br/>
<sec:authorize access="isAuthenticated()">
<a href="/logout">Logout</a><br/><br/>
</sec:authorize>

<sec:authorize access="isAnonymous()">
<a href="/register">Register New User</a><br/><br/>
</sec:authorize>
<br/>
</body>
</html>