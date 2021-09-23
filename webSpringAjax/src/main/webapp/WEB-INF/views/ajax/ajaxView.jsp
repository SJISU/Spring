<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script>
$(() => {
    // 문자열 가져오기(비동기)
    $("#str").click(function() {
       $.ajax({
          // 이 url도 매핑되므로 AjaxController에서 /ajaxStr을 찾아서 간다. 
          url: "/home/ajaxStr",
          data: "num=34&name=박서준",
          success: function(result) {
             $("#view").append(result);
          },
       });
    });
 });
</script>
</head>
<body>
<input type="button" id="str" value="ajax(문자열)"/>
<hr/>
<div id="view"></div>
</body>
</html>