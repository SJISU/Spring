package com.bitcamp.myapp.board;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.myapp.DBConnection;

public class BoardDAO extends DBConnection implements BoardDAOimpl {

	@Override
	public void totalRecordCount(pagingVO pVo) { //총레코드수
		
		try {
			dbConn();
			sql="select count(no) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				pVo.setTotalRecord(rs.getInt(1)); //vo안의 총레코드수 저장변수에 저장함
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}

	@Override
	public List<BoardVO> boardPageSelect(pagingVO pVo) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			dbConn();
			sql="select * from "
					+"(select * from "
					+"(select no, subject, userid, hit, to_char(writedate,'MM-DD-HH:Mi') writedate from board order by no desc) "
					+"where rownum<=? order by no asc) "
					+"where rownum<=? order by no desc";
			pstmt = con.prepareStatement(sql);
			//한페이지당레코드수*현제패이지
			pstmt.setInt(1, pVo.getOnePageRecord()*pVo.getNowPage());
			//한페이당레코드수
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

}
