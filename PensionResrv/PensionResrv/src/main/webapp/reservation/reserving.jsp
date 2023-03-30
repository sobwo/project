<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 실시간 예약하기</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserving.css" rel="stylesheet"/>
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
			});
		</script>
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
					<div style="margin:0 40px; background:rgba(230, 34, 34, 0.37);">
						<a href="reserving.jsp">예약하기</a>
					</div>
					<div style="width:130px;">
						<a href="reserv_check.jsp">예약확인/취소</a>
					</div>
				</div>
				<div id="pensionInfo_wrap">
					<h4 class="h4">예약정보</h4>
					<div id="pensionInfo">
						<br/><p>주소 : 전라북도 전주시 덕진구 덕진동</p><br/>
						<p>연락처 : 010-1234-5678</p><br/>
						<p>결제방법 : 카드결제, 무통장입금 (계좌번호 : 농협 0000-0000-0000)</p>
					</div>
				</div>
				<div id="dateInfo_wrap">
					<h4 class="h4">숙박기간</h4>
					<div id="dateInfo">
						<div id="dateImage"></div>
						<div id="begin">
							<p>체크인</p>
							<div id="checkIn">
								<input type="date" name="checkIn" 
									data-placeholder="숙박 시작일" 
									required
					 				aria-required="true"
					 				value="${rv.checkIn}"
					  				onChange={StartDateValueHandler}/>
			  				</div>
				  		</div>
				  		<div id="nights">
				  			<p>${night}박</p>
				  		</div>
				  		<div id="end">
				  			<p>체크아웃</p>
							<div id="checkOut">
								<input type="date" name="checkOut" 
									data-placeholder="숙박 종료일" 
									required
				 					aria-required="true"
				 					value="${rv.checkOut}"
				  					onChange={StartDateValueHandler}>
							</div>
						</div>
					</div>
				</div>
				<div id="roomInfo_wrap">
					<h4 class="h4">객실정보</h4>
					<div class="roomInfo">
						<c:forEach var="rlist" items="${rlist}">
							<div class="room">
								<div class="roomInfo_inner">
									<div class="roomImage"></div>
									<div class="roomInfo_inner_m">
										<p style="background:#20de07; color:#fff;">예약 가능</p><br/>
										<p>${rlist.roomName}</p><br/>
										<p>${rlist.capacity}</p><br/>
										<p>${rlist.numOfRoom} / ${rlist.sqft}</p>
									</div>
									<div class="roomPeople">
										<div class="adult">
											<p>성인<p>
											<input id="adult_minusBtn" type="button" value="&#45;">
											<input id="adult_value" type="text" name="adult" value="${rv.adultNum}" readonly
													style="	border:0;"/>
											<input id="adult_plusBtn" type="button" value="&#43;">
										</div>
										<div class="child">
											<p>아동<p>
											<input id="child_minusBtn" type="button" value="&#45;">
											<input id="child_value" type="text" name="child" value="${rv.childNum}" readonly
													style="	border:0;"/>
											<input id="child_plusBtn" type="button" value="&#43;">
										</div>
										<div class="baby">
											<p>유아<p>
											<input id="baby_minusBtn" type="button" value="&#45;">
											<input id="baby_value" type="text" name="baby" value="${rv.babyNum}" readonly
													style="	border:0;"/>
											<input id="baby_plusBtn" type="button" value="&#43;">
										</div>
									</div>
									<div class="roomBtn">
											<input type="button" value="선택">
											<p>200,000원</p>
									</div>								
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div id="roomOption_wrap">
					<h4 class="h4">옵션선택</h4>
					<div id="roomOption">
						<div id="option">
							<div id="option_inner">
								<div id="option_inner_m">
									<p>숯, 그릴</p>
								</div>
								<div id=optionCnt>
									<div id="cnt">
										<input type="button" value="&#45;">
										<p>0</p>
										<input type="button" value="&#43;">
									</div>
								</div>
								<div id="optionBtn">
									<input type="button" value="선택">
									<p>20,000원</p>
								</div>								
							</div>
						</div>
					</div>
				</div>
				<div id="totalPrice_wrap">
					<div id="totalPrice">
						<div id="price">
							<div id="price_inner">
								<div id="price_inner_m">
									총 결제 금액 :&nbsp;&nbsp;&nbsp;<div>220,000</div>&nbsp;원
								</div>
								<div id="priceBtn">
									<input type="button" value="결제" onclick="location.href='reserving_next.jsp'">
								</div>								
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</body>
</html>