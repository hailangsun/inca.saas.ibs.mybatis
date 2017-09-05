package com.inca.saas.ibs.tablesetup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private String headerStyle;
	private String displayField;
	private String name;
	
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
	
	public String getHeader() {
		return header;
	}

	public Column setHeader(String header) {
		this.header = header;
		return this;
	}
	
	public Column header(String header) {
		return setHeader(header);
	}
	
	public Editor getEditor() {
		return editor;
	}
	public Column setEditor(Editor editor) {
		this.editor = editor;
		return this;
	}
	public Column editor(Editor editor) {
		return setEditor(editor);
	}
	
	public String getType() {
		return type;
	}
	public Column setType(String type) {
		this.type = type;
		return this;
	}
	
	public Column type(String type) {
		return setType(type);
	}
	
	public String getDateFormat() {
		return dateFormat;
	}
	public Column setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
		return this;
	}
	
	public Column dateFormat(String dateFormat) {
		return setDateFormat(dateFormat);
	}
	
	public Boolean getAllowSort() {
		return allowSort;
	}
	public Column setAllowSort(Boolean allowSort) {
		this.allowSort = allowSort;
		return this;
	}
	
	public Column allowSort(Boolean allowSort) {
		return setAllowSort(allowSort);
	}

	public Integer getWidth() {
		return width;
	}

	public Column setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Column width(Integer width) {
		return setWidth(width);
	}

	public String getHeaderAlign() {
		return headerAlign;
	}

	public Column setHeaderAlign(String headerAlign) {
		this.headerAlign = headerAlign;
		return this;
	}

	public Column headerAlign(String headerAlign) {
		return setHeaderAlign(headerAlign);
	}
	
	public Boolean getAutoShowPopup() {
		return autoShowPopup;
	}

	public Column setAutoShowPopup(Boolean autoShowPopup) {
		this.autoShowPopup = autoShowPopup;
		return this;
	}
	
	public Column autoShowPopup(Boolean autoShowPopup) {
		return setAutoShowPopup(autoShowPopup);
	}

	public Integer getTrueValue() {
		return trueValue;
	}


	public Column setTrueValue(Integer trueValue) {
		this.trueValue = trueValue;
		return this;
	}
	
	public Column trueValue(Integer trueValue) {
		return setTrueValue(trueValue);
	}


	public Integer getFalseValue() {
		return falseValue;
	}

	public Column setFalseValue(Integer falseValue) {
		this.falseValue = falseValue;
		return this;
	}
	
	public Column falseValue(Integer falseValue) {
		return setFalseValue(falseValue);
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
		private List<Buttons> buttons = new ArrayList<>();
		private Boolean enterQuery = false;
		private List<Columns> columns = new ArrayList<>();
		private String popupWidth ="400";
		private String textField;
		private String valueField;
		private Object onbeforeload;
		private String onload;
		private String name;
		private String style;
		private Object onkeyup;
		private Object onvaluechanged;
		private String cls;
		private String searchField = "keyword";
		
		
		
		public String getSearchField() {
			return searchField;
		}
		public Editor setSearchField(String searchField) {
			this.searchField = searchField;
			return this;
		}
		
		public Editor searchField(String searchField) {
			return setSearchField(searchField);
		}
		
		public String getCls() {
			return cls;
		}
		public Editor setCls(String cls) {
			this.cls = cls;
			return this;
		}
		
		public Editor cls(String cls) {
			return setCls(cls);
		}
		
		public Boolean getEnterQuery() {
			return enterQuery;
		}
		public Editor setEnterQuery(Boolean enterQuery) {
			this.enterQuery = enterQuery;
			return this;
		}
		
		public Editor enterQuery(Boolean enterQuery) {
			return setEnterQuery(enterQuery);
		}
		
		
		public List<Buttons> getButtons() {
			return buttons;
		}
		public void setButtons(List<Buttons> buttons) {
			this.buttons = buttons;
		}
		
		public Editor addButtons(Buttons buttons) {
			if (null == this.buttons) {
				this.buttons = new ArrayList<>();
			}
			this.buttons.add(buttons);
			return this;
		}
		
		
		public String getType() {
			return type;
		}
		public Editor setType(String type) {
			this.type = type;
			return this;
		}
		
		public Editor type(String type) {
			return setType(type);
		}
		
		
		public Integer getMinValue() {
			return minValue;
		}
		public Editor setMinValue(Integer minValue) {
			this.minValue = minValue;
			return this;
		}
		
		public Editor minValue(Integer minValue) {
			return setMinValue(minValue);
		}
		
		public Integer getMaxValue() {
			return maxValue;
		}
		public Editor setMaxValue(Integer maxValue) {
			this.maxValue = maxValue;
			return this;
		}
		public Editor maxValue(Integer maxValue) {
			return setMaxValue(maxValue);
		}
		
		public String getValue() {
			return value;
		}
		public Editor setValue(String value) {
			this.value = value;
			return this;
		}
		
		public Editor value(String value) {
			return setValue(value);
		}
		
		public String getUrl() {
			return url;
		}
		public Editor setUrl(String url) {
			this.url = url;
			return this;
		}
		
		public Editor url(String url) {
			return setUrl(url);
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
		
		public String getPopupWidth() {
			return popupWidth;
		}
		public Editor setPopupWidth(String popupWidth) {
			this.popupWidth = popupWidth;
			return this;
		}
		
		public Editor popupWidth(String popupWidth) {
			return setPopupWidth(popupWidth);
		}
		
		public String getTextField() {
			return textField;
		}
		public Editor setTextField(String textField) {
			this.textField = textField;
			return this;
		}
		
		public Editor textField(String textField) {
			return setTextField(textField);
		}
		
		public String getValueField() {
			return valueField;
		}
		public Editor setValueField(String valueField) {
			this.valueField = valueField;
			return this;
		}
		
		public Editor valueField(String valueField) {
			return setValueField(valueField);
		}
		
		public Object getOnbeforeload() {
			return onbeforeload;
		}
		public Editor setOnbeforeload(Object onbeforeload) {
			this.onbeforeload = onbeforeload;
			return this;
		}
		
		public Editor onbeforeload(Object onbeforeload) {
			return setOnbeforeload(onbeforeload);
		}
		
		public String getOnload() {
			return onload;
		}
		public Editor setOnload(String onload) {
			this.onload = onload;
			return this;
		}
		public Editor onload(String onload) {
			return setOnload(onload);
		}
		public String getName() {
			return name;
		}
		public Editor setName(String name) {
			this.name = name;
			return this;
		}
		
		public Editor name(String name) {
			return setName(onload);
		}
		
		public String getStyle() {
			return style;
		}
		
		public Editor setStyle(String style) {
			this.style = style;
			return this;
		}
		
		public Editor style(String style) {
			return setStyle(style);
		}
		public Object getOnkeyup() {
			return onkeyup;
		}
		
		public Editor setOnkeyup(Object onkeyup) {
			this.onkeyup = onkeyup;
			return this;
		}
		
		public Editor onkeyup(Object onkeyup) {
			return setOnkeyup(onkeyup);
		}
		public Object getOnvaluechanged() {
			return onvaluechanged;
		}
		public Editor setOnvaluechanged(Object onvaluechanged) {
			this.onvaluechanged = onvaluechanged;
			return this;
		}
		
		public Editor onvaluechanged(String onvaluechanged) {
			return setOnvaluechanged(onvaluechanged);
		}
		
		public List<Columns> getColumns() {
			return columns;
		}
		
		public void setColumns(List<Columns> columns) {
			this.columns = columns;
		}
		
		public Editor addColumns(Columns columns) {
			if (null == this.columns) {
				this.columns = new ArrayList<>();
			}
			this.columns.add(columns);
			return this;
		}
		
	}
	
	public static class Buttons implements Serializable {
		private String iconCls = "icon-search";
		private Object handler;
		public Buttons handler(Object handler) {
			return setHandler(handler);
		}
		
		public String getIconCls() {
			return iconCls;
		}
		public void setIconCls(String iconCls) {
			this.iconCls = iconCls;
		}
		public Object getHandler() {
			return handler;
		}
		public Buttons setHandler(Object handler) {
			this.handler = handler;
			return this;
		}
	}
	
	public static class Columns implements Serializable {
		private String header;
		private String field;
		private String headerAlign = "center";
		
		public String getHeader() {
			return header;
		}
		public Columns setHeader(String header) {
			this.header = header;
			return this;
		}
		
		public Columns header(String header) {
			return setHeader(header);
		}
		
		public String getField() {
			return field;
		}
		public Columns setField(String field) {
			this.field = field;
			return this;
		}
		
		public Columns field(String field) {
			return setField(field);
		}
		
		public String getHeaderAlign() {
			return headerAlign;
		}
		public Columns setHeaderAlign(String headerAlign) {
			this.headerAlign = headerAlign;
			return this;
		}
		
		public Columns headerAlign(String headerAlign) {
			return setHeaderAlign(headerAlign);
		}
		
	}

	public String getHeaderStyle() {
		return headerStyle;
	}
	public Column setHeaderStyle(String headerStyle) {
		this.headerStyle = headerStyle;
		return this;
	}
	
	public Column headerStyle(String headerStyle) {
		return setHeaderStyle(headerStyle);
	}
	
	public String getDisplayField() {
		return displayField;
	}
	public Column setDisplayField(String displayField) {
		this.displayField = displayField;
		return this;
	}
	
	public Column displayField(String displayField) {
		return setDisplayField(displayField);
	}
	
	public String getName() {
		return name;
	}
	
	public Column setName(String name) {
		this.name = name;
		return this;
	}
	
	public Column name(String name) {
		return setName(name);
	}
	
}
