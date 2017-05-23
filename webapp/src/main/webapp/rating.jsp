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

	<form method="GET" action='rating'>
	<label for="from">С:</label>
	<input type="date" id="from" name="from"/>
	<label for="to">По:</label>
    <input type="date" id="to" name="to"/>
    <input type="submit" value="Выбрать"/>
	</form>

	<table>
	<tr>
	<th>Особа</th>
	<th>П.І.Б./Назва підприємства</th>
	<th>Пошта</th>
	<th>Адреса доставки</th>
	<th>ЕДРПОУ</th>
	<th>Контактний телефон</th>
	<th>Альтернативний телефон</th>
	<th>Рейтинг</th>
	<th>Закупівлі на сумму</th>
	</tr>
    <%! private int count = 0; %>
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
    	<c:out value="${user.tel2}"/>
    </td>
    <td>
        	<%= ++count %>
    </td>
    <td>
         	<c:out value="${user.rating}"/> грн.
    </td>
    </tr>

	</c:forEach>
	</table>
	<a href='rating?action=import<c:if test = "${from!=null&&to!=null}">&from=<c:out value="${from}"/>&to=<c:out value="${to}"/></c:if>'>Сформировать отчет</a>
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>
