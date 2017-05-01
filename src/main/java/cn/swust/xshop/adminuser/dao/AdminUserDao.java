package cn.swust.xshop.adminuser.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.swust.xshop.adminuser.vo.AdminUser;
import cn.swust.xshop.base.BaseDao;

/**
 * 管理员持久层代码:
 * 
 */
@Repository
public class AdminUserDao extends BaseDao<AdminUser> {
	
	// 根据用户名与密码查询管理员
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
