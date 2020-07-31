package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	
	UserModel findByUserNameAndPasswordAndStatus(String username, String password, Integer status);
}
