<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 회원가입</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_memberJoin.css" rel="stylesheet">
		
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){});

		function check(){
			var isYN;
			
			let memberId = $("#memberId").val();
			let memberPw = $("#memberPw").val();
			let memberPw2 = $("#memberPw2").val();
			let memberName = $("#memberName").val();
			let memberPhone = $("#memberPhone").val();
			let memberEmail = $("#memberEmail").val();
			let birth_yy = $("#birth_yy").val();
			let birth_dd = $("#birth_dd").val();
			
			$("#idMsg").text("");
			$("#pwMsg").text("");
			$("#pw2Msg").text("");
			$("#nameMsg").text("");
			$("#phoneMsg").text("");
			$("#emailMsg").text("");
			$("#birthMsg").text("");
			
			if(!memberId) {
				$("#idMsg").text("필수 정보입니다.");
				$("#memberId").focus();
				isYN = 0;
			}
			
			if(!memberPw) {
				$("#pwMsg").text("필수 정보입니다."); 
				$("#memberPw").focus();
				isYN = 0;
			}
			
			if(!memberPw2) {
				$("#pw2Msg").text("필수 정보입니다."); 
				$("#memberPw2").focus();
				isYN = 0;
			}
			
			else if(memberPw!=memberPw2){
				$("#pw2Msg").text("비밀번호가 일치하지 않습니다."); 
				$("memberPw2").focus();
				isYN = 0;
			}
			
			if(!memberName) {
				$("#nameMsg").text("필수 정보입니다."); 
				$("#name").focus();
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
				$("#emailMsg").text("필수 정보입니다."); 
				$("#memberEmail").focus();
				isYN = 0;
			}
			
			else IdCheck();
		}
		
		function IdCheck(){
 			let memberId = $("#memberId").val();
 			var isYN;
 			$.ajax({
 				url: "${pageContext.request.contextPath}/member/memberIdCheck.do",		
 				method: "POST",
 				data: {"memberId": memberId },
 				dataType: "json",
 				success : function(data){
	 					if (data.value == 1){
	 						$("#idMsg").text("중복된 ID 입니다.");
	 						$("#memberId").focus();
	 					}
	 					else{
 							alert("회원가입 완료");	
 							var fm = document.frm;
 							fm.action ="${pageContext.request.contextPath}/member/memberJoinAction.do";
 							fm.method = "post";
 							fm.submit();
 							fm.reset();
	 					}
 					},
 					error : function(request,status,error){
 						alert("다시 시도하시기 바랍니다.");		
 					}	
 			});	
		}
		
		</script>
	</head>
	<body>
	<header>	
		<div>
			<h1><strong>oo펜션 회원 가입</strong></h1>
		</div>	
	</header>		
	<form name="frm" id="frm">	
		<div id="join_wrap">
			<div class = "join_row">
				<h3>ID</h3>
				<span class="join_input">
					<input type="text" name="memberId" id="memberId">
				</span>
				<span id="idMsg"></span>
			</div>
			
			<div class = "join_row">
				<h3>비밀번호</h3>
				<span class="join_input">
					<input type="password" name="memberPw" id="memberPw">
				</span>
				<span id="pwMsg"></span> 
			</div>
			
			<div class = "join_row">
				<h3>비밀번호 확인</h3> 
				<span class="join_input">
					<input type="password" name="memberPw2" id="memberPw2">
				</span>
				<span id="pw2Msg"></span>
			</div>
			
			<div class = "join_row">
				<h3>이름</h3>
				<span class="join_input">
					<input type="text" name="memberName" id="memberName">
				</span>
				<span id="nameMsg"></span>
			</div>
			
			<div class = "join_row" id="join_birth">
				<h3>생년월일</h3>
					<span class="join_input">
						<input type="text" name="birth_yy" id="birth_yy" placeholder="년(4자)">
					</span>
					<span class="join_input">
						<select name="birth_mm" id="birth_mm">
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
					</span>
					<span class="join_input">
						<input type="text" name="birth_dd" id="birth_dd" placeholder="일">
					</span>
				<span id="birthMsg"></span>
			</div>
			
			<div class = "join_row">
				<h3>휴대전화</h3>
				<span class="join_input">
					<input type="text" name="memberPhone" id="memberPhone" placeholder="ex)01012345678">
				</span>
				<span id="phoneMsg"></span>
			</div>
			
			<div class = "join_row">
				<h3>본인 확인 Email</h3>
				<span class="join_input">
					<input type="text" name="memberEmail" id="memberEmail" placeholder="ex)abcd@naver.com">
				</span>
				<span id="emailMsg"></span>
			</div>
						
			<div class = "join_row" id="join_row_btn">
				<span id="join_btn">
					<input type="button" id="btn" value="가입하기" onclick="check()">
				</span>
			</div>
		</div>
	</form>
	</body>
</html>