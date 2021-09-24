package com.bitcamp.myapp.board;

import java.util.List;

public interface BoardDAOimpl {
	//총레코드수
	public void totalRecordCount(pagingVO pVo);
	//해당페이지 레코드 선택
	public List<BoardVO> boardPageSelect(pagingVO pVo);
	//글내용보기
	//public BoardVO boardSelect(int no);
	public void boardSelect(BoardVO vo);
}
