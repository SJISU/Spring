package com.bitcamp.myhome.board;

import java.util.List;

public interface BoardDAOImp {
   public List<BoardVO> boardAllSelect();
   public BoardVO boardView(int no);
   public int boardWriteOk(BoardVO vo);
   public int boardEditOk(BoardVO vo);//�ۼ���
   public int boarDelete(int no, String userid);//�ۻ��� - �۹�ȣ,�α����ѻ츶id
}