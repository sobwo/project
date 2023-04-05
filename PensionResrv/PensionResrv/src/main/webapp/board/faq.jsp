<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>oo펜션 공지사항</title>
		<link href="../css/board/style_board.css" rel="stylesheet">
		<link href="../css/board/style_faq.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>		
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">FAQ 및 문의사항</h2>
		<div id="faq_wrap">
			<div id="faq">
				<h4 class="h4">FAQ</h4>
				<c:if test="${sessionScope.memberNo==1}">
					<div id="write" style="margin-left: 710px;">
						<input type="button" onclick="location.href='${pageContext.request.contextPath}/board/boardWrite.jsp'" value="글쓰기">
					</div>
				</c:if>
				<table id="main_board">
					<thead>
						<tr class="board_col">
							<th style="width:620px">제목</th>
							<th style="width:180px">작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="faq" items="${faq}">
						<tr class="board_col">
							<td style="overflow:hidden; text-align:left; text-indent:20px;">
								<a href="${pageContext.request.contextPath}/board/boardContents.do?bidx=${faq.bidx}">${faq.subject}</a>
							</td>		
							<td style="text-align:center; text-indent:0;">${faq.writer}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="bottom_wrap" style="height:30px;line-height:30px;text-align:center;">	
					<form name="frm">	
						<div id="paging">
							<span class = "pagingNum">
								<c:if test="${pm_faq.prev==true}">
									<a href="${pageContext.request.contextPath}/board/faq.do?page_faq=${pm_faq.startPage-1}&dataPerPage_faq=10">◁이전</a>
								</c:if>
								<c:forEach var="i" begin="${pm_faq.startPage}" end="${pm_faq.endPage}" step="1">
									<a href="${pageContext.request.contextPath}/board/faq.do?page_faq=${i}&dataPerPage_faq=10">${i}</a>
								</c:forEach>
								<c:if test="${pm_faq.next && pm_faq.endPage>0}">
									<a href="${pageContext.request.contextPath}/board/faq.do?page_faq=${pm_faq.endPage+1}&dataPerPage_faq=10">다음▷</a>
								</c:if>
							</span>
						</div>
					</form>
				</div>
			</div>
			<div id="question">
				<h4 class="h4">문의사항</h4>
				<div id="write">
					<input type="button" onclick="location.href='${pageContext.request.contextPath}/board/boardWrite.jsp?varCheck=question" value="글쓰기">
				</div>
				<table id="main_board">
					<thead>
						<tr class="board_col">
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="que" items="${question}">
						<tr class="board_col">
							<td style="text-align:center;">${que.bidx}</td>
							<td style="overflow:hidden">
								<c:forEach var="i" begin="1" end="${que.level_}" step="1">
									&nbsp;
									<c:if test="${i==que.level_}">
										&#8618;
									</c:if>
								</c:forEach>
								<a href="${pageContext.request.contextPath}/board/boardContents.do?bidx=${que.bidx}&var=1">${que.subject}</a>
							</td>		
							<td>${que.writer}</td>
							<td>${que.writeday}</td>
							<td>${que.viewCnt}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="bottom_wrap">	
					<form name="frm2">	
						<div id="paging" style="margin-left:160px;">
							<span>
								<select id="dataPerPage_que" name="dataPerPage_que" onchange="changePage()">
			        				<option value="10" id="10">10개씩보기</option>
			        				<option value="15" id="15">15개씩보기</option>
			        				<option value="20" id="20">20개씩보기</option>
								</select>
							</span>
							<span class = "pagingNum">
								<c:if test="${pm_que.prev==true}">
									<a href="${pageContext.request.contextPath}/board/faq.do?page_que=${pm_que.startPage-1}&dataPerPage_que=${dataPerPage_que}&searchOption=${pm_que.scri.searchOption}&searchContext=${pm_que.encoding(pm_que.scri.searchContext)}">◁이전</a>
								</c:if>
								<c:forEach var="i" begin="${pm_que.startPage}" end="${pm_que.endPage}" step="1">
									<a href="${pageContext.request.contextPath}/board/faq.do?page_que=${i}&dataPerPage_que=${dataPerPage_que}&searchOption=${pm_que.scri.searchOption}&searchContext=${pm_que.encoding(pm_que.scri.searchContext)}">${i}</a>
								</c:forEach>
								<c:if test="${pm_que.next && pm_que.endPage>0}">
									<a href="${pageContext.request.contextPath}/board/faq.do?page_que=${pm_que.endPage+1}&dataPerPage_que=${dataPerPage_que}&searchOption=${pm_que.scri.searchOption}&searchContext=${pm_que.encoding(pm_que.scri.searchContext)}">다음▷</a>
								</c:if>
							</span>
						</div>
					</form>
					<form name = "frm">
						<div id="search">
							<span>
								<select name="searchOption">
									<option>제목만</option>
									<option>제목과내용</option>
									<option>글작성자</option>
								</select>				
							</span>
							<span>
								<input type="text" name="searchContext" placeholder="검색어를 입력해주세요.">
							</span>
							<span>
								<input type="submit" name="submit" value="검색" onclick="search()">
							</span>	
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
		<script>		
			$(document).ready(function(){
				if(${dataPerPage_que}==10){
					$("#dataPerPage_que").val("10").prop("selected",true);
					$("#faq_wrap").css("height","1600px");
				}
				else if(${dataPerPage_que}==15){
					$("#dataPerPage_que").val("15").prop("selected",true);
					$("#faq_wrap").css("height","1800px");
				}
				else if(${dataPerPage_que}==20){
					$("#dataPerPage_que").val("20").prop("selected",true);
					$("#faq_wrap").css("height","2000px");
				}
				
			});
			function search(){
				var fm = document.frm;
				fm.action = "${pageContext.request.contextPath}/board/faq.do";
				fm.method = "post";
				fm.submit();
			}

			function changePage(){
				var dataPerPage_que = $("#dataPerPage_que").val();
				var fm2 = document.frm2;
				fm2.action = "${pageContext.request.contextPath}/board/faq.do?page_que=${pm_que.getScri().getPage()}&dataPerPage_que="+dataPerPage_que+"&searchOption=${pm_que.scri.searchOption}&searchContext=${pm_que.encoding(pm_que.scri.searchContext)}";
				fm2.method = "post";
				fm2.submit();
			} 
		</script>
	</body>
</html>