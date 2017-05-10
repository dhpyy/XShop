package cn.swust.xshop.adminuser.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.adminuser.vo.AdminUser;
import cn.swust.xshop.base.BaseAction;

/**
 * 管理员Action类
 *
 */

@Controller
@Scope("prototype")
public class AdminUserAction extends BaseAction<AdminUser> {
	/**
	 * R : 登录
	 */
	public String login() {
		// 调用service方法完成登录
		AdminUser existAdminUser = adminUserService.login(model);
		// 判断
		if (existAdminUser == null) {
			// 用户名或密码错误
			this.addActionError("用户名或密码错误!");
			return "loginUI";
		} else {
			// 登录成功:
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "login";
		}
	}
}
