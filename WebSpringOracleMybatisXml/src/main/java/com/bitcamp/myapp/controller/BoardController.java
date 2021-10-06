package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;
@Controller
public class BoardController {
	@Inject //�������̽��� ��ü�θ���� ���� ���
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
	public ModelAndView boardWriteOk(BoardVO vo,HttpServletRequest   req ) {//������Ʈ���� ���ǿ� �ִ� ���̵��ϱ�
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("userid"));
		
		int result = boardService.boardWrite(vo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
				
		
	}
	
}
