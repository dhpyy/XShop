package cn.swust.xshop.categorysecond.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.categorysecond.vo.CategorySecond;


/**
 * 二级分类的Dao层的代码
 * 
 */
@Repository
public class CategorySecondDao extends BaseDao<CategorySecond> {
	// 分页查询
	public List<CategorySecond> findByPage(int begin, int limit) {
		return null;
	}
}
