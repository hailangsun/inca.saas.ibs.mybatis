package com.inca.saas.ibs.demo;

public class TestPlaceholder {
	public static void main(String[] args) {
		String s = "s";
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%s.valueOf(5)",s));
		
		System.out.println(sb.toString());
	}
}
