package cn.swust.xshop.cart.vo;

import java.io.Serializable;

import cn.swust.xshop.product.vo.Product;

/**
 * 购物项对象
 * 
 */
public class CartItem implements Serializable {
	private Product product;	 // 购物项中商品信息
	private int count; 			 // 购买某种商品数量

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// R : 购物车项小计价格
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	

}
