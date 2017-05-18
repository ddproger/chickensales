<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<a href="/app-presentation/home">
	<div class="logo">ТОВ "Курганський бройлер"</div>
	</a>
	<div class="login">
	<c:choose>
	  <c:when test="${admin!=null}">
	   	Ви зайшли як адміністратор: <c:out value="${admin.login}"/> <a href="logout">Вийти</a>
	  </c:when>
	  <c:when test="${user!=null}">
	    Ви зайшли як користувач: <c:out value="${user.name}"/> <a href="logout">Вийти</a>
	  </c:when>
	  <c:otherwise>
	  <form class="loginForm" action="login">
	   <label for="login">Логін:</label> 
	    <input placeholder="Введите логин" maxlength="20" size="20" name="login">
	   <label  for="password">Пароль:</label>
	    <input placeholder="Введите пароль" type="password" maxlength="20" size="20" name="password">
	  	<input type="submit" value="Ввійти"></input>
	  </form>
	  <a href="registration">Реєстрація</a>
	  </c:otherwise>
	</c:choose>
	</div>
	<a href="cart">
	<div id="cart" class="cart">		
			<span>В корзині: 
				<c:forEach items="${cookie}" var="currentCookie">  
					<c:if test="${currentCookie.value.name == 'countProduct'}">
					${currentCookie.value.value}
					</c:if>
				</c:forEach>
	 		товарів</span>			
	</div>
	</a>
	<div class="menu">
		<ul class="horizontalMenu">
		<li <c:if test="${currentPage == 'company'}">
					 class="selected"
					</c:if>>			
			<a href="company">Про компанію</a>
		</li >
		<li <c:if test="${currentPage == 'catalogue'}">
					 class="selected"
					</c:if>>
			<a href="catalogue">Продукція</a>
		</li>
		<li <c:if test="${currentPage == 'franchise'}">
					 class="selected"
					</c:if>>
			<a href="franchise">Франчайзинг</a>
		</li>
		<li <c:if test="${currentPage == 'actions'}">
					 class="selected"
					</c:if>>
			<a href="actions">Акції</a>
		</li>
		<li <c:if test="${currentPage == 'contacts'}">
					 class="selected"
					</c:if>>
			<a href="contacts">Контакти</a>
		</li>
		<c:if test="${admin!=null}">
		<li <c:if test="${currentPage == 'orders'}">
					 class="selected"
					</c:if>>
			<a href="orders">Замовлення</a>
		</li>
		<li <c:if test="${currentPage == 'customers'}">
					 class="selected"
					</c:if>>
			<a href="customers">Клієнти</a>
		</li>	
		<li <c:if test="${currentPage == 'planing'}">
					 class="selected"
					</c:if>>
			<a href="planing">Планування</a>
		</li>
		<li <c:if test="${currentPage == 'rating'}">
        					 class="selected"
        					</c:if>>
        			<a href="rating">Рейтинг</a>
        </li>
		</c:if>
		
		</ul>
		
		</div>
	</header>