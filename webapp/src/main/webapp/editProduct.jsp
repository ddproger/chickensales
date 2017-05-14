<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>
<head>
<title>Редагування каталогу</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center" class="context">
		<form method="POST" enctype="multipart/form-data" action='product?id=<c:out value="${product.productId}"/>' name="frmRegistration">
	<label for="name">Назва:</label>
    <input type="text" name="name"
	 title="Type Correct name"
	placeholder="Product Name" 
	<c:if test="${product != null}">value="<c:out value="${product.name}"/>"</c:if>required/> <br>
	<label for="price">Ціна:</label>
    <input type="text" name="price"
	 title="Type Correct Price"
	placeholder="Price" 
	<c:if test="${product != null}">value="<c:out value="${product.price}"/>"</c:if>required/><br> 
	<label for="description">Опис:</label>
    <textarea style="width:180px;height:230px;" name="description"
	 title="Type Correct description"
	placeholder="Your description" 
	required><c:if test="${product != null}"><c:out value="${product.description}"/></c:if></textarea> <br>
	<label for="file"> Фотографія: </label>
	<input type="file" name="file" accept="image/*">
	<c:if test="${product.photo != null}">
	<br>
	<img src="img/product/<c:out value="${product.photo}"/>">
	</c:if>	
	<br>
	<button type="submit">
	<c:choose>
	  <c:when test="${product!=null}">
	   	Зберегти зміни
	  </c:when>
	  <c:otherwise>
		Додати
	  </c:otherwise>
	</c:choose></button>
	</form>
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>
