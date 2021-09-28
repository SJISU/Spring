<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 부트스트랩 -->
<meta name= "viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<!-- bx slider -->
<script src="api/jquery-3.4.1.js"></script>
<script src="api/jquery.bxslider.js"></script>
<link rel="stylesheet" href="api/jquery.bxslider.css" type="text/css"/>
<!-- 부트스트랩 -->
<script src="api/bootstrap.min.js"></script>   

<title>SEOUL</title>

<style>
	
	#container{width:800px;margin:0 auto;position:relative;height:auto;}
	ul,li{margin:0; padding:0; list-style-type:none;}
	div,body{margin:0; padding:0;}
	a:link {text-decoration:none;}
	a{color:#fff;}
	#top{display:flex;text-align :center; height:24px;line-height:24px;width:100%;}	
	#logo{height:100px; text-align :center; line-height:100px;}	
	/*메인메뉴*/
	#menu{float:left; text-align:center; width:800px; background-color:white;  color:#fff;}
	#menulist{width:800px;}
	#menulist>li{float:left; height:50px; line-height:50px; text-align:center; width:20%;
	     background-color:#456;}
	/*서브메뉴*/
	#menulist>li>ul{
	   position:relative; z-index:100; background-color:#456;display:none;}
	
	/*bx슬라이더*/
	.imgslide{text-align:center;}
	#bx img{width:800px;height:350px;}
	/*게시판*/
	.boardtitle>li{float:left;width:9%;}	
	.boardtitle>li:nth-child(6n-3){width:50%; white-space:nowrap;overflow:hidden;text-overflow: ellipsis;}	
	.boardtitle>li:nth-child(6n-1){width:12%;}
	.paging{clear: both;}	
	.paging{display:flex;margin: 0 auto;}	
	.board>h1{text-align: center;}	
	.pagination-lg{width: 34%;margin: 0 auto;padding:10px;}
	.pagination-lg .page-link{padding: 8px; }	
	.boardtitle>li{ border-bottom: 1px solid black;}
	.wordCut>a{color: black;}
	
	/*footer*/
	footer{margin:0 auto;height:50px;line-height:50px;width:800px;background-color: #456;color:#FFF;text-align: center;}

</style>
<script>
	$(()=>{
		$("#logFrm").submit(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력하세요");
				return false;
			}
			if($("#userpwd").val()==""){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			return true;
		});
	});  
   
   
   
   $(function(){
      //마우스 오버, 마우스 아웃
      $("#menulist>li").on({mouseenter:function(){
         $(this).children('ul').css('display','block');
         $(this).children('a').css('color','#F00');
      },mouseleave:function(){
         $(this).children('ul').css('display','none'); 
         $(this).children('a').css('color','#fff');
      }});
   });
  
   
   
   $(function(){
		$('#bx').bxSlider({
			mode:'horizontal',
			slideWidth:800, 
			slideHeight:350,
			speed:3000, 
			auto:true,
			captions:true, 
			infiniteLoop:false, 
			hideControlOnEnd:true, 
			
		});		
	});

   $(()=>{
	   //게시물 불러오기 
	   
	   function boardList(){
		   
		   let url="/seoul/boardList";
		   $.ajax({
	            url:url,
	            success:function(result){
	               let $r=$(result);
	               
	               $r.each(function(idx, vo){
	                  let tag="";
	                  tag+="<li><input type='checkbox' id='' name='checked'></li>"
	                  tag+="<li>"+vo.no+"</li> <li class='wordCut'><a href='list.jsp?num=1'>"+vo.subject+"</a></li>"; 
	                  tag+="<li>"+vo.userid+"</li>";
	                  tag+="<li>"+vo.writedate+"</li>";
	                  tag+="<li>"+vo.hit+"</li>";
	                  $(".boardtitle").append(tag);
	               });
	            }
	         });
	         return false;
	   }
   
   
   

		$('#allchk').on('change',function(){//체인지 이벤트가 발생하면
			$('.boardtitle input[type=checkbox]').prop('checked',$('#allchk').prop('checked'));
	
		}); 
		
		boardList();
   });

</script>

</head>
<body>
<div id="container">
	
	<div id="top" style="justify-content: end">
	   <form method="post" action="<%=request.getContextPath()%>/login" id="logFrm">
	   		 <c:if test="${ logStatus == 'Y' }">
     			 <li>${ logname }님 <a href="/seoul/logout" style="color:#ddd">로그아웃</a></li>
      		  </c:if>
	   		 <c:if test="${ logStatus != 'Y' }">	   		 	
		         <input type="text" name="userid" id="userid" placeholder="아이디"/>
		         <input type="password" name="userpwd" id="userpwd" placeholder="비밀번호"/>
		         <input type="submit" value="로그인"/>	
		      </c:if>
	</form>  
		         <a href="#" style="color:#ddd">회원가입</a>
		         <a href="#" style="color:#ddd">고객센터</a>         
	 
	</div>
	
	<div id="logo">
	   <a href="https://www.seoul.go.kr/seoulMain/index.jsp"><img src="img/seoul.png"/></a>
	</div>
	
	<div id="menu" style="color:white">
	   <ul id="menulist" >
	      <li><a href="#">나의 서울</a></li>
	      <li><a href="#">전자우편</a></li>
	      <li><a href="#">서울소개</a>
	         <ul>
	            <li><a href="#">시청안내</a></li>
	            <li><a href="#">서울의상징</a></li>
	            <li><a href="#">서울의역사</a></li>
	            <li><a href="#">서울정보</a></li>
	         </ul>
	      </li>
	      <li><a href="#">시민참여</a>
	         <ul>
	            <li><a href="#">서울시민과의대화</a></li>
	            <li><a href="#">시민의견</a></li>
	            <li><a href="#">공모전</a></li>
	         </ul>
	      </li>
	      <li><a href="#">청사안내</a>
	         <ul>
	            <li><a href="#">조직도</a></li>
	            <li><a href="#">시의회</a></li>
	            <li><a href="#">자치구</a></li>
	         </ul>
	      </li>
	   </ul>
	</div>
	
	<!-- bx슬라이더 -->		
	
	<div style="visibility:hidden"><</div>
		<ul id="bx">
			<li><img src="img/banner1.jpg" title="Seoul Music FestivalSeoul"></li>
			<li><img src="img/banner2.jpg" title="SIBAC 2019"></li>
			<li><img src="img/banner3.jpg" title="2019 서울전환도시 국제컨퍼런스"></li>
			<li><img src="img/banner4.jpg" title="2019 다다다 발표대회"></li>
			<li><img src="img/banner5.jpg" title="2019 서울인공지능챗본론"></li>
			<li><img src="img/banner6.jpg" title="서울차 없는 날"></li>
			<li><img src="img/banner7.jpg" title="Zero 제로페이 미국 캐나다 이벤트"></li>
		</ul>


	<div class="board">
			<h1>자유게시판</h1>
			<input type="checkbox" id="allchk"><label>전체선택</label>
			<ul class="boardtitle">
            <li style="height:25px"> </li>
            
            <li>번호</li>
            <li>제목</li>
            <li>작성자</li>
            <li>작성일</li>
            <li>조회수</li>
                    
         </ul>
		<div>
		 <ul class="pagination pagination-lg">
		 		<li class='page-item'><a href="" class='page-link'>Prev</a></li>
		 		<li class='page-item'><a href="" class='page-link'>1</a></li>
		 		<li class='page-item'><a href="" class='page-link'>2</a></li>
		 		<li class='page-item'><a href="" class='page-link'>3</a></li>
		 		<li class='page-item'><a href="" class='page-link'>4</a></li>
		 		<li class='page-item'><a href="" class='page-link'>5</a></li>
		 		<li class='page-item'><a href="" class='page-link'>NEXT</a></li>
		 
		 </ul>
		
		</div><!-- boarddiv -->
		
</div>
