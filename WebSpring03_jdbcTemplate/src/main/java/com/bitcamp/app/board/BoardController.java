package com.bitcamp.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	public BoardDAO dao = new BoardDAO();
	
	@RequestMapping("/board/list")
	public ModelAndView list() { //리스트불러오는
		ModelAndView mav = new ModelAndView();
		//dao.allRecord(); //list 가 리턴됨
		mav.addObject("list",dao.allRecord());
		mav.setViewName("board/list");
		return mav;
	}
	@RequestMapping("/board/write")//글쓰기페이지로이동
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/board/writeOk",method=RequestMethod.POST) //글쓰기버튼누르면
	public ModelAndView writeOk(BoardVO vo, HttpServletRequest  req, HttpSession ses) {//req - ip,ses-작성자
		ModelAndView mav = new ModelAndView();
		
		vo.setIp(req.getRemoteAddr());//ip
		vo.setUserid((String)ses.getAttribute("userid"));//userid
		int result = dao.boardInsert(vo);
		
		mav.setViewName("redirect:list");		
		return mav;
	
	}
	
}
