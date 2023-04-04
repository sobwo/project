<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 로그인</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_memberLogin.css" rel="stylesheet"/>
		<script>
			function login(){
				var fm = document.frm;
				fm.action ="${pageContext.request.contextPath}/member/memberLoginAction.do";
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
					<h2 style="font-weight:bold;">OO펜션</h2>
					<span style="display:block;">oo펜션의 실시간 예약 서비스의<br/>원활한 이용을 위해서는 로그인이 필요합니다.</span>
				</div>
				<form name="frm">
					<div id="login_form">
						<span><input type="text" id="memberId" name="memberId" placeholder="아이디를 입력해주세요"></span>
						<span><input type="password" id="memberPw" name="memberPw" placeholder="비밀번호를 입력해주세요"></span>
					</div>
					<div id="login_btn">
						<span><input type="button" value="로그인" onclick="login()"></span>
					</div>
<!-- 					<div id="login_kakao"> -->
<!-- 						<span><input type="button" value="카카오톡으로 로그인"></span> -->
<!-- 					</div> -->
					<div id="login_off">
						<span><input type="button" value="비회원으로 로그인" onclick="onclick=location.href='${pageContext.request.contextPath}/member/memberLogin_off.jsp'"></span>
					</div>
				</form>
				<div id="searchInfo">
					<span><input type="button" value="아이디 찾기" onclick="location.href='${pageContext.request.contextPath}/search/searchId.do'"></span>
					<span><input type="button" value="비밀번호 찾기" onclick="location.href='${pageContext.request.contextPath}/search/searchPw.do'"></span>
					<span><input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath}/member/memberJoin.do'"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>