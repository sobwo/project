 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>ID 찾기 결과</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_search.css" rel="stylesheet"/>
		<link href="../css/member/style_searchIdVal.css" rel="stylesheet"/>
		<script>
			function search(){
				var fm = document.frm;
				fm.action ="${pageContext.request.contextPath}/search/searchPw.jsp";
				fm.method = "post";
				fm.submit();
				fm.reset();
			}
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp" flush="false" />
		<main>
			<div id="search_wrap">
				<div id="search_contents">
					<h2>아이디찾기</h2>
					<span>고객님의 정보와 일치하는 아이디 목록입니다.</span>
				</div>
				<div id="search_inner_wrap">
					<form name="frm">
						<div id="searchVal">
							<c:forEach var="vi" items="${valueId}">
								<div>
									<input type="radio" name="searchId" value="${vi.memberId}"><span>${vi.memberId}</span><span>가입 : ${vi.joinDate}</span>
								</div>
							</c:forEach>
						</div>
					</form>
				</div>
				<div id="searchBtn">
					<span><input type="button" value="로그인하기" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do'"></span>
					<span><input type="button" value="비밀번호찾기" onclick="search()"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>