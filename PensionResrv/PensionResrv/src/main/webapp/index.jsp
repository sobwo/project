<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>실시간예약(홈)</title>
		<link href="css/reset.css" rel="stylesheet"/>
		<style>
 			/*body {
			  display: flex; 
			  justify-content: center; 
 			  flex-direction: column; 
 			  align-items: center; 
 			} */
			#main_wrap{
				position:relative;
				color:#000;
				width:1920px;
				height:980px;
			}
 			#main_wrap::before{
 				content:"";
 				width:1920px;
 				height:980px;
				background-image:url("images/main3.jpg");
				background-repeat: no-repeat;
        		background-position: center;
        		background-size:cover;
        		opacity: 0.3;
        		position:absolute;
    		    top: 0px;
        		left: 0px;
        		right: 0px;
        		bottom: 0px
			}
			header,nav,main{
				position:relative;
			}	
			header{
				width:1920px;
				height:130px;
				margin-bottom:200px;
				border-bottom:1px solid #444;
			}
				#logo_menu{
					width:500px;
					height:55px;
					display:inline-block;
					margin:40px;
					margin-right:300px;
					}
				#logo_menu h1{
					width:180px;
					height:34px;
					display:inline-block;
					font-size:45px;
					font-weight:bold;
				}
				#logo_menu span{
					display:inline-block;
					font-size:35px;
					margin-left:15px;
					padding-left:10px;
					border-left:1px solid #444;
				}
				nav{
					display:inline-block;
					font-size:21px;
					font-weight:bold;
					
				}
				nav ul li{
					display:inline-block;
					margin:0 30px;
					font-size:25px;
				}
				
				nav ul li:hover{
					color:#f00;
					cursor:pointer;
				}	
			main{
				width:1900px;
			}	
				section h2, section span, #contents{
					width:100%;
					font-size:45px;
					font-weight:bold;
					text-align:center;
					padding-bottom:50px;
				}
					#contents span{
						font-size:25px;
						font-weight:normal;
					}
					#btn{
						background:#c4040c;
						width:390px;
						height:75px;
						border-radius:5px;
						font-size:20px;
						color:#fff;
						font-weight:bold;
						border:0;
					}
			footer{
				width:1920px;
				height:500px;
				background:#444;
			}
			
			a{
				cursor:pointer;
			}
			
		</style>
	</head>
	<body>
		<div id="main_wrap">
			<header>
				<div id="logo_menu">
					<h1><a href="index.jsp">로고(홈)</a></h1>
					<span><a href="#">실시간 예약</a></span>
				</div>
				<nav>
					<ul>
						<li><a href="#">공지사항</a></li>
						<li><a href="#">실시간 예약</a></li>
						<li><a href="#">예약가이드</a></li>
						<li><a href="#">예약확인/취소</a></li>
						<li><a href="member/memberLogin.jsp">로그인</a></li>
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
						<span style="display:block;"><input id="btn" name="btn" type="button" value="예약 바로가기"></span>
					</div>
				</section>
			</main>
		</div>
		<footer>
		</footer>
	</body>
</html>