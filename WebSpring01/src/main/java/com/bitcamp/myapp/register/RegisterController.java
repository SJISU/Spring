package com.bitcamp.myapp.register;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//현재 클래스가 컨트롤러가 되기 위해서는 클래스명 이전에 @Controllor어노테이션을 기술한다.
@Controller
public class RegisterController {
	RegisterDAO dao = new RegisterDAO();
	//회원가입폼 매핑
	@RequestMapping("/registerForm")
	public String registerForm() {
		//뷰파일명을 반환한다.
		return "register/form";
	}
	//아이디 중복검사
	/*@RequestMapping("/idCheck")
		public String idCheck(HttpServletRequest req) {
		String userid = req.getParameter("userid");
		System.out.println(userid);
		return "idCheck";
	}
	*/
	@RequestMapping("/idCheck")
	public ModelAndView idCheck(String userid) {
		int result = dao.idDoubleCheck(userid);
		ModelAndView mav = new ModelAndView();//뷰파일에 필요한 데이터, 뷰파일명을 저장하는곳
		mav.addObject("userid", userid);
		mav.addObject("result", result);
		mav.setViewName("register/idCheck");
		return mav;
	}
	//우편번호 검색페이지로
	@RequestMapping("/zipSearch")
	public String zipSearch() {
		return "register/zipcodeSearch";
	}
	//우편번호 검색
	@RequestMapping("/zipFind")
	@ResponseBody
	public List<ZipCodeVO> zipFine(String doro){
		//List<ZipCodeVO> list = dao.zipSearchRecord(doro);
		return dao.zipSearchRecord(doro);
		
	}
	//회원등록							post방식의 접속일때는 반드시 명시해야 한다.
	   @RequestMapping(value = "/formOk", method = RequestMethod.POST)
	   public String formOk(RegisterVO vo, Model model) {
	      // Spring에서는 맵핑되는 메소드의 매개변수로 VO를 전달하면, form의 name과 VO의 변수명이 일치하는 것을 찾아 set해준다.
	      // 즉 여기서는 RegisterVO vo를 전달하면 form에 name으로 전달된 값들이 전부 vo에 입력된다!
	      int result = dao.insertRecord(vo);
	      
	      // 데이터를 가지고 jsp 파일로 이동할 예정인데, ModelAndView를 쓸 수도 있고
	      // Model 객체를 매개변수로 받아서 모델에 따로 result를 넣어도 된다.
	      model.addAttribute("result", result);
	      return "register/formResult";
	   }
	   //로그인폼
	   @RequestMapping("/login")
	   public String loginForm() {
		   return "register/login";
	   }
	   //로그인
	   @RequestMapping(value="/loginOk", method=RequestMethod.POST)
	   public ModelAndView loginOk(HttpServletRequest req, RegisterVO vo) {
		   dao.loginSelect(vo);
		   
		   ModelAndView mav = new ModelAndView();
		   
		   if(vo.getLogStatus().equals("Y")) {//로그인성공
			   HttpSession session = req.getSession();
			   //이름 로그인상태
			   session.setAttribute("logname", vo.getUsername());
			   session.setAttribute("logid", vo.getUserid());
			   session.setAttribute("logStatus", vo.getLogStatus());
			   mav.setViewName("redirect:/");
		   }else {
			   mav.setViewName("redirect:login");
			   
		   }
		  return mav;   
	   }
	   @RequestMapping("/logout")
	   public ModelAndView logout(HttpSession session) {
		   session.invalidate();
		   ModelAndView mav = new ModelAndView();
		   mav.setViewName("redirect:/");
		   return mav;
	   }

	   @RequestMapping("/registerEdit")
	   public ModelAndView registerEdit(HttpSession session) {
		   
		 RegisterVO vo = new RegisterVO();
		 vo.setUserid((String)session.getAttribute("logid"));
		 dao.selectRecord(vo);
		 
		 ModelAndView mav = new ModelAndView();
		 mav.addObject("vo", vo);
		 mav.setViewName("register/edit");
		 return mav;
	   }
}

