package cn.swust.xshop.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;
import cn.swust.xshop.user.dao.UserDao;
import cn.swust.xshop.user.vo.User;
import cn.swust.xshop.utils.PageBean;

/**
 * 用户名模块业务层代码
 *
 */
@Service
public class UserService extends BaseService<User> {
	
	@Resource
	private UserDao userDao;			

	@Resource(name="userDao")                    // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<User> dao) {		
		super.setDao(dao);
	}
	
	// 根据用户名与密码查询用户
	public User login(User user) {
		return userDao.login(user);
	}
	
	// 根据用户名查询用户
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	// 带分页的查询
	public PageBean<User> findByPage(Integer page) {
		return null;
	}
}
