<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>업로드 결과</h1>
   작성자: ${ vo.userid }<br />
   제목: ${ vo.subject }<br />
   첨부파일 리스트<br />
   <c:forEach var="f" items="${ vo.fileList }" >
      ${ f },
   </c:forEach>
</body>
</html>