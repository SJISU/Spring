package com.bitcamp.app.register;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bitcamp.app.Constants;

public class RegisterDAO implements RegisterDAOImpl{
   public JdbcTemplate template;
   public JdbcTemplate getTemplate() {
      return template;
   }

   public void setTemplate(JdbcTemplate template) {
      this.template = template;
   }
   
   public RegisterDAO() {
      this.template = Constants.template; //현재 멤버에 있는 template에 아까 만든 constants에 있는 템플릿을 대입
   }
   //=======================================
   @Override
   public RegisterVO login(RegisterVO vo) {
      //데이터가 있는지 확인 
      String sql = "select count(userid) cnt from register where userid=? and userpwd=?";
      RegisterVO logVO = template.queryForObject(sql, new BeanPropertyRowMapper<RegisterVO>(RegisterVO.class), 
    		  vo.getUserid(), vo.getUserpwd()); //선택데이터를 어디에 담을 건지? rowmapper로 정의 
      
      if(logVO.getCnt()>0) {
          sql = "select userid, username from register where userid=? and userpwd=?";
         //아이디, 비번을 매개변수로 -> 결과는 vo가 리턴되야함(Object 객체)
         return template.queryForObject(sql, new BeanPropertyRowMapper<RegisterVO>(RegisterVO.class), 
        		 vo.getUserid(), vo.getUserpwd()); //선택데이터를 어디에 담을 건지? rowmapper로 정의 
         //쿼리문을 물음표 조건으로 셋팅한 후 선택한 결과는 new Bean~에 담긴다. 
      }else{
    	  return null;
      }
   }

}