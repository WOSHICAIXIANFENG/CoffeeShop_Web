<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sec:authorize access="hasRole('ADMIN')">	
<c:if test="${empty requestScope.persons }">
	No Persons!
</c:if>
<c:if test="${!empty requestScope.persons }">
	<table border="1"><tr>
			<th>Id</th>
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
		<c:forEach items="${requestScope.persons }" var="person">
		<tr>
			<td>${person.id} </td>			
			<td>${person.firstName} </td>			
			<td>${person.lastName} </td>			
			<td>${person.email} </td>			
			<td>${person.phone} </td>			
			<td>${person.address.city} </td>			
			<td>${person.address.state} </td>			
			<td>${person.address.country} </td>			
			<td>${person.address.zipcode} </td>			
			<td>${person.enable} </td>	
			<sec:authorize access="hasRole('ADMIN')">			
				<td><a href="/editPerson/${person.id }">update</a></td>	
			</sec:authorize>		
		</tr>
		</c:forEach>
	</table>
</c:if>
</sec:authorize>
<br/>
<br/>
<hr/>
<br/>
<sec:authorize access="hasRole('ADMIN')">	
<fieldset>
<legend>ADD ONE PERSON</legend>
<form:form modelAttribute="person" action="addPerson">	
    <table>
   		<tr>
			<td><form:label path="firstName">FirstName:</form:label></td>
			<td><form:input path="firstName" /></td>
		</tr>
		<tr>
			<td><form:label path="lastName">LastName:</form:label></td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td><form:label path="email">Email:</form:label></td>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<td><form:label path="phone">Phone:</form:label></td>
			<td><form:input path="phone" /></td>
		</tr>
		<tr>
			<td><form:label path="address.city">City:</form:label></td>
			<td><form:input path="address.city" /></td>
		</tr>
		<tr>
			<td><form:label path="address.state">State:</form:label></td>
			<td><form:input path="address.state" /></td>
		</tr>
		<tr>
			<td><form:label path="address.country">Country:</form:label></td>
			<td><form:input path="address.country" /></td>
		</tr>
		<tr>
			<td><form:label path="address.zipcode">ZIP Code:</form:label></td>
			<td><form:input path="address.zipcode" /></td>
		</tr>
		<tr>
			<td><form:label path="enable">Enable</form:label></td>
			<td><form:checkbox path="enable"  /></td>
		</tr>
      	<tr>
        	<td colspan="2"><input type="submit" value="Save Person" /></td>
      	</tr>
    </table>
    </form:form>
</fieldset>
</sec:authorize>
<br />  
<a href="/index">go home</a>
</body>
</html>