<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty requestScope.orders }">
	No orders!
</c:if>
<c:if test="${!empty requestScope.orders }">
	<table border="1"><tr>
			<th>Id</th>
			<th>OrderDate</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Phone</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
			<th>ZIP Code</th>
			<th>Enable</th>
		</tr>
		<c:forEach items="${requestScope.orders }" var="order">
		<tr>
			<td>${order.id} </td>			
			<td>${order.orderDate} </td>			
			<td>${order.person.firstName} </td>			
			<td>${order.person.lastName} </td>			
			<td>${order.person.email} </td>			
			<td>${order.person.phone} </td>			
			<td>${order.person.address.city} </td>			
			<td>${order.person.address.state} </td>			
			<td>${order.person.address.country} </td>			
			<td>${order.person.address.zipcode} </td>			
			<td>${order.person.enable} </td>			
		</tr>
		</c:forEach>
	</table>
</c:if>
<br/>
<hr/>
<br/>
<fieldset>
<legend>ADD ONE ORDER</legend>
<form:form modelAttribute="order" action="addOrder">
		<table>
			<tr>
				<td><form:label path="orderDate">OrderDate:</form:label></td>
				<td><form:input path="orderDate" type="date" /></td>
			</tr>
			<tr>
				<td><form:label path="person.firstName">FirstName:</form:label></td>
				<td><form:input path="person.firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="person.lastName">LastName:</form:label></td>
				<td><form:input path="person.lastName" /></td>
			</tr>
			<tr>
				<td><form:label path="person.email">Email:</form:label></td>
				<td><form:input path="person.email" /></td>
			</tr>
			<tr>
				<td><form:label path="person.phone">Phone:</form:label></td>
				<td><form:input path="person.phone" /></td>
			</tr>
			<tr>
				<td><form:label path="person.enable">Enable</form:label></td>
				<td><form:checkbox path="person.enable" /></td>
			</tr>
			<tr>
				<td><form:label path="person.address.city">City:</form:label></td>
				<td><form:input path="person.address.city" /></td>
			</tr>
			<tr>
				<td><form:label path="person.address.state">State:</form:label></td>
				<td><form:input path="person.address.state" /></td>
			</tr>
			<tr>
				<td><form:label path="person.address.country">Country:</form:label></td>
				<td><form:input path="person.address.country" /></td>
			</tr>
			<tr>
				<td><form:label path="person.address.zipcode">ZIP Code:</form:label></td>
				<td><form:input path="person.address.zipcode" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="saveProduct" /></td>
			</tr>
		</table>
	</form:form>
</fieldset>

	
<a href="/index">go home</a>
</body>
</html>