<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>비밀번호 찾기 결과</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_search.css" rel="stylesheet"/>
		<link href="../css/member/style_searchPwVal.css" rel="stylesheet"/>
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
								<span><label>ID</label></span>
								<span><label>비밀번호</label><input type="text"></span>
								<span><label>비밀번호 확인</label><input type="text"></span>
							</div>
						</div>
					</form>
				</div>
				<div id="searchBtn">
					<span><input type="button" value="로그인하기"></span>
					<span><input type="button" value="비밀번호 재설정"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>