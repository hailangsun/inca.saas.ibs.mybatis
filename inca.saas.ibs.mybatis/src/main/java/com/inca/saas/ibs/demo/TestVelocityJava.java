package com.inca.saas.ibs.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TestVelocityJava {
	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		String name = "DDDDD";
		Template template = ve.getTemplate("com/inca/saas/ibs/vms/controller.vm");
		VelocityContext context = new VelocityContext();
		context.put("model", new Demo());
		StringWriter sw = new StringWriter();
		File saveDir = new File("E:" ,name +"Controller.java");
				
		
		template.merge(context, sw);
		String text = sw.toString();
		FileWriter writer;
		try {
			sw.close();
			writer = new FileWriter(saveDir);
			writer.write(text);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//println "generate: ${file.absolutePath}"
		
	}
	
	public static class Demo{
		String pkgName = "com.inca.saas.ibs";
		String className ="DEMO";
		String funcPath ="TEST";
		String explain = "用法:";
		public String getPkgName() {
			return pkgName;
		}
		public void setPkgName(String pkgName) {
			this.pkgName = pkgName;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getFuncPath() {
			return funcPath;
		}
		public void setFuncPath(String funcPath) {
			this.funcPath = funcPath;
		}
		public String getExplain() {
			return explain;
		}
		public void setExplain(String explain) {
			this.explain = explain;
		}
		
		
		
	}
}
