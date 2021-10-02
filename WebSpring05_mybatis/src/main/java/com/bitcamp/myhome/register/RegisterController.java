package com.bitcamp.myhome.register;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class RegisterController {
	
	SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	@Autowired
	public void setSqlSession(SqlSession sqlSession) { //autowired에 의해서 전발닫은 sqlSession을 자동으로 넣어준다
		this.sqlSession = sqlSession;
	}
	
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public String loginOk(RegisterVO vo, HttpSession ses) { //view파일명리턴 ->홈페이지
		
		
		//로그인 -  여기서 db작업이 이루어짐
		// xml과 추상클래스를 매핑하여 추상클래스를 리턴받는다.
		RegisterDAOImp dao = sqlSession.getMapper(RegisterDAOImp.class); //괄호안에 추상클래스
		// 메소드실행 - 선택레코드가 없을 경우 null이 리턴되고, 선택레코드가 있으면 vo가 리턴 -디비에 일치하는 아디와비번이없으면 null값이 온다
		RegisterVO logVo = dao.loginCheck(vo); //vo넣어주면 다른vo가 넘어온다
		// 로그인성공(결과를돌려받는다) -> 세션저장
		if(logVo!=null) {//로그인된 경우
			ses.setAttribute("username", logVo.getUsername());
			ses.setAttribute("userid", logVo.getUserid());
			
		}else {
			
		}
		
		return "home";
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession ses) {
		ses.invalidate(); //세션초기화
		return "home";
	}
	
}
