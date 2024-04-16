<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.1.min.js"></script>
<script src="../js/jquery.serializejson.min.js"></script>

<script>
$(function() {
	$('#idChk').on('click', function() {
		id = $('#id').val().trim();
		$.ajax({
			url : '<%=request.getContextPath()%>/duplicateCheck.do',
			type : 'get',
			data : {"id" : id},
			success : function(result) {
				if(result.flag == "true"){ // 확인된 ID가 없을시
					alert("중복된 ID입니다.");
				}
				if(result.flag == "false"){ // 확인된 ID가 없을시
					alert("사용가능한 ID입니다.");
				}
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
		
	}) // /idChk
	
	
// 	$('#join').on('click', function() {

// // 		data = $('#memberJoin').serializeJSON();

// 		id   = $('#id').val().trim();
// 		pass = $('#pass').val().trim();
// 		name = $('#name').val().trim();
// 		tel  = $('#tel').val().trim();
// 		addr = $('#addr').val().trim();
// 		file = $('#file').val().trim();
		
// 		data = {"mem_id" : id
// 			  , "mem_pass" : pass
// 			  , "mem_name" : name
// 			  , "mem_tel" : tel
// 			  , "mem_addr" : addr
// 			  , "mem_photo" : file
// 		} 

// 		console.log(data);
		
// 		$.ajax({
<%-- 			url : '<%=request.getContextPath()%>/insertMember.do', --%>
// 			type : 'post',
// 			data : data,
// 			success : res => {
// 				alert(res.flag);
// 			},
// 			error : xhr => {
// 				alert("상태 : " + xhr.status);
// 			},
// 			dataType : 'json'
// 		})
		
// 	}) // /cancel
	
	
	
	$('#cancel').on('click', function() {
		window.history.back();
	}) // /cancel
})

/*
 id = $('#id').val().trim();
	pass = $('#pass').val().trim();
	name = $('#name').val().trim();
	tel = $('#tel').val().trim();
	addr = $('#addr').val().trim();
	file = $('#file').val().trim();
	
	data : {"id" : id
			, "pass" : pass
			, "name" : name
			, "tel" : tel
			, "addr" : addr
			, "file" : file
			} 
 
 */
</script>
</head>
<body>

<h3>회원 정보 입력 폼</h3>
<form action="<%=request.getContextPath()%>/insertMember.do" id="memberJoin" method="post" enctype="multipart/form-data">
	<table id="memberList" border="1">
		<tr>
			<td>회원 ID</td>
			<td> 
				<input type="text" id="id" name="mem_id"> 
				<input type="button" id="idChk" value="중복확인">
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" id="pass" name="mem_pass"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="text" id="passChk"></td>
		</tr>
		<tr>
			<td>회원이름</td>
			<td><input type="text" id="name" name="mem_name"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" id="tel" name="mem_tel"></td>
		</tr>
		<tr>
			<td>회원주소</td>
			<td><input type="text" id="addr" name="mem_addr"></td>
		</tr>
		<tr>
			<td>프로필 사진</td>
			<td><input type="file" id="file" name="mem_photo"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<input type="submit" id="join" value="저장">
				<input type="button" id="cancel" value="취소">
				<a href="<%=request.getContextPath()%>/myMember/memList.jsp"><input type="button" id="memberList" value="회원 목록"></a>
			</td>
		</tr>
		
	</table>
</form>

</body>
</html>