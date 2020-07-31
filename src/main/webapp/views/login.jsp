<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %> 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đăng nhập</title>
</head>
<body>
	<div style="padding-top: 20px">
		<h4>Đăng nhập hệ thống</h4>
	</div>
	
	<!-- Thông báo đăng nhập sai -->
	<c:if test="${not empty message }">
		<div class="alert alert-${ alert }">
			${ message }
		</div>
	</c:if>	
	
	<!-- Login Form -->
	<form action='<c:url value="/dang-nhap"/>' method="POST">
		<input type="hidden" name="action" value="login">
		<input type="text" id="login" class="fadeIn second" name="userName" placeholder="userName" value="admin"><br/>
		<input type="password" id="password" class="fadeIn third" name="password" placeholder="password" value="123456"><br/>
		<input type="submit" class="fadeIn fourth" value="Log In">
	</form>
	
	<!-- Remind Passowrd -->
	<div id="formFooter">
		<a class="underlineHover" href="#">Forgot Password?</a>
	</div>
</body>
</html>