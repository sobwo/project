<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>회원가입 페이지</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/style_header.css?var=1" rel="stylesheet"/>
	</head>
	<body>
		<header id="header">
			<div id="top_nav">
				<ul>
					<c:choose>
						<c:when test="${sessionScope.memberNo == null}">
							<li><a href="${pageContext.request.contextPath}/member/memberLogin.do">로그인</a></li>
							<li><a href="${pageContext.request.contextPath}/member/memberJoin.do">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<li>${sessionScope.memberName}</li>
							<li><a href="${pageContext.request.contextPath}/member/memberLogoutAction.do">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="${pageContext.request.contextPath}/reservation/reserv_status.do">실시간예약</a></li>
					<li><a href="${pageContext.request.contextPath}/member/memberInfo.do">마이페이지</a></li>
				</ul>
			</div>
			<div id="logo_menu">
				<h1><a href="../index.jsp">로고(홈)</a></h1>
			</div>
			<nav id="main_nav">
				<ul>
					<li><a href="${pageContext.request.contextPath}/board/boardNoti.jsp">공지사항</a></li>
					<li><a href="#">객실 안내</a></li>
					<li><a href="${pageContext.request.contextPath}/reservation/reserv_status.jsp">실시간 예약</a></li>
					<li><a href="${pageContext.request.contextPath}/reservation/reserv_check.do">예약확인/취소</a></li>
					<li><a href="#">오시는길</a></li>
					<li><a href="${pageContext.request.contextPath}/board/faq.jsp">FAQ</a></li>
				</ul>
			</nav>
		</header>
	</body>
</html>