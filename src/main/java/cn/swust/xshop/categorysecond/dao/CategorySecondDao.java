package cn.swust.xshop.categorysecond.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.swust.xshop.base.BaseDao;
import cn.swust.xshop.categorysecond.vo.CategorySecond;
import cn.swust.xshop.utils.PageHibernateCallback;


/**
 * 二级分类的Dao层的代码
 * 
 */
@Repository
public class CategorySecondDao extends BaseDao<CategorySecond> {
	// R ：分页查询
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		return list;
	}
}
