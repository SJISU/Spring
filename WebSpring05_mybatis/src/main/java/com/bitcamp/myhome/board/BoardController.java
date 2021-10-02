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
   SqlSession sqlSession;

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

}
