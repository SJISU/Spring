package com.bitcamp.myhome.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter extends HandlerInterceptorAdapter {
	//������ �����Ͽ� ��Ʈ�ѷ� ȣ�� -> url�ּ�,context,���� -----> ���ϸ��� 
	//��Ʈ�ѷ��� ȣ��Ǳ����� ȣ��Ǵ� �޼ҵ�
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		//request���� ���Ǳ��ؿ´�
		//�α��� ���θ� Ȯ���Ͽ� �α����� �ȵȰ�� �α��������� �̵�(Ȩ)
		//�α����� �Ȱ��� ������ ��Ʈ�ѷ��� ����
		
		//�α��ε� ���̵��ϱ�
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		
		//�α����� �ȉ���
		if(userid==null || userid.equals("")) {
			//                                       /myhome/
			response.sendRedirect(request.getContextPath()+"/"); //Ȩ���� ������
			return false; //�ڿ��� �����ѵǰ� false�� ���ش�.
		}
		//�α����� �Ȱ�� -> ������ ���� : ������ ��Ʈ�ѷ� �����ּҷ� �̵�
		//request�� �ִ� �ּҷ� ����		
		return true;
	}
	
	//��Ʈ�ѷ��� ����� �� view�������� �̵��ϱ����� ȣ��ȴ�.
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			@Nullable ModelAndView ModelAndView)throws Exception{
		
	}
	//��Ʈ�ѷ��� ������ ȣ��ȴ�.
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler,
			@Nullable Exception ex)throws Exception{
		
	}
}
