<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>
<head>
<title>Каталог товарів</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section class="context">
	<c:if test="${admin!=null}">
		<br>
		<a title="add product" class="btnAdd" href="product?action=add">
		</a>

		</c:if>
	<c:forEach var="product" varStatus="status" items="${products}">
    <div class="item">
		<a href="product?id=<c:out value="${product.productId}"/>">
			<img class=productImage alt="" src="img/product/<c:out value="${product.photo}"/>">
			<h2><span><c:out value="${product.name}"/> </span></h2>
		</a>
		<c:if test="${admin!=null}">
		<br>
		<a title="edit product" class="btnEdit" href="product?id=<c:out value="${product.productId}"/>&action=edit">
		</a>
		<a style="margin-top:-30px;" title="delete product" class="btnDelete" href="product?id=<c:out value="${product.productId}"/>&action=delete">
		</a>
		</c:if>
	</div>
    </c:forEach>	
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>
