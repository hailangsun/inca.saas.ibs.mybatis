package com.inca.saas.ibs.demo;

import javax.persistence.Column;

import com.inca.saas.ibs.common.Title;

public class Parent {
	
	@Title("父编号")
	@Column(name = "parent_code", length = 50, nullable = false, unique = true)
	private String ParentCode;

	@Title("父助记码")
	@Column(name = "parent_opcode", length = 50)
	private String parentOpcode;

	public String getParentCode() {
		return ParentCode;
	}

	public void setParentCode(String parentCode) {
		ParentCode = parentCode;
	}

	public String getParentOpcode() {
		return parentOpcode;
	}

	public void setParentOpcode(String parentOpcode) {
		this.parentOpcode = parentOpcode;
	}
	
	
	
}
