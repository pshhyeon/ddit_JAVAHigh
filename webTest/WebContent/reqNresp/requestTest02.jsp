<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<h3>Request연습 Form(숫자 입력은 정수형으로 입력하게요.)</h3>
	<br>
	<form action="/webTest/requestTest02.do" method="get">
	<table>
		<tr>
			<td><input type="text" size="15" name="num1"></td>
			<td>
				<select name="oper">
					<option value="+">+</option>
					<option value="-">-</option>
					<option value="*">*</option>
					<option value="/">/</option>
					<option value="%">%</option>
				</select>
			</td>
			<td><input type="text" size="15" name="num2"></td>
			<td><input type="submit" value="확인"></td>
		</tr>
	</table>
	</form>
</body>
</html>