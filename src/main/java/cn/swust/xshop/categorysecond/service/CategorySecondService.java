package cn.swust.xshop.categorysecond.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.base.BaseService;
import cn.swust.xshop.categorysecond.dao.CategorySecondDao;
import cn.swust.xshop.categorysecond.vo.CategorySecond;
import cn.swust.xshop.utils.PageBean;

/**
 * 二级分类的业务层代码
 * 
 */
@Service
public class CategorySecondService extends BaseService<CategorySecond> {
	
	@Resource
	CategorySecondDao categorySecondDao;							   // 注入自己的dao

	@Resource(name="categorySecondDao")                    // 重写父类set方法,通过name指定为父类泛型注入的bean
	public void setDao(BaseDao<CategorySecond> dao) {		
		super.setDao(dao);
	}
	
	// 二级分类带有分页的查询操作:
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 设置页码
		pageBean.setPage(page);
		// 设置每页显示记录数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

}
