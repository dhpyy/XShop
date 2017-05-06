package cn.swust.xshop.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;
import cn.swust.xshop.product.dao.ProductDao;
import cn.swust.xshop.product.vo.Product;
import cn.swust.xshop.utils.PageBean;

/**
 * 商品的业务层代码
 * 
 */
@Service
public class ProductService extends BaseService<Product> {
	
	@Resource
	ProductDao productDao;					
	
	@Resource(name="productDao")                    // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<Product> dao) {		
		super.setDao(dao);
	}
	
	// 首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		return productDao.findNew();
	}

	// 根据一级分类id带有分页的商品查询
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();          // 根据页码计算出分页查询的区间
		pageBean.setPage(page);					// 设置当前页码
		int limit = 12;							// 设置每页显示记录个数
		pageBean.setLimit(limit);
		int totalCount = 0;						// 设置总记录数
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;						// 设置总页数:
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;			// 设置当前页记录集合
		List<Product> list = productDao.findByPageCid(cid, begin, limit);// 调用Dao实现分页查询
		pageBean.setList(list);
		return pageBean;
	}
	
	// 根据二级分类id带有分页的商品查询
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();          // 根据页码计算出分页查询的区间
		pageBean.setPage(page);					// 设置当前页码
		int limit = 12;							// 设置每页显示记录个数
		pageBean.setLimit(limit);
		int totalCount = 0;						// 设置总记录数
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;						// 设置总页数:
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;			// 设置当前页记录集合
		List<Product> list = productDao.findByPageCsid(csid, begin, limit);// 调用Dao实现分页查询
		pageBean.setList(list);
		return pageBean;
	}
}
