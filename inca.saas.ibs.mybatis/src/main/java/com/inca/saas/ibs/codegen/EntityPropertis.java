package com.inca.saas.ibs.codegen;

import java.io.Serializable;

public class EntityPropertis {

	private String title;//标题
	private String name;//字段名
	private String metaType;//字段类型
	private String comp;//控件类型
	private String dbCol = "false";//数据库字段
	private String width = "150px";//宽度
	private String readOnly = "false";//是否只读
	private String hidden = "false";//是否隐藏
	private String advQuery = "false";//高级查询
	private String query ="false";//普通查询
	private String required = "false";//必填
	private String keyword = "false";//关键字过滤
	private String virtual ="false";//虚拟列
	private String convert = "false";//转换器
	
	public String getTitle() {
		return title;
	}
	
	public EntityPropertis setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public EntityPropertis title(String title){
		return setTitle(title);
	}
	
	public String getName() {
		return name;
	}
	public EntityPropertis setName(String name) {
		this.name = name;
		return this;
	}
	
	public EntityPropertis name(String name){
		return setName(name);
	}
	
	public String getMetaType() {
		return metaType;
	}
	public EntityPropertis setMetaType(String metaType) {
		this.metaType = metaType;
		return this;
	}
	
	public EntityPropertis metaType(String metaType){
		return setMetaType(metaType);
	}
	
	public String getComp() {
		return comp;
	}
	public EntityPropertis setComp(String comp) {
		this.comp = comp;
		return this;
	}
	
	public EntityPropertis comp(String comp){
		return setComp(comp);
	}
	
	
	public String getDbCol() {
		return dbCol;
	}
	public EntityPropertis setDbCol(String dbCol) {
		this.dbCol = dbCol;
		return this;
	}
	
	public EntityPropertis dbCol(String dbCol){
		return setDbCol(dbCol);
	}
	
	public String getWidth() {
		return width;
	}
	public EntityPropertis setWidth(String width) {
		this.width = width;
		return this;
	}
	
	public EntityPropertis width(String width){
		return setWidth(width);
	}
	
	public String getReadOnly() {
		return readOnly;
	}
	public EntityPropertis setReadOnly(String readOnly) {
		this.readOnly = readOnly;
		return this;
	}
	
	public EntityPropertis readOnly(String readOnly){
		return setReadOnly(readOnly);
	}
	
	public String getHidden() {
		return hidden;
	}
	public EntityPropertis setHidden(String hidden) {
		this.hidden = hidden;
		return this;
	}
	
	public EntityPropertis hidden(String hidden){
		return setHidden(hidden);
	}
	
	public String getAdvQuery() {
		return advQuery;
	}
	public EntityPropertis setAdvQuery(String advQuery) {
		this.advQuery = advQuery;
		return this;
	}
	
	public EntityPropertis advQuery(String advQuery){
		return setAdvQuery(advQuery);
	}
	
	public String getQuery() {
		return query;
	}
	public EntityPropertis setQuery(String query) {
		this.query = query;
		return this;
	}
	
	public EntityPropertis query(String query){
		return setQuery(query);
	}
	
	public String getRequired() {
		return required;
	}
	public EntityPropertis setRequired(String required) {
		this.required = required;
		return this;
	}
	
	public EntityPropertis required(String required){
		return setRequired(required);
	}
	
