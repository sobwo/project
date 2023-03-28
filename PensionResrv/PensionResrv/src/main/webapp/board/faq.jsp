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
		<script>
			$(document).ready(function(){
				if(${dataPerPage}==10)
					$("#dataPerPage").val("10").prop("selected",true);
				else if(${dataPerPage}==15)
					$("#dataPerPage").val("15").prop("selected",true);
				else if(${dataPerPage}==20)
					$("#dataPerPage").val("20").prop("selected",true);
			});
			function search(){
				var fm = document.frm;
				fm.action = "${pageContext.request.contextPath}/board/boardList.do";
				fm.method = "post";
				fm.submit();
			}
			
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp"/>
		<h2 id="h2">FAQ 및 문의사항</h2>
		<div id="faq_wrap">
			<div id="faq">
				<h4 class="h4">FAQ</h4>
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
						<c:forEach var="bv" items="${boardList}">
						<tr class="board_col">
							<td style="text-align:center;">${bv.bidx}</td>
							<td style="overflow:hidden">
								<c:forEach var="i" begin="1" end="${bv.level_}" step="1">
									out.println("&nbsp;");
									<c:if test="${i==bv.level_}">
										out.println("&#8618;");
									</c:if>
								</c:forEach>
								<a href="${pageContext.request.contextPath}/board/boardContents.do?bidx=${bv.bidx}">${bv.subject}</a>
							</td>		
							<td>${bv.writer}</td>
							<td>${bv.writeday}</td>
							<td>${bv.viewCnt}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="bottom_wrap">	
					<form name="frm2">	
						<div id="paging">
							<span>
								<select id="dataPerPage" name="dataPerPage" onchange="changePage()">
			        				<option value="10" id="10">10개씩보기</option>
			        				<option value="15" id="15">15개씩보기</option>
			        				<option value="20" id="20">20개씩보기</option>
								</select>
							</span>
							<span class = "pagingNum">
								<c:if test="${pm.prev==true}">
									<a href="${pageContext.request.contextPath}/board/boardList.do?page=${pm.startPage-1}&dataPerPage=${dataPerPage}&searchOption=${pm.scri.searchOption}&searchContext=${pm.encoding(pm.scri.searchContext)}">◁이전</a>
								</c:if>
								<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}" step="1">
									<a href="${pageContext.request.contextPath}/board/boardList.do?page=${i}&dataPerPage=${dataPerPage}&searchOption=${pm.scri.searchOption}&searchContext=${pm.encoding(pm.scri.searchContext)}">${i}</a>
								</c:forEach>
								<c:if test="${pm.next && pm.endPage>0}">
									<a href="${pageContext.request.contextPath}/board/boardList.do?page=${pm.endPage+1}&dataPerPage=${dataPerPage}&searchOption=${pm.scri.searchOption}&searchContext=${pm.encoding(pm.scri.searchContext)}">다음▷</a>
								</c:if>
							</span>
						</div>
					</form>
				</div>
			</div>
			<div id="question">
				<h4 class="h4">문의사항</h4>
				<div id="write">
					<input type="button" onclick="location.href='${pageContext.request.contextPath}/board/boardWrite.jsp'" value="글쓰기">
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
						<c:forEach var="bv" items="${boardList}">
						<tr class="board_col">
							<td style="text-align:center;">${bv.bidx}</td>
							<td style="overflow:hidden">
								<c:forEach var="i" begin="1" end="${bv.level_}" step="1">
									out.println("&nbsp;");
									<c:if test="${i==bv.level_}">
										out.println("&#8618;");
									</c:if>
								</c:forEach>
								<a href="${pageContext.request.contextPath}/board/boardContents.do?bidx=${bv.bidx}">${bv.subject}</a>
							</td>		
							<td>${bv.writer}</td>
							<td>${bv.writeday}</td>
							<td>${bv.viewCnt}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="bottom_wrap">	
					<form name="frm2">	
						<div id="paging">
							<span>
								<select id="dataPerPage" name="dataPerPage" onchange="changePage()">
			        				<option value="10" id="10">10개씩보기</option>
			        				<option value="15" id="15">15개씩보기</option>
			        				<option value="20" id="20">20개씩보기</option>
								</select>
							</span>
							<span class = "pagingNum">
								<c:if test="${pm.prev==true}">
									<a href="${pageContext.request.contextPath}/board/boardList.do?page=${pm.startPage-1}&dataPerPage=${dataPerPage}&searchOption=${pm.scri.searchOption}&searchContext=${pm.encoding(pm.scri.searchContext)}">◁이전</a>
								</c:if>
								<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}" step="1">
									<a href="${pageContext.request.contextPath}/board/boardList.do?page=${i}&dataPerPage=${dataPerPage}&searchOption=${pm.scri.searchOption}&searchContext=${pm.encoding(pm.scri.searchContext)}">${i}</a>
								</c:forEach>
								<c:if test="${pm.next && pm.endPage>0}">
									<a href="${pageContext.request.contextPath}/board/boardList.do?page=${pm.endPage+1}&dataPerPage=${dataPerPage}&searchOption=${pm.scri.searchOption}&searchContext=${pm.encoding(pm.scri.searchContext)}">다음▷</a>
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
	</body>
</html>