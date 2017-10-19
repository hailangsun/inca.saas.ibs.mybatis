package com.inca.saas.ibs.entity;

import javax.persistence.Column;

import com.inca.saas.ibs.common.Option;
import com.inca.saas.ibs.common.Title;

public class Supply {

	@Title("编号")
	@Column(name = "supply_code", length = 50, nullable = false, unique = true)
	private String supplyCode;

	@Title("助记码")
	@Column(name = "supply_opcode", length = 50)
	private String supplyOpcode;

	@Title("供应商")
	@Column(name = "supply_name", length = 100, nullable = false, unique = true)
	private String supplyName;
	
	@Title("首次经营")
	@Column(name = "first_business")
	private Boolean firstBusiness = false;

	@Title("首营审批状态")
	@Column(name = "gsp_status", length = 100)
	@Option(name = "PUB_GSP_STATUS", editable = false)
	private Integer gspStatus;

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyOpcode() {
		return supplyOpcode;
	}

	public void setSupplyOpcode(String supplyOpcode) {
		this.supplyOpcode = supplyOpcode;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Boolean getFirstBusiness() {
		return firstBusiness;
	}

	public void setFirstBusiness(Boolean firstBusiness) {
		this.firstBusiness = firstBusiness;
	}

	public Integer getGspStatus() {
		return gspStatus;
	}

	public void setGspStatus(Integer gspStatus) {
		this.gspStatus = gspStatus;
	}

	
	
	
}
