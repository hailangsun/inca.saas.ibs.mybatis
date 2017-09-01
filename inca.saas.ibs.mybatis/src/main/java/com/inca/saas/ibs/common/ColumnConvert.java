package com.inca.saas.ibs.common;

import java.io.Serializable;
import java.util.Map;

import com.inca.saas.ibs.support.ServiceManager;
import com.inca.saas.ibs.support.SpringContextHolder;


public interface ColumnConvert extends Serializable {
	public String convert(Map<String, Object> row, String field, Object dbValue);

	default <T> T lookup(String serviceName, Class<T> clazz) {
		ServiceManager serviceManager = SpringContextHolder.getApplicationContext().getBean(ServiceManager.class);
		return serviceManager.lookup(serviceName, clazz);
	}
}