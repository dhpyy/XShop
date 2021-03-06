package cn.swust.xshop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.product.vo.Product;
import cn.swust.xshop.utils.PageHibernateCallback;

/**
 * 商品持久层的代码
 * 
 */
@Repository
public class ProductDao extends BaseDao<Product> {
	
	// 首页上热门商品查询
	public List<Product> findHot() {
		// 使用离线条件查询.
		DetachedCriteria conditions = DetachedCriteria.forClass(Product.class);
		// 查询热门的商品,条件就是is_host = 1
		conditions.add(Restrictions.eq("is_hot", 1));
		// 倒序排序输出:
		conditions.addOrder(Order.desc("pdate"));
		// 执行查询:
		List<Product> list = this.getHibernateTemplate().findByCriteria(conditions, 0, 10);
		return list;
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		// 使用离线条件查询:
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按日期进行倒序排序:
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询:
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	
	// 根据一级分类id查询商品的个数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	// 根据一级分类id查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";  // 联合查询 
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	// 根据二级分类id查询商品个数
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	// 根据二级分类查询商品的集合
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	// 后台查询所有商品
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	// 根据关键词查询商品的个数
	public int findCountByLike(String keyword) {  
		DetachedCriteria conditions = DetachedCriteria.forClass(Product.class);
		Disjunction dis = Restrictions.disjunction();										// 添加多个or条件
		dis.add(Restrictions.like("pname", keyword, MatchMode.ANYWHERE));					// 设置模糊查询模式&str%
		dis.add(Restrictions.like("pdesc", keyword, MatchMode.ANYWHERE));
		conditions.add(dis);
		List<Product> list = this.getHibernateTemplate().findByCriteria(conditions, 0, 10);
		if(list != null && list.size() > 0){
			return list.size();
		}
		return 0;
    }  
		
	// 根据关键词查询商品的集合
	public List findByLike(String keyword, int begin, int limit) {  
		DetachedCriteria conditions = DetachedCriteria.forClass(Product.class);
		Disjunction dis = Restrictions.disjunction();										// 添加多个or条件
		dis.add(Restrictions.like("pname", keyword, MatchMode.ANYWHERE));					// 设置模糊查询模式&str%
		dis.add(Restrictions.like("pdesc", keyword, MatchMode.ANYWHERE));
		conditions.add(dis);
		List<Product> list = this.getHibernateTemplate().findByCriteria(conditions, 0, 10);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
    }  
    
	
      
}
