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
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script>
			$(document).ready(function(){
				$('#adult_minusBtn').click(function(){
					var adult_minus = $('#adult_value').val();
					if(adult_minus > 0){
						adult_minus = parseInt(adult_minus)-1;
						$('#adult_value').attr("value",adult_minus);
					}
				});
				
				$('#adult_plusBtn').click(function(){
					var adult_plus = $('#adult_value').val();
					adult_plus = parseInt(adult_plus)+1;
					$('#adult_value').attr("value",adult_plus);
				});
				
				$('#child_minusBtn').click(function(){
					var child_minus = $('#child_value').val();
					if(child_minus > 0){
						child_minus = parseInt(child_minus)-1;
						$('#child_value').attr("value",child_minus);
					}
				});
				
				$('#child_plusBtn').click(function(){
					var child_plus = $('#child_value').val();
					child_plus = parseInt(child_plus)+1;
					$('#child_value').attr("value",child_plus);
				});
				
				$('#baby_minusBtn').click(function(){
					var baby_minus = $('#baby_value').val();
					if(baby_minus > 0){
						baby_minus = parseInt(baby_minus)-1;
						$('#baby_value').attr("value",baby_minus);
					}
				});
				
				$('#baby_plusBtn').click(function(){
					var baby_plus = $('#baby_value').val();
					baby_plus = parseInt(baby_plus)+1;
					$('#baby_value').attr("value",baby_plus);
				});
				
				$(document).on("click", "#selectRoom", function(){
					var roomName = $(this).val();
					
					$('#rName').text(roomName);
					$('#roomName_popup').hide();
				});
			});
			
			function selectPeople(){
				let adult_value = $('#adult_value').val();
				let child_value = $('#child_value').val();
				let baby_value = $('#baby_value').val();
				$('#pNum').text("성인"+adult_value+"명/아동"+child_value+"명/유아"+baby_value);
				$('#pNum_popup').hide();
			}
			
			function searchRoom(){
				var fm = document.frm;
				fm.action="${pageContext.request.contextPath}/reservation/reservAction.do";
				fm.method="post";
				fm.submit();
			}
		</script>
	</head>
	<body onload="autoReload();" style="background:#fff;">
		<jsp:include page="../header.jsp"/>
		<main>
			<form name="frm">
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
								<input id="adult_minusBtn" type="button" value="&#45;">
								<p>성인<p>
								<input id="adult_plusBtn" type="button" value="&#43;">
								<input id="adult_value" type="text" name="adult" value="0" readonly
										style="	border:0;"/>
							</div>
							<div id="child">
								<input id="child_minusBtn" type="button" value="&#45;">
								<p>아동<p>
								<input id="child_plusBtn" type="button" value="&#43;">
								<input id="child_value" type="text" name="child" value="0" readonly
										style="	border:0;"/>
							</div>
							<div id="baby">
								<input id="baby_minusBtn" type="button" value="&#45;">
								<p>아동<p>
								<input id="baby_plusBtn" type="button" value="&#43;">
								<input id="baby_value" type="text" name="baby" value="0" readonly
										style="	border:0;"/>
							</div>
							<div id="popup_btn" style="display:block;">
								<input type="button" value="확인" class="btn_check" onclick="selectPeople()">
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
										<th>수용인원<br/>(기준/최대)</th>
										<th>가격</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="rlist" items="${rlist}">
										<tr>
											<td><input type="button" id="selectRoom" value="${rlist.roomName}"/></td>
											<td>${rlist.capacity}</td>
											<td>${rlist.price}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div id="popup_btn2" style="display:block;">
								<input type="button" value="닫기" class="btn_close2">
							</div>
						</div>
						<div id="reservBtn">
							<input type="button" value="검색" onclick="searchRoom()">
						</div>
					</div>
				</div>
			</form>
			<div style="height:200px;"></div>
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