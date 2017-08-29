package com.inca.saas.ibs.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServiceManagerImpl implements ServiceManager {

	@Autowired
	ApplicationContext context;

	public <T> T lookup(String serviceName, Class<T> type) {
		return this.context.getBean(getCusServiceName(serviceName), type);
	}

	public Object lookup(String serviceName) {
		return this.context.getBean(getCusServiceName(serviceName));
	}

	public String getCusServiceName(String serviceName) {
		return serviceName;
	}
}
