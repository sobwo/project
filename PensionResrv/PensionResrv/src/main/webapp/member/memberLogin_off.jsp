<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 오프라인으로 로그인</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_memberLogin.css" rel="stylesheet"/>
		<script>
			function login(){
				var fm = document.frm;
				fm.action ="${pageContext.request.contextPath}/member/memberLoginOffAction.do";
				fm.method = "post";
				fm.submit();
				fm.reset();
			}
			
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp" flush="false" />
		<main>
			<div id="login_wrap">
				<div id="login_contents">
					<h2 style="font-weight:bold;">비회원으로 로그인</h2>
					<span style="display:block;">이름, 생년월일, 핸드폰번호, 이메일주소를 입력해주세요.</span>
				</div>
				<form name="frm">
					<div id="login_form">
						<span><input type="text" id="memberName" name="memberName" placeholder="이름을 입력해주세요"></span>
						<span><input type="text" id="memberBirth" name="memberBirth" placeholder="생년월일을 입력해주세요"></span>
						<span><input type="text" id="memberPhone" name="memberPhone" placeholder="핸드폰번호를 입력해주세요"></span>
						<span><input type="text" id="memberEmail" name="memberEmail" placeholder="이메일주소를 입력해주세요"></span>
					</div>
					<div id="login_off">
						<span><input type="button" value="오프라인으로 로그인" onclick="login()"></span>
					</div>
				</form>
			</div>
		</main>
	</body>
	
</html>