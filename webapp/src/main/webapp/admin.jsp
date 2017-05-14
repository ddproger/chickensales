<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>
<head>
<title>Панель управління</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center;" class="context">
	<form method="POST" action='admin' name="frmAutorization">
	<label for="login">Логін:</label>
    <input type="text" name="login"
	 title="Type Correct Login without number"
	placeholder="Your login" required/> <br>
	<label for="password">Пароль:</label>
    <input type="password" name="password"
	 title="Type Correct password without number"
	placeholder="Your password" required/><br> 
	<button type="submit">Війти</button>
	</form>
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>