package cn.swust.xshop.order.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.order.vo.Order;

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
		return "order";
	}
	
	// R : 根据id查询订单:
	public String findByOid() {
		return "order";
	}
	
	// R : 根据用户查询订单:
	public String findByUid() {
		return "orderList";
	}
	
	// U : 更改订单物流信息，调用支付接口
	public String payOrder() {
		return NONE;
	}
	
	// U : 支付成功时回调，更改订单状态：已付款
	public String callBack() {
		return "msg";
	}
	
	// U : 修改订单的状态：确认收货
	public String updateState() {
		return "confirmReceipt";
	}
	
}
