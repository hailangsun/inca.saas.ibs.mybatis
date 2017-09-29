package com.inca.saas.ibs.common;

import java.io.Serializable;

public class AdvQueryModel implements Serializable{
	private static final long serialVersionUID = -2115503987320307503L;
	
	private String field;//数据库字段名
	private String header;//标题
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	
}
