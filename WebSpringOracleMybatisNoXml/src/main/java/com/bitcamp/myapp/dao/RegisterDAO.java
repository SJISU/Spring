package com.bitcamp.myapp.dao;

import org.apache.ibatis.annotations.Select;

import com.bitcamp.myapp.vo.RegisterVO;

public interface RegisterDAO {
	@Select("select userid ,username from register where userid=#{userid} and userpwd=#{userpwd}") 
	public RegisterVO login(RegisterVO vo);
}
