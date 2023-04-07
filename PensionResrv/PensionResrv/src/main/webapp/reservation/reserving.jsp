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
	</head>
	<body>
		<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">실시간 예약</h2>
		<div id="reserv_wrap">
			<form name="frm">
				<div id="reserv_menu_wrap">
					<div>
						<a href="${pageContext.request.contextPath}/reservation/reserv_status.do">예약 현황</a>
					</div>
					<div style="margin:0 40px; background:rgba(230, 34, 34, 0.37);">
						<a href="${pageContext.request.contextPath}/reservation/reserveAction.do">예약하기</a>
					</div>
					<div style="width:130px;">
						<a href="${pageContext.request.contextPath}/reservation/reserv_check.do">예약확인/취소</a>
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
						<input type="text" name="roomNo" value="${rv.roomNo}" readonly 
								style="display:none">
						<div id="dateImage"></div>
						<div id="begin">
							<p>체크인</p>
							<div id="checkIn">
								<input type="date" name="checkIn" 
									data-placeholder="숙박 시작일" 
									required
					 				aria-required="true"
					 				value="${rv.checkIn}"
					  				onChange="submitDay()"/>
			  				</div>
				  		</div>
				  		<div id="nights">
				  			<p></p>
				  		</div>
				  		<div id="end">
				  			<p>체크아웃</p>
							<div id="checkOut">
								<input type="date" name="checkOut" 
									data-placeholder="숙박 종료일" 
									required
				 					aria-required="true"
				 					value="${rv.checkOut}"
				  					onChange="submitDay()">
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
										<c:choose>
											<c:when test="${rlist.reservYn=='Y'}">
												<p style="background:#20de07; color:#fff;">예약 가능</p><br/>
											</c:when>
										</c:choose>
										<input type="text" id="rlist_roomNo" name="rlist_roomNo" value="${rlist.roomNo}"
												style="display:none"/>
										<p>${rlist.roomName}</p><br/>
										<input type="text" id="rlist_roomName" name="rlist_roomName" value="${rlist.roomName}"
												style="display:none"/>
										<input type="text" value="${rlist.pricePerDay}" name="select_price" style="display:none"/>
										<p id="capacity" class="capacity">${rlist.capacity}</p><br/>
										<p>${rlist.numOfRoom} / ${rlist.sqft}</p>
									</div>
									<div class="roomPeople">
										<div class="adult">
											<p>성인<p>
											<input id="adult_minusBtn" type="button" value="&#45;">
											<input id="adult_value" type="text" name="adultNum" value="${rv.adultNum}" readonly
													style="border:0;"/>
											<input id="adult_plusBtn" type="button" value="&#43;">
										</div>
										<div class="child">
											<p>아동<p>
											<input id="child_minusBtn" type="button" value="&#45;">
											<input id="child_value" type="text" name="childNum" value="${rv.childNum}" readonly
													style="border:0;"/>
											<input id="child_plusBtn" type="button" value="&#43;">
										</div>
										<div class="baby">
											<p>유아<p>
											<input id="baby_minusBtn" type="button" value="&#45;">
											<input id="baby_value" type="text" name="babyNum" value="${rv.babyNum}" readonly
													style="border:0;"/>
											<input id="baby_plusBtn" type="button" value="&#43;">
										</div>
									</div>
									<div class="roomBtn">
											<input id="rBtn" type="button" value="선택"/>
											<input type="button" id="roomNameCheck" name="roomNameCheck" value="${rlist.roomNo}"
												style="display:none"/>
											<input type="text" value="1" name="room_check" style="display:none"/>
											<p>${rlist.pricePerDay}원</p>
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
									<p>숯,그릴</p>
								</div>
								<div id=optionCnt>
									<div id="cnt">
										<input id="option_minusBtn" type="button" value="&#45;">
										<input id="option_value" type="text" name="option_value" value=0 readonly
													style="border:0;"/>
										<input id="option_plusBtn" type="button" value="&#43;">
									</div>
								</div>
								<div id="optionBtn">
									<input id="oBtn" type="button" value="선택">
									<p id="optionPrice">0원</p>
									<input id="option_oBtn" type="text" value="1" 
											style="display:none"/>
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
									총 결제 금액 :&nbsp;&nbsp;&nbsp;<input type="text" id="totalPrice" name="totalPrice" value=0 readonly>원
								</div>
								<div id="priceBtn">
									<input type="button" value="결제" onclick="pay()">
								</div>								
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
		<input type="text" name="test" value="test">
		<script>	
			$(document).ready(function(){			
				var length = $('input[id=roomNameCheck]').length;
				var roomNameCheck = $('input[name=roomNameCheck]');
				var roomNo = $('input[name=roomNo]').val();
				for(var i=0;i<length;i++){
					if(roomNameCheck.eq(i).val()==roomNo){
						$('input[name=roomNameCheck]').eq(i).siblings("input[type='button']").trigger("click");
					}
				}
			});
			
			//성인 인원수
			$(document).on("click","#adult_minusBtn",function(){
				var adult_minus = $(this).closest("div").find("input[type='text']").val();
				if(adult_minus > 0){
					adult_minus = parseInt(adult_minus)-1;
					$(this).closest("div").find("input[type='text']").attr("value",adult_minus);
				}
			});
			
			$(document).on("click","#adult_plusBtn",function(){
				var adult_plus = $(this).closest("div").find("input[type='text']").val();
				adult_plus = parseInt(adult_plus)+1;
				$(this).closest("div").find("input[type='text']").attr("value",adult_plus);
				
			});
			
			//아이 인원수
			$(document).on("click","#child_minusBtn",function(){ 
				var child_minus = $(this).closest("div").find("input[type='text']").val();
				if(child_minus > 0){
					child_minus = parseInt(child_minus)-1;
					$(this).closest("div").find("input[type='text']").attr("value",child_minus);
				}
			});
			
			$(document).on("click","#child_plusBtn",function(){
				var child_plus = $(this).closest("div").find("input[type='text']").val();
				child_plus = parseInt(child_plus)+1;
				$(this).closest("div").find("input[type='text']").attr("value",child_plus);
			});
			
			//유아 인원수	
			$(document).on("click","#baby_minusBtn",function(){
				var baby_minus = $(this).closest("div").find("input[type='text']").val();
				if(baby_minus > 0){
					baby_minus = parseInt(baby_minus)-1;
					$(this).closest("div").find("input[type='text']").attr("value",baby_minus);
				}
			});
			
			$(document).on("click","#baby_plusBtn",function(){
				var baby_plus = $(this).closest("div").find("input[type='text']").val();
				baby_plus = parseInt(baby_plus)+1;
				$(this).closest("div").find("input[type='text']").attr("value",baby_plus);
			});
			
			
			//방 선택 버튼
			$(document).on("click","#rBtn",function(){
				var totalPrice = $("input[name=totalPrice]").val();
				var rBtn_option = $(this).closest("div").find("input[type='text']").val();
				var price = $(this).siblings("p").text();
				var index = price.indexOf("원");
				
				price = price.substring(0,index);
				
				if(rBtn_option=="1"){
					totalPrice = parseInt(totalPrice) + parseInt(price); 
					$("input[name=totalPrice]").attr("value",totalPrice);
					$(this).css('background-color','rgb(211, 0, 0)');
					$(this).closest("div").find("input[type='text']").attr("value","2");
				}
				
				else if(rBtn_option=="2"){
					totalPrice = parseInt(totalPrice) - parseInt(price);
					
					$("input[name=totalPrice]").attr("value",totalPrice);
					$(this).css('background-color','#3498db');
					$(this).closest("div").find("input[type='text']").attr("value","1"); 
				}
			});
			
			//옵션 수
			$(document).on("click","#option_minusBtn",function(){
				var option_minus = $(this).closest("div").find("input[type='text']").val();
				if(option_minus > 0){
					option_minus = parseInt(option_minus)-1;
					$(this).closest("div").find("input[type='text']").attr("value",option_minus);
					
					var option_price = $('#optionPrice').text();
					var index = option_price.indexOf("원");
					option_price = option_price.substring(0,index);
					option_price = parseInt(option_price) - 20000;
					
					$("#optionPrice").text(option_price+"원");
				}
			});
			
			$(document).on("click","#option_plusBtn",function(){
				var option_plus = $(this).closest("div").find("input[type='text']").val();
				option_plus = parseInt(option_plus)+1;
				$(this).closest("div").find("input[type='text']").attr("value",option_plus);
				
				var option_price = $('#optionPrice').text();
				var index = option_price.indexOf("원");
				option_price = option_price.substring(0,index);
				option_price = parseInt(option_price) + 20000;
				
				$("#optionPrice").text(option_price+"원");
			});
			
			//옵션 선택 버튼
			$(document).on("click","#oBtn",function(){
				var totalPrice = $("input[name=totalPrice]").val();
				var oBtn_option = $("#option_oBtn").val();
				
				var price = $(this).siblings("p").text();
				var index = price.indexOf("원");
				price = price.substring(0,index);
				
				if(price <= 0){
					oBtn_option = 2;
				}
				
				if(oBtn_option=="1"){
					totalPrice = parseInt(totalPrice) + parseInt(price);
					$("input[name=totalPrice]").attr("value",totalPrice);
					$(this).css('background-color','rgb(211, 0, 0)');
					$(this).closest("div").find("input[type='text']").attr("value","2");
				}
				
				else if(oBtn_option=="2"){
					$("#option_value").attr("value","0");  // 동적할당시 변경 필요
					$("#optionPrice").text("0원"); // 동적할당시 변경 필요
					totalPrice = parseInt(totalPrice) - parseInt(price);
					$("input[name=totalPrice]").attr("value",totalPrice);
					$(this).css('background-color','#3498db');
					$(this).closest("div").find("input[type='text']").attr("value","1"); 
				}
			});
					
			function pay(){
				
				var checkIn = new Date($("input[name=checkIn]").val());
				var checkOut = new Date($("input[name=checkOut]").val());
 				var timeDiff = checkOut.getTime()-checkIn.getTime();
				var dayDiff = timeDiff/(1000*3600*24);
				var num = 0;
				for(var i=0;i<$('input[name=room_check]').length;i++){
					if($('input[name=room_check]').val()=='2') num++;
				}
				if($("input[name=totalPrice]").val()==0)
					alert("방을 선택해 주세요.");
				else if(checkIn==0)
					alert("체크인 날짜를 선택해 주세요.");
				else if(checkOut==0)
					alert("체크아웃 날짜를 선택해 주세요.");
				else if(dayDiff<=0)
					alert("체크인,체크아웃 날짜를 확인해주세요.");
				else if(num>1)
					alert("방을 1개만 선택해주세요.");
				else{
					var fm = document.frm;
					fm.action="${pageContext.request.contextPath}/reservation/reserving_next.do";
					fm.method="post";
					fm.submit();
				}
			}
			
			function submitDay(){
				var checkIn = $("input[name=checkIn]").val();
				var checkOut = $("input[name=checkOut]").val();
				var contextPath = $("input[name=contextPath]").val();
				
				$.ajax({
				    type: "GET",
				    url:contextPath+"/reservation/reserv_ajax.do",
				    data: {"checkIn":checkIn,"checkOut":checkOut},
				    dataType: "json",
				    success: function (data) {
						var str ="";
						for(var i=0;i<data.length;i++){
							str+="<div class='room'>";
							str+="<div class='roomInfo_inner'>";
							str+="<div class='roomImage'></div>";
							str+="<div class='roomInfo_inner_m'>";
							str+="<p style='background:#20de07; color:#fff;'>예약 가능</p><br/>";
							str+="<input type='text' id='rlist_roomNo' name='rlist_roomNo' value='"+data[i].roomNo+"' style='display:none'/>";
							str+="<p>"+data[i].roomName+"</p><br/>";
							str+="<input type='text' id='rlist_roomName' name='rlist_roomName' value='"+data[i].roomName+"' style='display:none'/>";
							str+="<input type='text' value='"+data[i].pricePerDay+"' name='select_price' style='display:none'/>";
							str+="<p id='capacity' class='capacity'>"+data[i].capacity+"</p><br/>";
							str+="<p>"+data[i].numOfRoom+" / "+data[i].sqft+"</p>";
							str+="</div>";
							str+="<div class='roomPeople'>";
							str+="<div class='adult'>";
							str+="<p>성인<p>";
							str+="<input id='adult_minusBtn' type='button' value='&#45;'>";
							str+="<input id='adult_value' type='text' name='adultNum' value=0 readonly style='border:0;'/>";
							str+="<input id='adult_plusBtn' type='button' value='&#43;'>";
							str+="</div>";
							str+="<div class='child'>";
							str+="<p>아동<p>";
							str+="<input id='child_minusBtn' type='button' value='&#45;'>";
							str+="<input id='child_value' type='text' name='childNum' value=0 readonly style='border:0;'/>";
							str+="<input id='child_plusBtn' type='button' value='&#43;'>";
							str+="</div>";
							str+="<div class='baby'>";
							str+="<p>유아<p>";
							str+="<input id='baby_minusBtn' type='button' value='&#45;'>";
							str+="<input id='baby_value' type='text' name='babyNum' value=0 readonly style='border:0;'/>";
							str+="<input id='baby_plusBtn' type='button' value='&#43;'>";
							str+="</div>";
							str+="</div>";
							str+="<div class='roomBtn'>";
							str+="<input id='rBtn' type='button' value='선택'/>";
							str+="<input type='button' id='roomNameCheck' name='roomNameCheck' value='"+data[i].roomNo+"' style='display:none'/>";
							str+="<input type='text' value='1' name='room_check' style='display:none'/>";
							str+="<p>"+data[i].pricePerDay+"원</p>";
							str+="</div>";								
							str+="</div>";
							str+="</div>";
						}
						
						$('.roomInfo').html(str);
// 						location.href=contextPath+"/reservation/reserv_ajax.do";
				    },
				    error: function () {
				        alert("전송 실패");
				    }
				});
			}

		</script>
	</body>
</html>