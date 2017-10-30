package com.inca.saas.ibs.codegen;

import java.io.File;

public class CodegenModel {
	
	//保存路径
	private File savePath;
	private String basePkgName;
	private String controllerName;//controller前缀名
	private String funcPath;
	private String dtlBaseSql;
	private String docBaseSql;
	private String baseSql;
	private String explain;
	private String docSubsysName;
	private String dtlSubsysName;
	private String subsysName;
	
	private String docPrefix;
	private String dtlPrefix;
	private String prefix;
	
	public String getDocPrefix() {
		return docPrefix;
	}
	public void setDocPrefix(String docPrefix) {
		this.docPrefix = docPrefix;
	}
	public String getDtlPrefix() {
		return dtlPrefix;
	}
	public void setDtlPrefix(String dtlPrefix) {
		this.dtlPrefix = dtlPrefix;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getDocSubsysName() {
		return docSubsysName;
	}
	public void setDocSubsysName(String docSubsysName) {
		this.docSubsysName = docSubsysName;
	}
	public String getDtlBaseSql() {
		return dtlBaseSql;
	}
	public void setDtlBaseSql(String dtlBaseSql) {
		this.dtlBaseSql = dtlBaseSql;
	}
	public String getDocBaseSql() {
		return docBaseSql;
	}
	public void setDocBaseSql(String docBaseSql) {
		this.docBaseSql = docBaseSql;
	}
	public String getBasePkgName() {
		return basePkgName;
	}
	public void setBasePkgName(String basePkgName) {
		this.basePkgName = basePkgName;
	}

	public File getSavePath() {
		return savePath;
	}
	public void setSavePath(File savePath) {
		this.savePath = savePath;
	}
	
	public String getFuncPath() {
		return funcPath;
	}
	public void setFuncPath(String funcPath) {
		this.funcPath = funcPath;
	}
	public String getBaseSql() {
		return baseSql;
	}
	public void setBaseSql(String baseSql) {
		this.baseSql = baseSql;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getDtlSubsysName() {
		return dtlSubsysName;
	}
	public void setDtlSubsysName(String dtlSubsysName) {
		this.dtlSubsysName = dtlSubsysName;
	}
	public String getSubsysName() {
		return subsysName;
	}
	public void setSubsysName(String subsysName) {
		this.subsysName = subsysName;
	}
	
	
	
	
}
