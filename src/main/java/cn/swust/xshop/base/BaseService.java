package cn.swust.xshop.base;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;



/**
 * 公共Service类
 *
 */
@Transactional
public class BaseService<T> {
	
	private BaseDao<T> dao ;
	
    @Resource
	public void setDao(BaseDao<T> dao) {		// 依靠子类重写该方法实现泛型属性的注入(父类引用指向子类对象)
		this.dao = dao;
	}
    
    public BaseDao<T> getDao() {
		return dao;
	}

	// C
  	public void save(T t) {
  		dao.save(t);
  	}
  	
    // R
 	public List<T> findAll() {
 		return dao.findAll();
 	}
 	
 	// R : id
  	public T findById(Integer t) {
  		return dao.findById(t);
  	}
  	
  	// R: number
 	public int findCount() {
 		return dao.findCount();
 	}
 	
 	// R : ids
 	public List<T> findByIds(Long[] ids) {
 		return dao.findByIds(ids);
 	}
  	
  	// U
  	public void update(T t) {
  		dao.update(t);
  	}

 	// D
 	public void delete(T t) {
 		dao.delete(t);
 	}
		
}
