<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML">
<html>
<head>
<title>Клієнти</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/dropStyle.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center;" class="context">

	<form method="POST" action='action' name="actionFrm">
	
	<table>
	<tr>
	<th>Особа</th>
	<th>П.І.Б./Назва підприємства</th>
	<th>Пошта</th>
	<th>Адреса доставки</th>
	<th>ЕДРПОУ</th>
	<th>Контактний телефон</th>
	<th>Альтернативний телефон</th>
	</tr>
	<c:forEach var="user" varStatus="status" items="${users}">
    <tr>
    <td>
    <c:choose>
	  <c:when test="${user.EDRPOU != ''}">
	   	Юридична особа
	  </c:when>		
	  <c:otherwise>
			Фізична особа
	  </c:otherwise>
	</c:choose>


    </td>
    <td>
    <a href="customers?id=<c:out value="${user.userId}"/>"><c:out value="${user.name}"/></a>
    </td>
     <td>
    	<c:out value="${user.mail}"/>
    </td>
     <td>
    	<c:out value="${user.deliveryAdress}"/>
    </td>
     <td>
    	<c:out value="${user.EDRPOU}"/>
    </td>
    <td>
    	<c:out value="${user.tel1}"/>
    </td>
    <td>
    	<c:out value="${user.tel1}"/>
    </td>
    <td>
        	<c:out value="${user.tel1}"/>
     </td>
    </tr>
	</c:forEach>
	</table>
	</form>
	
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>
