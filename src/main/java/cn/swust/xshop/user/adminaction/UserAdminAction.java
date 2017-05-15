package cn.swust.xshop.user.adminaction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.user.vo.User;
import cn.swust.xshop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;

/**
 * 后台用户管理的Action类
 *
 */

@Controller
@Scope("prototype")
public class UserAdminAction extends BaseAction<User> {
	// 接受page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// R : 查询用户带分页
	public String findAll(){
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "list";
	}
	
	// U : 修改用户UI
	public String edit(){
		model = userService.findById(model.getUid());
		return "editUI";
	}
	
	// U : 修改用户:
	public String update(){
		userService.update(model);
		return "toList";
	}

	// D : 删除用户
	public String delete(){
		User existUser = userService.findById(model.getUid());
		userService.delete(existUser);
		return "toList";
	}
}
