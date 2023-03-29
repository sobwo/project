<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>비밀번호 찾기 결과</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_search.css" rel="stylesheet"/>
		<link href="../css/member/style_searchPwVal.css" rel="stylesheet"/>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script>
			$(document).ready(function(){});
			function reset(){
				var isYN=1;
				let memberPw = $("#memberPw").val();
				let memberPw2 = $("#memberPw2").val();
				
				if(!memberPw) {
					alert("비밀번호를 입력해주세요.");
					$("#memberPw").focus();
					isYN = 0;
				}
				
				if(!memberPw2) {
					alert("비밀번호 확인을 입력해주세요.");
					$("#memberPw2").focus();
					isYN = 0;
				}
				
				else if(memberPw!=memberPw2){
					alert("비밀번호가 다릅니다.");
					$("memberPw2").focus();
					isYN = 0;
				}
				
				if(isYN==1){
					var fm = document.frm;
					fm.action ="${pageContext.request.contextPath}/search/searchPwReset.do";
					fm.method = "post";
					fm.submit();
					fm.reset();
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp" flush="false" />
		<main>
			<div id="search_wrap">
				<div id="search_contents">
					<h2>비밀번호찾기</h2>
					<span>새 비밀번호를 작성해주세요.</span>
				</div>
				<div id="search_inner_wrap">
					<form name="frm">
						<div id="searchVal">
							<div>
								<span><label>ID</label><input type="text" id="memberId" name="memberId" value="${memberId}" readonly style="border:0"></span>
								<span><label>비밀번호</label><input type="password" id="memberPw" name="memberPw"></span>
								<span><label>비밀번호 확인</label><input type="password" id="memberPw2" name="memberPw2"></span>
							</div>
						</div>
					</form>
				</div>
				<div id="searchBtn">
					<span><input type="button" value="비밀번호 재설정" onclick="reset()"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>