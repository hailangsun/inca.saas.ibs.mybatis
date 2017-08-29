package com.inca.saas.ibs.support;

public interface ServiceManager {
	
	public abstract Object lookup(String paramString);
	  
	public abstract <T> T lookup(String paramString, Class<T> paramClass);
}
