package com.sns.user.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {
	public boolean existLoginId(String loginId);
	
	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password,
			@Param("name") String name, 
			@Param("email") String email);
	
	public User selectUserByLoginIdAndPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
}