<%@page import="kr.or.ddit.vo.FileInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<FileInfoVO> fileList = (List<FileInfoVO>)request.getAttribute("fileList");
%>
<body>
<h3>전체 파일 목록</h3>

<a href="<%=request.getContextPath()%>/fileupload/fileupload.do">파일 업로드</a>

<table border="1">
<thead>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>저장 파일명</th>
		<th>원래의 파일명</th>
		<th>파일 크기</th>
		<th>날 짜</th>
		<th>비 고</th>
	</tr>

</thead>
<tbody>
<%
	if(fileList == null || fileList.size() == 0){
%>
	<tr>
		<td colspan="7">파일 목록이 하나도 없습니다...</td>
	</tr>

<%} else {
		for(FileInfoVO fileVO : fileList) {
%>
	<tr>
		<td><%=fileVO.getFile_no() %></td>
		<td><%=fileVO.getFile_writer() %></td>
		<td><%=fileVO.getSave_file_name() %></td>
		<td><%=fileVO.getOrigin_file_name() %></td>
		<td><%=fileVO.getFile_size() %></td>
		<td><%=fileVO.getFile_date() %></td>
		<td><a href="<%=request.getContextPath()%>/fileupload/filedownload.do?fileno=<%=fileVO.getFile_no()%>">Download</a></td>
	</tr>

<%} }%>
</tbody>


</table>
</body>
</html>