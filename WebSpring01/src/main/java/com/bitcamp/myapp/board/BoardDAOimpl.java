package com.bitcamp.myapp.board;

import java.util.List;

public interface BoardDAOimpl {
	//�ѷ��ڵ��
	public void totalRecordCount(pagingVO pVo);
	//�ش������� ���ڵ� ����
	public List<BoardVO> boardPageSelect(pagingVO pVo);
}
