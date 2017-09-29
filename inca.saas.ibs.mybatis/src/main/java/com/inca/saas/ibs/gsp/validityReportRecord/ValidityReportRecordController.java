package com.inca.saas.ibs.gsp.validityReportRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inca.saas.ibs.common.AdvQueryModel;
import com.inca.saas.ibs.common.BaseController;
import com.inca.saas.ibs.common.CommonService;
import com.inca.saas.ibs.mapper.ValidityReportRecordMapper;
import com.inca.saas.ibs.support.BaseDao;
import com.inca.saas.ibs.support.QueryResult;
import com.inca.saas.ibs.tablesetup.Column;
import com.inca.saas.ibs.tablesetup.Column.Buttons;
import com.inca.saas.ibs.tablesetup.Column.Columns;
import com.inca.saas.ibs.tablesetup.Column.Editor;
import com.inca.saas.ibs.util.export.CsvUtil;

@Controller
@RequestMapping(ValidityReportRecordController.FUNC_PATH)
@SessionAttributes({ ValidityReportRecordController.SESSION_ATTR_QUERY })
public class ValidityReportRecordController extends BaseController {
	final Log log = LogFactory.getLog(getClass());

	public static final String FUNC_PATH = "/IBSGSP347";

	public static final String SESSION_ATTR_QUERY = "rsa_validityReport_Record_query";

	@ModelAttribute("funcPath")
	String funcPath() {
		return FUNC_PATH;
	}
	
	@RequestMapping({"/advQueryHome"})
	public String advQueryHome(Model model){
		model.addAttribute("advQueryColumns", advQueryColumns());
		return "ibs/common/advQueryHome2";
	}
	
	public Vector<AdvQueryModel> advQueryColumns() {
		Map<String, Object> map = getColumns();
		List<Column> listColumn = (List<Column>) map.get("columns");
		Vector<AdvQueryModel> advQueryModel = new Vector<>();
		for (int i = 0; i < listColumn.size(); i++) {
			AdvQueryModel advQueryModelTemp = new AdvQueryModel();
			if(listColumn.get(i).getIsAdvQuery()){
				advQueryModelTemp.setField(listColumn.get(i).getField());
				advQueryModelTemp.setHeader(listColumn.get(i).getHeader());
				advQueryModel.add(advQueryModelTemp);
			}
		}
		return advQueryModel;
	}

	@Autowired
	BaseDao baseDao;
	@Autowired
	ValidityReportRecordMapper validityReportRecordMapper;

	protected CommonService getCommonService() {
		return serviceManager.lookup(CommonService.SERVICE_NAME, CommonService.class);
	}

	@RequestMapping({ "/" })
	public String home(Model model) throws Exception {
		model.addAttribute("columns", getColumns());
		return "ibs/gsp/validityReportRecord/home";
	}

	@RequestMapping(value = "/miniuiSearch")
	@ResponseBody
	public QueryResult miniSearch(ValidityReportRecordQuery query, HttpServletResponse response) {
		QueryResult search;
		try {
			StringBuffer sql = getSql(query);
			search = baseDao.search(sql.toString(), query);
			return search;
		} catch (Exception e) {
			log.error("search error", e);
			e.printStackTrace();
			return new QueryResult<>("search error");
		}
	}

	public Map<String, Object> getColumns() {
		Map<String, Object> map = new HashMap<>();
		List<Column> columns = new ArrayList<>();
		columns.add(new Column().type("indexcolumn").header("#"));
		columns.add(new Column().editor(
				new Editor().type("autocomplete").cls("mini-autocomplete")
				.name("goods_code").enterQuery(true).style("width: 250px")
				.onvaluechanged("onValueChanged").onkeyup("keyup")
				.addButtons(new Buttons().handler("onGoods")).popupWidth("800").url("/IBSPUB010Hov/miniuiHovSearch")
				.textField("goods_code").valueField("goods_code").onbeforeload("onBeforeLoad")
				.addColumns(new Columns().field("goods_code").header("商品编码"))
				.addColumns(new Columns().field("goods_name").header("通用名"))
				.addColumns(new Columns().field("goods_unit").header("基本单位"))
				.addColumns(new Columns().field("medicine_type").header("剂型"))
				.addColumns(new Columns().field("factory_name").header("生产厂商"))
				).field("goods_code").header("商品编号").headerAlign("center").headerStyle("color:#0175be").name("goods_code").displayField("goods_code").isAdvQuery(true));
		columns.add(new Column().editor(new Editor().type("textbox")).field("goods_name").header("通用名").isAdvQuery(true));
		columns.add(new Column().field("goods_opcode").header("助记码").isAdvQuery(true));
		columns.add(new Column().field("goods_spec").header("规格"));
		columns.add(new Column().field("goods_unit").header("基本单位"));
		map.put("columns", columns);
		return map;
	}
	

	@RequestMapping("/export")
	public void exportDtl(HttpServletResponse response, HttpServletRequest request, HttpSession session,
			ValidityReportRecordQuery query) throws Exception {
		File csv = null;
		try {

			Map<String, Object> maps = new HashMap<>();
			maps.put("columnsJson", query.getColumnsJson());
			maps.put("prefix", "测试导出");
			String sql = "select * from pub_goods ";
			maps.put("sql", sql);
			// Map<String,ColumnConvert> convertMap = new HashMap();
			Object[] ags = null;
			csv = getCommonService().getExportExcel(maps, ags);
			log.info(" 导出文件大小：" + csv.length());
			if (csv.length() > 1024 * 1024 * 10) {
				CsvUtil.buildFastCsvFile(response, csv);
			} else {
				CsvUtil.buildCsvFile(response, csv);
			}
		} finally {
			if (csv != null && csv.exists()) {
				log.info("删除临时文件：" + csv.delete());
			}
		}

	}

	@RequestMapping({ "/dialog" })
	public String importExcel() {
		return "ibs/gsp/validityReportRecord/dialog";
	}

	@RequestMapping({ "/inputFile" })
	@ResponseBody
	public String update(String inputFile){
		System.out.println(inputFile);
		return "1";
	}
	
	//拼接sql
	public StringBuffer getSql(ValidityReportRecordQuery validityReportRecordQuery){
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from pub_goods ");
		if(validityReportRecordQuery.getIsAdvQuery()){
			
		}else{
			
		}
		
		return sb;
	}
	
	public String getAdvSql(ValidityReportRecordQuery validityReportRecordQuery){
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
	

}
