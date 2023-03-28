<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>비밀번호 찾기</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/member/style_search.css" rel="stylesheet"/>
		<link href="../css/member/style_searchPw.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp" flush="false" />
		<main>
			<div id="search_wrap">
				<div id="search_contents">
					<h2>비밀번호찾기</h2>
					<span>비밀번호 찾는방법을 선택해 주세요.</span>
				</div>
				<div id="search_inner_wrap">
					<form>
						<div id="searchPhone">
							<div>
								<input type="radio" name="searchMeasure"><h3>회원정보에 등록한 휴대전화로 찾기</h3>
							</div>
							<div>
								<span class="searchInfo">회원정보에 등록한 휴대전화 번호와 입력한 휴대전화 번호가 같아야 합니다.</span>
							</div>
							<div>
								<span><label>ID</label><input type="text"></span>
								<span><label>이름</label><input type="text"></span>
								<span><label>휴대전화 번호</label><input type="text"></span>
							</div>
						</div>
						<div id="searchEmail">
							<div>
								<input type="radio" name="searchMeasure"><h3>회원정보에 등록한 이메일 주소로 찾기</h3>
							</div>
							<div>
								<span class="searchInfo">회원정보에 등록한 이메일 주소와 입력한 이메일 주소가 같아야 합니다.</span>
							</div>
							<div>
								<span><label>ID</label><input type="text"></span>
								<span><label>이름</label><input type="text"></span>
								<span><label>이메일 주소</label><input type="text"></span>
							</div>
						</div>
						<div id ="searchBtn">
							<span><input type="button" value="찾기"></span>
						</div>
					</form>
				</div>
			</div>
		</main>
	</body>
</html>