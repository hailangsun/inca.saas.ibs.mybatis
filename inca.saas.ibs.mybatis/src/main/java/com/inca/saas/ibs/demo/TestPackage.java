package com.inca.saas.ibs.demo;

import java.io.File;

public class TestPackage {

	public static void main(String[] args) {
		String packageName = "com.inca.saas.ibs.entity.pur.SuPlanDtl";
		String[] ss = packageName.split("\\.");
		String subsysName = ss[ss.length - 1];//系统包路径
		String pu = ss[ss.length - 2];
		System.out.println("subsysName:"+subsysName);
		System.out.println("subsysName:"+pu);
		System.out.println(File.separator);
	}
}
