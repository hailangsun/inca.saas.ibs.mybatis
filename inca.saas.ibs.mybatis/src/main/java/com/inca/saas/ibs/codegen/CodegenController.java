package com.inca.saas.ibs.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inca.saas.ibs.codegen.EntityPropertis.SysOption;
import com.inca.saas.ibs.common.Option;
import com.inca.saas.ibs.common.Title;

@Controller
@RequestMapping(CodegenController.FUNC_PATH)
public class CodegenController {
	final Log log = LogFactory.getLog(getClass());

	public static final String FUNC_PATH = "/miniCodegen";
	/**
	 * 代码生成首页（含页签）
	 * @param model
	 * @return
	 */
	@RequestMapping({"/"})
	public String home(Model model){
		return "ibs/codegen/home";
	}
	/**
	 * 单表代码生成子页面
	 * @param model
	 * @return
	 */
	@RequestMapping({"/ste"})
	public String ste(Model model){
		return "ibs/codegen/ste";
	}
	/**
	 * 总单细目代码生成子页面
	 * @param model
	 * @return
	 */
	@RequestMapping({"/mde"})
	public String mde(Model model){
		return "ibs/codegen/mde";
	}
	/**
	 * 总单设计页面（含页签）
	 * @param model
	 * @param codeGen
	 * @return
	 */
	@RequestMapping({"/mdeDesign"})
	public String mdeDesign(Model model){
		return "ibs/codegen/mdeDesign";
	}
	
	/**
	 * 单表设计页面（含页签）
	 * @param model
	 * @param codeGen
	 * @return
	 */
	@RequestMapping({"/design"})
	public String tabs(Model model){
		return "ibs/codegen/design";
	}
	
	/**
	 * 表单设计子页面
	 * @param formStatus 1是总单 2是细单
	 * @param model
	 * @return
	 */
	@RequestMapping({"/form"})
	public String form(Model model,@RequestParam String formStatus){
		//获取实体，根据实体生成，表数据
		model.addAttribute("formStatus",formStatus);
		return "ibs/codegen/form";
	}
	/**
	 * 表格设计子页面
	 * @param model
	 * @return
	 */
	@RequestMapping({"/table"})
	public String table(Model model){
		return "ibs/codegen/table";
	}
	
