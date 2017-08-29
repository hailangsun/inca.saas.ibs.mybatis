package com.inca.saas.ibs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.inca.saas.ibs.entity.User;
import com.inca.saas.ibs.support.SuperMapper;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    @Select("select id,user_opcode userOpcode,user_name userName from pub_user")
    List<User> selectListBySQL();
    
    /**
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * </p>
     *
     * @param page
     *            翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @param state
     *            状态
     * @return
     */
    List<User> selectUserList(Pagination page);    
}