<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--   -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
<c:url value="/admin/updateproduct" var="url"></c:url>
<form:form modelAttribute="product" action="${url }" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td></td>
				<!-- user need not
				 give value for id, auto generation -->
				<td><form:hidden path="ProductId"></form:hidden></td>
			</tr>
			<tr>
				<td>Enter Product Name
				</td>
				<td><form:input path="ProductName"></form:input></td>
			</tr>
			<tr>
			<td>Enter Product Description</td>
			<td><form:textarea path="ProductDescription"></form:textarea></td></tr>
			<tr>
			<td>Enter Price</td>
			<td><form:input path="ProductPrice"></form:input></td></tr>
			<tr><td>Enter Quantity</td>
			<td><form:input path="ProductQuantity"></form:input></td></tr>
			<tr><td>Select the Category</td>
			
			<td><form:select path="Category.categoryId"><!-- FK column value -->
			<!-- value is to set the value for the path category.id -->
		    <c:forEach items="${categories }" var="c">
		    <form:option value="${c.categoryId }">${c.categoryName }</form:option>
		    </c:forEach>
			</form:select></td></tr>
			<tr><td>Upload image</td><td>
			<form:input path="image" type="file"/>
			<tr>
			<td></td>
			<td><input type="submit" value="Edit Product"></td>
			</tr>


		</table>

	</form:form>
</div>
<a href="<c:url value='/all/getallproducts'></c:url>">Browse all products</a>
<jsp:include page="footer.jsp"/>
</body>
</html>