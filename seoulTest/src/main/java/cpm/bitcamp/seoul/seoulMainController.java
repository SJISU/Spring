package cpm.bitcamp.seoul;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cpm.bitcamp.seoul.BoardDAO;


import cpm.bitcamp.seoul.RegisterVO;

@Controller
public class seoulMainController {
	
		RegisterDAO dao = new RegisterDAO();
	
	@RequestMapping("/main")
	public String seoulMainStart() {
		return "main/index";
	}
	
	  //�α���
	   @RequestMapping(value="/loginOk", method=RequestMethod.POST)
	   public ModelAndView loginOk(HttpServletRequest req, RegisterVO vo) {
		   dao.loginSelect(vo);
		   
		   ModelAndView mav = new ModelAndView();
		   
		   if(vo.getLogStatus().equals("Y")) {//�α��μ���
			   HttpSession session = req.getSession();
			   //�̸� �α��λ���
			   session.setAttribute("logname", vo.getUsername());
			   session.setAttribute("logid", vo.getUserid());
			   session.setAttribute("logStatus", vo.getLogStatus());
			   mav.setViewName("redirect:/");
		   }else {
			   mav.setViewName("redirect:/");
			   
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
	   BoardDAO dao2 = new BoardDAO();
		
		@RequestMapping("/list")
		public ModelAndView boardList(PagingVO pVo) {
			ModelAndView mav = new ModelAndView();
			
			//�ѷ��ڵ��
			dao2.totalRecordCount(pVo);
			mav.addObject("pVo",pVo);		
			mav.addObject("list",dao2.boardPageSelect(pVo));
			mav.setViewName("main/index");
			return mav;
			
		}
	
}
