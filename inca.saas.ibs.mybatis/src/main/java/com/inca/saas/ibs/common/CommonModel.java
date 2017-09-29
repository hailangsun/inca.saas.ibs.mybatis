package com.inca.saas.ibs.common;

import java.io.Serializable;
import java.util.Vector;

public class CommonModel implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8262943557622496246L;
	/**
     * 高级查询字段列表
     */
    private Vector<AdvQueryModel> advQueryModel=new Vector<>();
}
