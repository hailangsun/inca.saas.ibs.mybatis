package com.inca.saas.ibs.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;

import com.inca.saas.ibs.codegen.CodegenController;

public class TestParentFields {
	public static void main(String[] args) {
		List<Field> fieldList = new ArrayList<>() ;
		try {
			Object ob = getEntity("com.inca.saas.ibs.demo.Children");
			java.lang.reflect.Field[] fields = ((Class<? extends CodegenController>) ob).getDeclaredFields();  
			java.lang.reflect.Field[] ss = ((Class<? extends CodegenController>) ob).getSuperclass().getDeclaredFields();
			for (java.lang.reflect.Field f:fields) {
				System.out.println(f.getName());
			}
			for (java.lang.reflect.Field f:ss) {
				System.out.println(f.getName());
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		     
	}
	
	
	public static Class<?> getEntity(String entityClassName) throws ClassNotFoundException{
		 Class<?> forName = Class.forName(entityClassName);
		 return forName;
	}
}
