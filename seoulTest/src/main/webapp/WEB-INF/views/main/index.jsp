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
	#top{text-align :center; height:24px;line-height:24px;width:100%;}	
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
	h1{text-align:center;color:darkgray;}
	#board{width:800px;height:auto;}
	h1{width:800px;text-align:center;color:darkgray;}
	
	#chkdiv{text-align:left;}
	
	
	#board>li{float:left; width:10%; border-bottom:1px solid #ddd; height:40px;}
    #board>li:nth-child(6n+3){width:50%;}
    
     .wordCut{
      white-space:nowrap;
      overflow:hidden;
      text-overflow:ellipsis;
   }
    #pagingdiv{width:800px;text-align:center;}
    
    
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
	   $('#allChk').on('change',function(){
	       $('#board input[type=checkbox]').prop('checked', $('#allChk').prop('checked'));
	       
	    });
   });

</script>

</head>
<body>
<div id="container">
	
	<div id="top">
	   <form method="post" action="<%=request.getContextPath()%>/loginOk" id="logFrm">
	   		 <c:if test="${ logStatus != 'Y' }">	   		 	
		         <input type="text" name="userid" id="userid" placeholder="아이디"/>
		         <input type="password" name="userpwd" id="userpwd" placeholder="비밀번호"/>
		         <input type="submit" value="로그인"/>	
		      </c:if>
		      <c:if test="${ logStatus == 'Y' }">
     			 <li>${ logname }님 <a href="/seoul/logout">로그아웃</a></li>
      		  </c:if>
		         <a href="#" style="color:#ddd">회원가입</a>
		         <a href="#" style="color:#ddd">고객센터</a>         
	   </form>
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
	<
	
		<ul id="bx">
			<li><img src="img/banner1.jpg" title="Seoul Music FestivalSeoul"></li>
			<li><img src="img/banner2.jpg" title="SIBAC 2019"></li>
			<li><img src="img/banner3.jpg" title="2019 서울전환도시 국제컨퍼런스"></li>
			<li><img src="img/banner4.jpg" title="2019 다다다 발표대회"></li>
			<li><img src="img/banner5.jpg" title="2019 서울인공지능챗본론"></li>
			<li><img src="img/banner6.jpg" title="서울차 없는 날"></li>
			<li><img src="img/banner7.jpg" title="Zero 제로페이 미국 캐나다 이벤트"></li>
		</ul>


	<!-- 게시판 -->
	
		<h1>자유게시판</h1><br/>
		<div  id="chkdiv"><input type="checkbox"  id="allChk"/>전체선택</div>
			<ul id="board">
				<li><input type="checkbox" /></li>
				<li>번호</li>
			    <li>제목</li>
			    <li>작성자</li>
			    <li>작성일</li>
			    <li>조회수</li>		

	
			 	<c:forEach var="vo" items="${list}">
					<li>${vo.no}</li>
					<li class="wordCut"><a href="/seoul/boardView?no=${vo.no}&nowPage=${pVo.nowPage}">${vo.subject}</a></li>
					<li>${vo.userid}</li>
					<li>${vo.writedate}</li>
					<li>${vo.hit}</li>
				</c:forEach>      
			</ul>
<!-- 페이징 -->
	<div>
	<ul class="pagination pagination-lg">
	      <!-- 이전페이지는 현재페이지가 1페이지보다 클때만 표시한다 -->
	      <c:if test="${pVo.nowPage>1 }">
	            <li class='page-item'><a href="/seoul/list?nowPage=${pVo.nowPage-1 }<c:if test='${pVo.searchWord!=null && pVo.searchKey!=null }'>&searchWord=${pVo.searchKey}&searchWord=${pVo.searchWord}</c:if>" class='page-link'>Prev</a>
	         </c:if>
	         <c:if test="${pVo.nowPage==1}">
	            <li class='page-item'><a href="#" class='page-link'>Prev</a></li>
	         </c:if>
	         <!-- 시작페이지부터 5개의 페이지를 출력한다. -->
	         <!-- 6,7,8,9,10 -->
	         <c:forEach var="i" begin="${pVo.startPage }" end="${pVo.startPage+pVo.onePageNumCount-1 }">
	            <!-- 출력할 페이지번호 총페이지수보다 작을때만 출력한다. -->
	            <c:if test="${i<=pVo.totalPage}">
	               <c:if test="${i==pVo.nowPage }">
	                  <li class='page-item active'>
	               </c:if>
	               <c:if test="${i!=pVo.nowPage }">
	                  <li class='page-item'>
	               </c:if>
	               <a href="/seoul/list?nowPage=${i}<c:if test='${pVo.searchWord!=null && pVo.searchKey!=null }'>&searchWord=${pVo.searchKey}&searchWord=${pVo.searchWord}</c:if>" class='page-link'>${i}</a>
	            </c:if>
	         </c:forEach>
	         
	         <!-- 다음페이지는 현재페이지가 총페이지수보다 작으면 다음페이지가 있다. -->
	         <c:if test="${pVo.nowPage<pVo.totalPage}">
	            <li class='page-item'><a href="/seoul/list?nowPage=${pVo.nowPage+1 }<c:if test='${pVo.searchWord!=null && pVo.searchKey!=null }'>&searchWord=${pVo.searchKey}&searchWord=${pVo.searchWord}</c:if>" class='page-link'>Next</a></li>
	         </c:if>   
	   </ul>
	</div>
</div>
</body>
</html>