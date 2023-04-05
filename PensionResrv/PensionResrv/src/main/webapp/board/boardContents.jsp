<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// 	if (session.getAttribute("memberNo") == null){	
// 		out.println("<script>alert('로그인이 필요합니다.'); history.back(-1);</script>");
// 	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 내용 페이지</title>
		<link href="../css/board/style_boardContents.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp"></jsp:include>
			<div id="contents_wrap">
				<c:choose>
					<c:when test="${sessionScope.path=='boardNoti'}"><h4 class="h4">공지사항</h4></c:when> 
					<c:when test="${sessionScope.path=='faq' && var!=1}"><h4 class="h4">FAQ</h4></c:when>
					<c:when test="${sessionScope.path=='faq' && var==1}"><h4 class="h4">문의사항</h4></c:when>
				</c:choose>
				<table id="contents_board">
				<tbody>
					<tr>
						<td colspan="2" style="border-top:0; height:80px;">
							<span>${bv.subject}</span><br/><br/>
							<span>${bv.writer}</span>
							<span>${bv.viewCnt}</span>
							<span>${bv.writeday}</span>	
						</td>
					</tr>
					<tr>
						<td style="height:500px; text-indent:10px; padding-top:10px;" colspan="2">${bv.contents}</td>
					</tr>
					<tr>
						<td>
							<form>
								<div id="btn">
									<c:if test="${(var==1 && sessionScope.memberNo != null) || sessionScope.memberNo == 1}">
										<input type=button onclick="location.href='${pageContext.request.contextPath}/board/boardModify.do?bidx=${bv.bidx}'" value="수정">
										<input type=button onclick="location.href='${pageContext.request.contextPath}/board/boardDelete.do?bidx=${bv.bidx}'" value="삭제">
									</c:if>
									<c:if test="${sessionScope.memberNo == 1}">
										<input type=button onclick="location.href='${pageContext.request.contextPath}/board/boardReply.do?bidx=${bv.bidx}&originbidx=${bv.originbidx}&depth=${bv.depth}&level_=${bv.level_}&subject=${bv.subject}'" value="답변">
									</c:if>
									<input type=button onclick="location.href='${pageContext.request.contextPath}/board/boardList.do'" value="목록">
								</div>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

	</body>
</html>