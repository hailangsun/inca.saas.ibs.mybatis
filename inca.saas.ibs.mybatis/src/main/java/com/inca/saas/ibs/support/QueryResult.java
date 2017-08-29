package com.inca.saas.ibs.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -548584635991949215L;

	int total = 0;

	List<T> data = new ArrayList<T>();

	/**
	 * 基础查询参数
	 */
	private Map<String, String> prop = new HashMap();

	String errors;

	public QueryResult() {
	}
	
	public QueryResult(String error) {
		this.errors = error;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void clearData() {
		this.data.clear();
	}

	public void addData(List<T> list) {
		this.data.addAll(list);
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageData [total=" + total + ", data=" + data.size() + ", errors=" + errors + "]";
	}
}
