<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 실시간 예약하기</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserving_next.css" rel="stylesheet"/>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">예약 결제</h2>
		<form name="frm">
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
							<c:forEach var="rlist" items="${rlist}">
							<input type="text" name="select_roomNo" value="${rlist.select_roomNo}"
									style="display:none"/>
								<tr>
									<td><input type="text" name="select_roomName" value="${rlist.select_roomName}"readonly/></td>
									<td>
										체크인 : <input type="text" name="checkIn" value="${rlist.checkIn}"readonly/><br/>
										체크아웃 : <input type="text" name="checkOut" value="${rlist.checkOut}" readonly/>
									</td>
									<td>성인:<input type="text" name="adultNum" value="${rlist.adultNum}" readonly><br/>아동:<input type="text" name="childNum" value="${rlist.childNum}" readonly/><br/>유아:<input type="text" name="babyNum" value="${rlist.babyNum}" readonly/></td>
									<td>숯,그릴:<input type="text" name="optionNum" value="${rlist.optionNum}" readonly/></td>
									<td><input type="text" name="select_price" value="${rlist.select_price}" readonly/></td>
									<td><input type="text" name="select_totalPrice" value="${rlist.select_totalPrice}" readonly/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="insertInfo_wrap">
					<div id="insertInfo_subject">
						<h4 class="h4">예약자 정보</h4>
						<div id="check">
							<input id="infoCheck" type="checkbox">
							<p>등록된 정보입력</p>
						</div>
						<div id="insertInfo_contents">
							<input type="text" name="reserv_memberNo" value="${memberNo}" style="display:none">
							<table id="insertInfo_table">
								<tr>		
									<td>예약자명 : </td>
									<td><input type="text" name="reserv_memberName"></td>
								</tr>
								<tr>
									<td>생년월일 : </td>
									<td><input type="text" name="reserv_memberBirth"></td>
								</tr>
								<tr>
									<td>연락처 : </td>
									<td><input type="text" name="reserv_memberPhone"></td>
								</tr>
								<tr>
									<td>비상연락처 : </td>
									<td><input type="text" name="reserv_extraPhone"></td>
								</tr>
								<tr>
									<td>이메일 : </td>
									<td><input type="text" name="reserv_memberEmail"></td>
								</tr>
								<tr>
									<td>픽업여부 : </td>
									<td>
										<label>요청함</label><input type="checkbox" name="reserv_pickup" value="Y">
										<label>요청안함</label><input type="checkbox" name="reserv_pickup" value="N">
									</td>
								</tr>
								<tr>
									<td>도착 예상시간 : </td>
									<td><input type="text" name="reserv_arriveTime"></td>
								</tr>
								<tr>
									<td>예약요청사항 : </td>
									<td><textarea name="reserv_request"></textarea></td>
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
								<td><input type="text" name="totalPrice" value="${totalPrice}" readonly/></td>
							</tr>
							<tr>
								<td>결제 수단 :</td>
								<td>
									무통장입금
								</td>
							</tr>
						</table>
						<div id="payBtn">
							<input type="button" value="취소(이전단계)" onclick="history.go(-1)">
							<input type="button" value="결제하기" onclick="pay()">
						</div>
					</div>
					
				</div>
			</div>
		</form>
		<jsp:include page="../footer.jsp"></jsp:include>
		<script>
			$(document).ready(function(){
				$('input[type="checkbox"][name="reserv_pickup"]').click(function(){
					if($(this).prop('checked')){
						$('input[type="checkbox"][name="reserv_pickup"]').prop('checked',false);
						$(this).prop('checked',true); 
					}
				});
			});
		 	$("#infoCheck").change(function(){
		 		if($("#infoCheck").is(":checked")){
		 			$('input[name=reserv_memberName]').attr('value',${mv.memberName});
		 			$('input[name=reserv_memberBirth]').attr('value',${mv.memberBirth});
		 			$('input[name=reserv_memberPhone]').attr('value',${mv.memberPhone});
		 			$('input[name=reserv_memberEmail]').attr('value',${mv.memberEmail});
		 		}
		 		else{
		 			$('input[name=reserv_memberName]').attr('value','');
		 			$('input[name=reserv_memberBirth]').attr('value','');
		 			$('input[name=reserv_memberPhone]').attr('value','');
		 			$('input[name=reserv_memberEmail]').attr('value','');
		 		}
		 	});
		 	
		 	function pay(){
		 		alert($('input[name=reserv_memberBirth]').val());
		 		var memberName=$('input[name=reserv_memberName]').val();
		 		var memberBirth=$('input[name=reserv_memberBirth]').val();
		 		var memberPhone=$('input[name=reserv_memberPhone]').val();
		 		var extraPhone=$('input[name=reserv_extraPhone]').val();
		 		var memberEmail=$('input[name=reserv_memberEmail]').val();
		 		var pickup=$('input[name=reserv_pickup]').val();
		 		var arriveTime=$('input[name=reserv_arriveTime]').val();
		 		if(memberName==""){
		 			alert("예약자명을 입력해주세요.");
		 		}
		 		else if(memberBirth==""){
		 			alert("생년월일을 입력해주세요.");
		 		}
		 		else if(memberPhone==""){
		 			alert("핸드폰번호를 입력해주세요.");
		 		}
		 		else if(extraPhone==""){
		 			alert("비상연락처를 입력해주세요.");
		 		}
		 		else if(pickup==""){
		 			alert("픽업여부를 선택해주세요.");
		 		}
		 		else if(arriveTime==""){
		 			alert("예상도착시간을 입력해주세요.");
		 		}
		 		else{
			 		var fm = document.frm;
					fm.action="${pageContext.request.contextPath}/reservation/reserving_pay.do";
					fm.method="post";
					fm.submit();
		 		}
		 	}
		</script>
	</body>
</html>