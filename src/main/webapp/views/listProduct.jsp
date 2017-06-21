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
<c:if test="${empty requestScope.products }">
	No products!
</c:if>
<c:if test="${!empty requestScope.products }">
	<table><tr>
			<th>id</th>
			<th>productName</th>
			<th>description</th>
			<th>price</th>
			<th>productType</th>
		</tr>
		<c:forEach items="${requestScope.products }" var="product">
		<tr>
			<td>${product.id} </td>			
			<td>${product.productName} </td>			
			<td>${product.description} </td>			
			<td>${product.price} </td>			
			<td>${product.productType} </td>			
			<td><a href="/admin/deleteProducts/${product.id }">delete</a></td>			
			<td><a href="/admin/updateProducts/${product.id }">update</a></td>			
		</tr>
		</c:forEach>
	</table>
</c:if>
<a href="/admin">go home</a>
</body>
</html>