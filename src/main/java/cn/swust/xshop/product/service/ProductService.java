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
		return null;
	}
	
	// 根据二级分类id带有分页的商品查询
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		return null;
	}
}
