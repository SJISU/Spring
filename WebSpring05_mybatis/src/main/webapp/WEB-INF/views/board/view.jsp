<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function delCheck(){
		if(confirm("글을 삭제하시겠습니까?")){
			location.href="/myhome/boardDel?no=${vo.no}";
		}
	}
</script>
</head>
<body>
<h1>글내용보기</h1>
<ul>
	<li>번호 : ${vo.no }</li>
	<li>작성자 : ${vo.userid }</li>
	<li>등록일 : ${vo.writedate }, 조회수 ;${vo.hit }</li>
	<li>제목 : ${vo.subject }</li>
	<li>글내용<br/>${vo.content}</li>
</ul>
<a href="/myhome/edit?no=${vo.no}">수정</a>
<a href="javascript:delCheck()">삭제</a>
</body>
</html>