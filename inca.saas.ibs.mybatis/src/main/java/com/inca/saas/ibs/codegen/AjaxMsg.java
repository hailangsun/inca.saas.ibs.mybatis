package com.inca.saas.ibs.codegen;

public class AjaxMsg implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean ok;
	private String msg;
	private Object data;
	
	public AjaxMsg(){
		
	}
	
	public AjaxMsg(boolean ok){
		this.ok=ok;
	}
	
	public AjaxMsg(boolean ok, Object data) {
		this.ok=ok;
		this.data=data;
	}
	public boolean isOk() {
		return ok;
	}
	public AjaxMsg setOk(boolean ok) {
		this.ok = ok;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public AjaxMsg setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public Object getData() {
		return data;
	}
	public AjaxMsg setData(Object data) {
		this.data = data;
		return this;
	}
	
	public static AjaxMsg ok(String msg) {
		AjaxMsg ajaxMsg = new AjaxMsg(true).setMsg(msg);
		return ajaxMsg;
	}
	
	public static AjaxMsg ok() {
		AjaxMsg ajaxMsg = new AjaxMsg(true);
		return ajaxMsg;
	}
	
	public static AjaxMsg error(String msg) {
		AjaxMsg ajaxMsg = new AjaxMsg(false).setMsg(msg);
		return ajaxMsg;
	}
	
	 
}
