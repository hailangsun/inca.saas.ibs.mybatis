package com.inca.saas.ibs.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	 * 设计页面（含页签）
	 * @param model
	 * @param codeGen
	 * @return
	 */
	@RequestMapping({"/design"})
	public String tabs(Model model,@RequestParam String pattern){
		String patternName="";
		if("1".equals(pattern)){
			patternName="单表";
		}else if("2".equals(pattern)){
			patternName="总单";
		}else if("3".equals(pattern)){
			patternName="细单";
		}
		model.addAttribute("patternName", patternName);
		return "ibs/codegen/design";
	}
	
	/**
	 * 表单设计子页面
	 * @param model
	 * @return
	 */
	@RequestMapping({"/form"})
	public String form(Model model){
		//获取实体，根据实体生成，表数据
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
				entityPropertis.metaType(f.getType()+"");
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
	
	//代码生成
	/**
	 * @param docJson 代码生成的基本属性信息
	 * @param prop 下拉hov、按钮信息
	 * @param tableColumns 显示列信息
	 * @return
	 */
	@RequestMapping({"/generateCode"})
	@ResponseBody
	public AjaxMsg generateCode(String docJson,String prop,String tableColumns){
		
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
        //实例化输出格式对象
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置输出编码
        format.setEncoding("UTF-8");
        //创建需要写入的File对象
        File file = new File("H:" + File.separator + "dtlModel.xml");
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
      
		return AjaxMsg.ok();
	}
}
