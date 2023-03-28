<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>예약페이지</title>
		<link href="../css/reserve/style_reserv_main.css" rel="stylesheet"/>
		<link href="../css/board/style_board.css" rel="stylesheet"/>
	</head>
	<body onload="autoReload();" style="background:#fff;">
		<jsp:include page="../header.jsp"/>
		<main>
			<form>
				<div id="reserv_wrap">
					<div id="reserv_inner_wrap">
						<div id="checkIn">
							<input type="date" name="checkIn" 
								data-placeholder="숙박 시작일" 
								required
			 					aria-required="true"
			 					value={startDateValue}
			  					onChange={StartDateValueHandler}/>
		  				</div>
						<div id="checkOut">
							<input type="date" name="checkOut" 
								data-placeholder="숙박 종료일" 
								required
			 					aria-required="true"
			 					value={startDateValue}
			  					onChange={StartDateValueHandler}>
						</div>
						<div id="peopleNum">
							<a href="#pNum_popup" id="pNum">인원(성인/유아)</a>
						</div>
						<div id="pNum_popup" style="display:none; border:1px solid black;">
							<div id="adult">
								<input type="button" value="&#45;">
								<p>성인<p>
								<input type="button" value="&#43;">
								<p id="adult_value">0</p>
							</div>
							<div id="child">
								<input type="button" value="&#45;">
								<p>아동<p>
								<input type="button" value="&#43;">
								<p id="child_value">0</p>
							</div>
							<div id="popup_btn" style="display:block;">
								<input type="button" value="확인" class="btn_check">
								<input type="button" value="취소" class="btn_close">
							</div>
						</div>	
						<div id="roomName">
							<a href="#roomName_popup" id="rName">방 이름</a>
						</div>
						<div id="roomName_popup" style="display:none;border:1px solid black;">
							<table>
								<thead>
									<tr>
										<th>방이름</th>
										<th>수용인원</th>
										<th>기타내용</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>1</th>
										<th>1</th>
										<th>1</th>
									</tr>
								</tbody>
							</table>
							<div id="popup_btn2" style="display:block;">
								<input type="button" value="닫기" class="btn_close2">
							</div>
						</div>
						<div id="reservBtn">
							<input type="button" value="검색" onclick=>
						</div>
					</div>
				</div>
			</form>
			<div id="reserv_status_wrap">
				<div id="reserv_status">
					<h3>실시간 예약 현황</h3>
				</div>
				<div id="calender_wrap">
					<div id="date">
						<h3>날짜(연/월/일)</h3>
					</div>
					<table id="calendar">
						<thead>
							<tr id="dateMove">
								<th>
									<a id="before" href="javascript:beforem()"></a>
								</th>
								<th colspan="5" align="center">
									<div id="yearmonth"></div>
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
			<div id="noti_wrap_main">
				<h3>공지사항</h3><br/>
				<table id="main_board" style="margin:0;">
					<thead>
						<tr class="board_col">
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bv" items="${boardList}">
						<tr class="board_col">
							<td style="text-align:center;">${bv.bidx}</td>
							<td style="overflow:hidden">
								<c:forEach var="i" begin="1" end="7" step="1">
									out.println("&nbsp;");
									<c:if test="${i==7}">
										out.println("&#8618;");
									</c:if>
								</c:forEach>
								<a href="${pageContext.request.contextPath}/board/boardContents.do?bidx=${bv.bidx}">${bv.subject}</a>
							</td>		
							<td>${bv.writer}</td>
							<td>${bv.writeday}</td>
							<td>${bv.viewCnt}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="map_wrap_main">
				<h3>오시는길</h3><br/>
				<div id="map">
				</div>
			</div>
		</main>
		<jsp:include page="../footer.jsp"></jsp:include>
		<script type="text/javascript" src="table.js"></script>
	</body>
</html>