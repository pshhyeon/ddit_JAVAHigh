<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>File Upload 연습</h3>
																				<!-- form에 있는 데이터를 파트영역 안에 담아서 보내라 -->
<form action="<%=request.getContextPath()%>/fileupload/fileupload.do" method="post" enctype="multipart/form-data">
	- 작성자 이름 : <input type="text" name="username"> <br> <hr>
	- 한 개의 파일 선택 : <input type="file" name="upFile1"> <br> <hr>
	- 다중 파일 선택 : <input type="file" name="upFile2" multiple> <br> <hr>
	<input type="submit" value="전 송">
</form>
 <br> <hr> <br>
 <a href="<%=request.getContextPath()%>/fileupload/fileList.do">전체 파일  목록 보기</a>
</body>
</html>