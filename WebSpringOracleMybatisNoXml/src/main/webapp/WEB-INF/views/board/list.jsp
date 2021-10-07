<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ul,li{padding:0;margin:0;list-style-type:none;}
	.wordCut{
		white-space:nowrap;text-overflow:ellipsisl;overflow:hidden;
	}
	li{
		width:10%;
		border-bottom:1px solid gray;
		height:40px; line-height:40px;	
		float:left;	
	}
	li:nth-child(5n+2){width:60%;}
</style>
</head>
<body>
<h1>게시판목록</h1>
<ul>
	<li>번호</li>
	<li>제목</li>
	<li>작성자</li>
	<li>조회수</li>
	<li>등록일</li>
	<c:forEach var="vo" items="${list}">
		<li>${vo.no }</li>
		<li class="wordCut"><a href="/myapp/boardView?no=${vo.no}">${vo.subject }</a></li>
		<li>${vo.userid }</li>
		<li>${vo.hit }</li>
		<li>${vo.writedate }</li>
	</c:forEach>
</ul>
</body>
</html>