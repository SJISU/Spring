package com.bitcamp.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	   @Autowired  // @Inject
	    public BoardDAO dao;
	   //public BoardDAO dao = new BoardDAO();

	
	@RequestMapping("/board/list")
	public ModelAndView list() { //리스트불러오는
		ModelAndView mav = new ModelAndView();
		//dao.allRecord(); //list 가 리턴됨
		mav.addObject("list",dao.allRecord());
		mav.setViewName("board/list");
		return mav;
	}
	@RequestMapping("/board/write")//글쓰기페이지로이동
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/board/writeOk",method=RequestMethod.POST) //글쓰기버튼누르면
	public ModelAndView writeOk(BoardVO vo, HttpServletRequest  req, HttpSession ses) {//req - ip,ses-작성자
		ModelAndView mav = new ModelAndView();
		
		vo.setIp(req.getRemoteAddr());//ip
		vo.setUserid((String)ses.getAttribute("userid"));//userid
		int result = dao.boardInsert(vo);
		
		mav.setViewName("redirect:list");		
		return mav;
	
	}
	//글내용보기
	 @RequestMapping("/board/view")
	   public ModelAndView view(int no) {
	      ModelAndView mav = new ModelAndView();
	      mav.addObject("vo", dao.oneRecord(no));
	      mav.setViewName("board/view");
	      return mav;
	      
	   }
	 @RequestMapping("/board/edit")
	 public ModelAndView edit(int no) {
		 ModelAndView mav = new ModelAndView();
		 mav.addObject("vo",dao.oneRecord(no));
		 mav.setViewName("board/edit");
		 return mav;
	 }
	 //수정이끝나고나면 -> 성공,실패
	 @RequestMapping(value="/board/editOk",method=RequestMethod.POST)
	 public ModelAndView editOk(BoardVO vo) {
		 ModelAndView mav = new ModelAndView();
		 mav.addObject("no",vo.getNo());
		 
		 int result = dao.boardUpdate(vo);//디비작업		 
		 if(result>0){//수정시 글내용보기로
			 mav.setViewName("redirect:view"); // 앞에경로빼고 바로 그페이지로
		 }else{//수정실패시 글수정폼으로
			 mav.setViewName("redirect:edit");
		 }
		 return mav;
	 }
	 @RequestMapping("/board/del")
	    public ModelAndView del(int no) {
	         ModelAndView mav = new ModelAndView();
	         int result = dao.boardDelete(no);
	         if(result>0) {//삭제한경우
	            mav.setViewName("redirect:list");
	         }else {//삭제못한경우
	            mav.addObject("no",no);       
	            mav.setViewName("redirect:view");
	         }
	         return mav;
	    }
}
