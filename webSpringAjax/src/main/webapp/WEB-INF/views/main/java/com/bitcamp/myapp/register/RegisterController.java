package com.bitcamp.myapp.register;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//���� Ŭ������ ��Ʈ�ѷ��� �Ǳ� ���ؼ��� Ŭ������ ������ @Controllor������̼��� ����Ѵ�.
@Controller
public class RegisterController {
	RegisterDAO dao = new RegisterDAO();
	//ȸ�������� ����
	@RequestMapping("/registerForm")
	public String registerForm() {
		//�����ϸ��� ��ȯ�Ѵ�.
		return "register/form";
	}
	//���̵� �ߺ��˻�
	/*@RequestMapping("/idCheck")
		public String idCheck(HttpServletRequest req) {
		String userid = req.getParameter("userid");
		System.out.println(userid);
		return "idCheck";
	}
	*/
	@RequestMapping("/idCheck")
	public ModelAndView idCheck(String userid) {
		int result = dao.idDoubleCheck(userid);
		ModelAndView mav = new ModelAndView();//�����Ͽ� �ʿ��� ������, �����ϸ��� �����ϴ°�
		mav.addObject("userid", userid);
		mav.addObject("result", result);
		mav.setViewName("register/idCheck");
		return mav;
	}
	//������ȣ �˻���������
	@RequestMapping("/zipSearch")
	public String zipSearch() {
		return "register/zipcodeSearch";
	}
	//������ȣ �˻�
	@RequestMapping("/zipFind")
	@ResponseBody
	public List<ZipCodeVO> zipFine(String doro){
		//List<ZipCodeVO> list = dao.zipSearchRecord(doro);
		return dao.zipSearchRecord(doro);
		
	}
	//ȸ�����							post����� �����϶��� �ݵ�� �����ؾ� �Ѵ�.
	   @RequestMapping(value = "/formOk", method = RequestMethod.POST)
	   public String formOk(RegisterVO vo, Model model) {
	      // Spring������ ���εǴ� �޼ҵ��� �Ű������� VO�� �����ϸ�, form�� name�� VO�� �������� ��ġ�ϴ� ���� ã�� set���ش�.
	      // �� ���⼭�� RegisterVO vo�� �����ϸ� form�� name���� ���޵� ������ ���� vo�� �Էµȴ�!
	      int result = dao.insertRecord(vo);
	      
	      // �����͸� ������ jsp ���Ϸ� �̵��� �����ε�, ModelAndView�� �� ���� �ְ�
	      // Model ��ü�� �Ű������� �޾Ƽ� �𵨿� ���� result�� �־ �ȴ�.
	      model.addAttribute("result", result);
	      return "register/formResult";
	   }
	   //�α�����
	   @RequestMapping("/login")
	   public String loginForm() {
		   return "register/login";
	   }
	   //�α���
	   @RequestMapping(value="/loginOk", method=RequestMethod.POST)
	   public ModelAndView loginOk(HttpServletRequest req, RegisterVO vo) {
		   dao.loginSelect(vo);
		   
		   ModelAndView mav = new ModelAndView();
		   
		   if(vo.getLogStatus().equals("Y")) {//�α��μ���
			   HttpSession session = req.getSession();
			   //�̸� �α��λ���
			   session.setAttribute("logname", vo.getUsername());
			   session.setAttribute("logid", vo.getUserid());
			   session.setAttribute("logStatus", vo.getLogStatus());
			   mav.setViewName("redirect:/");
		   }else {
			   mav.setViewName("redirect:login");
			   
		   }
		  return mav;   
	   }
	   @RequestMapping("/logout")
	   public ModelAndView logout(HttpSession session) {
		   session.invalidate();
		   ModelAndView mav = new ModelAndView();
		   mav.setViewName("redirect:/");
		   return mav;
	   }
	   //������
	   @RequestMapping("/registerEdit")
	   public ModelAndView registerEdit(HttpSession session) {
		   
		 RegisterVO vo = new RegisterVO();
		 vo.setUserid((String)session.getAttribute("logid"));
		 dao.selectRecord(vo);
		 
		 ModelAndView mav = new ModelAndView();
		 mav.addObject("vo", vo);
		 mav.setViewName("register/edit");
		 return mav;
	   }
	   
	   //ȸ����������
	      @RequestMapping(value="/editOk", method=RequestMethod.POST)
	      public ModelAndView editOk(RegisterVO regVo, HttpSession session) {
	         ModelAndView mav = new ModelAndView();
	         regVo.setUserid((String)session.getAttribute("logid"));
	         int result = dao.updateRecord(regVo);
	  
	         //�������н� history, �������� : ���������� �̵�
	         if(result>0) {//����
	            mav.setViewName("redirect:registerEdit");
	            
	         }else {//��������
	            mav.setViewName("register/editResult");
	         }
	         return mav;
	      }
}
