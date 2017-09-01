package com.inca.saas.ibs.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.inca.saas.ibs.support.ServiceManager;

public class BaseController {
	@Autowired(required = false)
	protected ServiceManager serviceManager;
}
