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
   SqlSession sqlSession; //객체가 들어가있음 아래의 오토와이어드로 자동으로

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
      // boardAllSelect처럼 list를 받아야 하는 경우에도 매퍼의 result type에 굳이 리스트를 쓸 필요가 없다.
      // 왜냐하면 마이바티스가 알아서 컬렉션에 담아주기 때문이다!
      
      mav.setViewName("board/list");
      return mav;
   }
   
   
   //글내영보기
   @RequestMapping("/view")
   public ModelAndView view(int no) {
	   ModelAndView mav = new ModelAndView();
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   mav.addObject("vo",dao.boardView(no));
	   mav.setViewName("board/view");
	   return mav;
   }
   
   //글쓰기폼으로이동
   @RequestMapping("/write")
   public String write() {
	   return "board/write";
   }
   
   //글쓰기폼에선 글등록버튼누르면
   @RequestMapping(value="/writeOk", method=RequestMethod.POST)
   public ModelAndView writeOk(BoardVO vo, HttpSession ses, HttpServletRequest req) {
      vo.setUserid((String)ses.getAttribute("userid"));
      vo.setIp(req.getRemoteAddr());
      
      ModelAndView mav = new ModelAndView();
      
      BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
      int cnt = dao.boardWriteOk(vo);
      if(cnt>0) {//글등록
         mav.setViewName("redirect:list");
      }else {//등록실패
    	 mav.addObject("msg","등록");
         mav.setViewName("board/writeResult");
      }
      return mav;
   }
   //글수정폼으로가는 
   @RequestMapping("/edit")
   public ModelAndView edit(int no) {
	   ModelAndView mav = new ModelAndView();
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   mav.addObject("vo",dao.boardView(no));
	   mav.setViewName("board/edit");
	   return mav;
   }
   
   //글수정
   @RequestMapping(value="/editOk",method=RequestMethod.POST)
   public ModelAndView editOk(BoardVO vo,HttpSession  session) {//세션에 저장되어 있는 아이디와 글작성자 비교
	   
	   vo.setUserid((String)session.getAttribute("userid"));
	   
	   ModelAndView mav = new ModelAndView();
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   int cnt = dao.boardEditOk(vo);
	   mav.addObject("no",vo.getNo());
	   
	   if(cnt>0){//수정이 되면 글내용보기 "/view"
		   mav.setViewName("redirect:view"); //왜 수정은 redirect인가.....
		   
	   }else{ //수정안되면 글수정으로 이동 "/edit" -> 그전에 쓴글은 지워진다 ->h history로 보낸다
		   mav.addObject("msg","수정");
		   mav.setViewName("board/writeResult");
	   }
	   
	   return mav;
   }
   //글삭제
   @RequestMapping("/boardDel")
   public ModelAndView boardDel(int no,HttpSession session) {//레코드번호받은
	   String userid=(String)session.getAttribute("userid");
	   
	   BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
	   int cnt = dao.boarDelete(no, userid);
	   
	   ModelAndView mav = new ModelAndView();
	   if(cnt>0) { //글삭제 -> 리스트
		   mav.setViewName("redirect:list");//다른컨트롤러호출//컨트롤러에 리스트
		   
	   }else {//글삭제실패 -> 글내용보기 ->레코드번호가져가야함
		   mav.addObject("no",no);
		   mav.setViewName("redirect:view");
	   }
	   return mav;
   }

}
