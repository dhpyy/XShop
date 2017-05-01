package cn.swust.xshop.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 公共Action类
 *
 */
public class BaseAction<T>  extends ActionSupport implements ModelDriven<T> { // 抽象出action类继承ActionSupport、实现ModelDriven接口的共性
	
	protected T model;
	
	// 通过反射得到泛型的对象
	public BaseAction() {
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); 
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];       // 通过反射拿到子类传递来的泛型类
			model = clazz.newInstance();                       				 //  通过反射拿到泛型类的实例
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public T getModel() {
		return model;
	}
}
