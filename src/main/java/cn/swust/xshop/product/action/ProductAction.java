package cn.swust.xshop.product.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.product.vo.Product;
import cn.swust.xshop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;

/**
 * 前端商品的Action对象
 * 
 */

@Controller
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
	private Integer cid; 			// 接收一级分类id
	private Integer csid; 			// 接收二级分类id
	private int page;				// 接收当前页数
	private String keyword;			// 接受搜索关键词
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// 根据商品的id查询商品
	public String findById() {
		// 调用Service的方法完成查询.
		model = productService.findById(model.getPid());	// model默认压入ValueStack
		return "product";
	}

	// 根据一级分类的id查询商品
	public String findByCid() {
		// List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);// 根据一级分类查询商品,带分页查询
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "productList";
	}

	// 根据二级分类id查询商品
	public String findByCsid() {
		// 根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "productList";
	}

	// 根据关键词的查询商品
	public String findByLike() throws UnsupportedEncodingException {
		// 根据关键词查询商品
		PageBean<Product> pageBean = productService.findByLike(keyword, page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		if (pageBean.getList() == null) {
			this.addActionError("不好意思亲，本商城暂无此款商品");				// 查询失败，向页面输出信息
		}
		return "productList";
	}
}
