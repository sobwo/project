<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>마이페이지</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_check.css" rel="stylesheet"/>
		<link href="../css/member/style_memberInfo.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">마이페이지</h2>
		<div id="reserv_wrap">
			<form>
				<div id="memberInfo_wrap">
					<h4 class="h4">등록정보</h4>
					<div id="memberInfo">
						<table id="memberInfo_table">
							<tr>
								<td>아이디 : </td>
								<td></td>
							</tr>
							<tr>
								<td>비밀번호 : </td>
								<td></td>
							</tr>
							<tr>
								<td>이름 : </td>
								<td></td>
							</tr>
							<tr>
								<td>생년월일 : </td>
								<td></td>
							</tr>
							<tr>
								<td>휴대전화 : </td>
								<td></td>
							</tr>
							<tr>
								<td>이메일 : </td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
				<div id="checkInfo_wrap">
					<h4 class="h4">예약내역</h4>
					<div id="checkInfo">
						<br/><p>예약자명 :</p><br/>
						<p>예약객실 :</p><br/>
						<table id="roomTable">
						<thead>
							<tr>
								<th>객실명</th>
								<th>이용일</th>
								<th>인원</th>
								<th>요금타임</th>
								<th>이용요금</th>
								<th>결제금액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>101호<br/>등등</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
						<p>결제수단 :</p>
					</div>
				</div>
				<div id="payBtn">
					<input type="button" value="등록정보 변경">
					<input type="button" value="예약 취소">
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</body>
</html>