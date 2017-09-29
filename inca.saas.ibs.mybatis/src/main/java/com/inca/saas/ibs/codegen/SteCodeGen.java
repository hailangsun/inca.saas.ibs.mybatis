package com.inca.saas.ibs.codegen;

public class SteCodeGen {
	private String packageName;
	private String functionName;
	private String entityName;
	private String baseSql;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getBaseSql() {
		return baseSql;
	}
	public void setBaseSql(String baseSql) {
		this.baseSql = baseSql;
	}
	@Override
	public String toString() {
		return "SteCodeGen [packageName=" + packageName + ", functionName=" + functionName + ", entityName="
				+ entityName + ", baseSql=" + baseSql + "]";
	}
	
}
