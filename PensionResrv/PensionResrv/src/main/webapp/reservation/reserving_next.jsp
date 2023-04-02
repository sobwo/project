<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 실시간 예약하기</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserving_next.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">예약 결제</h2>
		<form>
			<div id="reserv_wrap">
				<div id="selectRoom_wrap">
					<h4 class="h4">선택 객실목록</h4>
					<table id="roomTable">
						<thead>
							<tr>
								<th>객실명</th>
								<th>이용일</th>
								<th>인원</th>
								<th>옵션</th>
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
				</div>
				<div id="insertInfo_wrap">
					<div id="insertInfo_subject">
						<h4 class="h4">선택 객실목록</h4>
						<div id="check">
							<input type="checkbox">
							<p>등록된 정보입력</p>
						</div>
						<div id="insertInfo_contents">
							<table id="insertInfo_table">
								<tr>
									<td>예약자명 : </td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td>생년월일 : </td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td>연락처 : </td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td>비상연락처 : </td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td>이메일 : </td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td>픽업여부 : </td>
									<td>
										<label>요청함</label><input type="checkbox">
										<label>요청안함</label><input type="checkbox">
									</td>
								</tr>
								<tr>
									<td>도착 예상시간 : </td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td>예약요청사항 : </td>
									<td><textarea></textarea></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div id="payInfo_wrap">
					<h4 class="h4">결제 정보</h4>
					<div id="payInfo">
						<table>
							<tr>
								<td>총 결제금액 :&nbsp;&nbsp;</td>
								<td>220,000원</td>
							</tr>
							<tr>
								<td>결제 수단</td>
								<td>
									<label>신용카드</label><input type="checkbox">
									<label>무통장입금</label><input type="checkbox">
								</td>
							</tr>
						</table>
						<div id="payBtn">
							<input type="button" value="취소(이전단계)" onClick="history.go(-1)">
							<input type="button" value="결제하기">
						</div>
					</div>
					
				</div>
			</div>
		</form>
		<jsp:include page="../footer.jsp"></jsp:include>
	</body>
</html>