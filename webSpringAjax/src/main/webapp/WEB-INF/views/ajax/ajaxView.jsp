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
    //서버에서 Listㅋ털렉션 객체 비동기식으로 가져오기
    $('#lst').on('click',function(){
       var url="/home/ajaxList"
       var params = "num=100&nick=고구마";
       $.ajax({
          url : url,
          data : params,
          success:function(r){
             var rr = $(r); //r에 list가 담겨져있음
             
             rr.each(function(idx, vo){
                $("#view").append("<hr/>"+idx+"-->"+vo.num+":"+vo.userid+":"+vo.nick);
             });
          },error:function(e){//콜백함수 에러발생시 실행됨
             console.log("List전송 에러발생함",e.responseText);   
          }
       });
    });
    //서버에서 비동기식으로 Object가져오기
         // 객체 가져오기
      $(document).on('click', '#obj', function() {
         $.ajax({
            url: "/home/ajaxObject",
            success: function(result) {
               $("#view").append("<hr />" + result.num + result.userid + result.nick);
            }
         });
      });
    //서버에서 비동기식으로 Map객체 가져오기
    $(document).on('click','#map',function(){
    	$.ajax({
    		url:"/home/ajaxMap" ,
    		success:function(result){
    			//						key vo.num
    			var tag = "번호="+result.k1.num+", 아이디="+result.k1.userid+", 닉네임="+result.k1.nick+"<br/>";
    			tag += "번호="+result.k2.num+", 아이디="+result.k2.userid+", 닉네임="+result.k2.nick+"<br/>";
    			tag += "번호="+result.k3.num+", 아이디="+result.k3.userid+", 닉네임="+result.k3.nick+"<br/>";  
    			 
    			$("#view").append(tag);
    		}
    	});
    });
    //서버에서 비동기식으로 json정보를 문자여로 가져와 json객체로 피시아여 정보를 사용하기
    $('#json').click(function(){
    	$.ajax({
    		url:"/home/ajaxJson",
    		success:function(result){ //result에 스트링json담겨져있음
    			//문자열을 전송받아 json형식으로 파싱한다.
    			var jsonParsing =JSON.parse(result); //json형식의문자열을 매개변수로 넣어주며된다
    			console.log(jsonParsing);
    			$('#view').append("no ="+jsonParsing.no+",username ="+jsonParsing.username);
    			$('#view').append(", tel="+jsonParsing.tel+",addr ="+jsonParsing.addr+"<br/>");
    		}
    	});
    });
    
 });
</script>
</head>
<body>
<input type="button" id="str" value="ajax(문자열)"/>
<input type="button" id="lst" value="ajax(list)"/>
<input type="button" id="obj" value="ajax(object)"/>
<input type="button" id="map" value="ajax(Map)"/>
<input type="button" id="json" value="ajax(json)"/>
<hr/>
<div id="view"></div>
</body>
</html>