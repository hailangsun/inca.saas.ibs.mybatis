package com.inca.saas.ibs.tablesetup;

import java.io.Serializable;

public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4600551134002050679L;

	private String field;
	private String dateFormat;
	private Boolean allowSort = true;
	private String header;
	private Integer width;
	private String headerAlign = "center";
	private Boolean autoShowPopup;
	private Integer trueValue;
	private Integer falseValue;
	private String type;
	
	private Editor editor;
	
	public String getField() {
		return field;
	}
	public Column setField(String field) {
		this.field = field;
		return this;
	}
	public Column(){
		
	}
	
	public Column field(String field) {
		return setField(field);
	}

	public Editor getEditor() {
		return editor;
	}
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public Boolean getAllowSort() {
		return allowSort;
	}
	public void setAllowSort(Boolean allowSort) {
		this.allowSort = allowSort;
	}

	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}


	public String getHeaderAlign() {
		return headerAlign;
	}

	public void setHeaderAlign(String headerAlign) {
		this.headerAlign = headerAlign;
	}

	public Boolean getAutoShowPopup() {
		return autoShowPopup;
	}

	public void setAutoShowPopup(Boolean autoShowPopup) {
		this.autoShowPopup = autoShowPopup;
	}

	public Integer getTrueValue() {
		return trueValue;
	}


	public void setTrueValue(Integer trueValue) {
		this.trueValue = trueValue;
	}


	public Integer getFalseValue() {
		return falseValue;
	}

	public void setFalseValue(Integer falseValue) {
		this.falseValue = falseValue;
	}

	public Column(String field, String dateFormat, Boolean allowSort, String header, Integer width, String headerAlign,
			Boolean autoShowPopup, Integer trueValue, Integer falseValue, String type) {
		super();
		this.field = field;
		this.dateFormat = dateFormat;
		this.allowSort = allowSort;
		this.header = header;
		this.width = width;
		this.headerAlign = headerAlign;
		this.autoShowPopup = autoShowPopup;
		this.trueValue = trueValue;
		this.falseValue = falseValue;
		this.type = type;
	}



	public static class Editor implements Serializable {
		private String type;
		private Integer minValue;
		private Integer maxValue;
		private String value;
		private String url;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Integer getMinValue() {
			return minValue;
		}
		public void setMinValue(Integer minValue) {
			this.minValue = minValue;
		}
		public Integer getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(Integer maxValue) {
			this.maxValue = maxValue;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Editor(String type, Integer minValue, Integer maxValue, String value, String url) {
			super();
			this.type = type;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.value = value;
			this.url = url;
		}
		public Editor(){
			
		}
		
		
		
	}

}
