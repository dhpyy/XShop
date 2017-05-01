package cn.swust.xshop.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.order.vo.Order;
import cn.swust.xshop.order.vo.OrderItem;


/**
 * 订单持久层的代码
 */
@Repository
public class OrderDao extends BaseDao<Order> {
	
	// 根据用户查询订单个数
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	// 带分页的查询订单
	public List<Order> findByPage(int begin, int limit) {
		return null;
	}
	
	// 根据用户带分页的查询订单
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		return null;
	}
	
	// 根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	
}
