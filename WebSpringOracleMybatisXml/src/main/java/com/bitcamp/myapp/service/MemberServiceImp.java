package com.bitcamp.myapp.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.MemberDAO;
import com.bitcamp.myapp.vo.MemberVO;
@Service
public class MemberServiceImp implements MemberService {
   @Inject
   MemberDAO memberDAO;
   
   @Override
   public MemberVO loginSelect(MemberVO vo) {
      //DAO의 메소드를 호출
      return memberDAO.loginSelect(vo);
   }

}