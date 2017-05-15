package cn.swust.xshop.order.adminaction;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.order.vo.Order;
import cn.swust.xshop.order.vo.OrderItem;
import cn.swust.xshop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;

/**
 * 后台订单管理的Action
 *
 */

@Controller
@Scope("prototype")
public class AdminOrderAction extends BaseAction<Order> {
	// 接收page参数
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	// R : 查询所有订单带分页
	public String findAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "list";
	}

	// R : 根据订单的id查询订单项
	public String findOrderItem(){
		List<OrderItem> list = orderService.findOrderItem(model.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "orderItem";
	}
	
	// U : 修改订单状态
	public String updateState(){
		Order currOrder = orderService.findById(model.getOid());
		currOrder.setState(3);			    // 已发货
		orderService.update(currOrder);
		return "toList";
	}
}
