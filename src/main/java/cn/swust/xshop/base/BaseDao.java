package cn.swust.xshop.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 公共Dao类
 *
 */
public class BaseDao<T> extends HibernateDaoSupport {		
	
	private Class<T> clazz;				      						  // 实体类属性  Class<T> clazz ： clazz是一个Class类
	
	@Autowired  
	public void setMySessionFactory(SessionFactory sessionFactory){  // 源码中HibernateDaoSupport类的sessionFactory属性没有被注入
		super.setSessionFactory(sessionFactory);             		 // 继承HibernateDaoSupport的子类需要手动注入sessionFactory属性
	}  
	
	// 通过反射得到子类所传递来的泛型类
	public BaseDao() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();  // 得到父类的Class类
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];   // 得到子类所传递来的泛型类
	}

	
	// C
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	// R
	public List<T> findAll() {
		String hql = "from " + clazz.getSimpleName();
		List<T> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	// R : id
	public T findById(Integer id) {
		if(id == null) {                   			 		  //  id为空，返回空对象
			return null;
		} else{
			return this.getHibernateTemplate().get(clazz, id);   // id不为空，执行查询操作
		}
	}
	
	// R: number
	public int findCount() {
		String hql = "select count(*) from " + clazz.getSimpleName();
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	// R : ids
	public List<T> findByIds(Long[] ids) {
		if(ids == null || ids.length == 0) {	 //  id为空，返回空集合对象
			return Collections.EMPTY_LIST;
		} else {
			String hql = "from " + clazz.getSimpleName() + " where id in (:ids)";
			List<T> list = this.getHibernateTemplate().findByNamedParam(hql, "ids", ids);
			return list;
		}
	}
	
	// U
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	
	// D
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	// D
	public void deleteById(Integer id) {
		Object o = findById(id);              //  先根据id找到删除对象
		if(o != null) {                       //  对象不为空，执行删除操作
			this.getHibernateTemplate().delete(o);     
		}
	}
}
