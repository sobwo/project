<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>실시간예약(홈)</title>
		<link href="css/reset.css" rel="stylesheet"/>
		<link href="css/style_index.css" rel="stylesheet"/>
	</head>
	<style>
	</style>
	<body>
		<div id="main_wrap" style="outline:1px solid #f00;">
			<header>
				<div id="logo_menu">
					<h1><a href="index.jsp">로고(홈)</a></h1>
					<span><a href="reservation/reserv_main.jsp">실시간 예약</a></span>
				</div>
				<nav>
					<ul>
						<li><a href="board/boardNoti.jsp">공지사항</a></li>
						<li><a href="reservation/reserv_status.jsp">실시간 예약</a></li>
						<li><a href="#">예약가이드</a></li>
						<li><a href="#">예약확인/취소</a></li>
						<c:choose>
							<c:when test="${sessionScope.memberNo == null}">
								<li><a href="${pageContext.request.contextPath}/member/memberLogin.do">로그인</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/member/memberLogoutAction.do">로그아웃</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</header>
			<main>
				<section>
					<h2>
						<span>oo펜션 실시간 예약</span>
					</h2>
					<div id="contents">
						<span>oo펜션 실시간 예약사이트에 방문해주셔서 감사합니다.<br/>인터넷을 활용해 누구나 쉽고 빠르게 실시간으로 예약이 가능합니다.</span>
					</div>
					<div>
						<span style="display:block;"><input id="btn" name="btn" type="button" value="메인페이지 바로가기" onclick="location.href='reservation/reserv_main.do'"></span>
					</div>
				</section>
			</main>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>