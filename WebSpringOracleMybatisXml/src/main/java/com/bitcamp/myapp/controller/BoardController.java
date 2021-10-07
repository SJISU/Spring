package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;
@Controller
public class BoardController {
	@Inject //인터페이스를 객체로만들기 위해 사용
	BoardService  boardService;
	
	@RequestMapping("/board/list")
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",boardService.boardAllSelect());
		mav.setViewName("board/list");
		return mav;
	}
	@RequestMapping("/board/write")
	public String boardWrite() {
		return "board/write";
	}
	
	@RequestMapping(value="/board/writeOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo,HttpServletRequest   req ) {//리퀘스트에서 세션에 있는 아이디구하기
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("userid"));
		
		int result = boardService.boardWrite(vo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
				
		
	}
	//글내용보기
	@RequestMapping("/board/view")
	public ModelAndView boardView(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",boardService.boardView(no));
		mav.setViewName("board/view");
		return mav;
	}
	//수정폼으로 가는
	@RequestMapping(value="/board/Edit", method=RequestMethod.GET)
	   public ModelAndView boardEdit(int no) {
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("vo", boardService.boardView(no));
	      mav.setViewName("board/edit");
	      return mav;
	   }

	
	
	
	//글수정
	@RequestMapping(value = "/board/editOk", method = RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("userid"));
		int result = boardService.boardEdit(vo);
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:view");
			mav.addObject("no",vo.getNo());
		}else{
			 mav.setViewName("board/result");

		}
		return mav;
	}
	
	//글삭제
	@RequestMapping(value="/board/del", method=RequestMethod.GET)
	public ModelAndView boardDel(int no,HttpSession session) {
		
		String userid = (String)session.getAttribute("userid");
		
		int result = boardService.boardDel(no, userid);
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:list"); //디ㅏ른컨트롤러호풀
		}else {
			mav.addObject("no",no); //no라는 변수에 매개변수no를 보맨다
			mav.setViewName("redirect:view");
		}
		return mav;
	}
	
}
