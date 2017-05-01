package cn.swust.xshop.user.adminaction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.user.vo.User;

/**
 * 后台用户管理的Action类
 *
 */

@Controller
@Scope("prototype")
public class UserAdminAction extends BaseAction<User> {
	
}
