<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 로그인</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_memberLogin.css" rel="stylesheet"/>
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
						<span><input type="text" id="memberPw" name="memberPw" placeholder="비밀번호를 입력해주세요"></span>
					</div>
					<div id="login_btn">
						<span><input type="button" value="로그인"></span>
					</div>
					<div id="login_kakao">
						<span><input type="button" value="카카오톡으로 로그인"></span>
					</div>
				</form>
				<div id="searchInfo">
					<span><input type="button" value="아이디 찾기" onclick="location.href='searchId.jsp'"></span>
					<span><input type="button" value="비밀번호 찾기" onclick="location.href='searchPw.jsp'"></span>
					<span><input type="button" value="회원가입 찾기" onclick="location.href='memberJoin.jsp'"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>