package cpm.bitcamp.seoul;
import java.util.List;

import cpm.bitcamp.seoul.BoardVO;
import cpm.bitcamp.seoul.PagingVO;

public interface BoardDAOIml {
	

		//총레코드수
		public void totalRecordCount(PagingVO pVo);
	
		
		//해당페이지 레코드 선택
		public List<BoardVO> boardPageSelect(PagingVO pVo);
		
		//글내용보기
		//public BoardVO boardSelect(int no);
		public void boardSelect(BoardVO vo);
	}

