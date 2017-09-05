package com.inca.saas.ibs.hov;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inca.saas.ibs.support.BaseDao;
import com.inca.saas.ibs.support.QueryResult;

@Service(GoodsHovService.SERVICE_NAME)
public class GoodsHovServiceImpl implements GoodsHovService{
	final Log log = LogFactory.getLog(getClass());
	@Autowired
	BaseDao baseDao;

	@RequestMapping(value = "/miniuiSearch")
	@ResponseBody
	public QueryResult miniSearch(MiniuiGoodsQuery query,String type)throws Exception {
		QueryResult search;
		try {
			String sql = "select * from pub_goods ";
			query.setLoggerPrefix("商品管理Hov");
			search = baseDao.search(sql, query);
			return search;
		} catch (Exception e) {
			log.error("search error", e);
			e.printStackTrace();
			return new QueryResult<>("search error");
		}
	}

}
