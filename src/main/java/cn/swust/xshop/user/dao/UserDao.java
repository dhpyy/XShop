package cn.swust.xshop.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.user.vo.User;
import cn.swust.xshop.utils.PageHibernateCallback;


/**
 * 用户持久层代码:
 * 
 */
@Repository
public class UserDao extends BaseDao<User> {
	
	// 根据用户名查询用户:
	public User findByUsername(String username) {
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	// 根据用户名与密码查询用户
	public User login(User user) {
		String hql = "from User where username = ? and password = ?";
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(), user.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	// 带分页的查询
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}
}
