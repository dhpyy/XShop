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
	 * 跳转到注册页面:(CUI)
	 */
	public String registPage() {
		return "registUI";
	}
	
	/**
	 * 用户注册的方法:(C)
	 */
	public String regist() {
		return "msg";
	}
	
	/**
	 * AJAX进行异步校验用户名的执行方法
	 * 
	 */
	public String findByName() {
		return NONE;				// struts.xml中没有相应的result，即不跳转到新的页面
	}
	
	/**
	 * 跳转到登录页面:(RUI)
	 */
	public String loginPage() {
		return "registUI";
	}
	
	/**
	 * 登录:(R)
	 */
	public String login() {
		return "login";
	}
	
	/**
	 * 用户注销
	 */
	public String quit(){
		return "logout";
	}
}
