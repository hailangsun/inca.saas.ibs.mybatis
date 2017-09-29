package com.inca.saas.ibs.codegen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String ste(Model model,@RequestParam String pattern){
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
	public String tabs(Model model,SteCodeGen codeGen){
		System.out.println(codeGen);
		//获取实体，根据实体生成，表数据
		//
		model.addAttribute("packageName", codeGen.getPackageName());
		return "ibs/codegen/design";
	}
	/**
	 * 表单设计子页面
	 * @param model
	 * @return
	 */
	@RequestMapping({"/form"})
	public String form(Model model){
		
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
	
	
}
