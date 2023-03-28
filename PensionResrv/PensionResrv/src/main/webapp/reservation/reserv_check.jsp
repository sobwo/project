<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 실시간 예약확인/취소</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_check.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">실시간 예약</h2>
		<div id="reserv_wrap">
			<form>
				<div id="reserv_menu_wrap">
					<div>
						<a href="reserv_status.jsp">예약 현황</a>
					</div>
					<div style="margin:0 40px; ">
						<a href="reserving.jsp">예약하기</a>
					</div>
					<div style="width:130px; background:rgba(230, 34, 34, 0.37);">
						<a href="reserv_check.jsp">예약확인/취소</a>
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
					<input type="button" value="예약 취소">
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</body>
</html>