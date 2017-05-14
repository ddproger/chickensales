<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>
<head>
<title>Акції</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="/layouts/header.jsp" %>
<section style="text-align:center;" class="context">
	<form method="POST" action='action' name="actionFrm">
	<label for="mail">Пошта:</label>
    <input type="text" name="mail"
	 title="Type Correct mail"
	placeholder="Your mail" required/> <br>
	<label for="content">Текст:</label>
    <input type="text" name="content"
	 title="Type Correct content"
	placeholder="Your content" required/><br> 
	<button type="submit">Відправити</button>
	</form>
</section>
<%@ include file="/layouts/footer.jsp" %>	
</body>
</html>