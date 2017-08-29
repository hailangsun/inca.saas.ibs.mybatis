package com.inca.saas.ibs.support;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;

/**
 * 演示实体父类
 */
public class SuperEntity<T extends Model> extends Model<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -421653262978448806L;

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

}
