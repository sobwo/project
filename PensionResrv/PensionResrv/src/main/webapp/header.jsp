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
					<li><a href="memberLogin.jsp">로그인</a></li>
					<li><a href="memberJoin.jsp">회원가입</a></li>
					<li><a href="#">실시간예약</a></li>
					<li><a href="#">마이페이지</a></li>
				</ul>
			</div>
			<div id="logo_menu">
				<h1><a href="../index.jsp">로고(홈)</a></h1>
			</div>
			<nav id="main_nav">
				<ul>
					<li><a href="../board/boardNoti.jsp">공지사항</a></li>
					<li><a href="#">객실 안내</a></li>
					<li><a href="../reservation/reserv.jsp">실시간 예약</a></li>
					<li><a href="#">예약확인/취소</a></li>
					<li><a href="#">오시는길</a></li>
					<li><a href="#">부대시설</a></li>
				</ul>
			</nav>
		</header>
	</body>
</html>