package cn.swust.xshop.categorysecond.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.swust.xshop.utils.PageBean;
import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;
import cn.swust.xshop.category.dao.CategoryDao;
import cn.swust.xshop.categorysecond.vo.CategorySecond;

/**
 * 二级分类的业务层代码
 * 
 */
@Service
public class CategorySecondService extends BaseService<CategorySecond> {
	
	@Resource
	CategoryDao categorySecond;							   // 注入自己的dao

	@Resource(name="categorySecondDao")                    // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<CategorySecond> dao) {		
		super.setDao(dao);
	}
	
	// 带分页的查询
	public PageBean<CategorySecond> findByPage(Integer page) {
		return null;
	}

}
