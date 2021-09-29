package com.bitcamp.app.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.bitcamp.app.Constants;

public class BoardDAO implements BoardDAOImpl {
   public JdbcTemplate template;
   public BoardDAO() {
      template = Constants.template;
   }
   // 전체 리스트 => 전체 레코드 선택
   @Override
   public List<BoardVO> allRecord() {
      String sql = "select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MI') as writedate from board order by no desc";
      // select 쿼리문에 물음표가 없으면 이걸 쓰면 될듯
      return template.query(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
   };
   
   // 글내용 보기, 수정 => 레코드 하나 선택
   @Override
   public BoardVO oneRecord(int no) {
      String sql = "select no, userid, subject, content, hit, writedate from board where no=?";
      return template.queryForObject(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class), no);
   };
   
   // 글 수정
   @Override
   public int boardUpdate(BoardVO vo) {
      String sql = "update board set content=?, subject=? "
      				+ " where no=?" ;
    
      return  template.update(sql, vo.getContent(), vo.getSubject(), vo.getNo());//1:수정 0:수정못함
      
   };
   
   // 글 등록
   @Override
   public int boardInsert(final BoardVO vo) {
      String sql = "insert into board(no, userid, subject, content, ip) values(boardsq.nextval, ?, ?, ?, ?)";
      
      // 추가할 때는 update!
      return template.update(sql, new PreparedStatementSetter() {
         @Override
         public void setValues(PreparedStatement ps) throws SQLException {
            // update의 두 번째 인자인 PreparedStatementSetter는 익명의 내부 클래스로 작성
            // 따라서 BoardVO 객체를 쓰기 위해서는 boardInsert로 전달되는 BoardVO의 값을 final로 바꿔 주어야 한다.
            ps.setString(1, vo.getUserid());
            ps.setString(2, vo.getSubject());
            ps.setString(3, vo.getContent());
            ps.setString(4, vo.getIp());
         }
      });
   };
   
   // 글 삭제
   @Override
   public int boardDelete(int no) {
	   String sql ="delete from board where no=?";
	   return template.update(sql,no);
     
   }
}