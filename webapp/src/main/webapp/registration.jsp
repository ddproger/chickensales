<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>
<head>
<title>Реєстрація</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="height:500px;" class="context">
<div  style="text-align:right;float:right;margin-right:30%; ">
	<c:choose>
	  <c:when test="${param.type!=null}">	
	  <h2>Зареєструватися як Юридична особа</h2><a href="registration">Змінити</a>   	
	  </c:when>
	  <c:otherwise>
	  <h2>Зареєструватися як Фізична особа</h2><a href="registration?type=legalEntity">Змінити</a>
	  </c:otherwise>
	</c:choose>
	<form   method="POST" action='registration' name="frmRegistration">
	<label for="login">Логін:</label>
    <input type="text" name="login"
	 title="Type Correct Login without number"
	placeholder="Your login" required/><br>
	<label for="password">Пароль:</label>
    <input type="password" name="password"
	 title="Type Correct password without number"
	placeholder="Your password" required/><br> 
	<label for="mail">Пошта:</label>
    <input type="text" name="mail"
	 title="Type Correct mail"
	placeholder="Your mail" required/><br>
	<label for="name">
	<c:choose>
	  <c:when test="${param.type!=null}">	
	  Організація:   	
	  </c:when>
	  <c:otherwise>
	  Ім'я:
	  </c:otherwise>
	</c:choose>
	</label>
	<input type="text" name="name"
	 title="Type Correct Name without number"
	placeholder="Your name" required/><br>
	<c:if test="${param.type!=null}">
	<label for="edrpou">Код ЕДРПОУ:</label>
	<input type="text" name="edrpou"
	 title="Type Correct EDRPOU"
	placeholder="Your EDRPOU" /><br>
	</c:if>	
	<label for="adress">Адреса:</label>
    <input type="text" name="adress"
	maxlength="50" title="Type Correct adress"
	placeholder="Your adress" required/><br> 
	<label for="tel1">Мобільний телефон:</label>
    <input type="text" name="tel1"
	 title="Type Correct telephone"
	placeholder="Your Telephone" /><br>
	<label for="tel2">
	<c:choose>
	  <c:when test="${param.type!=null}">	
	  	Службовий телефон:
		</c:when>
	  <c:otherwise>
	  	Додатковий телефон:
	  </c:otherwise>
	</c:choose>	
	</label>
    <input type="text" name="tel2"
	 title="Type Correct telephone"
	placeholder="Your Telephone" /><br>	
	<button type="submit">Зареєструватися</button>
	</form>
	</div>
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>