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
	public int boardInsert(final BoardVO vo) { //�۾����ۼ���ư������߻��ϴ� //�͸��ǳ���Ŭ�����ȿ����� ����ϱ����� final �ٿ��ش�
		
		String sql="insert into board(no, userid, subject, content, ip) values(boardsq.nextval, ?, ?, ?, ?)";
		return template.update(sql, new PreparedStatementSetter() {//preparedStatementSetter->�͸� �����ϸ� �������̵��ϴ¸޼ҵ�
			
			//�͸��� ����Ŭ����
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {//����ǥ����
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
