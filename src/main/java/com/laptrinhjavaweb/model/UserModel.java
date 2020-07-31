package com.laptrinhjavaweb.model;

public class UserModel extends AbstractModel<UserModel> {

	private String userName;
	private String password;
	private String fullName;
	private int status;
	private Long roleId;
	private RoleModel role = new RoleModel();

	public UserModel() {
		super();
	}
		
	public UserModel(String userName, String password, String fullName, int status, Long roleId) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.status = status;
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}
}
