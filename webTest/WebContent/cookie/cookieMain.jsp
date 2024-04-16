<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie연습용 main페이지입니다</title>
</head>
<body>
	<h2>Cookie 연습용 main 페이지입니다</h2>
	<br> <br>
	<a href="<%= request.getContextPath() %>/cookie/cookieLogin.jsp">Login창으로 이동</a>
</body>
</html>