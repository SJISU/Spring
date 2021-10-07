package com.bitcamp.myapp.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitcamp.myapp.service.BoardService;

@Controller
public class BoardController {
	@Inject
	BoardService boardService;
	//게시판 목록
	@RequestMapping("/boardList")
	public String boardList(Model model) {
		model.addAttribute("list", boardService.boardList());
		return "board/list";
	}
	//글내용보기
	@RequestMapping("/boardView")
	public String boardView(Model model, int no) {
		model.addAttribute("vo",boardService.boardView(no));
		return "board/boardView";
	}
}
