<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>

</script>

</head>

<%
	List<LprodVO> lprodList = (List<LprodVO>)request.getAttribute("lprodList");	
%>
<body>
<h3>Lprod 자료 출력</h3>
<table border="1">
	<tr>
		<th>Lprod_ID</th>
		<th>Lprod_GU</th>
		<th>Lprod_NM</th>
	</tr>
	<% for(LprodVO lvo : lprodList){ %>
	<tr>
		<td><%=lvo.getLprod_id() %></td>
		<td><%=lvo.getLprod_gu() %></td>
		<td><%=lvo.getLprod_nm() %></td>
	</tr>
	<%}%>
	<tr></tr>
</table>

</body>
</html>