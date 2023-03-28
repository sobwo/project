<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>등록정보 변경 페이지</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_check.css" rel="stylesheet"/>
		<link href="../css/member/style_memberInfoModify.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">등록정보 변경</h2>
		<div id="reserv_wrap">
			<form>
				<div id="memberInfo_wrap">
					<h4 class="h4">등록정보</h4>
					<div id="memberInfo">
						<table id="memberInfo_table">
							<tr>
								<td>아이디  </td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>비밀번호  </td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>비밀번호 확인 </td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>이름  </td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>생년월일  </td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>휴대전화  </td>
								<td><input type="text"></td>
							</tr>
							<tr>
								<td>이메일  </td>
								<td><input type="text"></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div id="payBtn">
					<input type="button" value="정보 변경">
					<input type="button" value="변경 취소">
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</body>
</html>