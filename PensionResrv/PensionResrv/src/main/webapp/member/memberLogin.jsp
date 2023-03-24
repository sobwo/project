<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 로그인</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<style>
			#login_wrap{
				margin: 0 auto;
				width:700px	;
			  	height:500px;
			  	background:#fff;
			  	display: flex;
				justify-content: center;
				flex-direction: column;
				align-items: center;
				padding:30px 0;
				margin-bottom:120px;
				border-radius:10px;
			}
				#login_contents{
					width:700px;
				}
				#login_contents h2,#login_contents span{
					width:100%;
					font-size:38px;
					text-align:center;
					padding-bottom:30px;
				}
				#login_contents span{
					font-size:20px;
					line-height:1.5em;
				}
				
				#login_form{
					text-align:center;
					width:600px;
					padding-bottom:15px;
				}
				#login_form span input{
					width:100%;
					height:45px;
					border:0;
					border:1px solid gray;
					border-radius:5px;
					font-size:18px;
					text-indent:15px;
					margin-bottom:15px;
				}
				
				#login_btn input, #login_kakao input,#searchInfo input{
					width:600px;
					height:45px;
					margin-bottom:15px;
					border:0;
					border-radius:5px;
					cursor:pointer;
					font-size:18px;
					font-weight:bold;
					color:#fff;
					transition: all 0.2s ease-in-out;
				}
				#login_btn input{
					background:#e74c3c;
				}
				#login_kakao input{
					background:#f1c40f
				}
				#searchInfo input{
					width:180px;
					height:45px;
					background:#3498db;
					margin:0 13px;
				}	
				
				#login_btn input:hover, 
				#login_kakao input:hover,
				#searchInfo input:hover{
					 transform: scale(1.01);
				}
		</style>
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
					<span><input type="button" value="아이디 찾기"></span>
					<span><input type="button" value="비밀번호 찾기"></span>
					<span><input type="button" value="회원가입 찾기"></span>
				</div>
			</div>
		</main>
	</body>
	
</html>