package cn.swust.xshop.order.adminaction;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.order.vo.Order;

/**
 * 后台订单管理的Action
 *
 */

@Controller
@Scope("prototype")
public class AdminOrderAction extends BaseAction<Order> {
	
}
