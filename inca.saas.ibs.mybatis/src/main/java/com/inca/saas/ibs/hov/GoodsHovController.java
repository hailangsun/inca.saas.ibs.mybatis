package com.inca.saas.ibs.hov;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.inca.saas.ibs.common.BaseController;
import com.inca.saas.ibs.support.QueryResult;

@Controller
@RequestMapping("/IBSPUB010Hov")
@SessionAttributes({GoodsHovController.SESSION_ATTR_QUERY })
public class GoodsHovController extends BaseController{

	final Log log = LogFactory.getLog(getClass());
	public static final String SESSION_ATTR_QUERY = "pub_goods_hov_query";
	
	protected GoodsHovService getGoodsHovService() {
		return serviceManager.lookup(GoodsHovService.SERVICE_NAME, GoodsHovService.class);
	}
	
	@RequestMapping("/miniuihome/{methodParam}")
	public String miniuihome(Model model,@PathVariable String methodParam) {
		model.addAttribute("methodParam", methodParam);
		return "ibs/hov/miniuihome";
	}
	
	@RequestMapping(value = "/miniuiHovSearch")
	@ResponseBody
	public QueryResult miniuiHovSearch(MiniuiGoodsQuery query) {
		try {
			System.out.println();
			return getGoodsHovService().miniSearch(query,"");
		} catch (Exception e) {
			log.error("search error", e);
			e.printStackTrace();
		}
		return new QueryResult();
	}
}
