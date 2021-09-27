package cpm.bitcamp.seoul;

import java.util.ArrayList;
import java.util.List;



import cpm.bitcamp.seoul.BoardDAOIml;

import cpm.bitcamp.seoul.BoardVO;
import cpm.bitcamp.seoul.PagingVO;

public class BoardDAO extends DBConnection implements BoardDAOIml{
	
	@Override
	public void totalRecordCount(PagingVO pVo) { //�ѷ��ڵ��
		
		try {
			dbConn();
			sql="select count(no) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				pVo.setTotalRecord(rs.getInt(1)); //vo���� �ѷ��ڵ�� ���庯���� ������
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}
	

	@Override
	public List<BoardVO> boardPageSelect(PagingVO pVo) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			dbConn();
			sql="select * from "
					+"(select * from "
					+"(select no, subject, userid, hit, to_char(writedate,'MM-DD-HH:Mi') writedate from board order by no desc) "
					+"where rownum<=? order by no asc) "
					+"where rownum<=? order by no desc";
			pstmt = con.prepareStatement(sql);
			//���������緹�ڵ��*����������
			pstmt.setInt(1, pVo.getOnePageRecord()*pVo.getNowPage());
			//�����̴緹�ڵ��
			pstmt.setInt(2,pVo.getOnePageRecord());
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setUserid(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setWritedate(rs.getString(5));
				list.add(vo);
				
				
			}
			
		}catch(Exception e) {
			
		}finally {
			dbClose();
		}
		
		
		return list;
	}
	
	@Override
	public void boardSelect(BoardVO vo) {
		try {
			dbConn();
			sql = "select no, userid, subject, content, hit, writedate from board where no = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1,vo.getNo());
		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setWritedate(rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
	}

}
