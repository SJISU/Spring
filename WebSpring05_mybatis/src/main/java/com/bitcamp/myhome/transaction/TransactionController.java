package com.bitcamp.myhome.transaction;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myhome.board.BoardDAOImp;
import com.bitcamp.myhome.board.BoardVO;

@Controller
public class TransactionController {
	@Autowired
	SqlSession  sqlSession;//디비작업하기위해서
	
	//*****************transactionr 객체 -> servler context에 있는거 자바클래스로 가져옴
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	
	@RequestMapping("/transactionTest")
	@Transactional(rollbackFor= {Exception.class, RuntimeException.class})//에러가낫을때 처리하는 클래스를 알려준다
	public ModelAndView tran() {
		//****************************************
		DefaultTransactionDefinition def= new DefaultTransactionDefinition();//트랜잭션 작업을 하기위해 객체생성
		def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);//없으면생성
		TransactionStatus status = transactionManager.getTransaction(def);//현재실행상태보관
		
		//****************************************
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/list"); //추가하고나서 리스트로 이동
		BoardDAOImp dao = sqlSession.getMapper(BoardDAOImp.class);
		try {
			//첫번째 추가할 레코드
			BoardVO vo = new BoardVO();
			vo.setUserid("goguma");
			vo.setSubject("트랜잭션 테스트 중");
			vo.setContent("~~~~~~~~");
			vo.setIp("123.123.123.123");
			
			//두번째 추가할 레코드
			BoardVO vo2 = new BoardVO();
			vo2.setUserid("goguma");
			vo2.setSubject("Transaction Test");
			vo2.setContent("********************");
			vo2.setIp("111.222.333.444");
			
			dao.boardWriteOk(vo); //첫번째추가 -> insert 가일어남
			dao.boardWriteOk(vo2);//두번째추가
			
			transactionManager.commit(status);//추가가되면 commit
		}catch(Exception e) {
			e.printStackTrace();	
		}
	
		return mav;
	}
}
