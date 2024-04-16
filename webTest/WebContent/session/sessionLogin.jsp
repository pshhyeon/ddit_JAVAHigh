<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	// ==> JSP문서에서 세션은 'session'이라는 이름으로 저장되어있다.

	// 세션에 저장된 로그인 정보를 가져온다.
	String loginId = (String) session.getAttribute("loginId");
	%>
	
	<% 
	if (loginId == null) {
	%>
	
	<form action="<%= request.getContextPath() %>/sessionLogin.do" method="post" >
		<table border="1" style="margin: 0 auto;">
			<tr>
				<td>ID</td>
				<td>
					<input type="text" name="userId" placeholder="ID입력(admin)">
				</td>
			</tr>
			<tr>
				<td>PASS</td>
				<td>
					<input type="password" name="userPass" placeholder="PassWord입력(1234)">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="Login">
				</td>
			</tr>
		</table>
	</form>
	
	<% 
	} else { // 로그인이 되었을 때
	%>
	
	<h2><%= loginId %>님 반갑습니다</h2>
	<a href="<%= request.getContextPath()%>/sessionLogout.do">로그아웃</a>
	
	<% 
	}
	%>
	
</body>
</html>