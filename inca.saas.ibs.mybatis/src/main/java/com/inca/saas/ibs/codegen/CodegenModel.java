package com.inca.saas.ibs.codegen;

import java.io.File;

public class CodegenModel {
	//基础包名
	
	//保存路径
	private File savePath;
	
	private String basePkgName = "com.inca.saas.ibs";
	private String name;//controller前缀名
	
	private String entityClassName;
	private String dtlEntityClassName;
	private String docEntityClassName;
	private String docPrefix;
	private String dtlPrefix;
	private String prefix;
	private String docSubsysName;
	private String dtlBaseSql;
	private String docBaseSql;
	

	public String getEntityClassName() {
		return entityClassName;
	}
	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}
	public String getDtlEntityClassName() {
		return dtlEntityClassName;
	}
	public void setDtlEntityClassName(String dtlEntityClassName) {
		this.dtlEntityClassName = dtlEntityClassName;
	}
	public String getDocEntityClassName() {
		return docEntityClassName;
	}
	public void setDocEntityClassName(String docEntityClassName) {
		this.docEntityClassName = docEntityClassName;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getSavePath() {
		return savePath;
	}
	public void setSavePath(File savePath) {
		this.savePath = savePath;
	}
	
	
	
	
}
