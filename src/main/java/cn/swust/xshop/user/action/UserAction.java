package cn.swust.xshop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.user.vo.User;


/**
 * 前端用户Action类
 * 
 */

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	/**
	 * CUI : 跳转到注册页面
	 */
	public String registPage() {
		return "registUI";
	}
	
	/**
	 * C : 用户注册
	 */
	public String regist() {
		userService.save(model);
		return "msg";
	}
	
	/**
	 * R : AJAX进行异步校验用户
	 * 
	 */
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUsername(model.getUsername());
		// 获得response对象,项页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;				// struts.xml中没有相应的result，即不跳转到新的页面
	}
	
	/**
	 * RUI : 跳转到登录页面
	 */
	public String loginPage() {
		return "loginUI";
	}
	
	/**
	 * R : 登录
	 */
	public String login() {
		User existUser = userService.login(model);
		// 判断
		if (existUser == null) {
			// 登录失败
			this.addActionError("登录失败:用户名或密码错误或用户未激活!");
			return "loginUI";
		} else {
			// 登录成功
			ServletActionContext.getRequest().getSession()		// 将用户的信息存入到session中
					.setAttribute("existUser", existUser);
			// 页面跳转
			return "login";
		}
	}
	
	/**
	 * D : 用户注销
	 */
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();	// 销毁session
		return "logout";
	}
}
