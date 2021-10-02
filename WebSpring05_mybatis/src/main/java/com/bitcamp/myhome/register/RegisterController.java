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
	public void setSqlSession(SqlSession sqlSession) { //autowired�� ���ؼ� ���ߴ��� sqlSession�� �ڵ����� �־��ش�
		this.sqlSession = sqlSession;
	}
	
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public String loginOk(RegisterVO vo, HttpSession ses) { //view���ϸ��� ->Ȩ������
		
		
		//�α��� -  ���⼭ db�۾��� �̷����
		// xml�� �߻�Ŭ������ �����Ͽ� �߻�Ŭ������ ���Ϲ޴´�.
		RegisterDAOImp dao = sqlSession.getMapper(RegisterDAOImp.class); //��ȣ�ȿ� �߻�Ŭ����
		// �޼ҵ���� - ���÷��ڵ尡 ���� ��� null�� ���ϵǰ�, ���÷��ڵ尡 ������ vo�� ���� -��� ��ġ�ϴ� �Ƶ�ͺ���̾����� null���� �´�
		RegisterVO logVo = dao.loginCheck(vo); //vo�־��ָ� �ٸ�vo�� �Ѿ�´�
		// �α��μ���(����������޴´�) -> ��������
		if(logVo!=null) {//�α��ε� ���
			ses.setAttribute("username", logVo.getUsername());
			ses.setAttribute("userid", logVo.getUserid());
			
		}else {
			
		}
		
		return "home";
	}
	
	//�α׾ƿ�
	@RequestMapping("/logout")
	public String logout(HttpSession ses) {
		ses.invalidate(); //�����ʱ�ȭ
		return "home";
	}
	
}
