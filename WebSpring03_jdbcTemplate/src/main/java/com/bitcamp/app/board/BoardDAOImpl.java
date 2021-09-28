package com.bitcamp.app.board;

import java.util.List;

public interface BoardDAOImpl {
	//리스트 -> 레코드 전체 선택
	public List<BoardVO> allRecord();
	//글내용보기 -> 레코드1개 선택
	public BoardVO oneRecord(int no);
	//글수정하기
	public int boardUpdate(BoardVO vo);
	//글등록하기
	public int boardInsert(BoardVO vo);
	//글삭제하기
	public int boardDelete(int no);
	
}
