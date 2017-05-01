package cn.swust.xshop.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.product.vo.Product;

/**
 * 商品持久层的代码
 * 
 */
@Repository
public class ProductDao extends BaseDao<Product> {
	
	// 首页热门商品查询
	public List<Product> findHot() {
		return null;
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
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
		return null;
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
		return null;
	}
	
}
