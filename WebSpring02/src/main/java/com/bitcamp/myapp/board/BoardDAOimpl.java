package com.bitcamp.myapp.board;

import java.util.List;

public interface BoardDAOimpl {
	//�ѷ��ڵ��
	public void totalRecordCount(pagingVO pVo);
	//�ش������� ���ڵ� ����
	public List<BoardVO> boardPageSelect(pagingVO pVo);
	//�۳��뺸��
	//public BoardVO boardSelect(int no);
	public void boardSelect(BoardVO vo);
}
