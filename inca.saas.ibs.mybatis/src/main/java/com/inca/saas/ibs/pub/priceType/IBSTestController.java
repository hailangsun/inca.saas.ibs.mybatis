package com.inca.saas.ibs.pub.priceType;


import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inca.saas.ibs.support.BaseDao;
import com.inca.saas.ibs.support.Query;
import com.inca.saas.ibs.support.QueryResult;

@Controller
@RequestMapping(IBSTestController.FUNC_PATH)
@SessionAttributes({IBSTestController.SESSION_ATTR_QUERY })
public class IBSTestController{
	final Log log = LogFactory.getLog(getClass());

	public static final String FUNC_PATH = "/IBSTest";

	public static final String SESSION_ATTR_QUERY = "pub_ibs_test_query";
	
	@ModelAttribute("funcPath")
	String funcPath() {
		return FUNC_PATH;
	}
	@Autowired
	BaseDao baseDao;
	
	
	@RequestMapping({ "/" })
	public String home() throws Exception {
		return "ibs/pub/ibsTest/home";
	}
	
	@RequestMapping(value = "/miniuiSearch")
	@ResponseBody
	public QueryResult miniSearch(Query query, HttpServletResponse response,String ids) {
		QueryResult search;
		try {
			String sql ="select * from pur_plan_doc where busi_date BETWEEN '2017-08-01'  AND  '2017-08-18' ";
			query.setLoggerPrefix("采购计划");
			search = baseDao.search(sql,query);
			return search;
		} catch (Exception e) {
			log.error("search error", e);
			e.printStackTrace();
			return new QueryResult<>("search error");
		}
	}
}
