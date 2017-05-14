<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML">
<html>
<head>
<title>Закупки</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/dropStyle.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center;" class="context">

	<form  method="POST" style="padding-top:0;" action="planing">
	<table>
	<tr>	
	<th>Id</th>
	<th>Виконано</th>
	<th>Клієнт</th>
	<th>Зміст</th>
	<th>Дата закінчення</th>	
	<th>Тип</th>
	</tr>
	<c:forEach var="action" varStatus="status" items="${actions}">
    <tr>
    <td>
    <input type ="hidden" name="id" value="<c:out value="${action.actionId}"/>" >
    	<c:out value="${action.actionId}"/>
    </td>
     <td>
     <input type="checkbox" name="complite[<c:out value="${action.actionId}"/>]" <c:if test="${action.complete==true}">checked='checked'</c:if> />
    </td>

     <td>
    	<a href="customers?id=<c:out value="${action.user.userId}"/>"><c:out value="${action.user.name}"/></a>
    </td>
        <td>
    	<input type="text" name="context[<c:out value="${action.actionId}"/>]" value = "<c:out value="${action.name}"/>"/>
    </td>
     <td>
    	<input type="date" name="date[<c:out value="${action.actionId}"/>]" value = "<c:out value="${action.endDate}"/>"/>
    </td>
    <td>
    	<select name="actionType[<c:out value="${action.actionId}"/>]">
	    <option disabled>Тип дії</option>	    
	    <c:forEach var="actionType" items="${actionTypes}">
	    <option <c:if test="${actionType.actionTypeId eq action.actionType.actionTypeId}">selected='selected'</c:if> 
	    	value="<c:out value="${actionType.actionTypeId}"/>"><c:out value="${actionType.name}"/></option>
	    </c:forEach>
	   </select>
    </td>   

    </tr>
	</c:forEach>
	</table>
	<input type="submit" value="Зберегти зміни">

		</form>	
	
	
	
	
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
<script LANGUAGE="JavaScript" src="js/cartManager.js"></script>
</html>
