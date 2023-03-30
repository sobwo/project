<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>등록정보 변경 페이지</title>
		<link href="../css/reserve/style_reserv_common.css" rel="stylesheet"/>
		<link href="../css/reserve/style_reserv_check.css" rel="stylesheet"/>
		<link href="../css/member/style_memberInfoModify.css" rel="stylesheet"/>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script>
		
		$(document).ready(function(){});
		function modify(){
			var isYN=1;

			let memberPw = $("#memberPw").val();
			let memberPw2 = $("#memberPw2").val();
			let memberName = $("#memberName").val();
			let memberBirth = $("#memberBirth").val();
			let memberPhone = $("#memberPhone").val();
			let memberEmail = $("#memberEmail").val();
			
			$("#pwMsg").text("");
			$("#pw2Msg").text("");
			$("#nameMsg").text("");
			$("#phoneMsg").text("");
			$("#emailMsg").text("");
			$("#birthMsg").text("");
			
			if(!memberPw) {
				$("#pwMsg").text("비밀번호를 입력해주세요.");
				$("#memberPw").focus();
				isYN = 0;
			}
			
			if(!memberPw2) {
				$("#pw2Msg").text("비밀번호 확인을 입력해주세요.");
				$("#memberPw2").focus();
				isYN = 0;
			}
			
			else if(memberPw!=memberPw2){
				$("#pw2Msg").text("비밀번호가 다릅니다.");
				$("memberPw2").focus();
				isYN = 0;
			}
			
			if(!memberName) {
				$("#nameMsg").text("필수 정보입니다."); 
				$("#membername").focus();
				isYN = 0;
			}
			
			if(memberBirth.length != 8){
				$("#birthMsg").text("8자리를 입력해주세요."); 
				$("#memberBirth").focus();
				isYN = 0;
			}
			
			if(!memberPhone) {
				$("#phoneMsg").text("필수 정보입니다."); 
				$("#memberPhone").focus();
				isYN = 0;
			}
			else if(isNaN(memberPhone)){
				$("#phoneMsg").text("숫자를 입력해주세요."); 
				$("#memberPhone").focus();
				isYN = 0;
			}
			
			if(!memberEmail) {
				alert("");
				$("#emailMsg").text("필수 정보입니다."); 
				$("#memberEmail").focus();
				isYN = 0;
			}
			
			else if(memberEmail.includes("@") ==false || memberEmail.includes(".com")==false){
				$("#emailMsg").text("형식에 맞지 않습니다."); 
				$("#memberEmail").focus();
				isYN = 0;
			}
			
			if(isYN==1){
				var fm = document.frm;
				fm.action ="${pageContext.request.contextPath}/member/memberInfoModifyAction.do";
				fm.method = "post";
				fm.submit();
			}
		}
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">등록정보 변경</h2>
		<div id="reserv_wrap">
			<form name="frm">
				<div id="memberInfo_wrap">
					<h4 class="h4">등록정보</h4>
					<div id="memberInfo">
						<table id="memberInfo_table">
							<tr>
								<td>아이디  </td>
								<td>${param.memberId}</td>
							</tr>
							<tr>
								<td>비밀번호  </td>
								<td><input type="password" id="memberPw" name="memberPw" value="${param.memberPw}"></td>
								<td class="msg" id="pwMsg"></td>
							</tr>
							<tr>
								<td>비밀번호 확인 </td>
								<td><input type="password" id="memberPw2" name="memberPw2"></td>
								<td class="msg" id="pw2Msg"></td>
							</tr>
							<tr>
								<td>이름  </td>
								<td><input type="text" id="memberName" name="memberName" value="${param.memberName}"></td>
								<td class="msg" id="nameMsg"></td>
							</tr>
							<tr>
								<td>생년월일  </td>
								<td><input type="text" id="memberBirth" name="memberBirth" value="${param.memberBirth}" placeholder="8자리 입력 ex)19110101"></td>
								<td class="msg" id="birthMsg"></td>
							</tr>
							<tr>
								<td>휴대전화  </td>
								<td><input type="text" id="memberPhone" name="memberPhone" value="${param.memberPhone}"></td>
								<td class="msg" id="phoneMsg"></td>
							</tr>
							<tr>
								<td>이메일  </td>
								<td><input type="text" id="memberEmail" name="memberEmail" value="${param.memberEmail}" placeholder="ex) asdf@naver.com"></td>
								<td class="msg" id="emailMsg"></td>
							</tr>
						</table>
					</div>
				</div>
				
				<div id="payBtn">
					<input type="button" value="정보 변경" onclick="modify()">
					<input type="button" value="변경 취소" onclick="history.go(-1)">
					<input type="button" value="회원 탈퇴" onclick="location.href='${pageContext.request.contextPath}/member/memberQuitAction.do'">
				</div>
			</form>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</body>
</html>