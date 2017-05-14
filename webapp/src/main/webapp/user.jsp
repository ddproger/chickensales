<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML">
<html>
<head>
<title>Кліент <c:out value="${user.name}"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/dropStyle.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="js/dropdown.js"></script>
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center;" class="context">
	<h2>Інформація про клієнта</h2>
	<div>Логін: <c:out value="${user.login}"/></div>
	<div>Пароль: <c:out value="${user.password}"/></div>
	<div>П.І.Б.: <c:out value="${user.name}"/></div>
	<div>Почта: <c:out value="${user.mail}"/></div>
	<div>ЕДРПОУ: <c:out value="${user.EDRPOU}"/></div>
	<div>Адреса: <c:out value="${user.deliveryAdress}"/></div>
	<div>Телефон 1: <c:out value="${user.tel1}"/></div>
	<div>Телефон 2: <c:out value="${user.tel2}"/></div>	
	<h2>Акції Кліента</h2>
	<c:forEach var="action" items="${actions}">
	<div>Назва: <c:out value="${action.name}"/></div>
	<div>Дата: <c:out value="${action.endDate}"/></div>
	</c:forEach>
	
	<h2>Закупки Клієнта</h2>
	<table>
	<tr>
	<th>Дата замовлення</th>
	<th>Адреса доставки</th>
	<th>Статус</th>
	<th>Продукти</th>
	</tr>
	<c:forEach var="order" varStatus="status" items="${orders}">
    <tr>
     <td>
    	<c:out value="${order.date}"/>
    </td>
     <td>
    	<c:out value="${order.deliveryAdress}"/>
    </td>
    <td>
    	<select name="status[<c:out value="${order.orderId}"/>]">
	    <option disabled>Статус замовлення</option>	    
	    <c:forEach var="status" items="${statuses}">
	    <option <c:if test="${status.statusId eq order.status.statusId}">selected='selected'</c:if> 
	    	value="<c:out value="${status.statusId}"/>"><c:out value="${status.name}"/></option>
	    </c:forEach>
	   </select>
    </td>
    <td>
	    <div class="order_block">
		<ul class="products_list" id="<c:out value="${order.orderId}"/>">
			<c:forEach var="orderproduct" items="${order.orderProduct}">
				<li>
				<div class="productImg"><img src="img/product/<c:out value="${orderproduct.product.photo}"/>"/></div>
				<div class="productName">Назва: <c:out value="${orderproduct.product.name}"/></div>
				<div class="productCount">Кількість: <c:out value="${orderproduct.count}"/></div>
				</li>
			</c:forEach>
		</ul>
		</div>
		<div onclick="slideTogle(<c:out value="${order.orderId}"/>)" class="order_list">
		<div class="btn"></div>
		 Cписок продуктів
		 </div>
    </td>
    </tr>
	</c:forEach>
	</table>
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>
