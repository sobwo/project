<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 실시간 예약확인/취소</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_check.css" rel="stylesheet"/>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">실시간 예약</h2>
		<div id="reserv_wrap">
			<form name="frm">
				<div id="reserv_menu_wrap">
					<div>
						<a href="${pageContext.request.contextPath}/reservation/reserv_status.do">예약 현황</a>
					</div>
					<div style="margin:0 40px; ">
						<a href="${pageContext.request.contextPath}/reservation/reserveAction.do">예약하기</a>
					</div>
					<div style="width:130px; background:rgba(230, 34, 34, 0.37);">
						<a href="${pageContext.request.contextPath}/reservation/reserv_check.do">예약확인/취소</a>
					</div>
				</div>
				<div id="checkInfo_wrap">
					<h4 class="h4">예약내역</h4>
					<div id="checkInfo">
						<br/><p>예약자명 : ${sessionScope.memberName}</p><br/>
						<p>예약객실 :</p><br/>
						<table id="roomTable">
						<thead>

							<tr>
								<th style="display:none">객실번호</th>
								<th>객실명</th>
								<th>체크인</th>
								<th>체크아웃</th>
								<th>인원</th>
								<th>옵션</th>
								<th>결제금액</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rlist" items="${rlist}">
								<tr>
									<td style="display:none"><input type="text" name="reservNo" value="${rlist.reservNo}"></td>
									<td>${rlist.roomName}</td>
									<td>${rlist.checkIn}</td>
									<td>${rlist.checkOut}</td>
									<td>성인:${rlist.adultNum}<br/>아동:${rlist.childNum}<br/>유아:${rlist.babyNum}</td>
									<td>숯,그릴:${rlist.optionNum}</td>
									<td>${rlist.totalPay}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
						<p>결제수단 : 무통장 입금</p>
					</div>
				</div>
				<div id="payBtn">
					<input type="button" value="예약 취소" onclick="cancel()"/>
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
		<script>
			function cancel(){
				if(confirm("예약 취소하시겠습니까? 예(확인) 아니오(취소)")){
					var fm = document.frm;
					fm.action="${pageContext.request.contextPath}/reservation/reserv_cancel.do";
					fm.method="post";
					fm.submit();
				}
			}
		</script>
	</body>
</html>