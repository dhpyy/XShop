package cn.swust.xshop.index.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.user.vo.User;

/**
 * 首页访问的Action
 *
 */

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction<User> {
	
	/**
	 * 执行的访问首页的方法:
	 */
	public String execute(){
		return "index";
	}
	
	
}
