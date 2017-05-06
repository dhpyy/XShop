package cn.swust.xshop.cart.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.cart.vo.Cart;
import cn.swust.xshop.cart.vo.CartItem;
import cn.swust.xshop.product.service.ProductService;
import cn.swust.xshop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 前端购物车Action
 * 
 */

@Controller
@Scope("prototype")
public class CartAction extends  ActionSupport {
	private Integer pid; 			 // 接收pid
	private Integer count; 			 // 接收数量count
	@Resource
	private ProductService productService;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	// C/R当前session中的购物车
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);				
		}
		return cart;
	}
	
	// R : 查看我的购物车
	public String myCart() {
		return "cart";						// cart对象已保存在session中
	}
		
	// U : 将购物项添加到购物车
	public String addCart() {
		CartItem cartItem = new CartItem();					// 创建购物车项
		cartItem.setCount(count);
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		getCart().addCart(cartItem);			            // 将购物项添加到购物车
		return "cart";
	}

	// U : 从购物车中移除购物项的方法:
	public String removeCart() {
		getCart().removeCart(pid);
		return "cart";
	}
	
	// U : 清空购物车:
	public String clearCart() {
		getCart().clearCart();
		return "cart";
	}
	
}
