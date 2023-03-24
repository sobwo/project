<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>ID 찾기 결과</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<link href="../css/style_search.css" rel="stylesheet"/>
		<style>
			#search_inner_wrap{
				height:200px;
				display: flex; 
				justify-content: center; 
				flex-direction: column; 
				align-items: center; 
			}
			#searchVal{
				width:100%;
			}
			#searchVal div{
				margin:20px 0;
			}
			#searchVal div span{
				display:inline-block;
				margin-left:10px;
				margin-right:40px;
			}
			#searchVal div span:nth-child(2){
				width:100px;
				font-weight:bold;
			}
			#searchVal div span:last-child{
				font-size:13px;
			}
		</style>
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
							<div>
								<input type="radio" name="searchVal"><span>ID</span><span>가입 : oooo년oo월oo일</span>
							</div>
						</div>
					</form>
				</div>
				<div id="searchBtn">
					<span><input type="button" value="로그인하기"></span>
					<span><input type="button" value="비밀번호찾기"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>