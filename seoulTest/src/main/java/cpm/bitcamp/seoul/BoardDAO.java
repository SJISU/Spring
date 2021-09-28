package cpm.bitcamp.seoul;

import java.util.ArrayList;
import java.util.List;

import cpm.bitcamp.seoul.DBConnection;

public class BoardDAO extends DBConnection {
	
public List<BoardVO> boardSelect(){
		
		List<BoardVO> list=new ArrayList<BoardVO>();
		
		try {
			dbConn();
			
			System.out.println("다오들어왓다");
			sql="select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MM') writedate from board "; 
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				BoardVO vo= new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setUserid(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setWritedate(rs.getString(5));
			
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return list;
	}

}
