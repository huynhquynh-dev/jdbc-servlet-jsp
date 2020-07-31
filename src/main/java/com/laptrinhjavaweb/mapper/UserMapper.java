package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {

		UserModel userModel = new UserModel();
		try {
			userModel.setId(resultSet.getLong("id"));
			userModel.setUserName(resultSet.getString("username"));
			userModel.setFullName(resultSet.getString("fullname"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setStatus(resultSet.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				userModel.setRole(role);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}			
			return userModel;
		} 
		catch (SQLException e) {			
			return null;
		}
	}
}
