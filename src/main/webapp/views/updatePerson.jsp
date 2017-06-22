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
<fieldset>
<legend>EDIT ONE PERSON</legend>
	<form:form modelAttribute="person" action="/editPerson" method="post">
		<c:if test="${person.id != null }">
			<form:hidden path="id" />
		</c:if>
		<table>
			<tr>
				<td><form:label path="firstName">FirstName:</form:label></td>
				<td><form:input path="firstName" value="${person.firstName }" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">LastName:</form:label></td>
				<td><form:input path="lastName" value="${person.lastName }" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email:</form:label></td>
				<td><form:input path="email" value="${person.email }" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">Phone:</form:label></td>
				<td><form:input path="phone" value="${person.phone }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.city">City:</form:label></td>
				<td><form:input path="address.city"
						value="${person.address.city }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.state">State:</form:label></td>
				<td><form:input path="address.state"
						value="${person.address.state }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.country">Country:</form:label></td>
				<td><form:input path="address.country"
						value="${person.address.country }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.zipcode">ZIP Code:</form:label></td>
				<td><form:input path="address.zipcode"
						value="${person.address.zipcode }" /></td>
			</tr>
			<tr>
				<td><form:label path="enable">Enable</form:label></td>
				<td><form:checkbox path="enable" value="${person.enable }" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="updatePerson" /></td>
			</tr>
		</table>
	</form:form>
</fieldset>
</body>
</html>