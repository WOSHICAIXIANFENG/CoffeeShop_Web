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
	<form:form modelAttribute="person" action="/admin/updatePersonAndSave">
		<c:if test="${person.id != null }">
			<form:hidden path="id" />
		</c:if>
		<table>
			<tr>
				<td><form:label path="firstName">firstName:</form:label></td>
				<td><form:input path="firstName" value="${person.firstName }" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">lastName:</form:label></td>
				<td><form:input path="lastName" value="${person.lastName }" /></td>
			</tr>
			<tr>
				<td><form:label path="email">email:</form:label></td>
				<td><form:input path="email" value="${person.email }" /></td>
			</tr>
			<tr>
				<td><form:label path="phone">phone:</form:label></td>
				<td><form:input path="phone" value="${person.phone }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.city">city:</form:label></td>
				<td><form:input path="address.city"
						value="${person.address.city }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.state">state:</form:label></td>
				<td><form:input path="address.state"
						value="${person.address.state }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.country">country:</form:label></td>
				<td><form:input path="address.country"
						value="${person.address.country }" /></td>
			</tr>
			<tr>
				<td><form:label path="address.zipcode">zipcode:</form:label></td>
				<td><form:input path="address.zipcode"
						value="${person.address.zipcode }" /></td>
			</tr>
			<tr>
				<td><form:label path="enable">enable</form:label></td>
				<td><form:checkbox path="enable" value="${person.enable }" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="updatePerson" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>