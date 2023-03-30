<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>ID찾기</title>
		<link href="../css/member/style_search.css" rel="stylesheet"/>
		<link href="../css/member/style_searchId.css" rel="stylesheet"/>
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script>
			$(document).ready(function(){
				$('#phoneInput').hide();
				$('#emailInput').hide();
				$("input:radio[name=searchMeasure]").click(function(){
					if($("input[name=searchMeasure]:checked").val() == "phone"){
						$('#emailInput').hide();
						$('#phoneInput').show();
					}
					else if($("input[name=searchMeasure]:checked").val() == "email"){
						$('#emailInput').show();
						$('#phoneInput').hide();
					}
				});
			});
			
			function search(){
				var fm = document.frm;
				fm.action ="${pageContext.request.contextPath}/search/searchAction.do?var=1";
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
					<span>아이디 찾는방법을 선택해 주세요.</span>
				</div>
				<div id="search_inner_wrap">
					<form name="frm">
						<div id="searchPhone">
							<div>
								<input type="radio" name="searchMeasure" value="phone"><h3>회원정보에 등록한 휴대전화로 찾기</h3>
							</div>
							<div>
								<span class="searchInfo">회원정보에 등록한 휴대전화 번호와 입력한 휴대전화 번호가 같아야 합니다.</span>
							</div>
							<div id="phoneInput">
								<span><label>이름</label><input type="text" name="memberName_phone"></span>
								<span><label>휴대전화 번호</label><input type="text" name="memberPhone"></span>
							</div>
						</div>
						<div id="searchEmail">
							<div>
								<input type="radio" name="searchMeasure" value="email"><h3>회원정보에 등록한 이메일 주소로 찾기</h3>
							</div>
							<div>
								<span class="searchInfo">회원정보에 등록한 이메일 주소와 입력한 이메일 주소가 같아야 합니다.</span>
							</div>
							<div id="emailInput">
								<span><label>이름</label><input type="text" name="memberName_email"></span>
								<span><label>이메일 주소</label><input type="text" name="memberEmail"></span>
							</div>
						</div>
						<div id ="searchBtn">
							<span><input type="button" value="찾기" onclick="search()"></span>
						</div>
					</form>
				</div>
			</div>
		</main>
	</body>
	
</html>