<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img alt="Penguins.jpg" src="<%=request.getContextPath() %>/images/Penguins.jpg" width="250px"> <br> <br>

	<img src="<%=request.getContextPath() %>/images/imageView.do?fileno=17" width="250px">
</body>
</html>