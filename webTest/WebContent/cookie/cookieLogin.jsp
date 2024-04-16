<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 쿠키값 가져오기 -->
	<%
		Cookie[] cookieArr = request.getCookies();
		
		String cookieUserId = ""; // 쿠키값(id)이 저장될 변수
		String strCheck = "";
		
		if (cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				if ("USERID".equals(cookie.getName())) { // 쿠키가 있는지 검사
					cookieUserId = cookie.getValue();
					strCheck = "checked"; 
				}
			}
		}
	
	%>
	
	<form action="<%= request.getContextPath() %>/cookieLoginServlet.do" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td>ID : </td>
				<td>
					<input type="text" value="<%= cookieUserId %>" name="userId" placeholder="ID입력(test)">
				</td>
			</tr>
			<tr>
				<td>PASS : </td>
				<td>
					<input type="password" name="userPass" placeholder="PassWord입력(1234)">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="chkId" value="check" <%= strCheck %>>ID 기억하기
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="Login">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>