package com.bitcamp.myapp.board;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface BoardDAOimpl {
	//�ѷ��ڵ��
	public void totalRecordCount(pagingVO pVo);
	//�ش������� ���ڵ� ����
	public List<BoardVO> boardPageSelect(pagingVO pVo);
	//�۳��뺸��
	//public BoardVO boardSelect(int no);
	public void boardSelect(BoardVO vo);
	//��۾���
	public int replyInsert(ReplyBoardVO replyVo);
	//��۸��
	public List<ReplyBoardVO> replyListSelect(int no);
	//��ۼ���
	public int replyUpdate(ReplyBoardVO replyVo);
	//��ۻ���
	public int replyDelete( int num, String userid);
}
