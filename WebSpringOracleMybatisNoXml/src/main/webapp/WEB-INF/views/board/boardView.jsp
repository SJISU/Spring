<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글내용보기</h1>
번호 : ${vo.no }<br/>
작성자 : ${vo.userid}<br/>
등록일 : ${vo.writedate }, 조회수 : ${vo.hit }<br/>
제목 : ${vo.subject }<br/>
글내용 :${vo.content }<br/>
<hr/>
수정
삭제

</body>
</html>