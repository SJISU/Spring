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
	@Override
	   public List<BoardVO> allRecord() {
	      String sql = "select no, subject, userid, hit, to_char(writedate,'MM-DD HH:MI') writedate "
	            + " from board order by no desc";
	      return template.query(sql, new BeanPropertyRowMapper<BoardVO>(BoardVO.class));
	   }
	@Override
	public BoardVO oneRecord(int no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int boardInsert(final BoardVO vo) { //글쓰기작성버튼누르면발생하는 //익명의내부클래스안에서도 사용하기위해 final 붙여준다
		
		String sql="insert into board(no, userid, subject, content, ip) values(boardsq.nextval, ?, ?, ?, ?)";
		return template.update(sql, new PreparedStatementSetter() {//preparedStatementSetter->익명 선택하면 오버라이딩하는메소드
			
			//익명의 내부클래스
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {//물음표셋팅
				ps.setString(1, vo.getUserid());
				ps.setString(2,vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4,vo.getIp());
				
			}
		} );
		
	}
	@Override
	public int boardDelete(int no) {
		// TODO Auto-generated method stub
		return 0;
	}
}
