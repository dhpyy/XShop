package cn.swust.xshop.adminuser.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.swust.xshop.adminuser.dao.AdminUserDao;
import cn.swust.xshop.adminuser.vo.AdminUser;
import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;

/**
 * 管理员模块业务层代码
 *
 */
@Service
public class AdminUserService extends BaseService<AdminUser> {

    @Resource
	private AdminUserDao adminUserDao;
    
    @Resource(name="adminUserDao")                    // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<AdminUser> dao) {		
		super.setDao(dao);
	}
    
    public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}
