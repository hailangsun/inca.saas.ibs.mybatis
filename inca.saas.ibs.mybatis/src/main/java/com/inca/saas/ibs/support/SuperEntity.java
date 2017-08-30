package com.inca.saas.ibs.support;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * 演示实体父类
 */
public class SuperEntity<T extends Model> extends Model<T> {

	
	@TableId
	private Long id;
	
	@Override
    protected Serializable pkVal() {
        return this.id;
    }

}
