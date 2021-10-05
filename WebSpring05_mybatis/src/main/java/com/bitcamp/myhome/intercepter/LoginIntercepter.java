package com.bitcamp.myhome.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter extends HandlerInterceptorAdapter {
	//서버에 접속하여 컨트롤러 호출 -> url주소,context,서버 -----> 제일먼저 
	//컨트롤러가 호출되기전에 호출되는 메소드
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		//request에서 세션구해온다
		//로그인 여부를 확인하여 로그인이 안된경우 로그인폼으로 이동(홈)
		//로그인이 된경우는 접속한 컨트롤러로 구현
		
		//로그인된 아이디구하기
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		
		//로그인이 안됭경우
		if(userid==null || userid.equals("")) {
			//                                       /myhome/
			response.sendRedirect(request.getContextPath()+"/"); //홈으로 보낸다
			return false; //뒤에가 실행한되게 false를 써준다.
		}
		//로그인이 된경우 -> 가던길 간다 : 접속한 컨트롤러 매핑주소로 이동
		//request에 있는 주소로 간다		
		return true;
	}
	
	//컨트롤러가 실행된 후 view페이지로 이동하기전에 호출된다.
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			@Nullable ModelAndView ModelAndView)throws Exception{
		
	}
	//컨트롤러가 실행후 호출된다.
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler,
			@Nullable Exception ex)throws Exception{
		
	}
}
