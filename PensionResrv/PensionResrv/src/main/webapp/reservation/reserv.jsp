<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>예약페이지</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/style_reserv.css" rel="stylesheet"/>
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
								<p>유아<p>
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
							<input type="button" value="검색">
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
							<tr>
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
								<th width="14%"> 월 </th>
								<th width="14%"> 화 </th>
								<th width="14%"> 수 </th>
								<th width="14%"> 목 </th>
								<th width="14%"> 금 </th>
								<th width="14%"><font color="#009de0">토</font></th>
								<th width="14%"><font color="#ed5353">일</font></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</main>
		<script>
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
	
			//달력 스크립트
	
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
							
							//원하는 날짜 영역에 내용 추가하기
	//		 				var tdId = "01"; //1일
	//		 				var str = "";
	//		 				str += "<br>09:00 일정1";
	//		 				str += "<br>12:00 일정2 \n";
	//		 				document.getElementById(tdId).innerHTML = str;
						}
		</script>
	</body>
</html>