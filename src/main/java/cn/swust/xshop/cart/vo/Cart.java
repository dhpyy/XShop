package cn.swust.xshop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 购物车对象
 * 
 */
public class Cart implements Serializable {
	
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>(); // 购物项的Map: key为商品pid
	
	private double total;		// 购物总计
	
	public Collection<CartItem> getCartItems() {		// Cart对象中有一个叫cartItems的属性，可以通过OGNL表达式访问
		return map.values();
	}

	public double getTotal() {
		return total;
	}

	// U : 将购物项添加到购物车(读写购物车、购物车项)
	public void addCart(CartItem cartItem) {   // session中保存的是cart对象的引用地址，更改cart对象即更改了session中的cart对象
		// 获得商品id.
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物车中是否已经存在该购物项:
		if(map.containsKey(pid)){
			// 存在
			CartItem _cartItem = map.get(pid);// 获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());  // cart对象中保存的是cartItem对象的引用地址，更改...
		}else{
			// 不存在
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();
	}

	// U : 从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 -移除的购物项小计:
		total -= cartItem.getSubtotal();
	}

	// U : 清空购物车
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}
}
