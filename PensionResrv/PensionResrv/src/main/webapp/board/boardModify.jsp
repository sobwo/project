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
		<title>게시판 수정 페이지</title>
		<link href="../css/board/style_boardWrite.css" rel="stylesheet"/>
	</head>
	<body>
		<jsp:include page="../header.jsp"></jsp:include>
		<form name="frm">
			<div id="write_wrap">
				<table id="contents_board">
					<thead>
						<tr>
							<th style="display:none;"><input type="text" name="bidx" value="${bv.bidx}"></th>
							<th><input type="text" name="subject" value="${bv.subject}"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="writer" value="${bv.writer}" readonly></td>
						</tr>
						<tr>
							<td><input type="text" id="contents" name="contents" value="${bv.contents}"></td>
						</tr>
					</tbody>
				</table>
				<div id="submit">
					<span><input type="button" onclick="check()" value="등록"></span>
					<span><input type="reset" value="초기화"></span>
				</div>
			</div>
		</form>
		<script type="text/javascript">
			function check(){	
				var fm = document.frm;	

				if (fm.subject.value == "" ){
					alert("제목을 입력하세요");
					fm.subject.focus();

				}
				else if (fm.contents.value == ""){
					alert("내용을 입력하세요");
					fm.contents.focus();
				}
				
				else{
					fm.action = "${pageContext.request.contextPath}/board/boardModifyAction.do";
					fm.method="post";
					fm.submit();
				}
			}
		</script>
	</body>
</html>