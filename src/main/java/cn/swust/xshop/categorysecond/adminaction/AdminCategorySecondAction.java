package cn.swust.xshop.categorysecond.adminaction;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.category.vo.Category;
import cn.swust.xshop.categorysecond.vo.CategorySecond;
import cn.swust.xshop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;


/**
 * 后台二级分类的管理的Action.
 * 
 */

@Controller
@Scope("prototype")
public class AdminCategorySecondAction extends BaseAction<CategorySecond> {
	// 接收page参数:
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	// C : 跳转到添加UI
	public String addPage() {
		// 查询所有一级分类.
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "addUI";
	}

	// C : 添加二级分类
	public String save() {
		categorySecondService.save(model);
		return "toList";
	}
	
	// R : 带有分页的查询所有二级分类
	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "list";
	}

	// U : 修改二级分类的UI
	public String edit() {
		// 根据id查询二级分类，model默认压入值栈
		model = categorySecondService.findById(model.getCsid());
		// 查询所有一级分类
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转
		return "editUI";
	}
	
	// U : 修改二级分类
	public String update(){
		categorySecondService.update(model);
		return "toList";
	}
	
	// D : 删除二级分类
	public String delete() {
		categorySecondService.delete(model);
		return "toList";
	}
}
