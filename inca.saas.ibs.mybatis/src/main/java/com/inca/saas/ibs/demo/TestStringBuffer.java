package com.inca.saas.ibs.demo;

public class TestStringBuffer {
	
	public static void main(String[] args) {
		getStringBuffer01();
	}
	
	/**
	 * 演示StringBuffer %s 用法
	 * 
	 */
	public static void getStringBuffer01(){
		Integer fieldName = 4;
		Integer value = 5;
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(" and %s>=%s", new Object[] {
                fieldName, value
            }));
		System.out.println(sb.toString());
	}
}
