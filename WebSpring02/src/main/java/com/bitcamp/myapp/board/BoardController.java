package com.bitcamp.myapp.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	BoardDAO dao = new BoardDAO();
	
	@RequestMapping("/boardList")
	public ModelAndView boardList(pagingVO pVo) {
		ModelAndView mav = new ModelAndView();
		
		//총레코드수
		dao.totalRecordCount(pVo);
		mav.addObject("pVo",pVo);		
		mav.addObject("list",dao.boardPageSelect(pVo));
		mav.setViewName("board/boardList");
		return mav;
		
	}
	//글내용보기
	@RequestMapping("/boardView")
	public ModelAndView boardView(BoardVO vo, pagingVO pVo) {
		ModelAndView mav = new ModelAndView();
		dao.boardSelect(vo);
		mav.addObject("vo",vo);
		mav.addObject("pVo",pVo);
		mav.setViewName("board/boardView");
		return mav;
		
	}
	//ajax로 구현
	//댓글쓰기 - ajax방식으로 쓸거임 -> 화면안바뀌고
	@RequestMapping(value="/replyWrite",method=RequestMethod.POST)
	@ResponseBody
	public int replyWrite(ReplyBoardVO replyVo,HttpSession ses) {
		//댓글 작성자를 session 얻어오기
		replyVo.setUserid((String)ses.getAttribute("logid"));
		return dao.replyInsert(replyVo);
		
	}
	
	//댓글목록선택 - ajax
	 @RequestMapping(value="/replyList")
     @ResponseBody
     public List<ReplyBoardVO> replyList(int no){
        return dao.replyListSelect(no);
     }

	 //댓글수정 - Edit 버튼 -ajax
	 @RequestMapping(value="/replyEditOk",method=RequestMethod.POST)
     @ResponseBody
	 public int replyEditOk(ReplyBoardVO replyVo,HttpSession ses ) {
		 replyVo.setUserid((String)ses.getAttribute("logid"));
		 return dao.replyUpdate(replyVo);
	 }
	 
	//댓글 삭제
	   @RequestMapping(value="/replyDel")
	   @ResponseBody
	   public int replyDel(int num, HttpSession ses) {
	      return dao.replyDelete(num, (String)ses.getAttribute("logid"));
	   }

}
