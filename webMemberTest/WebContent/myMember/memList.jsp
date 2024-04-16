<%@page import="kr.or.ddit.vo.MyMemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.1.min.js"></script>
<script>
	mypath = '<%=request.getContextPath()%>';
	
	$(function() {
		// list 비동기 요청
		$.ajax({
			url : `<%=request.getContextPath()%>/myMemberList.do`,
			type : 'get',
			success : function(res) {
				let code = "";
				$.each(res, function(i, v) {
					console.log("mem : " + v.toString())
					code += `<tr>
								<td><a href="">${v.mem_id}</a></td> // a태그 입력시 프로필 창으로 이동
								<td>${v.mem_pass}</td>
								<td>${v.mem_name}</td>
								<td>${v.mem_tel}</td>
								<td>${v.mem_addr}</td>
							 </tr>`;
					
				});
				$('table').append(code);
			},
			error : function(xhr) {
				alert("실패 : " + xhr.status);
			},
			dataType : 'json'
		})
		
		// 회원가입폼 노출
		$('#insertMemberForm').on('click', function() {
			window.location.href = `${mypath}/myMember/insertMemberForm.jsp`;
		})	
		
	})
</script>
</head>
<body>

<h3>회원 목록 보기</h3>
<table id="memberList" border="2">
	<tr><td colspan="5" style="text-align: right;"><input style="margin: 2px;" type="button" id="insertMemberForm" value="회원 추가"></td></tr>
	<tr>
		<th>ID</th> 
		<th>PASS</th> 
		<th>NAME</th> 
		<th>TEL</th> 
		<th>ADDR</th> 
	</tr>
</table>

</body>
</html>