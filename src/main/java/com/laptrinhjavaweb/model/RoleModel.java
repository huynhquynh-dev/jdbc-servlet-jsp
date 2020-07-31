package com.laptrinhjavaweb.model;

public class RoleModel extends AbstractModel<RoleModel>{
	
	private String name;
	private String code;

	public RoleModel() {
		super();
	}

	public RoleModel(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
