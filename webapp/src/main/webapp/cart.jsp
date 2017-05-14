<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML">
<html>
<head>
<title>Корзина</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center;" class="context">
	<c:choose>
    <c:when test="${empty productList}">
        Корзина пуста
    </c:when>
    <c:otherwise>
	<form method="POST" style="padding-top:0;" action="cart">
	<label for="deliveryAdress">Пошта:</label>	
    <input type="text" name="mail" 
    value="<c:out value="${user.mail}"/>" required><br>
	<label for="deliveryAdress">Адреса доставки:</label>
	<input type="text" name="deliveryAdress"  
    value="<c:out value="${user.deliveryAdress}"/>" required><br>
	<div class="cartItemHeader">    	
    		<div id="headerItemImg" >Зображення</div>
    		<div id="headerItemName" >Назва</div>
    		<div id="headerItemPrice" >Ціна</div>
    		<div id="headerItemCount" >Кіл.</div>
    		<div id="headerItemSum" >Сума</div>	    		
    </div><br>

	<c:forEach begin="0" end="${fn:length(productList)-1}" var="current">
    <div id="<c:out value="${productList[current].productId}"/>" name="cartItem" class="cartItem">    	
    		<input type="hidden" name="id" value="<c:out value="${productList[current].productId}"/>" readonly>
    		<img class=productImage alt="" src="img/product/<c:out value="${productList[current].photo}"/>">
    			
    		<textarea id="productName" type="text" value="" readonly><c:out value="${productList[current].name}"/></textarea>
    		
    		<input type="text" name="product<c:out value="${productList[current].productId}"/>[price]" value="<c:out value="${productList[current].price}"/>" readonly>
    		
    		<input type="text" name="product<c:out value="${productList[current].productId}"/>[count]" value="<c:out value="${counts[current]}"/>" readonly>
    		
    		<input type="text" value="<c:out value="${counts[current]*productList[current].price}"/>" readonly>

    		<a style="margin-left:430px;" class="btnDelete" href="" onclick="deleteItem(<c:out value="${productList[current].productId}"/>)" >Видалити</a>
    </div><br>
	</c:forEach>
	<input type="submit" value="Підтвердити замовлення">

		</form>	
			</c:otherwise>
	</c:choose>		
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
<script LANGUAGE="JavaScript" src="js/cartManager.js"></script>
</html>
