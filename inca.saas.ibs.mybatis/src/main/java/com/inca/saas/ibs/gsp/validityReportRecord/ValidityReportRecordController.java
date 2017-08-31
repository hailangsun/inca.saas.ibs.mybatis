package com.inca.saas.ibs.gsp.validityReportRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.inca.saas.ibs.mapper.ValidityReportRecordMapper;
import com.inca.saas.ibs.support.BaseDao;
import com.inca.saas.ibs.support.Query;
import com.inca.saas.ibs.support.QueryResult;
import com.inca.saas.ibs.tablesetup.Column;
import com.inca.saas.ibs.tablesetup.Column.Editor;

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
	public String home(Model model) throws Exception {
		model.addAttribute("columns", getColumns());
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
	
	
	public Map<String,Object> getColumns(){
		Map<String,Object> map = new HashMap<>();
		List<Column> columns = new ArrayList<>();
		Column column1 = new Column();
		Editor editor = new Editor();
		editor.setType("textbox");
		column1.setType("indexcolumn");
		column1.setHeader("#");
		columns.add(column1);
		Column column2 = new Column();
		column2.field("goods_code");
		column2.setWidth(120);
		column2.setHeader("商品编号");
		columns.add(column2);
		
		Column column3 = new Column();
		column3.field("goods_name");
		column3.setWidth(120);
		column3.setHeader("通用名");
		column3.setEditor(editor);
		columns.add(column3);
		
		Column column4 = new Column();
		column4.field("goods_opcode");
		column4.setWidth(120);
		column4.setHeader("助记码");
		columns.add(column4);
		
		Column column5 = new Column();
		column5.field("goods_spec");
		column5.setWidth(120);
		column5.setHeader("规格");
		columns.add(column5);
		
		Column column6 = new Column();
		column6.field("goods_unit");
		column6.setWidth(120);
		column6.setHeader("基本单位");
		columns.add(column6);
		map.put("columns", columns);
		return map;
	}
	
}
