package com.bitcamp.myapp.regitser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//현재 클래스 컨트롤러가 되기 위해서는 클래스명 이전에 @Controller어노테이션을 기술한다.
//클래스 정의되기전 @어노테이션
@Controller //-> 서블릿이 된다 //dispatcher컨트롤러가 
public class RegisterController {
	
	//회원가입폼 매핑
	//              *링크걸때 사용햇던 이름 
	//클라이언트가 보내는 데이터 받는곳
	@RequestMapping("/registerForm") //매핑된 거라는표시
	public String registerForm() {
		
		
		//뷰파일명을 반환한다.
		return "register/form"; //서블릿콘텍스트의 ....앞뒤로 붙일거기때문에 이렇게만 쓴다
	}

}
