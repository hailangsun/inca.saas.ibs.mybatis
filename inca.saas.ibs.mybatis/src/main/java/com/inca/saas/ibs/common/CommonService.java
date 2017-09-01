package com.inca.saas.ibs.common;

import java.io.File;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {
	static final String SERVICE_NAME = "commonService";
	
	
	public File getExportExcel(Map<String,Object> maps,Object... args)throws Exception;
}