	public String getKeyword() {
		return keyword;
	}
	public EntityPropertis setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}
	
	public EntityPropertis keyword(String keyword){
		return setKeyword(keyword);
	}
	
	
	public String getVirtual() {
		return virtual;
	}
	public EntityPropertis setVirtual(String virtual) {
		this.virtual = virtual;
		return this;
	}
	
	public EntityPropertis virtual(String virtual){
		return setVirtual(virtual);
	}
	
	public String getConvert() {
		return convert;
	}
	public EntityPropertis setConvert(String convert) {
		this.convert = convert;
		return this;
	}
	
	public EntityPropertis convert(String convert){
		return setConvert(convert);
	}
	
	private SysOption sysOption;
	private HovOption hovOption;
	
	
	public SysOption getSysOption() {
		return sysOption;
	}

	public EntityPropertis setSysOption(SysOption sysOption) {
		this.sysOption = sysOption;
		return this;
	}
	
	public EntityPropertis sysOption(SysOption sysOption){
		return setSysOption(sysOption);
	}
	
	

	public HovOption getHovOption() {
		return hovOption;
	}

	public EntityPropertis setHovOption(HovOption hovOption) {
		this.hovOption = hovOption;
		return this;
	}
	public EntityPropertis hovOption(HovOption hovOption){
		return setHovOption(hovOption);
	}


	public static class SysOption implements Serializable{
		private String field;
		private String keyword;
		private String scope;
		public String getField() {
			return field;
		}
		public SysOption setField(String field) {
			this.field = field;
			return this;
		}
		
		public SysOption field(String field){
			return setField(field);
		}
		
		public String getKeyword() {
			return keyword;
		}
		public SysOption setKeyword(String keyword) {
			this.keyword = keyword;
			return this;
		}
		public SysOption keyword(String keyword){
			return setKeyword(keyword);
		}
		public String getScope() {
			return scope;
		}
		public SysOption setScope(String scope) {
			this.scope = scope;
			return this;
		}
		
		public SysOption scope(String scope){
			return setScope(scope);
		}
		
	}
	
	public static class HovOption implements Serializable{
		private String field;
		private String url;
		private String scope;
		private String searchUrl;
		private String autoComplete = "false";
		private String hovMapping;//从hov中自动回填有哪些值
		private String dropDown;//下拉显示列名称
		
		
		public String getField() {
			return field;
		}
		public HovOption setField(String field) {
			this.field = field;
			return this;
		}
		
		public HovOption field(String field){
			return setField(field);
		}
		
		public String getUrl() {
			return url;
		}
		public HovOption setUrl(String url) {
			this.url = url;
			return this;
		}
		
		public HovOption url(String url){
			return setUrl(url);
		}
		
		public String getScope() {
			return scope;
		}
		public HovOption setScope(String scope) {
			this.scope = scope;
			return this;
		}
		
		public HovOption scope(String scope){
			return setScope(scope);
		}
		
		public String getSearchUrl() {
			return searchUrl;
		}
		public HovOption setSearchUrl(String searchUrl) {
			this.searchUrl = searchUrl;
			return this;
		}
		
		public HovOption searchUrl(String searchUrl){
			return setSearchUrl(searchUrl);
		}
		
		public String getAutoComplete() {
			return autoComplete;
		}
		public HovOption setAutoComplete(String autoComplete) {
			this.autoComplete = autoComplete;
			return this;
		}
		
		public HovOption autoComplete(String autoComplete){
			return setAutoComplete(autoComplete);
		}
		
		public String getHovMapping() {
			return hovMapping;
		}
		public HovOption setHovMapping(String hovMapping) {
			this.hovMapping = hovMapping;
			return this;
		}
		
		public HovOption hovMapping(String hovMapping){
			return setHovMapping(hovMapping);
		}
		
		public String getDropDown() {
			return dropDown;
		}
		public HovOption setDropDown(String dropDown) {
			this.dropDown = dropDown;
			return this;
		}
		public HovOption dropDown(String dropDown){
			return setDropDown(dropDown);
		}
		
		
	}
	public static class HovBackfill implements Serializable{
		private String form;
		private String to;
		private String name;
		private String title;
		
		public String getForm() {
			return form;
		}
		public HovBackfill setForm(String form) {
			this.form = form;
			return this;
		}
		
		public HovBackfill form(String form){
			return setForm(form);
		}
		
		public String getTo() {
			return to;
		}
		public HovBackfill setTo(String to) {
			this.to = to;
			return this;
		}
		
		public HovBackfill to(String to){
			return setTo(to);
		}
		
		public String getName() {
			return name;
		}
		public HovBackfill setName(String name) {
			this.name = name;
			return this;
		}
		
		public HovBackfill name(String name){
			return setName(name);
		}
		
		public String getTitle() {
			return title;
		}
		public HovBackfill setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public HovBackfill title(String title){
			return setTitle(title);
		}
		
	}
	
}
