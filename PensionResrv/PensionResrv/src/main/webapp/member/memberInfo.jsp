<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	if (session.getAttribute("memberNo") == null){	
		out.println("<script>alert('로그인이 필요합니다.'); history.back(-1);</script>");
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>마이페이지</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_check.css" rel="stylesheet"/>
		<link href="../css/member/style_memberInfo.css" rel="stylesheet"/>
		<script>
			function modify(){
				var fm = document.frm;
				fm.action="${pageContext.request.contextPath}/member/memberInfoModify.jsp";
				fm.method="post";
				fm.submit();
			}
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">마이페이지</h2>
		<div id="reserv_wrap">
			<form name="frm">
				<div id="memberInfo_wrap">
					<h4 class="h4">등록정보</h4>
					<div id="memberInfo">
						<table id="memberInfo_table">
							<tr>
								<td>아이디 :</td>
								<td><input type="text" name="memberId" value="${mv.memberId}" readonly/></td>
							</tr>
							<tr>
								<td> 비밀번호 : </td>
								<td>
									<input type="password" name="memberPw" value="${mv.memberPw}" readonly/>
								</td>
							</tr>
							<tr>
								<td> 이름 :  </td>
								<td><input type="text" name="memberName" value="${mv.memberName}" readonly/></td>
							</tr>
							<tr>
								<td> 생년월일 :</td>
								<td><input type="text" name="memberBirth" value="${mv.memberBirth}" readonly/></td>
							</tr>
							<tr>
								<td> 휴대전화 : </td>
								<td><input type="text" name="memberPhone" value="${mv.memberPhone}" readonly/></td>
							</tr>
							<tr>
								<td> 이메일 : </td>
								<td><input type="text" name="memberEmail" value="${mv.memberEmail}" readonly/></td>
							</tr>
						</table>
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