package com.inca.saas.ibs.gsp.validityReportRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inca.saas.ibs.entity.ValidityReportRecord;
import com.inca.saas.ibs.mapper.ValidityReportRecordMapper;
import com.inca.saas.ibs.support.BaseDao;
import com.inca.saas.ibs.support.Query;
import com.inca.saas.ibs.support.QueryResult;
import com.inca.saas.ibs.support.ServiceManager;

@Controller
@RequestMapping(ValidityReportRecordController.FUNC_PATH)
@SessionAttributes({ ValidityReportRecordController.SESSION_ATTR_QUERY })
public class ValidityReportRecordController{
	final Log log = LogFactory.getLog(getClass());

	public static final String FUNC_PATH = "/IBSGSP347";

	public static final String SESSION_ATTR_QUERY = "rsa_validityReport_Record_query";
	
	@ModelAttribute("funcPath")
	String funcPath() {
		return FUNC_PATH;
	}
	@Autowired
	BaseDao baseDao;
	@Autowired
	ValidityReportRecordMapper validityReportRecordMapper;
	
	
	@RequestMapping({ "/" })
	public String home() throws Exception {
		return "ibs/gsp/validityReportRecord/home";
	}
	
	@RequestMapping(value = "/miniuiSearch")
	@ResponseBody
	public QueryResult miniSearch(Query query, HttpServletResponse response) {
		QueryResult search;
		try {
			String sql ="select * from pub_goods ";
			search = baseDao.search(sql,query);
			return search;
		} catch (Exception e) {
			log.error("search error", e);
			e.printStackTrace();
			return new QueryResult<>("search error");
		}
	}
}
