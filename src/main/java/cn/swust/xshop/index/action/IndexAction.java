package cn.swust.xshop.index.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.category.vo.Category;
import cn.swust.xshop.product.vo.Product;
import cn.swust.xshop.user.vo.User;

import com.opensymphony.xwork2.ActionContext;

/**
 * 首页访问的Action
 *
 */

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction<User> {
	
	/**
	 * 查询首页
	 */
	public String execute(){
		List<Product> hList = productService.findHot();					// 查询热门商品
		List<Product> nList = productService.findNew();					// 查询最新商品
		ActionContext.getContext().getValueStack().set("hList", hList);
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		List<Category> cList = categoryService.findAll();				// 查询一级分类
		ActionContext.getContext().getSession().put("cList", cList);    // 写到session中，后面的请求也能用到
		
		return "index";
	}
	
	
}
