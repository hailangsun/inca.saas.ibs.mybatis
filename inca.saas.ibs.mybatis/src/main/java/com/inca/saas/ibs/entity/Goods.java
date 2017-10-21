package com.inca.saas.ibs.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.inca.saas.ibs.common.Func;
import com.inca.saas.ibs.common.Option;
import com.inca.saas.ibs.common.Title;

@Title("商品管理")
@Func("IBSPUB010")
public class Goods {
	
	@Title("商品编码")
	@Column(name = "goods_code", length = 50, nullable = false, unique = true)
	private String goodsCode;

	@Title("助记码")
	@Column(name = "goods_opcode", length = 100)
	private String goodsOpcode;

	@Title("通用名")
	@Column(name = "goods_name", length = 200, nullable = false)
	private String goodsName;

	@Title("商品名")
	@Column(name = "commodity_name", length = 200)
	private String commodityName;

	@Title("拼音")
	@Column(name = "goods_pinyin", length = 200)
	private String goodsPinyin;

	@Title("英文名")
	@Column(name = "goods_eng_name", length = 100)
	private String goodsEngName;

	@Title("学名")
	@Column(name = "goods_formal_name", length = 100)
	private String goodsFormalName;

	@Title("规格")
	@Column(name = "goods_spec", length = 50, nullable = false)
	private String goodsSpec;

	@Title("基本单位")
	@Column(name = "goods_unit", length = 20, nullable = false)
	private String goodsUnit;

	@Title("产地")
	@Column(name = "prod_area", length = 50)
	private String prodArea;

	@Title("剂型")
	@Column(name = "medicine_type", length = 50, nullable = false)
	@Option(name = "PUB_GOODS_MEDICINE_TYPE", editable = true)
	private String medicineType;

	@Title("批准文号")
	@Column(name = "approval_no", length = 50, nullable = false)
	private String approvalNo;

	@Title("批准文号有效期")
	@Column(name = "approval_date")
	private Date approvalDate;

	@Title("GSP管控")
	@Column(name = "gsp_flag")
	private Boolean gspFlag = true;


	
	@Title("条码")
	@Column(name = "barcode", length = 50)
	private String barcode;

	@Title("品牌")
	@Column(name = "brand", length = 100)
	private String brand;

	@Title("商标")
	@Column(name = "trade_mark", length = 100)
	private String tradeMark;

	@Title("注册证号")
	@Column(name = "regist_no", length = 50)
	private String registNo;

	@Title("装箱规格")
	@Column(name = "carton_size", length = 20)
	private String cartonSize;

	@Title("包装规格")
	@Column(name = "packing", length = 50)
	private String packing;

	@Title("商品类型")
	@Column(name = "goods_type", precision = 5, nullable = false)
	@Option(name = "PUB_GOODS_TYPE", editable = false)
	private Integer goodsType;

	@Title("药物类型")
	@Column(name = "drug_type", precision = 5)
	@Option(name = "PUB_GOODS_DRUG_TYPE", editable = false)
	private Integer drugType;

	@Title("处方药")
	@Column(name = "rx_flag", precision = 5)
	@Option(name = "PUB_GOODS_RX", editable = false)
	private Integer rxFlag;

	@Title("零售登记")
	@Column(name = "resa_registe_flag")
	private Boolean resaRegisteFlag = false;

	@Title("电子监管")
	@Column(name = "supervise_flag")
	private Boolean superviseFlag = false;

	@Title("进口药品")
	@Column(name = "imported_flag")
	private Boolean importedFlag = false;

	@Title("冷藏药品")
	@Column(name = "cold_flag")
	private Boolean coldFlag = false;

	@Title("中药饮片")
	@Column(name = "chinese_pieces_flag")
	private Boolean chinesePiecesFlag = false;

	@Title("双人验收作业")
	@Column(name = "double_in_flag")
	private Boolean doubleInFlag = false;

	@Title("双人复核出库")
	@Column(name = "double_out_flag")
	private Boolean doubleOutFlag = false;

	@Title("有效期")
	@Column(name = "valid_period", precision = 5)
	private Integer validPeriod;

	@Title("期间单位")
	@Column(name = "period_unit", precision = 5)
	@Option(name = "PUB_GOODS_PERIOD_UNIT", editable = false)
	private Integer periodUnit;

