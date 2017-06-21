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
	<form:form modelAttribute="product" action="/admin/updateProductAndSave">
		<c:if test="${product.id != null }">
			<form:hidden path="id" />
		</c:if>
		<table>
			<tr>
				<td><form:label path="price">price:</form:label></td>
				<td><form:input path="price" value="${product.price }" /></td>
			</tr>
			<tr>
				<td><form:label path="productName">productName:</form:label></td>
				<td><form:input path="productName"
						value="${product.productName }" /></td>
			</tr>
			<tr>
				<td><form:label path="description">description:</form:label></td>
				<td><form:textarea path="description"
						value="${product.description }" /></td>
			</tr>
			<tr>
				<td><form:label path="productType">productType:</form:label></td>
				<td><form:select path="productType" items="${productType}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="updateProduct" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>