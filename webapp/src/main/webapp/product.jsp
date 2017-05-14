<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML">
<html>
<head>
<title><c:out value="${product.name}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section class="context">
		<div class="item">
		<a href="product?id=<c:out value="${product.productId}"/>">
			<img class=productImage alt="" src="img/product/<c:out value="${product.photo}"/>">
			<h2><span><c:out value="${product.name}"/> </span></h2>
		</a>
	</div>
	<div class="item">
		 <c:out value="${product.description}" escapeXml="false"/>
		 <span>Ціна за кг: <span index="<c:out value="${product.productId}"/>" style="font-size:26px; color:white;" id="cost"><c:out value="${product.price}"/></span> грн.<span><br>
		<label for="Count">Кількість:</label>
	    <input type="number" id="Count" name="Count"
			min="1"
			max="100"
			step="1"
			value="1">
		<button id="addProduct"  type="submit" style="border: 0; background: transparent">
	    <img src="img/addToCart.png" width="60" height="50" alt="submit" />
		</button>
	</div>	
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
<script LANGUAGE="JavaScript" src="js/cartManager.js"></script>
</html>
