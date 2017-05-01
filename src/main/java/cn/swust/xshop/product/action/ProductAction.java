package cn.swust.xshop.product.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.product.vo.Product;

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
	
	// 根据商品的id查询商品
	public String findByPid() {
		return "product";
	}

	// 根据一级分类的id查询商品
	public String findByCid() {
		return "productList";
	}

	// 根据二级分类id查询商品
	public String findByCsid() {
		return "productList";
	}

}
