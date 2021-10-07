package com.bitcamp.myapp.service;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardList();
	public BoardVO boardView(int no);
}
