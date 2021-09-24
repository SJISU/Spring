<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>글내용보기</h1>
<ul>
	<li>번호 : ${vo.no}</li>
	<li>작성자 : ${vo.userid}</li>
	<li>작성일 : ${vo.writedate }, 조회수:${vo.hit }</li>
	<li>제목 : ${vo.subject }</li>
	<li>${vo.content }</li>
	<li>
	수정
	삭제
	<a href="/myapp/boardList?nowPage=${pVo.nowPage}">목록</a>
	</li>
</ul>
<hr/>
댓글