package com.inca.saas.ibs.pub.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.inca.saas.ibs.entity.User;
import com.inca.saas.ibs.mapper.UserMapper;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

	@Override
	public List<User> selectUserAll() {
		return baseMapper.selectUserAll();
	}
	
	
}