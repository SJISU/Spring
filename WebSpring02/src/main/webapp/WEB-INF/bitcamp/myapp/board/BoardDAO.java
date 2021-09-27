package com.bitcamp.myapp.board;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.myapp.DBConnection;

public class BoardDAO extends DBConnection implements BoardDAOImpl {

   @Override
   public void totalRecordCount(pagingVO pVo) { //�ѷ��ڵ��
      
      try {
         dbConn();
         sql="select count(no) from board";
         //�˻�� ������
         if(pVo.getSearchWord()!=null && !pVo.getSearchWord().equals("")) {
            sql += " where "+pVo.getSearchKey()+" like '%"+pVo.getSearchWord()+"%'";
            
         }
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
   public List<BoardVO> boardPageSelect(pagingVO pVo) {
      List<BoardVO> list = new ArrayList<BoardVO>();
      try {
         dbConn();
         sql="select * from "
               +"(select * from "
               +"(select no, subject, userid, hit, to_char(writedate,'MM-DD-HH:Mi') writedate from board ";
              
               //�˻�� ������
               if(pVo.getSearchWord()!=null && !pVo.getSearchWord().equals("")){
                  sql += " where "+pVo.getSearchKey() + " like '%"+pVo.getSearchWord()+"%'";
               }
         
               sql += "order by no desc) "
               +"where rownum<=? order by no asc) "
               +"where rownum<=? order by no desc";
         pstmt = con.prepareStatement(sql);
         //���������緹�ڵ��*����������
         pstmt.setInt(1, pVo.getOnePageRecord()*pVo.getNowPage());
         //�����̴緹�ڵ��
         //���� ���ڵ��
         int lastPageRecord = pVo.getTotalRecord() % pVo.getOnePageRecord();//3
         
         if(pVo.getTotalPage()== pVo.getNowPage() && lastPageRecord!=0) {//�������������϶� , �������������� 0�̾ƴҶ� ��ü/5 �ؼ� 3�� ������ �϶�??
            pstmt.setInt(2,lastPageRecord);
         }else {
            pstmt.setInt(2,pVo.getOnePageRecord());
         }
       
         
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