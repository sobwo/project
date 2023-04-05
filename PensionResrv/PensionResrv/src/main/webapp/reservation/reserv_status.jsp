<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
				<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
				<c:forEach var="rpvlist" items="${rpvlist}">
					<input type="text" name="roomName" value="${rpvlist.roomName}" style="display:none">
					<input type="text" name="reservYn" value="${rpvlist.reservYn}" style="display:none">
					<input type="text" name="date_" value="${rpvlist.date_}" style="display:none">
					<input type="text" name="pricePerDay" value="${rpvlist.pricePerDay}" style="display:none">
				</c:forEach>
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
		<script>
			date = new Date();
			year = date.getFullYear();
			month = date.getMonth() + 1;
			day = date.getDate();
			document.getElementById("current_date").innerHTML = "오늘 날짜 : "+year+"년 "+month + "월" + day + "일";
			var target = document.querySelectorAll('#pNum'); // 클릭할 버튼요소를 변수 처리
			
			var btnPopClose = document.querySelectorAll('.btn_close');
			var targetID, targetID2;
			var target2 = document.querySelectorAll('#rName');
			var btnPopClose2 = document.querySelectorAll('.btn_close2');
			// 팝업 열기
			for(var i = 0; i < target.length; i++){
			  target[i].addEventListener('click', function(){
			    targetID = this.getAttribute('href');
			    document.querySelector(targetID).style.display = 'block';
			  });
			}

			for(var i = 0; i < target2.length; i++){
				target2[i].addEventListener('click', function(){
				targetID2 = this.getAttribute('href');
				document.querySelector(targetID2).style.display = 'block';
				});
			}

			// 팝업 닫기
			for(var j = 0; j < target.length; j++){
			  	btnPopClose[j].addEventListener('click', function(){
			    this.parentNode.parentNode.style.display = 'none';
			  });
			}

			for(var j = 0; j < target2.length; j++){
				btnPopClose2[j].addEventListener('click', function(){
				this.parentNode.parentNode.style.display = 'none';
				});
			}

			//달력 스크립트 ///////////////////////////////////////////////////

			var today = new Date(); //오늘 날짜        
			var date = new Date();

			//이전달
			function beforem() //이전 달을 today에 값을 저장
			{ 
				today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
				autoReload(); //만들기
			}

			//다음달
			function nextm()  //다음 달을 today에 저장
			{
				today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
				autoReload();
			}

			//오늘선택
			function thisMonth(){
				today = new Date();
				autoReload();
			}

			function autoReload()
			{
				var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
				var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날
				var tbcal = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
				var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
				var date_head = document.getElementById("date");
				date_head.innerHTML = today.getFullYear() + "년 "+ (today.getMonth() + 1) + "월";
				
				if(today.getMonth()+1==12) //  눌렀을 때 월이 넘어가는 곳
				{
					before.innerHTML=("◀"+today.getMonth())+"월";
					next.innerHTML="1월"+"▶";
				}
				else if(today.getMonth()+1==1) //  1월 일 때
				{
					before.innerHTML="◀"+"12월";
					next.innerHTML=(today.getMonth()+2)+"월" +"▶";
				}
				else //   12월 일 때
				{
					before.innerHTML="◀"+(today.getMonth())+"월";
					next.innerHTML=(today.getMonth()+2)+"월"+"▶";
				}


				// 남은 테이블 줄 삭제
				while (tbcal.rows.length > 2) 
				{
					tbcal.deleteRow(tbcal.rows.length - 1);
				}
				var row = null;
				row = tbcal.insertRow();
				var cnt = 0;
				var dayCheck = (nMonth.getDay()==0) ? 7 : nMonth.getDay(); //일요일을 마지막으로 넣기 위해서.

				 // 1일 시작칸 찾기
				for (i = 0; i < (dayCheck-1); i++) 
				{
					cnt = cnt + 1;	//요일값
					cell = row.insertCell();
					
					if (i>4) { //주말
						cell.style.backgroundColor = "#f7f7f7";
					}
				}


				// 달력 출력
				for (i = 1; i <= lastDate.getDate(); i++) // 1일부터 마지막 일까지
				{ 
					cell = row.insertCell();					
					var str="";
					
					str += "<div>"+i+"</div>";
					var day = (i<10) ? "0"+i : i;
					dateValue=year+month+day;
					str += "<div id='"+day+"'></div>"; //나중에 원하는 날에 일정을 넣기위해 id값을 날자로 설정
					cell.innerHTML = str;
					
					cnt = cnt + 1;
					if (cnt % 7 == 6) {//토요일
						var str="";
						str += "<div>"+i+"</div>";
						var day = (i<10) ? "0"+i : i;            	
						str += "<div id='"+day+"'>";
						str += "</div>";
						cell.innerHTML = str;                  
					}
					if (cnt % 7 == 0) { //일요일
						var str="";
						str += "<div>"+i+"</div>";
						var day = (i<10) ? "0"+i : i;            	
						str += "<div id='"+day+"'>";
						str += "</div>";
						cell.innerHTML = str;
						row = calendar.insertRow();// 줄 추가
					}
					
					//마지막 날짜가 지나면 일요일까지 칸 그리기
					if(lastDate.getDate() == i && ((cnt % 7) != 0)){
						var add = 7 - (cnt % 7);
						for(var k = 1; k <= add; k++){
							cell = row.insertCell();
							cnt = cnt + 1;
						}
					}
					
					//오늘날짜배경색
					if( today.getFullYear() == date.getFullYear() && today.getMonth() == date.getMonth() && i==date.getDate() )
					{
						cell.style.backgroundColor = "#e2f3da"; //오늘날짜배경색
					}
					
					//마지막 날짜가 지나면 일요일까지 칸 그리기
					if(lastDate.getDate() == i && ((cnt % 7) != 0)){
						var add = 7 - (cnt % 7);
						for(var k = 1; k <= add; k++){
							cell = row.insertCell();
							cnt = cnt + 1;
						}
					}
					  
				}
									
// 				원하는 날짜 영역에 내용 추가하기

				var month_val = (month<10)? "0"+month : month;
				
				var fullday;
				var tdId;
				var returnValue;
				for(var i=1;i<=lastDate;i++){
					var day = (i<10) ? "0"+i : i;
					tdId = str;
					var fullday = year+month_val+str;
	 				
					
	 				document.getElementById(tdId).innerHTML = fullday;
				}
				

			}
			
		</script>
	</body>
</html>