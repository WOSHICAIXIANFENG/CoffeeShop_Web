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
<c:if test="${empty requestScope.products }">
	No products!
</c:if>
<c:if test="${!empty requestScope.products }">
	<table border="1"><tr>
			<th>Id</th>
			<th>ProductName</th>
			<th>Description</th>
			<th>Price</th>
			<th>ProductType</th>
		</tr>
		<c:forEach items="${requestScope.products }" var="product">
		<tr>
			<td>${product.id} </td>			
			<td>${product.productName} </td>			
			<td>${product.description} </td>			
			<td>${product.price} </td>			
			<td>${product.productType} </td>	
			<sec:authorize access="hasRole('ADMIN')">		
				<td><a href="/removeProduct/${product.id }">delete</a></td>			
				<td><a href="/editProduct/${product.id }">update</a></td>	
			</sec:authorize>		
		</tr>
		</c:forEach>
	</table>
</c:if>
<br/>
<hr/>
<br/>
<sec:authorize access="hasRole('ADMIN')">		
<fieldset>
<legend>ADD ONE PRODUCT</legend>
<form:form modelAttribute="product" action="addProduct">	
    <table>
      <tr>
        <td><form:label path="price">Price:</form:label></td>
        <td><form:input path="price" /></td>
      </tr>
      <tr>
        <td><form:label path="productName">Product Name:</form:label></td>
        <td><form:input path="productName" /></td>
      </tr>
      <tr>
        <td><form:label path="description">Description:</form:label></td>
        <td><form:textarea path="description" cols="50" rows="4" /></td>
      </tr>
      <tr>
        <td><form:label path="productType">Product Type:</form:label></td>
        <td><form:select path="productType" items="${productType}" /></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Save Product" /></td>
      </tr>
    </table>
    </form:form>
</fieldset>
</sec:authorize>
<br/>  
<a href="/index">go home</a>
</body>
</html>