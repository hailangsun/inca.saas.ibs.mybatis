package com.inca.saas.ibs.hov;

import com.inca.saas.ibs.support.QueryResult;

public interface GoodsHovService {
	
	static final String SERVICE_NAME = "pubHovGoodsService";
	
	QueryResult miniSearch(MiniuiGoodsQuery query, String type) throws Exception;
}
