package cn.swust.xshop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.cart.vo.Cart;
import cn.swust.xshop.cart.vo.CartItem;
import cn.swust.xshop.order.vo.Order;
import cn.swust.xshop.order.vo.OrderItem;
import cn.swust.xshop.user.vo.User;
import cn.swust.xshop.utils.PageBean;
import cn.swust.xshop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;

/**
 * 前端订单Action类
 * 
 */

@Controller
@Scope("prototype")
public class OrderAction extends BaseAction<Order> {
	private String pd_FrpId;			// 接收支付通道编码:
	private String r3_Amt;				// 接收付款成功后的参数:
	private String r6_Order;
	private Integer page;				// 接收page
	
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	// C : 含购物车信息，无物流信息
	public String saveOrder() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()		   // 读session
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("亲!您还没有购物!");
			return "msg";
		}
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("亲!您还没有登录!");
			return "msg";
		}
		model.setOrdertime(new Date());												// 封装Order
		model.setTotal(cart.getTotal());
		model.setState(1); 					// 状态1:未付款.
		model.setUser(existUser);
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();									// 封装orderItem
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());                          
			orderItem.setOrder(model);
			
			model.getOrderItems().add(orderItem);									// 封装Order
		}
		orderService.save(model);												    // 写servcie
		// 清空购物车:
		cart.clearCart();
		return "order";		                 // 跳转到UUI
	}

	// R : 根据id查询订单
	public String findById() {
		model = orderService.findById(model.getOid());		// 压人对象栈中的model对象存放的是引用地址
		return "order";
	}
	
	// R : 根据用户带分页的查询订单
	public String findByUid() {
		// 获得用户的id.
		User existUser = (User) ServletActionContext.getRequest().getSession()  // 读session
				.getAttribute("existUser");
		Integer uid = existUser.getUid();
		PageBean<Order> pageBean = orderService.findByUid(uid, page);           // 读servcie
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);   // 写valuestack
		return "orderList";
	}
	
	// U : 更改订单物流信息，调用支付接口
	public String payOrder() throws IOException {
		Order currOrder = orderService.findById(model.getOid());
		currOrder.setAddr(model.getAddr());				           // 写service
		currOrder.setName(model.getName());
		currOrder.setPhone(model.getPhone());
		orderService.update(currOrder);
		// 调用接口需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";										// 商户编号（易宝提供的测试账号的商户编号）
		String p2_Order = model.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额（用于测试，非实际金额！）
		String p4_Cur = "CNY"; // 交易币种
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/XShop/order_callBack.action"; // 商户接收支付成功的回调请求
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥（易宝提供的测试账号的秘钥）
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,			  // 加密算法（易宝提供的测试账号的加密算法：不可解密）
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");	// 写接口：传递请求参数
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		ServletActionContext.getResponse().sendRedirect(sb.toString());	     
		return NONE;
	}
	
	// U : 支付成功时回调，更改订单状态：已付款
	public String callBack(){
		Order currOrder = orderService.findById(Integer.parseInt(r6_Order));	
		currOrder.setState(2);	// 状态2:已经付款
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	// U : 修改订单的状态：确认收货
	public String updateState() {
		Order currOrder = orderService.findById(model.getOid());
		currOrder.setState(4); // 状态4:确认收货付款
		orderService.update(currOrder);
		return "confirmReceipt";
	}
	
}
