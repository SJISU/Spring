package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.myapp.service.MemberService;
import com.bitcamp.myapp.vo.MemberVO;

@Controller
public class MemberController {
   @Inject
   MemberService memberService;
   
   @RequestMapping("/login")
   public String loginForm() {
      return "register/loginForm";
   }
   @RequestMapping(value="/loginOk", method=RequestMethod.POST)
   public String loginOk(MemberVO vo, HttpSession ses) {
      //肺弊牢
      MemberVO resultVo = memberService.loginSelect(vo);
      //技记贸府
      if(resultVo!=null) {
         ses.setAttribute("userid", resultVo.getUserid());
         ses.setAttribute("username", resultVo.getUsername());
      }
      return "home";
   }
   @RequestMapping("/logout")
   public String logout(HttpSession s) {
      s.invalidate();
      return "home";
   }
}