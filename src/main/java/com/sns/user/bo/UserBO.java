package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;

	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public int insertUser(String loginId, String password, String name, String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
}