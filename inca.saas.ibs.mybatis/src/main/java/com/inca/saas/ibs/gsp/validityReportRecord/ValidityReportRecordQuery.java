package com.inca.saas.ibs.gsp.validityReportRecord;

import com.inca.saas.ibs.support.Query;

public class ValidityReportRecordQuery extends Query {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String busiDateFrom;
	private String busiDateTo;
	private String columnsJson;

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

	public String getColumnsJson() {
		return columnsJson;
	}

	public void setColumnsJson(String columnsJson) {
		this.columnsJson = columnsJson;
	}
	
	

}
