<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>예약페이지</title>
		<link href="../css/reset.css" rel="stylesheet"/>
		<style>
			#reserv_wrap{
				border:1px solid #444; 
				width:1600px;
				height:200px;
				background:#fff;
				text-align:center;
			}
			#reserv_inner_wrap{
				width:100%;
				height:100px;
				text-align:center;
				margin-bottom:30px;
			}
			#reserv_inner_wrap div{
				display:inline-block;
				background:#fff;
				margin-top:40px;
				margin-left:50px;
				border:0;
			}
			
			#checkIn,#checkOut,#peopleNum,#roomName{
				width:300px;
				height:60px;
				text-align:center;
			}
			
			#peopleNum a,#roomName a{
				display:inline-block;
				line-height:60px;
			}
			
			#checkIn input, #checkOut input, #peopleNum a, #roomName a{
				width:100%;
				height:100%;
				font-size:20px;
				border:1px solid gray;
				border-radius:10px;
				color:gray;
				text-align:center;
				cursor:pointer;
			}

			input[type='date']::before {
				content: attr(data-placeholder);
				width: 100%;
			}
			
			input[type='date']:focus::before,
			input[type='date']:valid::before {
				display: none;
			}
			
			#reservBtn input{
				width:100px;
				height:30px;
				
				font-size:16px;
				background:#3498db;
				color:#fff;
			}
			
		</style>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<main>
			<form>
				<div id="reserv_wrap">
					<div id="reserv_inner_wrap">
						<div id="checkIn">
							<input type="date" name="checkIn" 
								data-placeholder="숙박 시작일" 
								required
			 					aria-required="true"
			 					value={startDateValue}
			  					onChange={StartDateValueHandler}/>
		  				</div>
						<div id="checkOut">
							<input type="date" name="checkOut" 
								data-placeholder="숙박 종료일" 
								required
			 					aria-required="true"
			 					value={startDateValue}
			  					onChange={StartDateValueHandler}>
						</div>
						<div id="peopleNum">
							<a>인원(성인/유아)</a>
						</div>
						<div id="roomName">
							<a>방 이름</a>
						</div>
					</div>
					<div id="reservBtn">
						<input type="button" value="검색">
					</div>
				</div>
			</form>
		</main>
	</body>
</html>