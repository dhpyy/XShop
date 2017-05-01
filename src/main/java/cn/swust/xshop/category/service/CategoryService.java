package cn.swust.xshop.category.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;
import cn.swust.xshop.category.dao.CategoryDao;
import cn.swust.xshop.category.vo.Category;


/**
 * 一级分类的业务层对象
 */
@Service
public class CategoryService extends BaseService<Category> {
	
	@Resource
	CategoryDao categoryDao;						// 注入自己的dao

	@Resource(name="categoryDao")                   // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<Category> dao) {		
		super.setDao(dao);
	}
	
}
