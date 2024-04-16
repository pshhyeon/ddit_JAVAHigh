<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script>
$(function(){
	$("#strBtn").on('click',function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonTest.do",
			type : "post",
			data : "choice=string",
			success : function(data){
				let htmlCode = "<h3>문자열 응답 결과</h3>";
				htmlCode += data;
				
				$("#result").html(htmlCode);
				
			},
			error : function(xhr){
				alert("오류 : "+xhr.status)
			},
			dataType : "json"
		})
	}) // /#strBtn
	
	$("#arrBtn").on('click',function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonTest.do",
			type : "post",
			data : "choice=array",
			success : function(data){
				// data = [10, 20, 30, 40, 50]
				let htmlCode = "<h3>배열 응답 결과</h3>";
				$.each(data, function(i, v) {
					htmlCode += i + "번째 데이터 = " + v + "<br>";
				});
				
				
				$("#result").html(htmlCode);
				
			},
			error : function(xhr){
				alert("오류 : "+xhr.status)
			},
			dataType : "json"
		})
	}) // /arrBtn
	
	$("#objBtn").on('click',function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonTest.do",
			type : "post",
			data : "choice=object",
			success : function(data){
				// data = {"lprod_id" : 100, "lprod_gu" : "P900", "lprod_nm" : "모니터"}
				let htmlCode = "<h3>객체 응답 결과</h3>";
				htmlCode += "ID : " + data.lprod_id + "<br>";
				htmlCode += "GU : " + data.lprod_gu + "<br>";
				htmlCode += "NM : " + data.lprod_nm + "<br>";
				
				$("#result").html(htmlCode);
				
			},
			error : function(xhr){
				alert("오류 : "+xhr.status)
			},
			dataType : "json"
		})
	}) // /objBtn
	
	$("#listBtn").on('click',function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonTest.do",
			type : "post",
			data : "choice=list",
			success : function(data){
				/* data =  [
					{"lprod_id":101,"lprod_gu":"P901","lprod_nm":"마우스"},
					{"lprod_id":102,"lprod_gu":"P902","lprod_nm":"키보드"},
					{"lprod_id":103,"lprod_gu":"P903","lprod_nm":"컴퓨터"}
					]
				*/
				let htmlCode = "<h3>List 응답 결과</h3>";
				$.each(data, function(i, v) {
				htmlCode += i + "번째 데이터<br>";
				htmlCode += "ID : " + v.lprod_id + "<br>"
				htmlCode += "GU : " + v.lprod_gu + "<br>"
				htmlCode += "NM : " + v.lprod_nm + "<br><hr>"
				});
				
				
				$("#result").html(htmlCode);
				
			},
			error : function(xhr){
				alert("오류 : "+xhr.status)
			},
			dataType : "json"
		})
	}) // /listBtn
	
	$("#mapBtn").on('click',function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonTest.do",
			type : "post",
			data : "choice=map",
			success : function(data){
				// data =  {"name":"홍길동", "tel":"010-1111-1111", "addr":"대전"}
				let htmlCode = "<h3>Map 응답 결과</h3>";
				htmlCode += "이름 : " + data.name + "<br>";
				htmlCode += "전화 : " + data.tel + "<br>";
				htmlCode += "주소 : " + data.addr + "<br>";
				htmlCode += "<hr><br>";
				
				// each에 사용하는 데이터가 객체형이면
				// 변수 i에는 해당 객체의 멤버변수 명이
				// 변수 v에는 해당 멤버변수명에 저장된 데이터 값이 저장된다.
				$.each(data, function(i, v) {
					htmlCode += i + " : " + v + "<br>";
				})
				$("#result").html(htmlCode);
				
			},
			error : function(xhr){
				alert("오류 : "+xhr.status)
			},
			dataType : "json"
		})
	}) // /listBtn
	
});


</script>


</head>
<body>
<form action="">
	<input type="button" id="strBtn" value="문자열 자료">
	<input type="button" id="arrBtn" value="배열 자료">
	<input type="button" id="objBtn" value="객체 자료">
	<input type="button" id="listBtn" value="List 자료">
	<input type="button" id="mapBtn" value="Map 자료">
	

</form>
<hr>
<div id="result"></div>


</body>
</html>