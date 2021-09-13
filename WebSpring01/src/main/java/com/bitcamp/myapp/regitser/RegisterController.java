package com.bitcamp.myapp.regitser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//���� Ŭ���� ��Ʈ�ѷ��� �Ǳ� ���ؼ��� Ŭ������ ������ @Controller������̼��� ����Ѵ�.
//Ŭ���� ���ǵǱ��� @������̼�
@Controller //-> ������ �ȴ� //dispatcher��Ʈ�ѷ��� 
public class RegisterController {
	
	//ȸ�������� ����
	//              *��ũ�ɶ� ����޴� �̸� 
	//Ŭ���̾�Ʈ�� ������ ������ �޴°�
	@RequestMapping("/registerForm") //���ε� �Ŷ��ǥ��
	public String registerForm() {
		
		
		//�����ϸ��� ��ȯ�Ѵ�.
		return "register/form"; //�������ؽ�Ʈ�� ....�յڷ� ���ϰű⶧���� �̷��Ը� ����
	}

}
