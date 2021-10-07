<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>홈페이지</h1>
<a href="/myapp/login">로그인</a> ,
${logname} 
<a href="/myapp/logout">로그아웃</a><br/>
<a href="/myapp/boardList">게시판</a>
</body>
</html>