	//数据库类型
	private String getClassType(String type){
		if("class java.lang.String".equals(type)){
			return "java.lang.String";
		}else if("class java.lang.Integer".equals(type)){
			return "java.lang.Integer";
		}else if("class java.lang.Boolean".equals(type)){
			return "java.lang.Boolean";
		}else if("class java.util.Date".equals(type)){
			return "java.util.Date";
		}else if("class java.math.BigDecimal".equals(type)){
			return "java.math.BigDecimal";
		}
		return null;
	}
	
	
	@RequestMapping(value = "/miniuiSearch")
	@ResponseBody
	public Map<String, Object> miniSearch(String entityName,String sql) {
		Map<String, Object> map = new HashMap<>();
		List<CodegenTableColumn> columns = new ArrayList<>();
//		QueryResult search = new QueryResult<>();
		try {
			List<EntityPropertis> ListMap = new ArrayList<>();
			Object ob = getEntity(entityName);//"com.inca.saas.ibs.entity.Goods"
			java.lang.reflect.Field[] fields = ((Class<? extends CodegenController>) ob).getDeclaredFields();  
			for (java.lang.reflect.Field f:fields) {
				CodegenTableColumn codegenTableColumn = new CodegenTableColumn();
				EntityPropertis entityPropertis = new EntityPropertis();
				Title title = f.getAnnotation(Title.class);
				Column column = f.getAnnotation(Column.class);
				Option option = f.getAnnotation(Option.class);
				entityPropertis.title(title.value());
				entityPropertis.name(column.name());
				String classType = getClassType(f.getType()+"");
				entityPropertis.metaType(classType);//设置类型
//				entityPropertis.comp(classType);//控件类型
				if(option != null){
					entityPropertis.sysOption(new SysOption().field(column.name()).keyword(option.name()));
				}
				ListMap.add(entityPropertis);
				//
				codegenTableColumn.setField(column.name());
				codegenTableColumn.setHeader(title.value());
				columns.add(codegenTableColumn);
			}
			map.put("data", ListMap);
			Map<String, Object> tableMap = new HashMap<>();
			tableMap.put("columns", columns);
			map.put("codegenTableColumn", tableMap);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public Class<?> getEntity(String entityClassName) throws ClassNotFoundException{
		 Class<?> forName = Class.forName(entityClassName);
		 return forName;
	}
	
	//获取hov默认回填
	@RequestMapping({"/hovMapping"})
	public String hovMapping(Model model){
		return "ibs/codegen/hovMapping";
	}
	
	
	//获取hov默认回填
	@RequestMapping({"/addButton"})
	public String addButton(Model model){
		return "ibs/codegen/addButton";
	}
	
	//获取hov下拉显示
	@RequestMapping({"/dropDown"})
	public String onDropDown(Model model){
		return "ibs/codegen/dropDown";
	}
	
	private void createXml(String type,String currentData,String getData,File dir){
		String entity = "";
		String title = "";
		String docOrDtl = "";
		//com.inca.saas.ibs.common.Title
		JSONObject getDataStr = JSONObject.parseObject(getData);
		if("0".equals(type)){
			entity = (String) getDataStr.get("entityName");
			docOrDtl = "model";
		}else if("1".equals(type)){
			entity = (String) getDataStr.get("docEntityName");
			docOrDtl = "docModel";
		}else if("2".equals(type)){
			entity = (String) getDataStr.get("dtlEntityName");
			docOrDtl = "dtlModel";
		}
		
		Document doc = DocumentHelper.createDocument();
        //增加根节点
        Element model = doc.addElement("model");
        model.addAttribute("title", title);
        model.addAttribute("entity", entity);
        //添加元素
        Element form = model.addElement("form");
        Element table = model.addElement("table");
        Element comps = model.addElement("comps");
        Element buttons = model.addElement("buttons");
		
		JSONObject currentDataObject = JSONObject.parseObject(currentData);
		
		String data = currentDataObject.getString("dataJson");//总数
		String prop = currentDataObject.getString("prop");//hov、下拉显示、状态等
		String tableColumns = currentDataObject.getString("tableColumns");//显示列
		
		JSONArray jsonArray = JSONArray.parseArray(data);
		JSONArray jsonTableColumnsArray = JSONArray.parseArray(tableColumns);
		JSONArray propAll = JSONArray.parseArray(prop);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject dataJ = jsonArray.getJSONObject(i);
			 //添加基本元素
	    	 Element field = form.addElement("field");
	    	 field.addAttribute("name", dataJ.getString("name"));
	    	 field.addAttribute("title", dataJ.getString("title"));
	    	 field.addAttribute("metaType", dataJ.getString("metaType"));
	    	 field.addAttribute("width", "150px");
	    	 field.addAttribute("hidden", dataJ.getString("hidden"));
	    	 field.addAttribute("comp", dataJ.getString("comp"));
	    	 field.addAttribute("dbCol", dataJ.getString("dbCol"));
	    	 field.addAttribute("readOnly", dataJ.getString("readOnly"));
	    	 field.addAttribute("advQuery", dataJ.getString("advQuery"));
	    	 field.addAttribute("query", dataJ.getString("query"));
	    	 field.addAttribute("required", dataJ.getString("required"));
	    	 field.addAttribute("keyword", dataJ.getString("keyword"));
	    	 field.addAttribute("virtual", dataJ.getString("virtual"));
	    	 field.addAttribute("convert", dataJ.getString("convert"));
	    	 String sysOptionStr = dataJ.getString("sysOption");
	    	 if(!"".equals(sysOptionStr) && sysOptionStr != null){
	    		 JSONObject sysOption =  JSONObject.parseObject(sysOptionStr);
	    		 //系统下拉选项
	    		 if(!"".equals(sysOption.getString("field")) && sysOption.getString("field") != null){
	    			 Element combobox = comps.addElement("combobox");
	    			 combobox.addAttribute("field", sysOption.getString("field"));
	    			 combobox.addAttribute("keyword", sysOption.getString("keyword"));
	    		 }
	    	 }
		}
		
		//hov循环
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject dataJ = jsonArray.getJSONObject(i);
			String hovOptionStr = dataJ.getString("hovOption");
		 	 //是否为hov
	    	 if(dataJ.getString("isHov") != null && "true".equals(dataJ.getString("isHov"))){
	    		 if(!"".equals(hovOptionStr) && hovOptionStr != null){
	    			 JSONObject hovOption =  JSONObject.parseObject(hovOptionStr);
	    			 Element hov = comps.addElement("hov");
	    			 hov.addAttribute("field", dataJ.getString("name"));
	    			 hov.addAttribute("url", hovOption.getString("url"));
	    			 hov.addAttribute("scope", hovOption.getString("scope"));
	    			 
	    			 //遍历hovMapping属性回填
	   				for (int j = 0; j < propAll.size(); j++) {
	   					JSONObject propJ = propAll.getJSONObject(j);
	   					if(propJ.get("field") != null && propJ.get("field").equals(dataJ.getString("name"))){
	   						if("howMapping".equals(propJ.get("flag"))){
	   							JSONArray howMappingArray = JSONArray.parseArray(propJ.get("howMappingData")+"");
	   							for (int k = 0; k < howMappingArray.size(); k++) {
	   									JSONObject 	howMappingJ = howMappingArray.getJSONObject(k);
								 	    	Element hovMapping = hov.addElement("hovMapping");
								 	    	hovMapping.addAttribute("from", howMappingJ.getString("from"));
								 	    	hovMapping.addAttribute("to", howMappingJ.getString("to"));
									}
	   						}
	   					}
	   				}
	    			 
	    			 if(hovOption.getString("autoComplete") != null && "true".equals(hovOption.getString("autoComplete"))){
	    				 hov.addAttribute("autoComplete", hovOption.getString("autoComplete"));
	    				 hov.addAttribute("searchUrl", hovOption.getString("searchUrl"));
	    				 //遍历hov下拉显示
	    				 for (int j = 0; j < propAll.size(); j++) {
	    					 JSONObject propJ = propAll.getJSONObject(j);
	    					 if(propJ.get("field") != null && propJ.get("field").equals(dataJ.getString("name"))){
	    						 if("dropDown".equals(propJ.get("flag"))){
	    							 JSONArray howMappingArray = JSONArray.parseArray(propJ.get("howDropDownData")+"");
	    							 for (int k = 0; k < howMappingArray.size(); k++) {
	    								 JSONObject 	howMappingJ = howMappingArray.getJSONObject(k);
	    								 Element hovMapping = hov.addElement("field");
	    								 hovMapping.addAttribute("name", howMappingJ.getString("name"));
	    								 hovMapping.addAttribute("title", howMappingJ.getString("title"));
	    							 }
	    						 }
	    					 }
	    				 }
	    			 }
	    			
   				
	    		 }
	    	 }
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < jsonTableColumnsArray.size(); i++) {
			JSONObject columnsJ = jsonTableColumnsArray.getJSONObject(i);
			sb.append(columnsJ.get("field")+",");
		}
        //添加显示
        table.setText(sb.substring(0, sb.length()-1));
       
	    //添加按钮
        for (int i = 0; i < propAll.size(); i++) {
			JSONObject propJ = propAll.getJSONObject(i);
			if(!"".equals(propJ.get("sysButton")) && propJ.get("sysButton") != null){
				JSONArray buttonArray = JSONArray.parseArray(propJ.get("sysButton")+"");
				for (int k = 0; k < buttonArray.size(); k++) {
					JSONObject 	buttonJ = buttonArray.getJSONObject(k);
					Element button = buttons.addElement("button");
					button.addAttribute("onClick", buttonJ.getString("onClick"));
					button.addAttribute("scope", buttonJ.getString("scope"));
					button.addText(buttonJ.getString("clickName"));
				}
			}
		}
        
        //实例化输出格式对象
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置输出编码
        format.setEncoding("UTF-8");
		File file = new File(dir, docOrDtl+".xml");
        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
        XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			  //开始写入，write方法中包含上面创建的Document对象
	        writer.write(doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//代码生成
	/**
	 * @param currentData 单表
	 * @param dtlData 细单
	 * @param docData 总单
	 * @param getData 名称、路径、sql
	 * @return
	 */
	@RequestMapping({"/generateCode"})
	@ResponseBody
	public AjaxMsg generateCode(String currentData,String dtlData,String docData,String getData,String type){
		
		//生成两个目录，一个是java 一个是html
		
		String entity = "";
		String title = "";
		JSONObject getDataStr = JSONObject.parseObject(getData);
		if("0".equals(type)){
			entity = (String) getDataStr.get("entityName");
		}else if("1".equals(type)){
			entity = (String) getDataStr.get("docEntityName");
		}else if("2".equals(type)){
			entity = (String) getDataStr.get("dtlEntityName");
		}
		String entityPath = "com.inca.saas.ibs";
		
		//创建需要写入的File对象
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path.substring(1, path.length());
        path = path.replace("bin/", "");
        //File file = new File(path+"src"+ File.separator +"gen"+File.separator+"sample"+File.separator+"java"+File.separator+docOrDtl+".xml");
        String[] subEntity = entity.split("\\.");
		entityPath+="."+subEntity[subEntity.length - 2];
		entityPath+="."+subEntity[subEntity.length - 1];
		title = subEntity[subEntity.length - 1];
		
        String saveDir = path+"src"+ File.separator +"gen"+File.separator+"sample"+File.separator;//实际路径
        File dir = new File(saveDir, "java");
		dir = new File(dir, entityPath.replace(".", File.separator));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		if(!"null".equals(currentData) && currentData != null){
			type = "0";
			createXml(type,currentData,getData,dir);
		}
		
		if(!"null".equals(docData) && docData != null){
			type = "1";
			createXml(type,docData,getData,dir);
		}
		
		if(!"null".equals(dtlData) && dtlData != null){
			type ="2";
			createXml(type,dtlData,getData,dir);
		}
		

		CodegenModel codegenModel = new CodegenModel();
		codegenModel.setSavePath(dir);//保存路径
		
		codegenModel.setDocBaseSql("select * from pub_goods");
		codegenModel.setName(title);
		
		try {
			getCreateJava(codegenModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AjaxMsg.ok();
	}
	
	//生成java 
	public void getCreateJava(CodegenModel codegenModel) throws Exception{
		Map<String,String> mdeMap = new HashMap<>();
		mdeMap.put("Controller", "controller.vm");
		mdeMap.put("Convert", "convert.vm");
		mdeMap.put("DocService", "docService.vm");
		mdeMap.put("DocServiceImpl", "docServiceImpl.vm");
		mdeMap.put("DtlService", "dtlService.vm");
		mdeMap.put("DtlServiceImpl", "dtlServiceImpl.vm");
		
		
		
		VelocityEngine ve = new VelocityEngine();
		
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		Template template = new Template();
		VelocityContext context = new VelocityContext();
		StringWriter sw = new StringWriter();
		for (Entry<String, String> map : mdeMap.entrySet()) {
			template = ve.getTemplate("com/inca/saas/ibs/vms/mde/"+map.getValue());
			context.put("model", codegenModel);
			
			template.merge(context, sw);
			String text = sw.toString();
			FileWriter writer;
			try {
				sw.close();
				writer = new FileWriter(new File(codegenModel.getSavePath(),codegenModel.getName() +map.getKey()+".java"));
				writer.write(text);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
//	Document doc = DocumentHelper.createDocument();
//    //增加根节点
//    Element model = doc.addElement("model");
//    model.addAttribute("title", "采购计划");
//    model.addAttribute("entity", "com.inca.saas.ibs.entity.pur.SuPlanDtl");
//    //添加元素
//    Element form = model.addElement("form");
//    Element table = model.addElement("table");
//    Element comps = model.addElement("comps");
//    Element buttons = model.addElement("buttons");
//	
//	JSONObject currentDataObject = JSONObject.parseObject(currentData);
//	
////	JSONObject dtlDataObject = JSONObject.parseObject(dtlData);
////	JSONObject docDataObject = JSONObject.parseObject(docData);
//	
//	
//	String data = currentDataObject.getString("dataJson");//总数
//	String prop = currentDataObject.getString("prop");//hov、下拉显示、状态等
//	String tableColumns = currentDataObject.getString("tableColumns");//显示列
//	
//	JSONArray jsonArray = JSONArray.parseArray(data);
//	JSONArray jsonTableColumnsArray = JSONArray.parseArray(tableColumns);
//	JSONArray propAll = JSONArray.parseArray(prop);
//	for (int i = 0; i < jsonArray.size(); i++) {
//		JSONObject dataJ = jsonArray.getJSONObject(i);
//		 //添加基本元素
//    	 Element field = form.addElement("field");
//    	 field.addAttribute("name", dataJ.getString("name"));
//    	 field.addAttribute("title", dataJ.getString("title"));
//    	 field.addAttribute("metaType", dataJ.getString("metaType"));
//    	 field.addAttribute("width", "150px");
//    	 field.addAttribute("hidden", dataJ.getString("hidden"));
//    	 field.addAttribute("comp", dataJ.getString("comp"));
//    	 field.addAttribute("dbCol", dataJ.getString("dbCol"));
//    	 field.addAttribute("readOnly", dataJ.getString("readOnly"));
//    	 field.addAttribute("advQuery", dataJ.getString("advQuery"));
//    	 field.addAttribute("query", dataJ.getString("query"));
//    	 field.addAttribute("required", dataJ.getString("required"));
//    	 field.addAttribute("keyword", dataJ.getString("keyword"));
//    	 field.addAttribute("virtual", dataJ.getString("virtual"));
//    	 field.addAttribute("convert", dataJ.getString("convert"));
//    	 String sysOptionStr = dataJ.getString("sysOption");
//    	 if(!"".equals(sysOptionStr) && sysOptionStr != null){
//    		 JSONObject sysOption =  JSONObject.parseObject(sysOptionStr);
//    		 //系统下拉选项
//    		 if(!"".equals(sysOption.getString("field")) && sysOption.getString("field") != null){
//    			 Element combobox = comps.addElement("combobox");
//    			 combobox.addAttribute("field", sysOption.getString("field"));
//    			 combobox.addAttribute("keyword", sysOption.getString("keyword"));
//    		 }
//    	 }
//	}
//	
//	//hov循环
//	for (int i = 0; i < jsonArray.size(); i++) {
//		JSONObject dataJ = jsonArray.getJSONObject(i);
//		String hovOptionStr = dataJ.getString("hovOption");
//	 	 //是否为hov
//    	 if(dataJ.getString("isHov") != null && "true".equals(dataJ.getString("isHov"))){
//    		 if(!"".equals(hovOptionStr) && hovOptionStr != null){
//    			 JSONObject hovOption =  JSONObject.parseObject(hovOptionStr);
//    			 Element hov = comps.addElement("hov");
//    			 hov.addAttribute("field", dataJ.getString("name"));
//    			 hov.addAttribute("url", hovOption.getString("url"));
//    			 hov.addAttribute("scope", hovOption.getString("scope"));
//    			 
//    			 //遍历hovMapping属性回填
//   				for (int j = 0; j < propAll.size(); j++) {
//   					JSONObject propJ = propAll.getJSONObject(j);
//   					if(propJ.get("field") != null && propJ.get("field").equals(dataJ.getString("name"))){
//   						if("howMapping".equals(propJ.get("flag"))){
//   							JSONArray howMappingArray = JSONArray.parseArray(propJ.get("howMappingData")+"");
//   							for (int k = 0; k < howMappingArray.size(); k++) {
//   									JSONObject 	howMappingJ = howMappingArray.getJSONObject(k);
//							 	    	Element hovMapping = hov.addElement("hovMapping");
//							 	    	hovMapping.addAttribute("from", howMappingJ.getString("from"));
//							 	    	hovMapping.addAttribute("to", howMappingJ.getString("to"));
//								}
//   						}
//   					}
//   				}
//    			 
//    			 if(hovOption.getString("autoComplete") != null && "true".equals(hovOption.getString("autoComplete"))){
//    				 hov.addAttribute("autoComplete", hovOption.getString("autoComplete"));
//    				 hov.addAttribute("searchUrl", hovOption.getString("searchUrl"));
//    				 //遍历hov下拉显示
//    				 for (int j = 0; j < propAll.size(); j++) {
//    					 JSONObject propJ = propAll.getJSONObject(j);
//    					 if(propJ.get("field") != null && propJ.get("field").equals(dataJ.getString("name"))){
//    						 if("dropDown".equals(propJ.get("flag"))){
//    							 JSONArray howMappingArray = JSONArray.parseArray(propJ.get("howDropDownData")+"");
//    							 for (int k = 0; k < howMappingArray.size(); k++) {
//    								 JSONObject 	howMappingJ = howMappingArray.getJSONObject(k);
//    								 Element hovMapping = hov.addElement("field");
//    								 hovMapping.addAttribute("name", howMappingJ.getString("name"));
//    								 hovMapping.addAttribute("title", howMappingJ.getString("title"));
//    							 }
//    						 }
//    					 }
//    				 }
//    			 }
//    			
//				
//    		 }
//    	 }
//	}
//	
//	StringBuffer sb = new StringBuffer();
//	for (int i = 0; i < jsonTableColumnsArray.size(); i++) {
//		JSONObject columnsJ = jsonTableColumnsArray.getJSONObject(i);
//		sb.append(columnsJ.get("field")+",");
//	}
//    //添加显示
//    table.setText(sb.substring(0, sb.length()-1));
//   
//    //添加按钮
//    for (int i = 0; i < propAll.size(); i++) {
//		JSONObject propJ = propAll.getJSONObject(i);
//		if(!"".equals(propJ.get("sysButton")) && propJ.get("sysButton") != null){
//			JSONArray buttonArray = JSONArray.parseArray(propJ.get("sysButton")+"");
//			for (int k = 0; k < buttonArray.size(); k++) {
//				JSONObject 	buttonJ = buttonArray.getJSONObject(k);
//				Element button = buttons.addElement("button");
//				button.addAttribute("onClick", buttonJ.getString("onClick"));
//				button.addAttribute("scope", buttonJ.getString("scope"));
//				button.addText(buttonJ.getString("clickName"));
//			}
//		}
//	}
//    
//    //实例化输出格式对象
//    OutputFormat format = OutputFormat.createPrettyPrint();
//    //设置输出编码
//    format.setEncoding("UTF-8");
//    //创建需要写入的File对象
//    File file = new File("E:" + File.separator + "dtlModel.xml");
//    //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
//    XMLWriter writer;
//	try {
//		writer = new XMLWriter(new FileOutputStream(file), format);
//		  //开始写入，write方法中包含上面创建的Document对象
//        writer.write(doc);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
}
