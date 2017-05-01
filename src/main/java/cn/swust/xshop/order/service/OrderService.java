package cn.swust.xshop.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;
import cn.swust.xshop.order.dao.OrderDao;
import cn.swust.xshop.order.vo.Order;
import cn.swust.xshop.order.vo.OrderItem;
import cn.swust.xshop.utils.PageBean;

/**
 * 订单的业务层代码
 * 
 */
@Service
public class OrderService extends BaseService<Order> {

	@Resource
	private OrderDao orderDao;
	
	@Resource(name="orderDao")                    // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<Order> dao) {		
		super.setDao(dao);
	}
	
	// 带分页的查询订单
	public PageBean<Order> findAll(Integer page) {
		return null;
	}
	
	// 根据用户带分页的查询订单
	public PageBean<Order> findByUid(Integer uid,Integer page) {
		return null;
	}
	
	// 根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
