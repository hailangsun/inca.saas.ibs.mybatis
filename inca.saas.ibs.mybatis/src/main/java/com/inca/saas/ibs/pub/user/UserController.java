package com.inca.saas.ibs.pub.user;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.inca.saas.ibs.entity.User;
import com.inca.saas.ibs.mapper.UserMapper;
import com.inca.saas.ibs.support.Query;
import com.inca.saas.ibs.support.QueryResult;

@Controller
@RequestMapping(UserController.FUNC_PATH)
@SessionAttributes({ UserController.SESSION_ATTR_QUERY })
public class UserController{
	final Log log = LogFactory.getLog(getClass());

	public static final String FUNC_PATH = "/IBSPUB002";

	public static final String SESSION_ATTR_QUERY = "pub_user_query";
	
	@ModelAttribute("funcPath")
	String funcPath() {
		return FUNC_PATH;
	}
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping({ "/" })
	public String home() throws Exception {
		Page<User> selectPage = userService.selectPage(new Page<User>(1,5),null);
		Page<User> userListPage = userService.selectPage(new Page<User>(1,5), new EntityWrapper<>(new User()));
		System.err.println("total="+userListPage.getTotal()+", current list size="+userListPage.getRecords().size());
//		List<User> selectListBySQL = userService.selectUserAll();
//		System.out.println(selectPage.getSize());
		EntityWrapper<User> ew = new EntityWrapper<>();
		return "";
	}
	
	@RequestMapping(value = "/miniuiSearch")
	@ResponseBody
	public QueryResult miniSearch(Query query, HttpServletResponse response) {
		QueryResult search;
		try {
			return new QueryResult<>();
		} catch (Exception e) {
			log.error("search error", e);
			e.printStackTrace();
			return new QueryResult<>("search error");
		}
	}
}
