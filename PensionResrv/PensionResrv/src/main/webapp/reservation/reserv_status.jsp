<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	if (session.getAttribute("memberNo") == null){	
		out.println("<script>alert('비회원으로 진행합니다.');</script>");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 실시간 예약 현황</title>
		<link href="../css/reserve/style_reserv_main.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_status.css" rel="stylesheet"/>
	</head>
	<body onload="autoReload();">
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">실시간 예약</h2>
		<div id="reserv_wrap">
			<div id="reserv_menu_wrap">
				<div style="background:rgba(230, 34, 34, 0.37);">
					<a href="${pageContext.request.contextPath}/reservation/reserv_status.do">예약 현황</a>
				</div>
				<div style="margin:0 40px;">
					<a href="${pageContext.request.contextPath}/reservation/reserveAction.do">예약하기</a>
				</div>
				<div style="width:130px;">
					<a href="${pageContext.request.contextPath}/reservation/reserv_check.do">예약확인/취소</a>
				</div>
			</div>
			<div id="reserv_calendar">
				<div id="calendar_inner_wrap">
					<h3 id="date" style="border:0">날짜(연,월,일)</h3>
					<p id="current_date"></p>
					<div id="payPerDate">
						<input type="checkbox">
						<p style="font-size:17px;">날짜 별 요금보기</p>
					</div>
				</div>
				<div id="status_noti">
						<p><strong>가</strong> : 예약가능&nbsp;&nbsp;&nbsp;&nbsp;<strong>진</strong> : 예약진행&nbsp;&nbsp;&nbsp;&nbsp;<strong>완</strong> : 예약완료</p>
				</div>
				<div id="calender_wrap">
					<table id="calendar">
						<thead>
							<tr id="dateMove" style="font-size:15px;">
								<th>
									<a id="before" href="javascript:beforem()"></a>
								</th>
								<th colspan="5" align="center">
								</th>
								<th>
									<a id="next" href="javascript:nextm()"></a>
								</th>
							</tr>
							<tr id="week">
								<th> 월 </th>
								<th> 화 </th>
								<th> 수 </th>
								<th> 목 </th>
								<th> 금 </th>
								<th><font color="#009de0">토</font></th>
								<th><font color="#ed5353">일</font></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
		<script type="text/javascript" src="table.js"></script>
		<script>
			date = new Date();
			year = date.getFullYear();
			month = date.getMonth() + 1;
			day = date.getDate();
			document.getElementById("current_date").innerHTML = "오늘 날짜 : "+year+"년 "+month + "월" + day + "일";
		</script>
	</body>
</html>