	@Title("近效期天数")
	@Column(name = "alert_days", precision = 5)
	private Integer alertDays;

	@Title("门店近效期天数")
	@Column(name = "retail_alert_days", precision = 5)
	private Integer retailAlertDays;

	@Title("一般养护天数")
	@Column(name = "conserve_days", precision = 5)
	private Integer conserveDays;
	
	@Title("税率")
	@Column(name = "tax_rate", precision = 16, scale = 6)
	private BigDecimal taxRate;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsOpcode() {
		return goodsOpcode;
	}

	public void setGoodsOpcode(String goodsOpcode) {
		this.goodsOpcode = goodsOpcode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getGoodsPinyin() {
		return goodsPinyin;
	}

	public void setGoodsPinyin(String goodsPinyin) {
		this.goodsPinyin = goodsPinyin;
	}

	public String getGoodsEngName() {
		return goodsEngName;
	}

	public void setGoodsEngName(String goodsEngName) {
		this.goodsEngName = goodsEngName;
	}

	public String getGoodsFormalName() {
		return goodsFormalName;
	}

	public void setGoodsFormalName(String goodsFormalName) {
		this.goodsFormalName = goodsFormalName;
	}

	public String getGoodsSpec() {
		return goodsSpec;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getProdArea() {
		return prodArea;
	}

	public void setProdArea(String prodArea) {
		this.prodArea = prodArea;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Boolean getGspFlag() {
		return gspFlag;
	}

	public void setGspFlag(Boolean gspFlag) {
		this.gspFlag = gspFlag;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getRegistNo() {
		return registNo;
	}

	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

	public String getCartonSize() {
		return cartonSize;
	}

	public void setCartonSize(String cartonSize) {
		this.cartonSize = cartonSize;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getDrugType() {
		return drugType;
	}

	public void setDrugType(Integer drugType) {
		this.drugType = drugType;
	}

	public Integer getRxFlag() {
		return rxFlag;
	}

	public void setRxFlag(Integer rxFlag) {
		this.rxFlag = rxFlag;
	}

	public Boolean getResaRegisteFlag() {
		return resaRegisteFlag;
	}

	public void setResaRegisteFlag(Boolean resaRegisteFlag) {
		this.resaRegisteFlag = resaRegisteFlag;
	}

	public Boolean getSuperviseFlag() {
		return superviseFlag;
	}

	public void setSuperviseFlag(Boolean superviseFlag) {
		this.superviseFlag = superviseFlag;
	}

	public Boolean getImportedFlag() {
		return importedFlag;
	}

	public void setImportedFlag(Boolean importedFlag) {
		this.importedFlag = importedFlag;
	}

	public Boolean getColdFlag() {
		return coldFlag;
	}

	public void setColdFlag(Boolean coldFlag) {
		this.coldFlag = coldFlag;
	}

	public Boolean getChinesePiecesFlag() {
		return chinesePiecesFlag;
	}

	public void setChinesePiecesFlag(Boolean chinesePiecesFlag) {
		this.chinesePiecesFlag = chinesePiecesFlag;
	}

	public Boolean getDoubleInFlag() {
		return doubleInFlag;
	}

	public void setDoubleInFlag(Boolean doubleInFlag) {
		this.doubleInFlag = doubleInFlag;
	}

	public Boolean getDoubleOutFlag() {
		return doubleOutFlag;
	}

	public void setDoubleOutFlag(Boolean doubleOutFlag) {
		this.doubleOutFlag = doubleOutFlag;
	}

	public Integer getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(Integer validPeriod) {
		this.validPeriod = validPeriod;
	}

	public Integer getPeriodUnit() {
		return periodUnit;
	}

	public void setPeriodUnit(Integer periodUnit) {
		this.periodUnit = periodUnit;
	}

	public Integer getAlertDays() {
		return alertDays;
	}

	public void setAlertDays(Integer alertDays) {
		this.alertDays = alertDays;
	}

	public Integer getRetailAlertDays() {
		return retailAlertDays;
	}

	public void setRetailAlertDays(Integer retailAlertDays) {
		this.retailAlertDays = retailAlertDays;
	}

	public Integer getConserveDays() {
		return conserveDays;
	}

	public void setConserveDays(Integer conserveDays) {
		this.conserveDays = conserveDays;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	
	
	
}
