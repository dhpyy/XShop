package cn.swust.xshop.category.adminaction;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.category.vo.Category;


/**
 * 后台一级分类管理Action
 *
 */

@Controller
@Scope("prototype")
public class AdminCategoryAction extends BaseAction<Category>  {
	// C : 保存一级分类
	public String save(){
		categoryService.save(model);
		return "toList";
	}
	
	// R : 查询所有一级分类
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "list";
	}

	// U : 修改一级分类UI
	public String edit(){
		model = categoryService.findById(model.getCid());
		return "editUI";
	}
	
	// U : 修改一级分类
	public String update(){
		categoryService.update(model);
		return "toList";
	}
	
	// D : 删除一级分类
	public String delete(){
		model = categoryService.findById(model.getCid());
		categoryService.delete(model);
		return "toList";
	}
	
}
