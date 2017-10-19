package com.inca.saas.ibs.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
public class TestXML {
	public static void main(String[] args) throws IOException {
		 	Document doc = DocumentHelper.createDocument();
	        //增加根节点
	        Element model = doc.addElement("model");
	        model.addAttribute("title", "采购计划");
	        model.addAttribute("entity", "com.inca.saas.ibs.entity.pur.SuPlanDtl");
	        //添加元素
	        Element form = model.addElement("form");
	        Element table = model.addElement("table");
	        Element comps = model.addElement("comps");
	        Element buttons = model.addElement("buttons");
	        
	        //添加基本元素
	        for (int i = 0; i < 5; i++) {
	        	 Element field = form.addElement("field");
	        	 field.addAttribute("name", "id"+i);
	        	 field.addAttribute("title", "单据ID"+i);
	        	 field.addAttribute("metaType", "java.lang.Integer"+i);
	        	 field.addAttribute("width", "150px");
	        	 field.addAttribute("hidden", "true");
			}
	        //添加显示
	        table.setText("goods_code,goods_incode,goods_name");
	       
	        //添加下拉选项
		    for (int i = 0; i < 2; i++) {
		    	 Element combobox = comps.addElement("combobox");
		    	 combobox.addAttribute("field", "come_from"+i);
		    	 combobox.addAttribute("keyword", "IBS_DOCUMENT_COME_FROM"+i);
		    }
		    
		    //添加hov
		    for (int i = 0; i < 3; i++) {
		    	Element hov = comps.addElement("hov");
		    	hov.addAttribute("field", "supply_name"+i);
		    	hov.addAttribute("url", "/hov/IBSPUB008/table/miniuihome/miniuiHovSearch"+i);
		    	hov.addAttribute("scope", "表格,表单,查询,高级查询");
		    	Element hovMapping = hov.addElement("hovMapping");
		    	hovMapping.addAttribute("from", "id"+i);
		    	hovMapping.addAttribute("to", "supply_id"+i);
			}
		    
		    //添加按钮
		    for (int i = 0; i < 3; i++) {
		    	Element button = buttons.addElement("button");
		    	button.addAttribute("onClick", "dtlDoStop"+i);
		    	button.addText("终止"+i);
			}
//	        //实例化输出格式对象
//	        OutputFormat format = OutputFormat.createPrettyPrint();
//	        //设置输出编码
//	        format.setEncoding("UTF-8");
//	        //创建需要写入的File对象
//	        File file = new File("H:" + File.separator + "dtlModel.xml");
//	        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
//	        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
//	        //开始写入，write方法中包含上面创建的Document对象
//	        writer.write(doc);
//	        
		    //实例化输出格式对象
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        //创建需要写入的File对象
	        File file = new File("D:" + File.separator + "dtlModel.html");
	        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
	        HTMLWriter writer = new HTMLWriter(new FileOutputStream(file), format);
	        //开始写入，write方法中包含上面创建的Document对象
	        writer.write(doc);
	        
	}
}
