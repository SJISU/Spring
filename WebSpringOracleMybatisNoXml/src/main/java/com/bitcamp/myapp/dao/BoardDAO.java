package com.bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bitcamp.myapp.vo.BoardVO;


public interface BoardDAO {
	//게시판목록
	@Select("select no, subject, userid, hit, "
			+" to_char(writedate,'MM-DD HH:MI') writedate from board "
			+" order by no desc")
	public List<BoardVO> boardList();
	
	//글내용보기
	@Select("select no, subject ,userid, hit, content, writedate "
			+ " from board where no=#{no}") //#no -> 매개변수
	public BoardVO boardView(int no);
}
