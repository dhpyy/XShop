package cn.swust.xshop.cart.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.cart.vo.Cart;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 前端购物车Action
 * 
 */

@Controller
@Scope("prototype")
public class CartAction extends ActionSupport {
	private Integer pid; 			 // 接收pid
	private Integer count; 			 // 接收数量count

	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * @return
	 */
	private Cart getCart() {
		Cart cart = null;
		return cart;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	// C : 将购物项添加到购物车
	public String addCart() {
		return "cart";
	}

	// R : 查看我的购物车
	public String myCart() {
		return "cart";
	}
			
	// D : 清空购物车:
	public String clearCart() {
		return "cart";
	}
	
	// D : 从购物车中移除购物项的方法:
	public String removeCart() {
		return "cart";
	}
	
}
