package cpm.bitcamp.seoul;
import java.util.List;

import cpm.bitcamp.seoul.BoardVO;
import cpm.bitcamp.seoul.PagingVO;

public interface BoardDAOIml {
	

		//�ѷ��ڵ��
		public void totalRecordCount(PagingVO pVo);
	
		
		//�ش������� ���ڵ� ����
		public List<BoardVO> boardPageSelect(PagingVO pVo);
		
		//�۳��뺸��
		//public BoardVO boardSelect(int no);
		public void boardSelect(BoardVO vo);
	}

