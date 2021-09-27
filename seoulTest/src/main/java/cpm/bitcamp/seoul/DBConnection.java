package cpm.bitcamp.seoul;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
	//변수
	protected Connection con = null; //프로텍티드 ->상속받아서 사용해라
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	protected String sql = "";
	
	//db연결
	public void dbConn() {
		try {
			Context ctx = new InitialContext();
			Context envCtx =(Context)ctx.lookup("java:comp/env");
			
			DataSource ds =(DataSource)envCtx.lookup("jdbc/myoracle");
			con = ds.getConnection();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//db종료
	public void dbClose() {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
			sql="";
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
