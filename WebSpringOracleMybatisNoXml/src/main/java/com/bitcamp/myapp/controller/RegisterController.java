package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.RegisterService;
import com.bitcamp.myapp.vo.RegisterVO;

@Controller
public class RegisterController {
	//서비스를 객체로 만든다
	@Inject
	RegisterService registerService;
	//로그인폼
	@RequestMapping("/login")
	public String login() {
		return "register/login";
	}
	//로그인
	@RequestMapping(value="/loginOk",method=RequestMethod.POST)
	public ModelAndView loginOk(RegisterVO vo, HttpSession session) { //리퀘스한 비밀번호아이디가 vo에 들어간다
		RegisterVO resultVO =registerService.login(vo); //로그인한 아이디와 사용자이름담아옴
		ModelAndView mav = new ModelAndView();	
		if(resultVO==null) {//로그인실패
			mav.setViewName("redirect:login");
		}else {//로그인성공
			session.setAttribute("logid",resultVO.getUserid());
			session.setAttribute("logname", resultVO.getUsername());
			mav.setViewName("redirect:/");
		}				
		return mav;
	}
	
	
	
	//로그아웃
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession s) {
		s.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	
}
