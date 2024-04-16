<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#lprodBtn').on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/lprodList.do",
				type : "get",
				success : function(data) {
					console.log("data : ",data);
					let htmlCode = "<table border='1'>";
					htmlCode += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>";
					$.each(data, function(i, v) {
						htmlCode += "<tr><td>" + v.lprod_id + "</td>";
						htmlCode += "<td>" + v.lprod_gu + "</td>";
						htmlCode += "<td>" + v.lprod_nm + "</td></tr>";
					}) // each
					htmlCode += "</table>"
					
					$("#result").html(htmlCode);
				},
				error : function(xhr) {
					alert("상태 : " + xhr.status)
				},
				dataType : 'json'
			}) // /ajax
		}) // /lprodBtn.onclick
		
		$('#lprodBtn2').on('click', function() {
			location.href = "<%=request.getContextPath()%>/lprodList2.do";
		})
		
	});
</script>
</head>
<body>
	<form action="">
		<input type="button" id="lprodBtn" value="Lprod자료 가져오기(비동기)">
		<input type="button" id="lprodBtn2" value="Lprod자료 가져오기(동기)">
	</form>
	<h3>Lprod 자료 목록</h3>
	<div id="result"></div>
</body>
</html>