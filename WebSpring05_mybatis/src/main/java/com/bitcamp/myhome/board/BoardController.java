package com.bitcamp.myhome.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
   SqlSession sqlSession; //��ü�� ������ �Ʒ��� ������̾��� �ڵ�����

   public SqlSession getSqlSession() {
      return sqlSession;
   }

   @Autowired
   public void setSqlSession(SqlSession sqlSession) {
      this.sqlSession = sqlSession;
   }
   
   @RequestMapping("/list")
   public ModelAndView list() {
      BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
      ModelAndView mav = new ModelAndView();
      mav.addObject("list", dao.boardAllSelect());
      // boardAllSelectó�� list�� �޾ƾ� �ϴ� ��쿡�� ������ result type�� ���� ����Ʈ�� �� �ʿ䰡 ����.
      // �ֳ��ϸ� ���̹�Ƽ���� �˾Ƽ� �÷��ǿ� ����ֱ� �����̴�!
      
      mav.setViewName("board/list");
      return mav;
   }
   
   
   //�۳�������
   @RequestMapping("/view")
   public ModelAndView view(int no) {
	   ModelAndView mav = new ModelAndView();
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   mav.addObject("vo",dao.boardView(no));
	   mav.setViewName("board/view");
	   return mav;
   }
   
   //�۾����������̵�
   @RequestMapping("/write")
   public String write() {
	   return "board/write";
   }
   
   //�۾��������� �۵�Ϲ�ư������
   @RequestMapping(value="/writeOk", method=RequestMethod.POST)
   public ModelAndView writeOk(BoardVO vo, HttpSession ses, HttpServletRequest req) {
      vo.setUserid((String)ses.getAttribute("userid"));
      vo.setIp(req.getRemoteAddr());
      
      ModelAndView mav = new ModelAndView();
      
      BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
      int cnt = dao.boardWriteOk(vo);
      if(cnt>0) {//�۵��
         mav.setViewName("redirect:list");
      }else {//��Ͻ���
    	 mav.addObject("msg","���");
         mav.setViewName("board/writeResult");
      }
      return mav;
   }
   //�ۼ��������ΰ��� 
   @RequestMapping("/edit")
   public ModelAndView edit(int no) {
	   ModelAndView mav = new ModelAndView();
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   mav.addObject("vo",dao.boardView(no));
	   mav.setViewName("board/edit");
	   return mav;
   }
   
   //�ۼ���
   @RequestMapping(value="/editOk",method=RequestMethod.POST)
   public ModelAndView editOk(BoardVO vo,HttpSession  session) {//���ǿ� ����Ǿ� �ִ� ���̵�� ���ۼ��� ��
	   
	   vo.setUserid((String)session.getAttribute("userid"));
	   
	   ModelAndView mav = new ModelAndView();
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   int cnt = dao.boardEditOk(vo);
	   mav.addObject("no",vo.getNo());
	   
	   if(cnt>0){//������ �Ǹ� �۳��뺸�� "/view"
		   mav.setViewName("redirect:view"); //�� ������ redirect�ΰ�.....
		   
	   }else{ //�����ȵǸ� �ۼ������� �̵� "/edit" -> ������ ������ �������� ->h history�� ������
		   mav.addObject("msg","����");
		   mav.setViewName("board/writeResult");
	   }
	   
	   return mav;
   }
   //�ۻ���
   @RequestMapping("/boardDel")
   public ModelAndView boardDel(int no,HttpSession session) {//���ڵ��ȣ����
	   String userid=(String)session.getAttribute("userid");
	   
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   int cnt = dao.boarDelete(no, userid);
	   
	   ModelAndView mav = new ModelAndView();
	   if(cnt>0) { //�ۻ��� -> ����Ʈ
		   mav.setViewName("redirect:list");//�ٸ���Ʈ�ѷ�ȣ��//��Ʈ�ѷ��� ����Ʈ
		   
	   }else {//�ۻ������� -> �۳��뺸�� ->���ڵ��ȣ����������
		   mav.addObject("no",no);
		   mav.setViewName("redirect:view");
	   }
	   return mav;
   }

}
