package com.inca.saas.ibs.common;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping({ "/miniStopBills" })
	public String stopDocument(String docDtlFlag,String ids,Integer docId ,String funPath, Model model, HttpSession session) {
		model.addAttribute("docDtlFlag", docDtlFlag);
		model.addAttribute("ids", ids);
		model.addAttribute("docId", docId);
		model.addAttribute("funPath", funPath.substring(1, funPath.length()));
		return "common/miniStopReason";
	}
	
	@RequestMapping({"/advQueryHome"})
	public String advQueryHome(){
		
		return "ibs/common/advQueryHome";
	}
}
