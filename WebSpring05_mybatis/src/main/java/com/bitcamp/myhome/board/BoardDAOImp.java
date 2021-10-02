package com.bitcamp.myhome.board;

import java.util.List;

public interface BoardDAOImp {
   public List<BoardVO> boardAllSelect();
   public BoardVO boardView(int no);
   public int boardWriteOk(BoardVO vo);
}