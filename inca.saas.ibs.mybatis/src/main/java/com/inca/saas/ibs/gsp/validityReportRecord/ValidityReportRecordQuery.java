package com.inca.saas.ibs.gsp.validityReportRecord;

import java.io.Serializable;

public class ValidityReportRecordQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String keyword;
	private Integer pageIndex;
	private Integer pageSize;
	private String sortField;
	private String sortOrder;
	private String busiDateFrom;
	private String busiDateTo;

	public java.lang.String getKeyword() {
		return keyword;
	}

	public void setKeyword(java.lang.String keyword) {
		this.keyword = keyword;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

	public String getBusiDateFrom() {
		return busiDateFrom;
	}

	public void setBusiDateFrom(String busiDateFrom) {
		this.busiDateFrom = busiDateFrom;
	}

	public String getBusiDateTo() {
		return busiDateTo;
	}

	public void setBusiDateTo(String busiDateTo) {
		this.busiDateTo = busiDateTo;
	}

}
