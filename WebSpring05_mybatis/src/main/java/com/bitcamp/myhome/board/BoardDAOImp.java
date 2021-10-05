package com.bitcamp.myhome.board;

import java.util.List;

public interface BoardDAOImp {
   public List<BoardVO> boardAllSelect();
   public BoardVO boardView(int no);
   public int boardWriteOk(BoardVO vo);
   public int boardEditOk(BoardVO vo);//글수정
   public int boarDelete(int no, String userid);//글삭제 - 글번호,로그인한살마id
}