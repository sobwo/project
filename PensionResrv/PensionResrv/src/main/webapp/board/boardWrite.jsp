<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판 작성 페이지</title>
		<link href="../css/board/style_boardWrite.css" rel="stylesheet"/>
		<script type="text/javascript">
			function check(){	
				var fm = document.frm;	
				var isYN = 1;

				if (fm.subject.value == "" ){
					alert("제목을 입력하세요");
					fm.subject.focus();
					isYN = 0;
				}
				if (fm.writer.value == ""){
					alert("작성자를 입력하세요");
					fm.writer.focus();
					isYN = 0;
				}
				if (fm.contents.value == ""){
					alert("내용을 입력하세요");
					fm.contents.focus();
					isYN = 0;
				}
				if (fm.pwd.value == ""){
					alert("비밀번호를 입력하세요");
					fm.pwd.focus();
					isYN = 0;
				}
				
				if(isYN==1){
					fm.action = "${pageContext.request.contextPath}/board/boardData.do";
					fm.method="post";
					fm.submit();
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="../header.jsp"></jsp:include>
		<form name="frm">
		<div id="write_wrap">
			<h4 class="h4">문의사항 작성</h4>
				<table>
					<thead>
						<tr>
							<th><input type="text" name="subject" placeholder="제목을 입력해 주세요."></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="writer"  placeholder="작성자를 입력하세요"></td>
						</tr>
						<tr>
							<td><textarea style="vertical-align:top;" name="contents" placeholder="내용을 입력하세요."></textarea></td>
						</tr>
						<tr>
							<td><input type="password" style="height:30px;" name="pwd" placeholder="비밀번호를 입력하세요."></td>
						</tr>
					</tbody>
				</table>
				<div id="submit">
					<span><input type="file"></span>
					<span><input type="button" onclick="check()" value="등록"></span>
					<span><input type="reset" value="초기화"></span>
				</div>
			</div>
		</form>
	</body>
</html>