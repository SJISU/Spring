package com.bitcamp.myapp.board;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface BoardDAOimpl {
	//총레코드수
	public void totalRecordCount(pagingVO pVo);
	//해당페이지 레코드 선택
	public List<BoardVO> boardPageSelect(pagingVO pVo);
	//글내용보기
	//public BoardVO boardSelect(int no);
	public void boardSelect(BoardVO vo);
	//댓글쓰기
	public int replyInsert(ReplyBoardVO replyVo);
	//댓글목록
	public List<ReplyBoardVO> replyListSelect(int no);
	//댓글수정
	public int replyUpdate(ReplyBoardVO replyVo);
	//댓글삭제
	public int replyDelete( int num, String userid);
}
