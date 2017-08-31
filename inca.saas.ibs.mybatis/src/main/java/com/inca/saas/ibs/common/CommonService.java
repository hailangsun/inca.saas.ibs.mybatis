package com.inca.saas.ibs.common;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {
	static final String SERVICE_NAME = "commonService";
	
	
	public void getExportExcel()throws Exception;
}
