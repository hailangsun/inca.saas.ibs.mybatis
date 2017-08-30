package com.inca.saas.ibs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inca.saas.ibs.entity.User;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    @Select("select id,user_opcode userOpcode,user_name userName from pub_user")
    List<User> selectListBySQL();
    
    List<User>selectUserAll();
    
}