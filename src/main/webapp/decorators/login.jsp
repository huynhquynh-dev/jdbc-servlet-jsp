<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>        

<!DOCTYPE html>
<html lang="en">
	<head>			
		<title><dec:title default="Đăng nhập"/></title>
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<link href='<c:url value="/template/login/style.css"/>' rel="stylesheet">	
	</head>
	
	<body>	
		<div class="wrapper fadeInDown">
			<div id="formContent">
				<dec:body/> 			
			</div>
		</div>
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	</body>
</html>
