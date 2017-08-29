package com.inca.saas.ibs.support;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Query implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4692802584372555701L;

	Logger logger = LoggerFactory.getLogger(getClass());

	private int pageIndex = 0;

	private int pageSize = 10;

	private String sortFields;

	private String sortField;

	private String sortOrder;

	private String keyword;

	String loggerPrefix = null;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortFields() {
		return sortFields;
	}

	public void setSortFields(String sortFields) {
		this.sortFields = sortFields;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLoggerPrefix() {
		return loggerPrefix;
	}

	public void setLoggerPrefix(String loggerPrefix) {
		this.loggerPrefix = loggerPrefix;
	}

	public String getDefaultSort() {
		return "";
	}
}
