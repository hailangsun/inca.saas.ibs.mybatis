package com.inca.saas.ibs.demo;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TestVelocity {
	public static void main(String[] args) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		ve.init();

		Template t = ve.getTemplate("com/inca/saas/ibs/vms/controller.vm");
		VelocityContext ctx = new VelocityContext();

		ctx.put("name", "velocity");
		ctx.put("date", (new Date()).toString());

		List temp = new ArrayList();
		temp.add("1");
		temp.add("2");
		ctx.put("list", temp);

		StringWriter sw = new StringWriter();

		t.merge(ctx, sw);

		System.out.println(sw.toString());
	}
}
